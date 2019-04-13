package com.spring.springbootdemo.controller;

import com.github.pagehelper.Page;
import com.spring.springbootdemo.pageinfo.PageInfo;
import com.spring.springbootdemo.model.TUser;
import com.spring.springbootdemo.redis.RedisService;
import com.spring.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dong
 * @Created by 2019/4/6
 */
@Api(value = "/user")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "addUser",notes = "add user",tags = {"add method"})
    @ApiImplicitParam(paramType = "add",name = "user",value = "用户属性",required = true,dataType = "int")
    public int addUser(TUser user){
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/findByName")
    public List<TUser> findByName(String userName){
        return userService.findByName(userName);
    }

    @ResponseBody
    @RequestMapping(value = "/selectAllUser/{pageNum}/{pageSize}")
    public PageInfo<TUser> selectAllUser(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        Page<TUser> tUsers = userService.selectAllUser(pageNum,pageSize);
        PageInfo<TUser> pageInfo = new PageInfo<>(tUsers);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public Object setRedisService(@Param("name") String name, @Param("values") String values){
        redisService.set(name,values);
        return redisService.get(name);
    }

    /**
     *
     * @param key   key
     * @param list  list
     * @param time 缓存失效
     * @return  测试返回
     */
    @ResponseBody
    @RequestMapping(value = "/redisSet",method = RequestMethod.POST)
    public Object redisSet(@Param("key") String key,@Param("list") List<TUser> list,@Param("time") Long time){
        redisService.set(key,list,time);
        return redisService.hmGet(key,list);
    }
}
