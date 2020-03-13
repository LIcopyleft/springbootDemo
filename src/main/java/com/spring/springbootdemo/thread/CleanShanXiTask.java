package com.spring.springbootdemo.thread;

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
				GovData content = new GovData();
				content.setTitle(data.getProjectname());
				content.setContent(data.getContent());
				content.setPubTime(data.getReceivetime());
				content.setUrl(data.getArtcleUrl());
				content.setUrlId(data.getUrlId());
				content.setRegion(data.getRegioncode());
				content.setStageShow("gonggao".equals(data.getCategoryFirst()) ? "交易信息" : "jiaoyi".equals(data.getCategoryFirst()) ? "成交结果公告" : null);

				dataContent.add(content);

			}

			LinkedBlockingQueue<GovData> queues = new LinkedBlockingQueue();
			for (GovData data : dataContent) {
				//if (stageShow.equals(data.getStageShow()) || stageShow.equals()) {
				if (data.getStageShow() != null) {
					queues.add(data);
				}
			}
			if (queues.size() < 1) {
				// logger.info(Thread.currentThread().getName() + "end====no " + stageShow + "=====beginIndex = " + beginIndex);
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

	//政府采购
	private static GovData cleanMethod_1(GovData data) throws InvocationTargetException, IllegalAccessException {

		Document parse = Jsoup.parse(data.getContent());
		Elements sitemap = parse.getElementsByClass("sitemap");

		String memu = "";
		if (sitemap != null && sitemap.size() > 0) {
			memu = sitemap.get(0).text();
		}

		Map map = new HashMap();
		//    Elements tables = parse.getElementsByTag("table");
		Elements tables = parse.getElementsByClass("lc-table");
		//     Element element = table;
		if (tables == null || tables.size() < 2) {
			return null;
		}
		Element table = tables.get(1);
		Elements trs = table.getElementsByTag("tr");
		for (Element tr : trs) {
			Elements ttr = tr.getElementsByTag("th");
			Elements td = tr.getElementsByTag("td");

			for (int i = 0; i < ttr.size(); i++) {
				map.put(ttr.get(i).text().replaceAll(":", ""), td.get(i).text());
			}

		}
		//   Set set = Contant.filedValueSet();

		data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
		data.setOther(JSON.toJSONString(map));
		data.setClasses(memu);
		data.setContent(null);

		// System.out.println(JSON.toJSONString(map));
		return data;
	}

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

		String memu = "";
		if (sitemap != null && sitemap.size() > 0) {
			memu = sitemap.get(0).text().replace("您的当前位置:","").trim();
		}

		Map map = new HashMap();
		//    Elements tables = parse.getElementsByTag("table");
		Elements tables = parse.getElementsByTag("p");

		if ("成交结果公告".equals(data.getStageShow())) {
			//TODO 判断表格类型
			Elements newsCon = parse.getElementsByClass("newsCon");
			List<String> tableList = new ArrayList<>();
			if (newsCon != null && newsCon.size() > 0) {
				Element element = newsCon.get(0);
				Elements ts = element.getElementsByTag("table");
				if (ts != null && ts.size() > 0) {
					Pattern pt = Pattern.compile(REG_TABLE);
					Matcher m = pt.matcher(element.html());
					//   List<String> tableList = new ArrayList<>();
					while (m.find()) {
						tableList.add(m.group());
					}
				}
			}
			logger.info(data.getUrl());
           /* if(data.getUrlId() == 51219){
                logger.info("");
            }*/
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

		for (String p : list) {
			p = p.replaceFirst(":", "#");
			String[] split = p.split("#");
			if (split.length == 2) {
				map.put(split[0], split[1]);
			}
		}
		//  Set set = Contant.filedValueSet();
		data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
		data.setOther(JSON.toJSONString(map));
		data.setClasses(memu.length() > 200 ? null : memu);
		data.setContent(null);
		data.setClassifyShow("成交结果公告");//政府采购  更正公告
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
