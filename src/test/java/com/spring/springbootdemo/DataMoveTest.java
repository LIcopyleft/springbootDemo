package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.thread.DataMoveTask;
import com.spring.springbootdemo.thread.GOVDataCleanTask;
import com.spring.springbootdemo.thread.TableMergeTask;
import com.spring.springbootdemo.utils.DataConvert;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
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
public class DataMoveTest {

    private static Integer MAX_THREAD_NUM = 5;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_THREAD_NUM);
    private static final String STAGE = "采购合同";
    private static final String INSERT_TABLE_NAME = "clean_china_zfcg";
    private static final String CLEAN_TABLE_NAME = "spider_2_ggzy_content_clean_result_cgzsgg";
    private static final int INSERT_MAX = 1000;
    private static final int QUERY_SIZE = 2000;
    private static final int TABLE_SIZE = 2;
    private static final int URL_ID = 16245;

    /**
     * 迁移要清洗的数据到temp 中间表
     * @throws InterruptedException
     */

    @Test
    public void dataMoveTempTable() throws InterruptedException {
        long start = System.currentTimeMillis();
        int totalSize = 329318;//mapper.getTotal();
        int beginIndex = 0;
        int times= totalSize / QUERY_SIZE;
        if(totalSize % QUERY_SIZE !=0) {
            times=times+1;
        }
        CountDownLatch latch = new CountDownLatch(Integer.valueOf(String.valueOf(times)));

        ConfigParam config = new ConfigParam();
        config.setCleanTableName(CLEAN_TABLE_NAME);
        config.setInsertTableName(INSERT_TABLE_NAME);
        config.setInsertMax(INSERT_MAX);
        config.setLatch(latch);
        config.setStage(STAGE);
        config.setTableNum(TABLE_SIZE);// 0,清洗不含表格 1,表格数量为1 ,2 全部

        for(int i = 0; i <times ; i++){
            Runnable task = new DataMoveTask(beginIndex,config);
            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");

    }

    /**
     * 多个表数据合并到一张表
     * @throws InterruptedException
     */
    @Test
    public void dataMoveOne() throws InterruptedException {
        long start = System.currentTimeMillis();

    //    int totalSize = 73569;//mapper.getTotal();
        int totalSize = 73569;//mapper.getTotal();


        int beginIndex = 0;
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
        config.setTableNum(TABLE_SIZE);// 0,清洗不含表格 1,表格数量为1 ,2 全部

        for(int i = 0; i <times ; i++){
            Runnable task = new TableMergeTask(beginIndex,config);
            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");

    }



}
