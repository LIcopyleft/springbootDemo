package com.spring.springbootdemo.mapper;

import com.spring.springbootdemo.model.ShanXiData;
import com.spring.springbootdemo.model.ShanXiDataExample;
import java.util.List;

public interface ShanXiDataMapper {
    int insert(ShanXiData record);

    int insertSelective(ShanXiData record);

    List<ShanXiData> selectByExampleWithBLOBs(ShanXiDataExample example);

    List<ShanXiData> selectByExample(ShanXiDataExample example);
}