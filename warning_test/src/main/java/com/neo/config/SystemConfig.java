package com.neo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 加载一些配置文件
 * 使用定时任务定时读取数据库中配置信息
 * @author Jeffrey
 * @since 2017年3月7日 下午1:43:23
 */
@Component // 如果不是使用spring容器，请自行配置容器加载时将该类使用默认构造器实例化
public class SystemConfig {
	private static Logger log = Logger.getLogger(SystemConfig.class);
	private static Properties props = new Properties();

	public SystemConfig() {
		init();
	}

	private void init() {
		InputStream in = Object.class.getResourceAsStream("/sms&mail.properties");
		try {
			props.load(in);
			log.error("===================配置文件加载成功===================");
		} catch (IOException e) {
			e.printStackTrace();
			log.error("===================加载配置文件出错===================");
		}
	}

	/**
	 * 获取属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return props == null ? null : props.getProperty(key);
	}

	/**
	 * 获取属性
	 * 
	 * @param key
	 *            属性key
	 * @param defaultValue
	 *            属性value
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		return props == null ? null : props.getProperty(key, defaultValue);

	}

	/**
	 * 获取properyies属性
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return props;
	}

}
