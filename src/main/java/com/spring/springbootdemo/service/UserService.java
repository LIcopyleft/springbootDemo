package com.spring.springbootdemo.service;

import com.github.pagehelper.Page;
import com.spring.springbootdemo.model.TUser;

import java.util.List;

/**
 * @author dong
 * @Created by 2019/4/6
 */
public interface UserService {

    int addUser(TUser user);

    List<TUser> findAllUser(int pageNum, int pageSize);

    List<TUser> findByName(String userName);

    Page<TUser> selectAllUser(int pageNum, int pageSize);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(TUser record);
}
