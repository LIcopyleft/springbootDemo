package com.spring.springbootdemo;

import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.thread.ShangHaiTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tengchao.li
 * @description
 * @date 2020/2/21
 */
@SpringBootTest(value = "application.yml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@Property(value = "application.yml")
//@PropertySource({"classpath:application.yml"})
public class ShangHaiTest {

    private static Integer MAX_THREAD_NUM = 10;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_THREAD_NUM);
   private static final String STAGE = "政府采购>采购公告";
 //   private static final String STAGE = "政府采购>合同公告";

    private static final String INSERT_TABLE_NAME = "clean_shanghai_zfcg_cgzsgg_copy1";
 //   private static final String CLEAN_TABLE_NAME = "spider_2_ggzy_content_clean_temp";
    private static final String CLEAN_TABLE_NAME = "spider_7_ggzyshanghai_content";
    private static final int INSERT_MAX = 1000;
    private static final int QUERY_SIZE = 1000;
    private static final int TABLE_SIZE = 1;
    //是否开启单独清洗 表格数为特定值得记录
    private static final boolean FLAG = false;


    @Test
    public void clean() throws InterruptedException {
        long start = System.currentTimeMillis();
        int beginIndex = 0;
        int totalSize = 41315;//mapper.getTotal();
        int times= totalSize / QUERY_SIZE;
        if(totalSize % QUERY_SIZE !=0) {
            times=times+1;
        }
        CountDownLatch latch = new CountDownLatch(Integer.valueOf(String.valueOf(times)));


        ConfigParam config = new ConfigParam();
        config.setCleanTableName(CLEAN_TABLE_NAME);
        config.setInsertTableName(INSERT_TABLE_NAME);
        config.setInsertMax(INSERT_MAX);
        config.setQuerySize(QUERY_SIZE);
        config.setLatch(latch);
        config.setStage(STAGE);
        config.setOpen(FLAG);
        config.setTableNum(TABLE_SIZE);// 0,清洗不含表格 1,表格数量为1 ,2 全部

        for(int i = 0; i <times ; i++){
            Runnable task = new ShangHaiTask(beginIndex,config);
            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");
    }



}
