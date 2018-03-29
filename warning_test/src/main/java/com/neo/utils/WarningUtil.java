package com.neo.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neo.entity.WarningArg;
import com.neo.entity.WarningItem;
import com.neo.entity.WarningUser;
import com.neo.service.WarningItemService;
import com.neo.service.WarningUserService;

/**
 * 预警工具
 * @author Jeffrey
 * @since 2017年3月8日 下午3:42:51
 */
@Component
public class WarningUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WarningUtil.class);
	
	/**
	 * 注意：该处涉及到数据库请求的，都要调用service层，方便控制事务（在读写分离中获取数据源）
	 * ，此处没涉及到事务
	 */
	@Autowired
	private WarningUserService warningUserService;
	@Autowired
	private WarningItemService warningItemService;
	
	private static WarningUserService staticWarningUserService;
	private static WarningItemService staticWarningItemService;
	
	@SuppressWarnings("static-access")
	@PostConstruct
    public void init() {
		this.staticWarningUserService = this.warningUserService;
		this.staticWarningItemService = this.warningItemService;
		logger.info("------------------------------------WarningUtil init()被执行了------------------------------------");
    }
	
	private static ExecutorService exec;
	//发送短信异步调用线程池大小 配置为0,默认获取服务器cpu个数
	private static Integer smsAsynSize = Runtime.getRuntime().availableProcessors();
	
	private synchronized static ExecutorService getInstanceExecutors() { 
		if (exec == null) {// 创建一个固定大小的线程池
			logger.info("-------- 创建一个固定大小的线程池------------smsAsynSize:" + smsAsynSize);
			exec = Executors.newFixedThreadPool(smsAsynSize);
		}
		return exec;
	}
	
	/**
	 * 短信&邮件预警
	 * @author Jeffrey
	 * @since 2017年3月13日 下午4:46:33
	 * @param args
	 */
	public static void warning(final WarningArg args) {
		// 开启多线程调用
		getInstanceExecutors().execute(new Runnable() {
            public void run(){
            	startSend(args);
            };
    	});
	}
	
	private static void startSend(WarningArg args) {
		logger.info("--------------调用预警方法参数args：" + args.toString());
		if (args == null) return;
		
		// 查询对应的报警项
		WarningItem item = staticWarningItemService.getWarningItemByName(args.getItemName());
		logger.info("--------------预警项WarningItem为：" + item.toString());
		if (item == null || (!item.getSendSms() && !item.getSendEmail())) return;
		
		// 查询对应报警项对应的报警用户
		List<WarningUser> users = staticWarningUserService.getWarningUserByName(item.getId(), args.getGroupNames());
		if (users == null || users.size() == 0) return;
		logger.info("--------------预警用户WarningUser为：" + listToString(users));
		
		// 发送短信
		if (item.getSendSms()) {
			String content = replaceHolder(args.getReplaceMap(), item.getSmsTpl());
			long startTime = System.currentTimeMillis();
			logger.info("--------------发送短信开始时间：" + startTime);
			for (WarningUser user : users) {
				if (isNullOrEmpty(user.getMobile())) continue;
				logger.info("用户：" + user.getMobile() + "--------------发送短信线程执行开始------------");
				getInstanceExecutors().execute(createTask(content, user.getMobile()));
			}
			long endTime = System.currentTimeMillis();
			logger.info("--------------发送短信结束时间：" + endTime + "----总用时:" + (endTime - startTime));
		}
		
		// 发送邮件
		if (item.getSendEmail()) {
			long startTime = System.currentTimeMillis();
			logger.info("--------------发送邮件开始时间：" + startTime);
			// 封装收件人
			String[] receivers = getReceivers(users);
			if (receivers.length == 0) return;
			
			/** FIXME: 如果邮件中需要出现用户的名字，生成不同的内容单独发给每个收件人即可*/
			MessageUtil.sendMail(receivers, item.getEmailTitle(), replaceHolder(args.getReplaceMap(), 
					item.getEmailTpl()), args.getAttachments(), args.getInlineImgs());
			long endTime = System.currentTimeMillis();
			logger.info("--------------发送邮件结束时间：" + endTime + "----总用时:" + (endTime - startTime));
		}
		logger.info("--------------调用预警方法结束");
	}
	
	private static String listToString(List<WarningUser> users) {
		StringBuffer sb = new StringBuffer();
		for (WarningUser user : users) {
			sb.append(user.toString());
			sb.append(">><<");
		}
		return sb.toString();
	}
	
	private static Runnable createTask(final String content, final String userPhone) {
    	return new Runnable() {
            public void run(){
            	try{
            		MessageUtil.sendSms(content, userPhone);
            		logger.info("用户：" + userPhone +"--------------线程执行结束------------");
                } catch (Exception e){
                	e.printStackTrace();
                	logger.info("多线程发送短信出现异常：" + e.getMessage());
                }
            };
    	};
    }
	
	private static String[] getReceivers(List<WarningUser> users) {
		String[] arr = new String[users.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = users.get(i).getEmail();
		}
		return arr;
	}
	
	private static String replaceHolder(Map<String, String> replaceMap, String srcContent) {
		if (replaceMap == null) return srcContent;
		for (Map.Entry<String, String> entry : replaceMap.entrySet()) {  
			srcContent = srcContent.replaceAll(entry.getKey(), entry.getValue());
		}
		return srcContent;
	}
	
	private static boolean isNullOrEmpty(String src) {
        return null == src || "".equals(src.trim());
    }
}