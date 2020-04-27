package com.spring.springbootdemo.contant;

import java.util.HashSet;
import java.util.Set;

/**
 * 主要针对表头判别
 * @author tengchao.li
 * @description
 * @date 2020/3/4
 */
public class ContantFiled {

    // **********************政府采购北京配置信息************
    public static final String PRO_NAME = "proName:采购项目名称/项目名称/货物服务名称";
    public static final String PRO_NO = "proNo:采购项目编号/项目编号/采购编号/采购文件编号";
    public static final String BUY_ORG = "buyingOrg:采购人名称/采购单位名称/采购单位/采购人/招标人/本项目采购人/本次招标联系事项采购人";
    public static final String BUY_ORG_ADDR = "buyingAddr:采购单位地址/单位地址/地址/采购人地址";
    public static final String PRO_CONTACT = "proContact:项目联系人/采购人联系人/采购单位联系人";
    public static final String PRO_PHONE = "proPhone:联系方式/联系电话/";
    public static final String BUDGET_AMOUNT = "budgetAmount:项目预算金额/项目预算/采购预算/采购项目预算最高限价/预算金额/总预算价格/采购预算金额/采购预算金额元/采购预算金额万元/采购预算金额人民币/采购预算资金";

    public static final String CLASSIFY = "classify:采购项目类型";
    public static final String  PACKAGE_NO = "packageNo: 标号/标包/包号/序号";
    /**
     * 代理机构信息
     */
    public static final String PRO_ORG_CONTACT = "proOrgContact:采购代理机构联系人/项目联系人/代理联系人";
    public static final String PROXY_ORG_ADDR = "proOrgAddr:采购代理机构地址/代理机构地址/招标代理机构地址/政府采购代理机构联系地址/";
    public static final String PROXY_ORG_NAME = "proxyOrgName:采购代理机构名称/采购代理机构全称/代理机构/代理机构全称/招标代理机构全称/招标代理机构/采购代理";
    public static final String PROXY_ORG_CONTACT_PHONE = "proxyOrgPhone:采购代理机构联系电话/采购代理机构联系方式/代理机构联系人/代理电话";

    /**
     * 时间类信息
     */
    public static final String PUB_TIME = "pubTime:项目创建时间/发布时间";
    public static final String NOTICE_TIME = "noticeTime:本项目招标公告日期/招标公告日期/本次变更日期";
    public static final String FIRST_NOTICE_TIME = "firstNoticeTime:首次公告日期";
    public static final String TENDER_TIME = "tenderTime:中标成交日期";
    public static final String OBTAIN_FILE_TIME = "obtainFileTime:获取文件时间/获得文件时间/得到文件时间";
    public static final String RES_FILE_OPEN_TIME = "resFileOpenTime:获取文件时间/获得文件时间/得到文件时间/获取招标文件时间";
    public static final String OPEN_TENDER_TIME = "opentendertime:开标时间/开标(谈判/磋商/询价)时间/谈判时间/磋商时间/询价时间";
    /**
     * 金额类信息
     */

    public static final String TENDER_TOTAL_AMOUNT = Contant.TENDER_TOTAL_AMOUNT;//"winBidTotalAmount:中标金额/中标结果/总成交金额/总中标成交金额/中标总金额/总金额/成交金额/中标(成交)金额";//中标金额
    /**
     * 中标机构信息
     */

    public static final String WIN_BID_BIS_NAME = Contant.WIN_BID_BIS_NAME;//"winBidBisName:中标成交供应商名称/投标人名称/供应商名称/中标供应商名称/成交供应商名称";
    public static final String WIN_ORG_ADDR = Contant.WIN_ORG_ADDR;//"winBidBisNo:中标序号/序号/名次/包号";
    public static final String WIN_BID_BIS_NO = "winBidBisNo:中标序号/序号/名次/包号";

    /**
     * 其他 邮箱，邮编，名单等
     */

    public static final String  EMAIL = "email:邮箱";

    public static final String ZIP_CODE = Contant.ZIP_CODE;//"zipcode:邮编/邮政编码/";

    //"expertName:评标委员会成员名单/评标委员会成员/采购小组成员名单/评审委员会名单/评审小组名单/名单/评审小组成员/谈判小组专家名单/采购结果确认谈判小组/磋商小组名单/评标委员会名单";//评标委员会成员名单
    public static final String EXPERT_NAME = Contant.EXPERT_NAME;


    /**
     * 招标文件信息
     */

    public static final String TENDER_WAY = Contant.TENDER_WAY;//    "tenderWay:招标方式/采购方式";

    public static final String TENDERING_FILE_PRICE = "tenderingFilePrice:招标文件售价/磋商文件售价";

    public static final String TENDERING_FILE_ADDR = "tenderingFileAddr:招标文件地址/招标文件报名及发售地点";

    public static final String OPEN_TENDER_ADDR = "openTenderAddr:开标地点/投标地点/投标开标地点/磋商地点/地点";

    public static final String RES_FILE_SUB_ADDR = "resFileSubAddr:响应文件送达地点";


}
