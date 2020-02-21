package com.spring.springbootdemo.mapper;

import com.github.pagehelper.Page;
import com.spring.springbootdemo.model.TUser;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TUserDao {



    @Select("select t.user_id,t.user_name,t.password,t.phone from t_user t")
    Page<TUser> selectAllUser();

    @Select("select t.user_id,t.user_name,t.password,t.phone from t_user t where t.user_name = #{userName}")
    TUser findByName (@Param("userName") String userName);
}
