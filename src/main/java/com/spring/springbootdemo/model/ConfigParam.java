package com.spring.springbootdemo.model;

import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * @author tengchao.li
 * @description 必要配置参数
 * @date 2020/4/27
 */
@Data
public class ConfigParam {
    /**
     * 清洗类型, eg:采购公告  中标公告 ..
     */
    private String stage;
   /* private int beginIndex;
    private int querySize;*/
    private CountDownLatch latch;
    private String insertTableName;
    private String cleanTableName;
    private int insertMax = 1000;
    private int querySize = 1000;
    private int tableSize = 0;



}
