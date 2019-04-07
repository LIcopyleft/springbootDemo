package com.spring.springbootdemo.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * t_user
 * @author 
 */
@Data
public class TUser implements Serializable {


    private Integer userId;

    private String userName;

    private String password;

    private String phone;

    private static final long serialVersionUID = 2627635791550388553L;


}