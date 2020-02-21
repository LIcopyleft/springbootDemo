package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.concurrent.LinkedBlockingQueue;

public class ThreadTask implements Runnable{
    private LinkedBlockingQueue<DataContentWithBLOBs> queue;


    public ThreadTask(LinkedBlockingQueue<DataContentWithBLOBs> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");

        if(queue.iterator().hasNext()){

            DataContentWithBLOBs data = queue.poll();

            String content = data.getContent();
            Document parse = Jsoup.parse(content);

            Element table = parse.getElementsByTag("table").get(0);
            Elements children = table.children();


            mapper.insertSelective(null);

        }



    }
}
