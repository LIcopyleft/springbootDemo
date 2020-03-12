package com.spring.springbootdemo.contant;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/4
 */
public class Contant {
    private static  String FIRST_NOTICE_TIME;
    /*
        private String procontact;
        private String prophone;
        private String buyingunit;
        private String buyingaddr;
        private String buyingphone;
        private String proxyorgaddr;
        private String proxyorgName;
        private String proxyorgphone;
        private String proname;
        private String classes;
        private String noticetime;
        private String region;
        private String submitfiledeadtime;
        private String entitledchecktime;
        private String appendix;
        private String prono;
        private String obtainfiletime;
        private String tenderingfileprice;
        private String tenderingfileaddr;
        private String opentendertime;
        private String opentenderaddr;
        private String budgetamount;
        private String resfilesubtime;
        private String resfilesubaddr;
        private String resfileopentime;
        private String winbidtime;
        private String tenderingnoticetime;
        private String winbidtotalamount;
        private String winbidbisname;
        private String winbidbisaddr;
        private String winbidbisamount;
        private String firstnoticetime;
    */
    private static String PRO_NAME;// = "proname:项目名称/采购项目名称/";
    private static String PRO_NO;// = "prono:项目编号/采购项目编号/交易编号/招标编号";
    private static String BUY_ORG;// = "buyingunit:招标单位/采购人名称/招标人/采购人/招标采购人名称";//采购单位
    private static String BUY_ORG_ADDR;// = "buyingaddr:采购单位地址/采购人地址/地址/址/";//采购单位地址
    private static String NOTICE_TIME;// = "noticetime:采购公告发布日期/采购公告日期";//采购公告日期
    private static String ORIGIN;// = "origin:地区/";
    private static String TENDER_TIME;// = "winbidtime:中标日期/成交日期";//中标日期
    private static String EXPERT_NAME;// = "expertname:评标委员会成员名单/采购小组成员名单/评审委员会名单/评审小组名单/名单/评审小组成员/谈判小组专家名单/采购结果确认谈判小组/磋商小组名单/评标委员会名单";//评标委员会成员名单
    private static String TENDER_TOTAL_AMOUNT;// = "winbidtotalamount:中标金额/中标总金额/总金额/成交金额/中标(成交)金额";//中标金额
    private static String WIN_ORG_NAME;// = "winbidbisname:中标人名称/中标人/中标机构名称/成交供应商/供应商名称/成交单位名称/成交供应商名称/中标供应商名称/中标成交供应商名称";//
    private static String WIN_ORG_ADDR;// = "winbidbisaddr:中标单位地址/中标成交供应商地址/成交单位地址/成交供应商地址/中标供应商联系地址/中标供应商地址/中标成交供应商联系地址";//中标单位地址
    private static String PRO_CONTACT;// = "procontact:项目联系人/联系人/联系人及联系方式";//项目联系人
    //   private static  String PRO_CONTACT_PHONE = "proname:电话/项目联系电话";//项目联系电话
    private static String PROXY_ORG_NAME;// = "proxyorgName:代理机构/代理机构名称/招标代理机构/采购代理机构名称/代理机构全称/招标采购代理机构名称";//代理机构名称
    private static String PROXY_ORG_ADDR;// = "proxyorgaddr:代理机构地址/政府采购代理机构联系地址/招标代理机构";//代理机构地址
    private static String PRO_ORG_CONTACT;// = "proxyorgaddr:代理机构地址/政府采购代理机构联系地址/招标代理机构";//代理机构地址

    private static String PROXY_ORG_CONTACT_PHONE;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String BUDGET_AMOUNT;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String CLASSIFY;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String PUB_TIME;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String PRO_PHONE;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field1;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field2;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field3;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field4;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field5;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field6;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
    private static String Field7;// = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//


    //   private static  String TENDER_WAY = "proname:";//


    private static Set set = new HashSet();
    private static String Field9;
    private static String Field8;
    // **********************政府采购北京配置信息************

    public static Set filedBJValueSet() {
        PRO_NAME = "proName:采购项目名称/项目名称";
        PRO_NO = "proNo:采购项目编号/项目编号/采购编号";
        BUY_ORG = "buyingOrg:采购人名称/采购单位名称/采购单位";
        BUY_ORG_ADDR="buyingAddr:采购单位地址/单位地址/地址/采购人地址";
        ORIGIN = "email:邮箱";
        PUB_TIME = "pubTime:项目创建时间";
        EXPERT_NAME = "expertName:评标委员会成员名单/评标委员会成员/采购小组成员名单/评审委员会名单/评审小组名单/名单/评审小组成员/谈判小组专家名单/采购结果确认谈判小组/磋商小组名单/评标委员会名单";//评标委员会成员名单

        PRO_CONTACT = "proContact:项目联系人/采购人联系人";
        PRO_PHONE = "proPhone:联系方式/联系电话/";
        PRO_ORG_CONTACT = "proOrgContact:采购代理机构联系人/项目联系人";
        PROXY_ORG_ADDR = "proOrgAddr:采购代理机构地址/代理机构地址/招标代理机构地址/政府采购代理机构联系地址/";
        PROXY_ORG_NAME = "proxyOrgName:采购代理机构名称/采购代理机构全称/代理机构/代理机构全称/招标代理机构全称/招标代理机构";
        PROXY_ORG_CONTACT_PHONE = "proxyOrgPhone:采购代理机构联系电话/采购代理机构联系方式/代理机构联系人";
        BUDGET_AMOUNT = "budgetAmount:项目预算/采购预算/预算金额/采购预算资金";
        CLASSIFY = "classify:采购项目类型";
        NOTICE_TIME = "noticeTime:本项目招标公告日期/招标公告日期/本次变更日期";
        FIRST_NOTICE_TIME = "firstNoticeTime:首次公告日期";
        TENDER_TIME = "tenderTime:中标成交日期";
        TENDER_TOTAL_AMOUNT = "winBidTotalAmount:中标金额/总成交金额/总中标成交金额/中标总金额/总金额/成交金额/中标(成交)金额";//中标金额
        Field1 = "tenderWay:招标方式/采购方式";
        Field2 = "winBidBisName:中标成交供应商名称/中标供应商名称/成交供应商名称";
        Field3 ="tenderingFilePrice:招标文件售价/磋商文件售价";
        Field4 = "tenderingFileAddr:招标文件地址/招标文件报名及发售地点";
        Field5 = "opentendertime:开标时间";
        Field6 ="openTenderAddr:开标地点";
        Field7 ="obtainFileTime:获取文件时间/获得文件时间/得到文件时间";
        Field8 ="resFileOpenTime:获取文件时间/获得文件时间/得到文件时间";
        Field9 ="resFileSubAddr:获取文件时间/获得文件时间/得到文件时间";

        return valueSet();
    }


    public static Set filedValueSet() {

        PRO_NAME = "proname:项目名称/采购项目名称/";
        PRO_NO = "prono:项目编号/采购项目编号/交易编号/招标编号";
        BUY_ORG = "buyingunit:招标单位/采购人名称/招标人/采购人/招标采购人名称";//采购单位
        BUY_ORG_ADDR = "buyingaddr:采购单位地址/采购人地址/地址/址/";//采购单位地址
        NOTICE_TIME = "noticetime:采购公告发布日期/采购公告日期";//采购公告日期
        ORIGIN = "origin:地区/";
        TENDER_TIME = "winbidtime:中标日期/成交日期";//中标日期
        EXPERT_NAME = "expertname:评标委员会成员名单/采购小组成员名单/评审委员会名单/评审小组名单/名单/评审小组成员/谈判小组专家名单/采购结果确认谈判小组/磋商小组名单/评标委员会名单";//评标委员会成员名单
        TENDER_TOTAL_AMOUNT = "winbidtotalamount:中标金额/总中标成交金额/中标总金额/总金额/成交金额/中标(成交)金额";//中标金额
        WIN_ORG_NAME = "winbidbisname:中标人名称/中标人/中标机构名称/成交供应商/供应商名称/成交单位名称/成交供应商名称/中标供应商名称/中标成交供应商名称";//
        WIN_ORG_ADDR = "winbidbisaddr:中标单位地址/中标成交供应商地址/成交单位地址/成交供应商地址/中标供应商联系地址/中标供应商地址/中标成交供应商联系地址";//中标单位地址
        PRO_CONTACT = "procontact:项目联系人/联系人/联系人及联系方式";//项目联系人
        PROXY_ORG_NAME = "proxyorgName:代理机构/代理机构名称/招标代理机构/采购代理机构名称/代理机构全称/招标采购代理机构名称";//代理机构名称
        PROXY_ORG_ADDR = "proxyorgaddr:代理机构地址/政府采购代理机构联系地址/招标代理机构";//代理机构地址
        PROXY_ORG_CONTACT_PHONE = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
        return valueSet();
    }


    private static Set valueSet() {

        set.add(PRO_NAME);
        set.add(PRO_NO);// "prono:项目编号/采购项目编号/交易编号/招标编号";
        set.add(BUY_ORG);// "proname:招标单位/采购人名称/招标人/名称/采购人";//采购单位
        set.add(BUY_ORG_ADDR);// "proname:地址/址/采购单位地址/";//采购单位地址
        set.add(NOTICE_TIME);// "proname:采购公告发布日期";//采购公告日期
        set.add(ORIGIN);
        set.add(TENDER_TIME);// "proname:";//中标日期
        set.add(EXPERT_NAME);
        set.add(TENDER_TOTAL_AMOUNT);// "proname:中标金额/中标总金额/总金额/成交金额";//中标金额
        set.add(WIN_ORG_NAME);// "proname:中标人名称/中标人/中标机构名称";//中标金额
        set.add(WIN_ORG_ADDR);// "proname:中标单位地址/中标成交供应商地址/成交单位地址";//中标单位地址
        set.add(PRO_CONTACT);// "proname:项目联系人/联系人/联系人及联系方式";//项目联系人
        //   set.add(PRO_CONTACT_PHONE);// "proname:电话/项目联系电话";//项目联系电话
        set.add(PROXY_ORG_NAME);// "proname:代理机构/代理机构名称/招标代理机构/采购代理机构名称";//代理机构名称
        set.add(PROXY_ORG_ADDR);// "proname:";//代理机构地址
        set.add(PROXY_ORG_CONTACT_PHONE);// "proname:";//
        set.add(BUDGET_AMOUNT);
        set.add(CLASSIFY);
        set.add(FIRST_NOTICE_TIME);
        set.add(PUB_TIME);
        set.add(PRO_ORG_CONTACT);
        set.add(PRO_PHONE);
        set.add(Field1);
        set.add(Field2);
        set.add(Field3);
        set.add(Field4);
        set.add(Field5);
        set.add(Field6);
        set.add(Field7);
        set.add(Field8);
        set.add(Field9);


        return set;

    }
}
