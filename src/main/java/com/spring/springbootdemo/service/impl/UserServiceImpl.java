package com.spring.springbootdemo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spring.springbootdemo.mapper.TUserDao;
import com.spring.springbootdemo.model.TUser;
import com.spring.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    public int addUser(TUser user) {
        return tUserDao.insertSelective(user);
    }

    @Override
    @Transactional
    @Cacheable(value = "redisCache",key = "'redis_user_'+#result.id")
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "redisCache",key = "'redis_user_'+#id",beforeInvocation = false)
    public int deleteByPrimaryKey(Integer id) {
        return tUserDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "redisCache",condition = "#result != 'null'",key = "'redis_user_'+#id")
    public int updateByPrimaryKey(TUser record) {
        if (record==null){
            return 0;
        }
        return tUserDao.updateByPrimaryKey(record);
    }
}
