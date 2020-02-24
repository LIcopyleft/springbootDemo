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


 //   private static final String StageShow = "招标/资审文件澄清";
    private static final String StageShow = "成交公示";

 //   private CountDownLatch latch;
/*
            "1.交易结果公示"
            "2.采购合同 Over"
            "3.招标/资审文件澄清 Over"
            "4.中标公告"
            "5.采购/资审公告"
            "6.更正事项"
            "7.招标/资审公告"
            "8.成交公示"
            "9.开标记录"
            "10.成交宗地"
            "11.交易公告"
            "12.信息披露"
            "13.出让公告"
            "14.挂牌披露"
            "15.出让公示"
            "16.交易结果"
            "17.结果公示"
            "18.交易目录"
            "19.成交公告"
            "20.出让结果"
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
                    EXECUTOR.execute(new ThreadTask(queue));
                }
            }
        } catch (Exception e) {
            logger.error("========= 采购合同 index" + i + "==============");
            return;
        }
    }

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
                    EXECUTOR.execute(new BisResultShowTask(queue));
                }
            }
        } catch (Exception e) {
            logger.error("========= 交易结果公示 index" + i + "==============");
            return;
        }
    }


    //===================






}
