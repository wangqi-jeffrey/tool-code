package com.neo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neo.entity.WarningUser;

public interface WarningUserDao {

	/**
	 * 根据报警组名称 和 报警项id 获取报警用户
	 * @author Jeffrey
	 * @since 2017年3月8日 上午10:47:42
	 * @param itemId 报警项id（调用该sql之前先获取报警项，判断是否有效，并且有需要报警的项）
	 * @param groupName 可为空，报给所有关联到该报警项的报警组
	 * @return
	 */
	List<WarningUser> getWarningUserByName(@Param("itemId") Integer itemId,@Param("groupNames") List<String> groupNames);
}