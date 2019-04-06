package com.spring.springbootdemo.controller;

import com.github.pagehelper.Page;
import com.spring.springbootdemo.pageinfo.PageInfo;
import com.spring.springbootdemo.model.TUser;
import com.spring.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dong
 * @Created by 2019/4/6
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
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
}
