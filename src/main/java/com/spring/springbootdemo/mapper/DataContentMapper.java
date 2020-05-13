package com.spring.springbootdemo.mapper;

import com.spring.springbootdemo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Mapper
public interface DataContentMapper {

   // int insert(DataContentWithBLOBs record);

    int insertSelective(DataContentWithBLOBs record);

    List<DataContentWithBLOBs> selectByExampleWithBLOBs(DataContentExample example);

    List<DataContent> selectByExample(DataContentExample example);

    DataContentWithBLOBs selectByPrimaryKey(Integer urlId);


    @Select("select * from ${table} where url_id>=(select url_id from ${table} order by url_id limit ${from},1) limit ${to}")
 //   @Select("select * from ${table} where id >=(select id from ${table} order by id limit ${from},1) limit ${to}")
    List<DataContentWithBLOBs> selectAll(@Param("from") long from , @Param("to") long to,@Param("table") String table);


    @Select("select * from ${table} where url_id>=(select url_id from ${table} order by url_id limit ${from},1) limit ${to}")
        //   @Select("select * from ${table} where id >=(select id from ${table} order by id limit ${from},1) limit ${to}")
    List<GovData> selectAll2(@Param("from") long from , @Param("to") long to,@Param("table") String table);


    @Select("select * from ${table} where url_id>=(select url_id from ${table} where stageShow = #{type} order by url_id limit ${from},1) limit ${to}")
    List<DataContentWithBLOBs> selectByType(@Param("from") long from , @Param("to") long to,@Param("table") String table,@Param("type") String type);
    @Select("select * from ${table} where url_id = ${urlId}")
    List<DataContentWithBLOBs> selectByUrlId(@Param("table") String table , @Param("urlId") long urlId);

    @Select("select * from spider_2_ggzy_beijing_content where url_id>=(select url_id from spider_2_ggzy_beijing_content order by url_id limit #{from},1) limit #{to}")
    List<GovData> selectAllBJ(@Param("from") int from , @Param("to") int to);


    @Select({"select * from spider_2_ggzy_content where stageShow = #{stageShow}"})
    List <DataContentWithBLOBs> selectAllByStageShow(@Param("stageShow") String stageShow);

    int updateByPrimaryKeySelective(DataContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DataContentWithBLOBs record);

    int insertList(List<DataContentWithBLOBs> list);
    int insertList_BJ(List<GovData> list,@Param("tableName") String tableName);

	@Select("select url_id,projectname,artcle_url,category_second ,REGIONCODE,RECEIVETIME ,category_first ,content from spider_5_ggzy_shanxi_content where url_id>=(select url_id from spider_5_ggzy_shanxi_content order by url_id limit #{from},1) limit #{to}")
	//@Select("select url_id,category_second ,REGIONCODE,RECEIVETIME ,category_first ,content from spider_5_ggzy_shanxi_content where url_id in (select C.url_id from (select url_id from spider_5_ggzy_shanxi_content order by url_id limit #{from},#{to}) C)")
    List<ShanXiData> selectAllShanXi(@Param("from") int from , @Param("to") int to);

    @Select("select * from ${table} where ${filed} is null limit ${from} , ${to}")
    List<GovData> selectThisFiledIsNull(String table,@Param("filed")String filed,@Param("from") int from , @Param("to") int to);
    @Select("select * from ${table} where url_id = ${urlId}")
    DataContentWithBLOBs selectById(String table, Integer urlId);

    @Select("update ${table} SET ${field} = #{fieldVal}  where url_id = #{urlId}")
    @Transactional(propagation= Propagation.SUPPORTS)
    DataContentWithBLOBs updateFiledByUrlId(String table,String field, String fieldVal ,Integer urlId);

    @Select("SELECT  * from spider_2_ggzy_beijing_content_clean where classify_show = '政府采购'")
    List<GovData> selectTable();
}
