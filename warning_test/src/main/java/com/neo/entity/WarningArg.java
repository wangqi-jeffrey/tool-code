package com.neo.entity;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 用来封装报警需要用到的参数
 * @author Jeffrey
 * @since 2017年3月8日 下午2:03:32
 */
public class WarningArg {
	// 报警项名称（唯一）
	private String itemName;
	// 报警组名称（唯一）
	private List<String> groupNames;
	// 模版中需要替换的占位符（key:被替换的占位符名称  value:要替换的字符） 没有可填null
	// 思考：邮件使用html格式发送，被替换后如出现'>' '<' 等符号，可能需要转义，根据实际情况而定
	private Map<String, String> replaceMap;
	// 附件
	private List<File> attachments;
	// 邮件中嵌套的图片 key为所对应嵌套图片的名称 如'<img src="cid:aaa"/>' key就为aaa
	private Map<String, File> inlineImgs;
	
	private WarningArg(){}
	
	private static WarningArg instance = new WarningArg();
	
	public static WarningArg getInstance() {
		return instance;
	}

	public String getItemName() {
		return itemName;
	}
	public WarningArg setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public List<String> getGroupNames() {
		return groupNames;
	}
	public WarningArg setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
		return this;
	}
	public Map<String, String> getReplaceMap() {
		return replaceMap;
	}
	public WarningArg setReplaceMap(Map<String, String> replaceMap) {
		this.replaceMap = replaceMap;
		return this;
	}

	public Map<String, File> getInlineImgs() {
		return inlineImgs;
	}

	public WarningArg setInlineImgs(Map<String, File> inlineImgs) {
		this.inlineImgs = inlineImgs;
		return this;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public WarningArg setAttachments(List<File> attachments) {
		this.attachments = attachments;
		return this;
	}

	@Override
	public String toString() {
		return "WarningArg [itemName=" + itemName + ", groupNames="
				+ groupNames + ", replaceMap=" + replaceMap + ", attachments="
				+ attachments + ", inlineImgs=" + inlineImgs + "]";
	}
}
