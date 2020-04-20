package com.spring.springbootdemo;

import com.spring.springbootdemo.thread.GetMapKeysTask;
import com.spring.springbootdemo.thread.OneByOneFTask;
import com.spring.springbootdemo.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.LinkedHashSet;
import java.util.Set;
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
public class GetMapKeysFile {
    private static final Integer MAX_THREAD_NUM = 10;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_THREAD_NUM);
    private static final Logger logger = LoggerFactory.getLogger(GetMapKeysFile.class);
    private static final int QUERY_SIZE = 2000;


    // private static final String STAGE_SHOW = "招标/资审文件澄清";
    //  private static final String STAGE_SHOW = "采购/资审公告";
    private static final String STAGE_SHOW = "中标公告";


    @Test
    public void writeKeyFlies() throws InterruptedException {
        long start = System.currentTimeMillis();
        int beginIndex = 0;
        int totalSize = 329318;//mapper.getTotal();
        Set set = new LinkedHashSet();
        int times = totalSize / QUERY_SIZE;
        if (totalSize % QUERY_SIZE != 0) {
            times = times + 1;
        }

        // mapper.selectAll();

        CountDownLatch latch = new CountDownLatch(Integer.valueOf(String.valueOf(times)));
        //    List<Runnable> tasks = new ArrayList<Runnable>();//添加任务
        for (int i = 0; i < times; i++) {
            Runnable task = new GetMapKeysTask(beginIndex, QUERY_SIZE, STAGE_SHOW, latch,set);

            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        FileUtils.writeAppendFile("金额.txt",set);
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时" + (end - start) / 1000 + "秒");
    }

}
