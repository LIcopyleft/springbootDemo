package com.spring.springbootdemo.thread;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.ShanXiData;
import com.spring.springbootdemo.model.WinBisInfo;
import com.spring.springbootdemo.utils.HtmlUtils;
import com.spring.springbootdemo.utils.ReflectionUtils;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanShanXiTask implements Runnable {


	private static final String REG_TABLE = "<table.*?>[\\s\\S]*?<\\/table>";

	private static final String KEY_WORD = "区域坐标";
//	private static final String TABLE_NAME = "spider_2_ggzy_beijing_content";

	private static final Set<String> FLAG = new HashSet<>();
	// 1
	private static final int INSERT_MAX = 1000;
	private static final int KEY_NUM = 6;
	private static final String INSERT_TABLE_NAME = "spider_2_ggzy_shanxi_content_clean_temp";

	private int beginIndex;
	private int querySize;
	private String stageShow;
	private CountDownLatch latch;


	private static final Logger logger = LoggerFactory.getLogger(CleanShanXiTask.class);


	public CleanShanXiTask(int beginIndex, int querySize, String stageShow, CountDownLatch latch) {
		this.beginIndex = beginIndex;
		this.querySize = querySize;
		this.stageShow = stageShow;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
			List<GovData> list = new LinkedList<>();
			List<GovData> dataContent = new ArrayList<>();

			List<ShanXiData> shanXiData = mapper.selectAllShanXi(beginIndex, querySize);
			if (shanXiData == null || shanXiData.size() < 1) {
				logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
				return;
			}

			for(ShanXiData data : shanXiData){
				if(!"zfcg".equals(data.getCategorySecond())){
					continue;
				}
				GovData content = new GovData();
				content.setTitle(data.getProjectname());
				content.setContent(data.getContent());
				content.setPubTime(data.getReceivetime());
				content.setUrl(data.getArtcleUrl());
				content.setUrlId(data.getUrlId());
				content.setRegion(data.getRegioncode());
				content.setTitle(data.getProjectname());
				content.setStageShow("gonggao".equals(data.getCategoryFirst()) ? "交易信息公告" : "jiaoyi".equals(data.getCategoryFirst()) ? "成交结果公告" : null);
				content.setClassifyShow("gonggao".equals(data.getCategoryFirst()) ? "交易信息公告" : "jiaoyi".equals(data.getCategoryFirst()) ? "成交结果公告" : null);

				dataContent.add(content);

			}
			if(dataContent.size()<1){
				return;
			}

			LinkedBlockingQueue<GovData> queues = new LinkedBlockingQueue();
			for (GovData data : dataContent) {
				//if (stageShow.equals(data.getStageShow()) || stageShow.equals()) {
			//	if (data.getStageShow() != null) {
					queues.add(data);
			//	}
			}
			if (queues.size() < 1) {
				return;
			}
			int row = 0;
			while (queues.iterator().hasNext()) {
				GovData data = queues.poll();
				try {
					String content = data.getContent();
					if (StringUtils.isBlank(content)) {
						continue;
					}
					//    Document parse = Jsoup.parse(content);
					// GovData dcb = cleanMethod(data,parse);
					GovData dcb = cleanMethod_2(data);
					if (dcb == null) {
						continue;
					}
					list.add(dcb);
				} catch (Exception e) {
					logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}", e);
					continue;
				}

				if (list.size() == INSERT_MAX || queues.size() == 0) {
					//   System.err.println(Thread.currentThread().getName());
					if (list.size() < 1) {
						continue;
					}
					row += mapper.insertList_BJ(list,INSERT_TABLE_NAME);
					list.clear();
					continue;
				}

			}
			logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
		} finally {
			latch.countDown();
		}
	}
private static Set set = new HashSet();
	//政府采购>成交结果公告 清洗
	private static GovData cleanMethod_2(GovData data) throws InvocationTargetException, IllegalAccessException {
		// logger.info(data.getUrl());
		//   String regEx_az = "[\\s\\@\\#\\$\\%\\^\\&\\*\\{\\}\\[\\]\\;\\'\\“\\”\\。\\，\\+\\/\\<\\>\\?\\《\\》\\=]+";
		//    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		String content = data.getContent();
		Pattern p_script = Pattern.compile("：", Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(content);
		content = m_script.replaceAll(":");
		Document parse = Jsoup.parse(content);
		Elements sitemap = parse.getElementsByClass("position");
//获取导航目录信息
		String memu = "";
		if (sitemap != null && sitemap.size() > 0) {
			memu = StrUtil.cleanBlank(sitemap.get(0).text().replace("您的当前位置:",""));

		}
		Map map = new HashMap();
		Elements tables = parse.getElementsByTag("p");

		//判断解析类型
		if ("成交结果公告".equals(data.getStageShow())) {
			//TODO 判断表格类型
			List<String> tableList = HtmlUtils.getHtmlTableList(parse);
			//	logger.info(data.getUrl());
			//粗略检验表格
			for(String tab : tableList){

				Document ta = Jsoup.parse(tab);
				String tr = ta.getElementsByTag("tr").get(0).text();

		//		set.add(tr);
		//		tr.contains("中标")|| tr.contains("中标") || tr.contains("单位")

			}
		//	logger.info(JSON.toJSONString(set));
			List<WinBisInfo> winBisInfos = HtmlUtils.parseRowTable(tableList);
			data.setWinBisInfoStr(winBisInfos.size() > 0 ? JSON.toJSONString(winBisInfos) : null);
		}

		List<String> list = new LinkedList<>();
		boolean flag = false;
		String names = "";
		for (Element element : tables) {
			String text = element.text();
			if (flag) {
				names = names + "专家名单:" + text;
				list.add(names);
				flag = false;
				names = "";
				continue;
			}
			if (StringUtils.isNotBlank(text) && text.contains(":")) {

				if (text.split(":").length < 2 || text.split(":")[0].length() > 15) {
					continue;
				} else if (text.contains("名单") && text.split(":").length < 2) {
					flag = true;
				}
				list.add(text);
			}
		}

		for(int i = 0;i< list.size();i++){
			String p = list.get(i);
			p = p.replaceFirst(":", "#");
			String[] split = p.split("#");

			if (split.length == 2 && split[0].length()>1) {
				//如果key 是联系人 . 地址 联系电话等有歧义信息,index向上 最多2 关联
				String key = split[0];
				key = HtmlUtils.reviseKey(key);
				if(HtmlUtils.keyIsUnclear(key)){
					if(i >= 1){
						String key_up1 = list.get(i - 1);
						//	String key_up2 = list.get(i - 2);
						//优先认定index相隔为1的
						if(key_up1.contains("代理")){
							key = "代理"+key;
						}else{
							key = "采购单位"+key;
						}
					}
					list.set(i,key+":"+split[1]);
				}
				//去除key非中文,以及首个字符为 一,二等
				map.put(key, split[1]);
			}
		}

		//  Set set = Contant.filedValueSet();
		data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
		data.setOther(JSON.toJSONString(map));
		data.setClasses(memu.length() > 200 ? null : memu);
		data.setContent(null);
		data.setClassifyShow(data.getStageShow());//政府采购  更正公告
		// System.out.println(JSON.toJSONString(map));
		// TODO 时间 金额 等字段在这里处理
		String proName = data.getProName();
		if (StringUtils.isNotBlank(proName) && proName.length() > 200) {
			data.setProName(proName.substring(0, 200));
		}
		//对金额 日期等字段处理
		//   String budgetAmount = data.getBudgetAmount();
		//   logger.info("预算金额： "+ budgetAmount);
		//   System.err.println(data.getTenderingFilePrice() + "/" + data.getBudgetAmount() + "/" + data.getWinBidTotalAmount());
		//
		if(data.getWinBisInfoStr() == null && data.getWinBidBisName() != null){
			List<WinBisInfo> winBisInfos = new LinkedList<>();
			WinBisInfo info = new WinBisInfo();
			info.setWinBidBisName(data.getWinBidBisName());
			info.setWinBidBisAddr(data.getWinBidBisAddr());
			info.setCreditNo(null);

			winBisInfos.add(info);
			data.setWinBisInfoStr(JSON.toJSONString(winBisInfos));
		}

		return data;
	}




}
