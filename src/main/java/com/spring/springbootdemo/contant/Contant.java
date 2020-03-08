package com.spring.springbootdemo.contant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/4
 */
public class Contant {
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
    private static final String PRO_NAME = "proname:项目名称/采购项目名称/";
    private static final String PRO_NO = "prono:项目编号/采购项目编号/交易编号/招标编号";
    private static final String BUY_ORG = "buyingunit:招标单位/采购人名称/招标人/采购人/招标采购人名称";//采购单位
    private static final String BUY_ORG_ADDR = "buyingaddr:采购单位地址/采购人地址/地址/址/";//采购单位地址
    private static final String NOTICE_TIME = "noticetime:采购公告发布日期/采购公告日期";//采购公告日期
    private static final String ORIGIN= "origin:地区/";
    private static final String TENDER_TIME = "winbidtime:中标日期/成交日期";//中标日期
    private static final String EXPERT_NAME = "expertname:评标委员会成员名单/采购小组成员名单/评审委员会名单/评审小组名单/名单/评审小组成员/谈判小组专家名单/采购结果确认谈判小组/磋商小组名单/评标委员会名单";//评标委员会成员名单
    private static final String TENDER_TOTAL_AMOUNT = "winbidtotalamount:中标金额/中标总金额/总金额/成交金额/中标(成交)金额";//中标金额
    private static final String WIN_ORG_NAME = "winbidbisname:中标人名称/中标人/中标机构名称/成交供应商/供应商名称/成交单位名称/成交供应商名称/中标供应商名称/中标成交供应商名称";//
    private static final String WIN_ORG_ADDR = "winbidbisaddr:中标单位地址/中标成交供应商地址/成交单位地址/成交供应商地址/中标供应商联系地址/中标供应商地址/中标成交供应商联系地址";//中标单位地址
    private static final String PRO_CONTACT = "procontact:项目联系人/联系人/联系人及联系方式";//项目联系人
 //   private static final String PRO_CONTACT_PHONE = "proname:电话/项目联系电话";//项目联系电话
    private static final String PROXY_ORG_NAME = "proxyorgName:代理机构/代理机构名称/招标代理机构/采购代理机构名称/代理机构全称/招标采购代理机构名称";//代理机构名称
    private static final String PROXY_ORG_ADDR = "proxyorgaddr:代理机构地址/政府采购代理机构联系地址/招标代理机构";//代理机构地址
    private static final String PROXY_ORG_CONTACT_PHONE = "proxyorgphone:代理机构电话/代理机构联系方式/政府采购代理机构联系电话";//
 //   private static final String TENDER_WAY = "proname:";//

    private static Set set = new HashSet();

    public static Set filedValueSet(){

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
        return set;

    }
}
