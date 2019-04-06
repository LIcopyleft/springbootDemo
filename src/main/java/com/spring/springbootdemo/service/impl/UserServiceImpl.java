package com.spring.springbootdemo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spring.springbootdemo.mapper.TUserDao;
import com.spring.springbootdemo.model.TUser;
import com.spring.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong
 * @Created by 2019/4/6
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserDao tUserDao;
    @Override
    public int addUser(TUser user) {
        return tUserDao.insertSelective(user);
    }

    @Override
    public List<TUser> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return tUserDao.selectAllUser();
    }

    @Override
    public List<TUser> findByName(String userName) {
        List<TUser> list = new ArrayList<>();
        if (userName==null){
            list = tUserDao.selectAllUser();
            return list;
        }
        list.add(tUserDao.findByName(userName));
        return list;
    }

    @Override
    public Page<TUser> selectAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return tUserDao.selectAllUser();
    }
}
