package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.ThreadTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Queue;
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

    @Autowired
    private DataContentMapper mapper;
/*
            "交易结果公示"
            "采购合同"
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
    public void test() {

        List<DataContentWithBLOBs> dataContent = mapper.selectAll(0, 100);
        LinkedBlockingQueue<DataContentWithBLOBs> queue = new LinkedBlockingQueue();
        for (DataContentWithBLOBs data : dataContent) {

            if ("采购合同".equals(data.getStageshow())) {

                queue.add(data);

            }

        }
        EXECUTOR.execute(new ThreadTask(queue));

    }

}
