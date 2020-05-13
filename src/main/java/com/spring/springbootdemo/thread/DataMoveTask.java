package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.utils.DataConvert;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class DataMoveTask implements Runnable {

    private int beginIndex;
    private ConfigParam config;

    private static final Logger logger = LoggerFactory.getLogger(DataMoveTask.class);


    public DataMoveTask(int beginIndex, ConfigParam config) {
        this.beginIndex = beginIndex;
        this.config = config;
    }

    @Override
    public void run() {
        try {
            DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
            List<GovData> list = new LinkedList<>();
            List<DataContentWithBLOBs> dataContent = mapper.selectByType(beginIndex, config.getQuerySize(), config.getCleanTableName(),config.getStage());
            if (dataContent == null || dataContent.size() < 1) {
                logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            for (DataContentWithBLOBs data : dataContent) {
                GovData govData = DataConvert.toGovData(data);
                list.add(govData);
            }
            int row = mapper.insertList_BJ(list, config.getInsertTableName());
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            config.getLatch().countDown();
        }
    }



}
