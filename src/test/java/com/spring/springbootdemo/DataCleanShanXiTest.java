package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.CleanBeiJingTask;
import com.spring.springbootdemo.thread.CleanDealNoticeTask;
import com.spring.springbootdemo.thread.CleanShanXiTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
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
public class DataCleanShanXiTest {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(15);
    private static final Logger logger = LoggerFactory.getLogger(DataCleanShanXiTest.class);
    private static final int QUERY_SIZE = 2000;

    //   private static final String STAGE_SHOW = "招标/资审文件澄清";
    private static final String STAGE_SHOW = "政府采购>成交结果公告";

//采购合同
//交易结果公示
    @Test
    public void doClean() throws InterruptedException {
        long start = System.currentTimeMillis();
        int beginIndex = 0;
        int totalSize = 171229;//mapper.getTotal();

        int times= totalSize / QUERY_SIZE;
        if(totalSize % QUERY_SIZE !=0) {
            times=times+1;
        }
        CountDownLatch latch = new CountDownLatch(Integer.valueOf(String.valueOf(times)));
    //    List<Runnable> tasks = new ArrayList<Runnable>();//添加任务
        for(int i = 0; i <times ; i++){
            Runnable task = new CleanShanXiTask(beginIndex,QUERY_SIZE,STAGE_SHOW,latch);

            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");
    }


}
