package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author tengchao.li
 * @description
 * @date 2020/2/21
 */
@SpringBootTest(value = "application.yml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DataCleanTest {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(15);
    private static final Logger logger = LoggerFactory.getLogger(DataCleanTest.class);
    private static final long QUERY_SIZE = 1000;

    @Autowired
    private DataContentMapper mapper;


    //   private static final String STAGE_SHOW = "招标/资审文件澄清";
    private static final String STAGE_SHOW = "采购合同";

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
            "9.开标记录 Over"
            "10.成交宗地"
            "11.交易公告 ****"
            "12.信息披露 **"
            "13.出让公告 Over"
            "14.挂牌披露"
            "15.出让公示**"
            "16.交易结果"
            "17.结果公示"
            "18.交易目录 只有两个 OVer"
            "19.成交公告   Over"
            "20.出让结果 Over"
    */

//采购合同
//交易结果公示
    @Test
    public void doClean() throws InterruptedException {
        long start = System.currentTimeMillis();
        long beginIndex = 0;
        long totalSize = 1786444;//mapper.getTotal();

        long times= totalSize / QUERY_SIZE;
        if(totalSize % QUERY_SIZE !=0) {
            times=times+1;
        }
        CountDownLatch latch = new CountDownLatch(Integer.valueOf(String.valueOf(times)));
    //    List<Runnable> tasks = new ArrayList<Runnable>();//添加任务
        for(int i = 0; i <times ; i++){
            Runnable task = new CleanTask(beginIndex,QUERY_SIZE,STAGE_SHOW,latch);
        //    tasks.add(task);
            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)/1000+"秒");
    }


    /*
     * @description  成交公告清洗  数量少，直接全文条件查询
     * @author tengchao.li
     * @date 2020/2/26
     * @param
     * @return void
     */
    @Test
    public void doClean2() {
        try {
                List<DataContentWithBLOBs> dataContent = mapper.selectAllByStageShow(STAGE_SHOW);
                if (dataContent == null || dataContent.size() < 1) {

                }
                LinkedBlockingQueue<DataContentWithBLOBs> queue = new LinkedBlockingQueue();
                for (DataContentWithBLOBs data : dataContent) {
                    if (STAGE_SHOW.equals(data.getStageshow())) {
                        queue.add(data);
                    }
                }
                if (queue.size() > 0) {
                    EXECUTOR.execute(new CleanDealNoticeTask(queue));
                }

        } catch (Exception e) {
          //  logger.error("========= " + STAGE_SHOW + " index" + i + "==============");

        }

    }






}
