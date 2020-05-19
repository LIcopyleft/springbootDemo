package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.thread.GOVDataCleanTask;
import com.spring.springbootdemo.utils.DataConvert;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.InvocationTargetException;
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
public class DebugTest {

    private static Integer MAX_THREAD_NUM = 1;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_THREAD_NUM);
    private static final String STAGE = "中标公告";
    private static final String INSERT_TABLE_NAME = "spider_2_ggzy_content_clean_result_zbgg_2";
    private static final String CLEAN_TABLE_NAME = "clean_jiangsu_zfcg";
    private static final int INSERT_MAX = 1000;
    private static final int QUERY_SIZE = 2000;
    private static final int TABLE_SIZE = 2;
    private static final int URL_ID = 122776;

    @Test
    public void doClean_cggg() throws InterruptedException {
        long start = System.currentTimeMillis();
        int totalSize = 329318;//mapper.getTotal();

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

        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");

        DataContentWithBLOBs data = mapper.selectById(config.getCleanTableName(),URL_ID);
        GovData govData = DataConvert.toGovData(data);
        try {
            GovData cleanZbgg = GOVDataCleanTask.clean_zbgg(govData);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");
    }

}
