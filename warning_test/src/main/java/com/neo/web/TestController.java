package com.neo.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.dao.WarningItemDao;
import com.neo.dao.WarningUserDao;
import com.neo.entity.WarningArg;
import com.neo.entity.WarningItem;
import com.neo.utils.MessageUtil;
import com.neo.utils.WarningUtil;

@RestController
public class TestController {
	
	@Autowired
	private WarningUserDao warningUserDao;
	@Autowired
	private WarningItemDao warningItemDao;
	
	@RequestMapping("/warning")
	public Object warning() {
		List<String> groupNames = new ArrayList<String>();
		//groupNames.add("weijie");
		groupNames.add("sys_exception_group");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("%date%", "2017年3月8日");
		map.put("%company%", "北京柚信科技有限公司");
		
		List<File> files = new ArrayList<File>();
		files.add(new File("d://我的附件1.txt"));
		files.add(new File("d://我的附件2.txt"));
		files.add(new File("d://非可执行文件.exe"));
		files.add(new File("d://百度云盘下载加速器.exe"));
		
		Map<String, File> inline = new HashMap<String, File>();
		inline.put("aaa", new File("d://aaa.jpg"));
		inline.put("bbb", new File("d://ccc.jpg"));
		
		WarningArg args = WarningArg.getInstance().setItemName("系统异常报警")
								.setGroupNames(groupNames)
								.setReplaceMap(map)
								.setAttachments(files)
								.setInlineImgs(inline);
		WarningUtil.warning(args);
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/warningItem")
	public Object getWarningItem(String itemName) {
		WarningItem item = warningItemDao.getWarningItemByName(itemName);
		
		return item;
	}
	
	@RequestMapping("/warningUsers")
	public Object getWarningUsers(Integer itemId) {
		List<String> list = new ArrayList<String>();
		list.add("sys_exception_group");
		return warningUserDao.getWarningUserByName(itemId, list);
	}
	
	@RequestMapping("/sendSms")
	public Object getHost() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("15811400952正常", MessageUtil.sendSms("【天尧信息】你好", "15811400952"));
		map.put("15753183209停机", MessageUtil.sendSms("【天尧信息】你好", "15753183209"));
		map.put("13500000000", MessageUtil.sendSms("【天尧信息】你好", "13500000000"));
		map.put("13661064250", MessageUtil.sendSms("【天尧信息】你好", "13661064250"));
		return map;
	}
	
	@RequestMapping("/sendMail")
	public String sendMail() {
		MessageUtil.sendMail(new String[]{"wang_qi7880@163.com"}, "测试邮件", "你们的Java培训真的是最牛的吗？<img src='https://www.baidu.com/img/bd_logo1.png'>", null, null);
		return "ok";
	}
}