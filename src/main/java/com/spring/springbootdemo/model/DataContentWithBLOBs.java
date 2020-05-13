package com.spring.springbootdemo.model;

import lombok.Data;

@Data
public class DataContentWithBLOBs extends DataContent {
    private String content;
    private String pubTime;


    private String category;
    private String expertname;


    @Override
    public String toString() {
        return "DataContentWithBLOBs{} " + super.toString();
    }
}
