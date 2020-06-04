package com.spring.springbootdemo.model;

import lombok.Data;

import java.util.List;

@Data
public class GovData extends GovDataKey {

    private String content;
    private String category;
    private String email;

    private String expertName;

    private String other;
    private String classify;

    private String stageName;

    private String platformName;

    private String classifyShow;

    private String tradeShow;

    private String districtShow;

    private String url;

    private String stageShow;

    private String title;
    private String proContact;
    private String proPhone;
    private String buyingOrg;
    private String buyingAddr;
    private String buyingOrgPhone;
    private String proxyOrgAddr;
    private String proxyOrgName;
    private String proxyOrgContact;
    private String proxyOrgPhone;
    private String proName;
    private String classes;
    private String noticeTime;
    private String region;
    private String submitFileDeadTime;
    private String entitledCheckTime;
    private String appendix;
    private String proNo;
    private String obtainFileTime;
    private String tenderWay;
    private String tenderingFilePrice;
    private String tenderingFileAddr;
    private String opentendertime;
    private String openTenderAddr;
    private String budgetAmount;
    private String resFileSubTime;
    private String resFileSubAddr;
    private String resFileOpenTime;
    private String winBidTime;
    private String tenderingNoticeTime;
    private String winBidTotalAmount;
    private String winBidBisName;
    private String winBidBisAddr;
    private String winBidBisAmount;
    private String firstNoticeTime;
    private String fileOpenWay;
    private String judgeMethod;
    private String location;
    private String coordinate;
    private String pubTime;
    private String zipcode;
    private String winBisInfoStr; //中标公告中中标
    private List<WinBisInfo> bisList;

    @Override
    public String toString() {
        return "GovData{" +
                "urlId='" + super.getUrlId() + '\'' +
                ", stageShow='" + stageShow + '\'' +
                ", budgetAmount='" + budgetAmount + '\'' +
                ", winBidTotalAmount='" + winBidTotalAmount + '\'' +
                ", winBidBisName='" + winBidBisName + '\'' +
                ", winBidBisAddr='" + winBidBisAddr + '\'' +
                ",category='" + category + '\'' +
                ", email='" + email + '\'' +
                ", expertName='" + expertName + '\'' +
                ", classifyShow='" + classifyShow + '\'' +
                ", districtShow='" + districtShow + '\'' +
                ", proContact='" + proContact + '\'' +
                ", buyingOrg='" + buyingOrg + '\'' +
                ", buyingAddr='" + buyingAddr + '\'' +
                ", proxyOrgAddr='" + proxyOrgAddr + '\'' +
                ", proxyOrgName='" + proxyOrgName + '\'' +
                ", proxyOrgContact='" + proxyOrgContact + '\'' +
                ", proName='" + proName + '\'' +
                ", noticeTime='" + noticeTime + '\'' +
                ", region='" + region + '\'' +
                ", proNo='" + proNo + '\'' +

                "} ";
    }
}
