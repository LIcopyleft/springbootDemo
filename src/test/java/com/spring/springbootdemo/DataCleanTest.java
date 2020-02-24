package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.BisResultShowTask;
import com.spring.springbootdemo.thread.CleanTask;
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
    private static final long QUERY_SIZE = 1000;

    @Autowired
    private DataContentMapper mapper;


    //   private static final String STAGE_SHOW = "招标/资审文件澄清";
    private static final String STAGE_SHOW = "开标记录";

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
//交易结果公示
    @Test
    public void doClean(){
        long i = 0;
        try {
            while (true) {
                List<DataContentWithBLOBs> dataContent = mapper.selectAll(i, QUERY_SIZE);
                if(dataContent == null || dataContent.size() < 1){
                    logger.warn("end========i="+i);
                    break;
                }
                i += QUERY_SIZE;
                LinkedBlockingQueue<DataContentWithBLOBs> queue = new LinkedBlockingQueue();
                for (DataContentWithBLOBs data : dataContent) {
                    if (STAGE_SHOW.equals(data.getStageshow())) {
                        queue.add(data);
                    }
                }
                if (queue.size() > 0) {
                    EXECUTOR.execute(new CleanTask(queue));
                }
            }
        } catch (Exception e) {
            logger.error("========= "+STAGE_SHOW +" index" + i + "==============");
            return;
        }
    }


}
