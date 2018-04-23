package com.jeffrey.mapper;

import com.jeffrey.entity.Tasks;

public interface TasksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tasks record);

    int insertSelective(Tasks record);

    Tasks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tasks record);

    int updateByPrimaryKey(Tasks record);
}