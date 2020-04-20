package com.spring.springbootdemo.enums;

import com.spring.springbootdemo.contant.ContantFiled;
import org.apache.commons.lang3.StringUtils;

/**
 * 中标公告
 */
public enum WinBidNoticeEnum {


    WIN_BID_BIS_NO("中标人信息", "中标序号", "提取正则表达式", ContantFiled.WIN_BID_BIS_NO),
    WIN_BID_BIS_NAME("中标人信息", "中标人", "提取正则表达式", ContantFiled.WIN_BID_BIS_NAME),
    TENDER_TOTAL_AMOUNT("中标人信息", "中标价", "提取正则表达式", ContantFiled.TENDER_TOTAL_AMOUNT),
    //WIN_BID_NO1("中标人信息","中标内容", "提取正则表达式",ContantFiled),

    PRO_NAME("项目信息", "项目名称", "提取正则表达式", ContantFiled.PRO_NAME),
    PRO_CONTACT("项目信息", "项目联系人", "提取正则表达式", ContantFiled.PRO_CONTACT),
    PRO_PHONE("项目信息", "项目联系电话", "提取正则表达式", ContantFiled.PRO_PHONE),
    BUDGET_AMOUNT("项目信息", "项目预算金额", "提取正则表达式", ContantFiled.BUDGET_AMOUNT),
    OPEN_TENDER_TIME("项目信息", "招标时间", "提取正则表达式", ContantFiled.OPEN_TENDER_TIME),
    RES_FILE_OPEN_TIME("项目信息", "获取文件时间", "提取正则表达式", ContantFiled.RES_FILE_OPEN_TIME),
    PUB_TIME("项目信息", "项目发布时间", "提取正则表达式", ContantFiled.PUB_TIME),
    NOTICE_TIME("项目信息", "招标公告日期", "提取正则表达式", ContantFiled.NOTICE_TIME),
    FIRST_NOTICE_TIME("项目信息", "首次公告日期", "提取正则表达式", ContantFiled.FIRST_NOTICE_TIME),
    TENDER_TIME("项目信息", "中标成交日期", "提取正则表达式", ContantFiled.TENDER_TIME),


    PRO_NO("项目信息", "招标编号", "提取正则表达式", ContantFiled.PRO_NO),
    PROXY_ORG_NAME("项目信息", "代理机构名称", "提取正则表达式", ContantFiled.PROXY_ORG_NAME),
    //	WIN_BID_NO1("中标人信息","评标日期", "提取正则表达式",ContantFiled.TIME),
    BUY_ORG("项目信息", "招标单位", "提取正则表达式", ContantFiled.BUY_ORG),
    BUY_ORG_ADDR("项目信息", "招标单位地址", "提取正则表达式", ContantFiled.BUY_ORG_ADDR),
    PROXY_ORG_CONTACT_PHONE("项目信息", "代理机构联系电话", "提取正则表达式", ContantFiled.PROXY_ORG_CONTACT_PHONE),
    PROXY_ORG_ADDR("项目信息", "代理机构地址", "提取正则表达式", ContantFiled.PROXY_ORG_ADDR),
    PRO_ORG_CONTACT("项目信息", "代理机构联系人", "提取正则表达式", ContantFiled.PRO_ORG_CONTACT),
    TENDER_WAY("项目信息", "招标方式", "提取正则表达式", ContantFiled.TENDER_WAY),
    OPEN_TENDER_ADDR("项目信息", "开标地点", "提取正则表达式", ContantFiled.OPEN_TENDER_ADDR),
    EXPERT_NAME("项目信息", "评标委员会成员名单", "提取正则表达式", ContantFiled.EXPERT_NAME),


    TENDERING_FILE_PRICE("招标文件信息", "招标文件售价", "提取正则表达式", ContantFiled.TENDERING_FILE_PRICE),
    OBTAIN_FILE_TIME("招标文件信息", "获取招标文件时间", "提取正则表达式", ContantFiled.OBTAIN_FILE_TIME),
    TENDERING_FILE_ADDR("招标文件信息", "招标文件地址", "提取正则表达式", ContantFiled.TENDERING_FILE_ADDR),
    RES_FILE_SUB_ADDR("招标文件信息", "响应文件送达地点", "提取正则表达式", ContantFiled.RES_FILE_SUB_ADDR),

    EMAIL("其它", "邮箱", "提取正则表达式", ContantFiled.EMAIL),
    ZIP_CODE("其它", " 邮编", "提取正则表达式", ContantFiled.ZIP_CODE);


    private String headerClass;
    private String headerType;
    private String regex;
    private String rule;

 	WinBidNoticeEnum(String headerClass, String headerType, String regex, String rule) {
        this.headerClass = headerClass;
        this.headerType = headerType;
        this.regex = regex;
        this.rule = rule;


    }

    public static WinBidNoticeEnum getFiledType(String key){

 		for (WinBidNoticeEnum en : WinBidNoticeEnum.values()){
 			if(keyIsContains(key,en.getRule())){
 				return en;
			}

		}
 		return null;
	}
	public static boolean keyIsContains(String key, String fieldStr) {

		if (StringUtils.isBlank(fieldStr) || fieldStr.split(":").length < 2) {
			return false;
		}
		String s = fieldStr.split(":")[1];
		if (!s.endsWith("/")) {
			s = s + "/";
		}

		String[] split = s.split("/");
		for (String str : split) {

			if (key.equals(str)) {
				return true;
			}
		}
		return false;
	}

	public String getHeaderClass() {
		return headerClass;
	}

	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}

	public String getHeaderType() {
		return headerType;
	}

	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
