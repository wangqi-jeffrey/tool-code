package com.yihaomen.mybatis.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihaomen.mybatis.model.Region;

public interface RegionMapper {
    int insert(Region record);

    int insertSelective(Region record);

	Region selectForKey(String string);

	List<Region> selectList();

	void update(@Param("code")String code, @Param("linkTextd")String linkTextd);
}