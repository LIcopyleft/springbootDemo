package com.spring.springbootdemo.mapper;

import com.spring.springbootdemo.model.DataContent;
import com.spring.springbootdemo.model.DataContentExample;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DataContentMapper {

    int insert(DataContentWithBLOBs record);

    int insertSelective(DataContentWithBLOBs record);

    List<DataContentWithBLOBs> selectByExampleWithBLOBs(DataContentExample example);

    List<DataContent> selectByExample(DataContentExample example);

    DataContentWithBLOBs selectByPrimaryKey(Integer urlId);


    @Select("select * from ${table} where url_id>=(select url_id from ${table} order by url_id limit ${from},1) limit ${to}")
    List selectAll(@Param("from") long from , @Param("to") long to,@Param("table") String table);

    @Select("select * from spider_2_ggzy_beijing_content where url_id>=(select url_id from spider_2_ggzy_beijing_content order by url_id limit #{from},1) limit #{to}")
    List<GovData> selectAllBJ(@Param("from") int from , @Param("to") int to);


    @Select({"select * from spider_2_ggzy_content where stageShow = #{stageShow}"})
    List <DataContentWithBLOBs> selectAllByStageShow(@Param("stageShow") String stageShow);

    int updateByPrimaryKeySelective(DataContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DataContentWithBLOBs record);

    int insertList(List<DataContentWithBLOBs> list);
    int insertList_BJ(List<GovData> list);
}
