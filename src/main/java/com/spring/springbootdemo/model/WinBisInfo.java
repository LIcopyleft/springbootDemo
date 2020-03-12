package com.spring.springbootdemo.model;

import lombok.Data;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/10
 */
@Data
public class WinBisInfo {
    private String winBidBisName;
    private String winBidBisAddr;
    private String winBidAmount;
    private String productName;
    private String productNum; //商品数量
    private String rule; //要求
    private String creditNo; //信用编号


}
