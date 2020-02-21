package com.spring.springbootdemo;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author tengchao.li
 * @description
 * @date 2020/2/21
 */
@SpringBootTest(value = "application.yml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DataCleanTest {
    @Autowired
    private DataContentMapper mapper;
    @Test
    public void test(){
        List<DataContentWithBLOBs> dataContentWithBLOBs = mapper.selectAll(0, 1);
        System.out.println(dataContentWithBLOBs.size());


    }

}
