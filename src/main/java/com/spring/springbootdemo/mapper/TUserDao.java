package com.spring.springbootdemo.mapper;

import com.github.pagehelper.Page;
import com.spring.springbootdemo.model.TUser;

import java.util.List;

import generator.TUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TUserDao {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    @Select("select t.user_id,t.user_name,t.password,t.phone from t_user t")
    Page<TUser> selectAllUser();

    @Select("select t.user_id,t.user_name,t.password,t.phone from t_user t where t.user_name = #{userName}")
    TUser findByName (@Param("userName") String userName);
}