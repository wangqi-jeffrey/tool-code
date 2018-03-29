package com.neo.dao;

import org.apache.ibatis.annotations.Param;

import com.neo.entity.WarningItem;

public interface WarningItemDao {

	/**
	 * 根据报警项名称获取报警项
	 * 
	 * @author Jeffrey
	 * @since 2017年3月8日 上午10:48:15
	 * @param name
	 * @return
	 */
	WarningItem getWarningItemByName(@Param("itemName") String itemName);

}