package com.neo.utils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.internet.MimeMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.neo.config.SystemConfig;

/**
 * 发送短信和邮件的工具类
 * @author Jeffrey
 * @since 2017年3月7日 上午10:53:52
 */
public class MessageUtil {

	private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

	/**
	 * Description: 发送携带附件和图片的邮件
	 * @param receivers 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param attachments 附件
	 * @param inlineImgs 邮件中嵌套的图片
	 * @return boolean
	 * @author Jeffrey
	 * @since 2017年3月7日 下午2:34:03
	 */
	public static boolean sendMail(String[] receivers, String subject, String content, List<File> attachments, Map<String, File> inlineImgs) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		String host = SystemConfig.getProperty("smtp.host");
		String from = SystemConfig.getProperty("smtp.from");
		String account = SystemConfig.getProperty("smtp.account");
		String password = SystemConfig.getProperty("smtp.password");
		try {
			// 设定mail server
			senderImpl.setHost(host);
			// 建立邮件消息
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = null;
			messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
			// 设置发件人邮箱
			messageHelper.setFrom(from);
			// 设置收件人邮箱
			messageHelper.setTo(receivers);
			// 设置抄送人
			//messageHelper.setCc(cc);
			// 设置暗送人
			//messageHelper.setBcc(bcc);
			// 邮件主题
			messageHelper.setSubject(subject);
			// true 表示启动HTML格式的邮件
			messageHelper.setText(content, true);
			
			// 设置附件
			if (attachments != null && attachments.size() > 0) {
				for (File file : attachments) {
					if (!checkFileType(file)) continue;
	                logger.info("===================添加附件对象:" + file.getName());
	                messageHelper.addAttachment(file.getName(), file);  
				}
			}
			// 设置嵌套图片
			if (inlineImgs != null && inlineImgs.size() > 0) {
				for (String key : inlineImgs.keySet()) {
					File file = inlineImgs.get(key);
					logger.info("===================添加嵌套图片key:" + key + "---fileName:" + file.getName());
					messageHelper.addInline(key, file);
				}
			}
			
			senderImpl.setUsername(account);
			senderImpl.setPassword(password);
			Properties prop = new Properties();
			prop.put("mail.smtp.port", SystemConfig.getProperty("mail.smtp.port"));
			prop.put("mail.smtp.auth", SystemConfig.getProperty("mail.smtp.auth")); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", SystemConfig.getProperty("mail.smtp.timeout"));
			// 如果是QQ邮箱需要这样做 start 密码为加密后的
			// prop.put("mail.smtp.starttls.enable", "true");
			// prop.put("mail.smtp.socketFactory.port", "465");
			// prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			// prop.put("mail.smtp.socketFactory.fallback", "false");
			// 如果是QQ邮箱需要这样做 end
			senderImpl.setJavaMailProperties(prop);
			// 发送邮件
			senderImpl.send(mailMessage);
			logger.info("=============发送邮件成功=============");
		} catch (Exception e) {
			logger.info("=============发送邮件报错，异常信息为：" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static boolean checkFileType(File file) {
		if ("application/octet-stream".equals(new MimetypesFileTypeMap().getContentType(file)) && file.getName().endsWith(".exe")) { 
			logger.info("=============构造邮件附件=============文件：{} 是可执行文件。出于安全性考虑，不允许添加此文件。", file.getName());
			return false;
	    }
		return true;
	}
	
	/**
	 * 发送短信（根据不同的短信提供商来修改此方法）
	 * @author Jeffrey
	 * @since 2017年3月7日 下午4:22:53
	 * @param content
	 * @param mobileNumber 需要根据短信提供商接口是否支持群发
	 * @return
	 */
	public static String sendSms(String content, String mobileNumber) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(SystemConfig.getProperty("sms.url"));
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"); // 在头文件中设置转码
		NameValuePair[] data = {
				new NameValuePair("sname", SystemConfig.getProperty("sms.sname")),
				new NameValuePair("spwd", SystemConfig.getProperty("sms.spwd")),
				new NameValuePair("sdst", mobileNumber),
				new NameValuePair("smsg", content),
				new NameValuePair("scorpid", SystemConfig.getProperty("sms.scorpid")),
				new NameValuePair("sprdid", SystemConfig.getProperty("sms.sprdid")), 
			};
		post.setRequestBody(data);
		String result = null;
		try {
			client.executeMethod(post);
			result = new String(post.getResponseBodyAsString().getBytes("UTF-8"));
		} catch (Exception e) {
			logger.info("=============发送短信报错，异常信息为：" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("=============完成发送短信=============");
		logger.info("result : " + result);
		return result;
	}
	
}
