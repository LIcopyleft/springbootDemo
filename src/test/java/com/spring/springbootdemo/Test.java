package com.spring.springbootdemo;

import com.spring.springbootdemo.enums.WinBidNoticeEnum;
import com.spring.springbootdemo.utils.FileUtils;
import com.spring.springbootdemo.utils.HtmlUtils;
import com.spring.springbootdemo.utils.RegExpUtil;
import com.spring.springbootdemo.utils.TableConvert;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {


    public static void main(String[] args) throws IOException {
String string  = "发布时间:([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        RegExpUtil.regGet("发布时间:2019-06-05 16:40",string);

        strT();
        String strs = "采购公告公示发布日期/本项目首次公告时间/首次公告发布日期及媒介/竞争性磋商邀请公告发布日期/发布竞争性谈判公告时间/原公告中报名时间/公告媒介及";

        List<String> list = FileUtils.readFileToList("金额_过滤_成交金额.txt");
        List<String> keys = new LinkedList<>();
        for (String str : list) {
            if (str.length() > 14) {
                continue;
            }


		/*	if(str.contains("金额") || str.contains("费")
					|| str.contains("协议")
					|| str.contains("编号")
					|| str.contains("本包")
					|| str.contains("开户")
					|| str.contains("报酬")
					|| str.contains("支持")
					|| str.contains("范围")
					|| str.contains("解密")
					|| str.contains("地址")
					|| str.contains("联系")
					|| str.contains("职责")
					|| str.contains("期限")
					|| str.contains("电话")
					|| str.contains("质疑")
					|| str.contains("提供")
					|| str.contains("提供")
					|| str.contains("提供")
					|| str.contains("地点")
					|| str.contains("负责人")
					|| str.contains("制作人")
					|| str.contains("如为")
					|| str.contains("方面")
					|| str.contains("工作人员")
					|| str.contains("邮箱")
					|| str.contains("负责人")
					|| str.contains("型号")
					|| str.contains("项目经理")
					|| str.contains("支付方式")
					|| str.contains("邮箱")
					|| str.contains("资料")
					|| str.contains("负责人")
					|| str.contains("信用代码")){
				continue;
			}

		 */

            if (str.contains("预算")
                    || str.contains("折扣率")
                //        ||str.contains("成交")

            ) {
                continue;
            }
            if (str.contains("中标")
                    || str.contains("成交")
            ) {
                keys.add(str);

            }
        }
        FileUtils.writeNewFile("金额_过滤_成交金额.txt", keys);
        StringBuilder sb = new StringBuilder();
        for (String s : keys) {
            sb.append(s + "/");
        }
        System.err.println(sb.toString());
        for (WinBidNoticeEnum en : WinBidNoticeEnum.values()) {
            String rule = en.getRule();
            if (HtmlUtils.keyIsContains("项目名称", rule)) {

                System.err.println(en);
            }

            System.err.println(rule);

        }


        getSimilarity("主要中标、成交标的名称", "成交标的名称");
        //	strFamate();

		/*
		String url = "1.html";
		InputStream in;
		TableConvert tableConvert = new TableConvert();
		//	Connection connect = Jsoup.connect(url);
		//	Document doc = connect.get();
		try {
			in = new FileInputStream(url);
			Document doc = Jsoup.parse(in, null, "");

			Elements tables = doc.getElementsByTag("table");
			for (Element aTable : tables) {
				Elements table = aTable.select("table");
				if(table.size() > 1){
					Elements children = aTable.children();
					Elements table1 = children.select("table").remove();
					System.out.println(aTable.select("table").size());

				}

				Elements subtables = aTable.getElementsByTag("table");
				subtables.remove(aTable);
				if (subtables.size() == 0) {
					System.out.println("converting table...");
					Element[][] result = tableConvert.toTable(aTable);


					if (null != result) {
						{
						//	tableConvert.printTable(result);
						}
					} else {
						System.out.println("Could not convert table.");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} */
    }


    public static void strFamate(String str) {
        //	String str = "中标单位:青海经纬水利水电设计有限公司 成交金额:￥ 488900.00元 设计周期:60天 采购代理机构:青海仁捷工程项目管理有限公司 联系人:索女士 联系电话:0971-2230320 联系地址:湟中县鲁沙尔镇北环路1号（鲁沙尔文化旅游产业园双创基地）六楼";

        String all = str.replaceAll("\\s", "#@#");
        all = all.replaceAll("：", ":");
        String[] split = all.split("#@#");
        StringBuilder sb = new StringBuilder();
        for (String string : split) {
            sb.append(string);
            if (string.contains(":")) {
                sb.append("#@#");
            } else {
                int start = sb.lastIndexOf("#@#");
                sb.delete(start, sb.length());
                sb.append(string + "#@#");
            }
        }
        split = sb.toString().split("#@#");

    }


    public static void strT() {

        String p1 = " :";
        String p2 = ": ";
        String str = "河北省全国公共资源交易平台-公告详情 我要报名 我要分享 我要打印 当前位置: 网站首页交易大厅交易公告政府采购采购/资审公告 邯郸市住房公积金管理中心核心业务系统新增需求项目 项目编号: 发布时间:2020-01-21 11:28信息来源:邯郸市邯郸市 阅读次数: 报名倒计时 天 小时 分钟 报名已结束 邯郸市住房公积金管理中心核心业务系统新增需求项目 (公开招标公告的公告期限为5个工作日) 发布时间:  2019-12-09 采购项目编号:HB2019105080020004 需要落实的政府采购政策:落实政策依据:（财政部、国家发展改革委员会公布的《节能产品政府采购清单》）、（财政部、环境保护部公布的《环境标志产品政府采购清单》）、《财政部 民政部 中国残疾人联合会关于促进残疾人就业政府采购政策的通知》（财库﹝2017﹞141号）的规定、《政府采购促进中小企业发展暂行办法》（财库﹝2011﹞181号）的规定、《关于政府采购支持监狱企业发展有关问题的通知》（财库﹝2014﹞68号）的规定、根据《河北省财政厅关于做好政府采购支持质量提升行动有关工作的通知》冀财采（2018）11号的规定等，详见采购文件。 采购人名称:邯郸市住房公积金管理中心 采购人地址 :邯郸市住房公积金管理中心 采购人联系方式:刘强 0310-8052912   采购代理机构地址 :邯郸市民服务中心四层 采购代理机构联系方式 :田先生   0310-2031776 采购预算金额:2419500.00 采购用途 :  核心业务系统新增需求项目 项目实施地点 :邯郸市 投标人的资格要求 :①备《中华人民共和国政府采购法》第二十二条规定的供应商基本条件； ②提供有效的营业执照；具有与投标产品或服务相应的生产或经营范围； ③委托代理人身份证和法定代表人授权委托书（法定代表人亲自参加的提供法定代表人证明和身份证；自然人提供身份证明）； ④提供上一年度经审计的财务报告（包括“四表一注”）或开标日期前三个月内的银行资信证明（供应商为法人的，应提供基本开户银行出具的资信证明和银行出具的基本账户证明）； ⑤提供供应商开标日期前半年内不少于3个月的依法缴纳税收和社会保障资金的相关材料； ⑥具有履行合同所必需的设备和专业技术能力（提供承诺函原件）； ⑦参加政府采购活动前3年内在经营活动中没有重大违法记录的书面声明； ⑧投标保证金的缴纳凭证或转账记录原件； 本项目不接受任何联合体投标。 招标文件发售地点 :在“邯郸市公共资源交易中心”网站（网址:http://www.hdsggzyjyw.com/）进行“投标人登录”后，自行下载招标文件。网站技术服务电话:0310-8031630。 招标文件发售方式 :其它 招标文件售价 :0 获取文件开始时间:2019-12-10 获取文件结束时间:2019-12-17 时刻说明:工作日上午9点至11点，下午3点至5点 投标截止时间:2019-12-31 09:30 开标时间: 2019-12-31 09:30 开标地点: 邯郸市民服务中心四层开标1室（人民东路342号） 供货时间:依据合同约定 简要技术要求/采购项目的性质:本项目核算业务系统升级及系统改造两个部分的设计、开发、改造等工作，必须在邯郸市住房公积金管理中心现有系统平台上完成详见招标文件。 传真电话: 受理质疑电话:0310-2031776 备注:提示:获取招标文件前供应商应先期进行资格确认:已在我中心受理处通过资格确认（注册登记）的供应商可直接购买或下载招标文件。未经资格确认（注册登记）的供应商，请按照“河北省公共资源交易中心”首页“市场主体”中“注册指南”的要求办理相关手续，具体事宜可联系0310-2031948，联系人:赵先生。 资格确认后供应商需办理河北CA，用于网站登录、电子投标文件的签章、加密、解密，以及开标过程、合同签订等的电子签章。具体办理流程请参阅“邯郸市公共资源交易中心”网站（网址:http://www.hdsggzyjyw.com/）“通知公告”中《关于办理河北CA数字证书的通知》。 邯郸市CA密钥网上办理方式及现场服务网点办理地址详见www.hebca.com/ggzyhd.html,咨询电话:4007073355。投标方获取招标文件后应时刻关注河北省政府采购网和邯郸市公共资源交易中心网更正公告栏目关于本项目的更正信息，如有更正不再另行通知。因未关注更正信息造成的投标失误，后果自负。 本公告发布媒体:河北省政府采购网、邯郸市公共资源交易中心网 附件";
        str = str.replace((char) 160, (char) 32).replaceAll(p1, ":").replaceAll(p2,":");

        System.err.println(str);

        String[] s1 = str.split(" ");


        char[] chars = str.toCharArray();
/*
        String s = str.replaceAll(pattern, ":");

        Pattern r = Pattern.compile(pattern);
*/




        int j = 0;
        for (int i = 0; i<chars.length; i++) {
            if(chars[i] == 160){
                continue;
            }
            if (chars[i] == ':') {
                j = i;
                j--;
                while (true) {
                    char aChar1 = chars[j];
                    if (!(aChar1 == 32 || aChar1 == 160)) {
                        break;
                    }
                    char aChar = aChar1;
                }

            }


        }


    }


    private static double getSimilarity(String doc1, String doc2) {
        if (StringUtils.isBlank(doc1) || StringUtils.isBlank(doc2)) {
            return 0L;
        }
        Map<Character, int[]> algMap = new HashMap<>();
        for (int i = 0; i < doc1.length(); i++) {
            char d1 = doc1.charAt(i);
            int[] fq = algMap.get(d1);
            if (fq != null && fq.length == 2) {
                fq[0]++;
            } else {
                fq = new int[2];
                fq[0] = 1;
                fq[1] = 0;
                algMap.put(d1, fq);
            }
        }
        for (int i = 0; i < doc2.length(); i++) {
            char d2 = doc2.charAt(i);
            int[] fq = algMap.get(d2);
            if (fq != null && fq.length == 2) {
                fq[1]++;
            } else {
                fq = new int[2];
                fq[0] = 0;
                fq[1] = 1;
                algMap.put(d2, fq);
            }
        }
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denuminator = 0;
        for (Map.Entry entry : algMap.entrySet()) {
            int[] c = (int[]) entry.getValue();
            denuminator += c[0] * c[1];
            sqdoc1 += c[0] * c[0];
            sqdoc2 += c[1] * c[1];
        }
        double v = denuminator / Math.sqrt(sqdoc1 * sqdoc2);
        System.out.println(v);
        return v;
    }

}


