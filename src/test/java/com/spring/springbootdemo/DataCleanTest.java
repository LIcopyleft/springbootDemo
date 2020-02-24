package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.BisResultShowTask;
import com.spring.springbootdemo.thread.ThreadTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tengchao.li
 * @description
 * @date 2020/2/21
 */
@SpringBootTest(value = "application.yml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DataCleanTest {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    private static final Logger logger = LoggerFactory.getLogger(DataCleanTest.class);

    @Autowired
    private DataContentMapper mapper;

    private CountDownLatch latch;
/*
            "交易结果公示"
            "采购合同 Over"
            "招标/资审文件澄清"
            "中标公告"
            "采购/资审公告"
            "更正事项"
            "招标/资审公告"
            "成交公示"
            "开标记录"
            "成交宗地"
            "交易公告"
            "信息披露"
            "出让公告"
            "挂牌披露"
            "出让公示"
            "交易结果"
            "结果公示"
            "交易目录"
            "成交公告"
            "出让结果"
    */


    //采购合同
    @Test
    public void test() throws InterruptedException {
        long i = 335708;
        try {
            while (true) {
                List<DataContentWithBLOBs> dataContent = mapper.selectAll(i, 1000);
                if(dataContent == null || dataContent.size() < 1){
                    break;
                }
                i += 1000;
                LinkedBlockingQueue<DataContentWithBLOBs> queue = new LinkedBlockingQueue();
                for (DataContentWithBLOBs data : dataContent) {
                    if ("采购合同".equals(data.getStageshow())) {
                        queue.add(data);
                    }
                }
                if (queue.size() > 0) {
                    EXECUTOR.execute(new ThreadTask(queue, latch));
                }
            }
        } catch (Exception e) {
            logger.error("========= 采购合同 index" + i + "==============");
            return;
        }
    }
    private static final String StageShow = "招标/资审文件澄清";
//交易结果公示
    @Test
    public void test2() throws InterruptedException {
        long i = 0;
        try {
            while (true) {
                List<DataContentWithBLOBs> dataContent = mapper.selectAll(i, 1000);
                if(dataContent == null || dataContent.size() < 1){
                    logger.warn("is end========i="+i);
                    break;
                }
                i += 1000;
                LinkedBlockingQueue<DataContentWithBLOBs> queue = new LinkedBlockingQueue();
                for (DataContentWithBLOBs data : dataContent) {
                    if (StageShow.equals(data.getStageshow())) {
                        queue.add(data);
                    }
                }
                if (queue.size() > 0) {
                    EXECUTOR.execute(new BisResultShowTask(queue, latch));
                }
            }
        } catch (Exception e) {
            logger.error("========= 交易结果公示 index" + i + "==============");
            return;
        }
    }


    //===================






}
