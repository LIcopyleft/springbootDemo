package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.thread.JiLinTask;
import com.spring.springbootdemo.thread.YunNanTask;
import com.spring.springbootdemo.utils.FileUtils;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class JiLinTest {
//    private static final Logger logger = LoggerFactory.getLogger(GOVDataCleanTest.class// private static final String STAGE_SHOW = "招标/资审文件澄清";
    //  private static final String STAGE_SHOW = "采购/资审公告";
    //   private static final String STAGE_SHOW = "更正事项";

    private static Integer MAX_THREAD_NUM = 5;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(MAX_THREAD_NUM);
    private static final String STAGE = "政府采购";
    // private static final String STAGE = "交易大厅>交易公告>政府采购>中标、成交结果公告|交易大厅>交易公告>政府采购>中标候选人公示";
    //   private static final String STAGE = "交易大厅>交易公告>政府采购>采购/资审公告";
    //   private static final String STAGE = "交易大厅>交易公告>政府采购>采购合同公示";
    private static final String INSERT_TABLE_NAME = "temp";
    //   private static final String CLEAN_TABLE_NAME = "spider_2_ggzy_content_clean_temp";
    private static final String CLEAN_TABLE_NAME = "spider_32_ggzy_jilin_content";
    private static final String UNION_TABLE_NAME = "spider_32_ggzy_jilin_url";
    private static final int INSERT_MAX = 1000;
    private static final int QUERY_SIZE = 200;
    private static final int TABLE_SIZE = 1;
    //是否开启单独清洗 表格数为特定值得记录
    private static final boolean FLAG = false;
    //是否开启使用查询 辅助表 完善字段
    private static final boolean IS_QUERY_UNION = true;

    @Test
    public void clean() throws InterruptedException {
        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
        long start = System.currentTimeMillis();
        int beginIndex = 0;
        // int totalSize = 329318;//mapper.getTotal();
      //     int totalSize = 4448;//mapper.getTotal();
       int totalSize = mapper.countNum(CLEAN_TABLE_NAME);

        System.out.println("数据总量为"+ totalSize);
        List<String> category = mapper.getCategory(CLEAN_TABLE_NAME);
        category.stream().forEach(item -> {
            System.out.println(item);
        });

        int times = totalSize / QUERY_SIZE;
        if (totalSize % QUERY_SIZE != 0) {
            times = times + 1;
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
        config.setUnionTableName(UNION_TABLE_NAME);
        config.setUseUnionTable(IS_QUERY_UNION);
        for (int i = 0; i < times; i++) {
            Runnable task = new JiLinTask(beginIndex, config);
            beginIndex += QUERY_SIZE;
            EXECUTOR.execute(task);
        }
        latch.await();
        EXECUTOR.shutdown();
        long end = System.currentTimeMillis();
        int num = mapper.countNum(INSERT_TABLE_NAME);
        System.out.println("共清洗政府采购数据" + num + "条");
        System.out.println("用时" + (end - start) / 1000 + "秒");
    }


    @Test
    public void debug() {
        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
        List<String> list = FileUtils.readFileToList("C:\\Users\\DRC\\Desktop\\url_id.txt");

        for (String urlId : list) {
            urlId = urlId.replace("\"", "");
            DataContentWithBLOBs temp = mapper.selectById("temp", Integer.valueOf(urlId));
            if (temp == null) {
                //    Integer urlId1 = temp.getUrlId();
                System.err.println(urlId);
            }
        }

    }

}
