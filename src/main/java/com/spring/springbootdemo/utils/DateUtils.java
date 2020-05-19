package com.spring.springbootdemo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * 
 * @author qi.sun
 * @time 2019年5月27日11:02:50
 */
public class DateUtils {

	public static final String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";

	public static final String POINT_YYYYMMDD = "yyyy.MM.dd";
	public static final String POINT_YYYYMM = "yyyy.MM";
	public static final String BACKSLASH_YYYYMMDD = "yyyy\\MM\\dd";
	public static final String BACKSLASH_YYYYMM = "yyyy\\MM";
	public static final String SLASH_YYYYMMDD = "yyyy/MM/dd";
	public static final String SLASH_YYYYMM = "yyyy/MM";

	public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMI = "yyyyMMddHHmm";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYY = "yyyy";

	public static final String YYYY年MM月DD日HH时MI分SS秒 = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String YYYY年MM月DD日HH时MI分 = "yyyy年MM月dd日HH时mm分";
	public static final String YYYY年MM月DD日HH_MI_SS = "yyyy年MM月dd日HH:mm:ss";
	public static final String YYYY年MM月DD日HH_MI = "yyyy年MM月dd日HH:mm";
	public static final String YYYY年MM月DD日 = "yyyy年MM月dd日";
	public static final String YYYY年MM月 = "yyyy年MM月";
	public static final String YYYY年 = "yyyy年";

	/**
	 * 获取时间的年份
	 */
	public static Integer getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取时间的月份
	 */
	public static Integer getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取时间的日期
	 */
	public static Integer getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);
		return day;
	}

	/**
	 * 格式化时间
	 */
	public static String parseDateToStr(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MI_SS);
		return formatter.format(date);
	}

	/**
	 * 格式化时间
	 */
	public static String parseDateToStr(Date date, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * 格式化时间
	 */
	public static String parseTimestampToStr(Timestamp date) {
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MI_SS);
		return formatter.format(date);
	}

	/**
	 * 格式化时间
	 */
	public static String parseTimestampToStr(Timestamp date, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * 格式化Date时间
	 */
	public static String parseDateToStr(Date time, String timeFromat,
			final Date defaultDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.format(time);
		} catch (Exception e) {
			if (defaultDate != null)
				return parseDateToStr(defaultDate, timeFromat);
			else
				return parseDateToStr(new Date(), timeFromat);
		}
	}

	/**
	 * 格式化Date时间
	 */
	public static String parseDateToStr(Date time, String timeFromat,
			final String defaultDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.format(time);
		} catch (Exception e) {
			return defaultDate;
		}
	}

	/**
	 * 格式化时间字符串
	 */
	public static Date parseStrToDate(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MI_SS);
			Date date = dateFormat.parse(time);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间字符串
	 */
	public static Date parseStrToDate(String time, String timeFromat) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			Date date = dateFormat.parse(time);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间字符串
	 */
	public static Date parseStrToDate(String strTime, String timeFromat,
			Date defaultDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.parse(strTime);
		} catch (Exception e) {
			return defaultDate;
		}
	}

	/**
	 * 格式化时间字符串
	 */
	public static Date parseStrToDate(String strTime, String timeFromat,
			String defaultDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.parse(strTime);
		} catch (Exception e) {
			if (defaultDate != null)
				return parseStrToDate(defaultDate, timeFromat);
			else
				return new Date();
		}
	}

	/**
	 * 获取当下年份指定前后数量的年份集合
	 * 
	 * @param before
	 *            当下年份前年数
	 * @param behind
	 *            当下年份后年数
	 */
	public static List<Integer> getYearListOfYears(int before, int behind) {
		if (before < 0 || behind < 0) {
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		Calendar c = null;
		c = Calendar.getInstance();
		c.setTime(new Date());
		int currYear = Calendar.getInstance().get(Calendar.YEAR);

		int startYear = currYear - before;
		int endYear = currYear + behind;
		for (int i = startYear; i < endYear; i++) {
			list.add(Integer.valueOf(i));
		}
		return list;
	}

	/**
	 * 解析两个日期之间的所有月份
	 * 
	 * @param beginDateStr
	 *            开始日期，至少精确到yyyy-MM
	 * @param endDateStr
	 *            结束日期，至少精确到yyyy-MM
	 */
	public static List<String> getMonthListOfDate(String beginDateStr,
			String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		// 返回的月份列表
		String sRet = "";

		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;

		GregorianCalendar beginGC = null;
		GregorianCalendar endGC = null;
		List<String> list = new ArrayList<String>();

		try {
			// 将字符串parse成日期
			beginDate = f.parse(beginDateStr);
			endDate = f.parse(endDateStr);

			// 设置日历
			beginGC = new GregorianCalendar();
			beginGC.setTime(beginDate);

			endGC = new GregorianCalendar();
			endGC.setTime(endDate);

			// 直到两个时间相同
			while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
				sRet = beginGC.get(Calendar.YEAR) + "-"
						+ (beginGC.get(Calendar.MONTH) + 1);
				list.add(sRet);
				// 以月为单位，增加时间
				beginGC.add(Calendar.MONTH, 1);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 某各时间所在月的总天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	
	
	/**
	 * 某各时间所在季度的总天数
	 * 
	 * @param String
	 *            字符串
	 * @return
	 */
	public static long getDaysOfFrequncy(String time) {
		String start = getFristDayByFrequncy(time);
		String end = getLastDayByFrequncy(time);
		return getDistanceDays(start, end)+1;
	}
	/**
	 * 某各时间所在月的总天数
	 * 
	 * @param String
	 *            字符串
	 * @return
	 */
	public static int getDaysOfMonth(String time) {
		return getDaysOfMonth(parseStrToDate(time, YYYYMMDD));
	}
	
	/**
	 * 某各时间所在旬的总天数
	 * 
	 * @param String
	 *            字符串
	 * @return
	 */
	public static int getDaysOfXun(String time) {
		if(Integer.parseInt(time.substring(6,8))<20){
			return 10;
		}else{
			return getDaysOfMonth(time)-20;
		}
	}
	

	/**
	 * 解析两个日期段之间的所有日期
	 * 
	 * @param beginDateStr
	 *            开始日期 ，至少精确到yyyy-MM-dd
	 * @param endDateStr
	 *            结束日期 ，至少精确到yyyy-MM-dd
	 */
	public static List<String> getDayListOfDate(String beginDateStr,
			String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;

		Calendar beginGC = null;
		Calendar endGC = null;
		List<String> list = new ArrayList<String>();

		try {
			// 将字符串parse成日期
			beginDate = f.parse(beginDateStr);
			endDate = f.parse(endDateStr);

			// 设置日历
			beginGC = Calendar.getInstance();
			beginGC.setTime(beginDate);

			endGC = Calendar.getInstance();
			endGC.setTime(endDate);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			// 直到两个时间相同
			while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {

				list.add(formatter.format(beginGC.getTime()));
				// 以日为单位，增加时间
				beginGC.add(Calendar.DAY_OF_MONTH, 1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当前日期是一年中第几周
	 */
	public static Integer getWeekthOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取某一年各星期的始终时间
	 */
	public static HashMap<Integer, String> getWeekTimeOfYear(int year) {
		HashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekthOfYear(c.getTime());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dayOfWeekStart = "";
		String dayOfWeekEnd = "";
		for (int i = 1; i <= count; i++) {
			dayOfWeekStart = formatter.format(getFirstDayOfWeek(year, i));
			dayOfWeekEnd = formatter.format(getLastDayOfWeek(year, i));
			map.put(Integer.valueOf(i), "第" + i + "周(从" + dayOfWeekStart + "至"
					+ dayOfWeekEnd + ")");
		}
		return map;

	}

	/**
	 * 获取某一年的总周数
	 */
	public static Integer getWeekCountOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekthOfYear(c.getTime());
		return count;
	}

	/**
	 * 获取指定日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 获取指定日期所在周的最后一天
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 获得某天所属周的第一天，周一
	 *
	 * @return
	 */
	public static String getWeekDayStartTime(String timeId) {
		Date nowDate = parseStrToDate(timeId, YYYYMMDD);
		return parseDateToStr(getFirstDayOfWeek(nowDate), YYYYMMDD);
	}

	/**
	 * 获得某天所属周的最后一天，周日
	 *
	 * @return
	 */
	public static String getWeekDayEndTime(String timeId) {
		if(timeId.length()<7){
			timeId = getLastDayByMonth(timeId);
		}
		Date nowDate = parseStrToDate(timeId, YYYYMMDD);
		return parseDateToStr(getLastDayOfWeek(nowDate), YYYYMMDD);
	}

	/**
	 * 获取某年某周的第一天
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 获取某年某周的最后一天
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 获取某年某月的第一天
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		month = month - 1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);

		int day = c.getActualMinimum(Calendar.DAY_OF_MONTH);

		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取某年某月的最后一天
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		month = month - 1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	/**
 		* 获取当前月第一天
 	*/
	public static Date getFristDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH);

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);

		int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前最后一天
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH);

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	/**
	 * 获取某个日期为星期几
	 */
	public static String getDayWeekOfDate1(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获得指定日期的星期几数
	 */
	public static Integer getDayWeekOfDate2(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);
		return weekDay;
	}

	/**
	 * 将指定日期的时分秒格式为零
	 */
	public static Date formatHhMmSsOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得指定时间加减参数后的日期(不计算则输入0)
	 * 
	 * @param date
	 *            指定日期
	 * @param year
	 *            年数，可正可负
	 * @param month
	 *            月数，可正可负
	 * @param day
	 *            天数，可正可负
	 * @param hour
	 *            小时数，可正可负
	 * @param minute
	 *            分钟数，可正可负
	 * @param second
	 *            秒数，可正可负
	 * @param millisecond
	 *            毫秒数，可正可负
	 * @return 计算后的日期
	 */
	public static Date addDate(Date date, int year, int month, int day,
			int hour, int minute, int second, int millisecond) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.YEAR, year);// 加减年数
		c.add(Calendar.MONTH, month);// 加减月数
		c.add(Calendar.DATE, day);// 加减天数
		c.add(Calendar.HOUR, hour);// 加减小时数
		c.add(Calendar.MINUTE, minute);// 加减分钟数
		c.add(Calendar.SECOND, second);// 加减秒
		c.add(Calendar.MILLISECOND, millisecond);// 加减毫秒数

		return c.getTime();
	}

	/**
	 * 获得指定时间加减参数后的日期(不计算则输入0)
	 * 
	 * @param date
	 *            指定日期
	 * @param millisecond
	 *            毫秒数，可正可负
	 * @return 计算后的日期
	 */
	public static Date addDate(Date date, int millisecond) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, millisecond);// 加减毫秒数
		return c.getTime();
	}

	/**
	 * 获得两个日期的时间戳之差
	 */
	public static Long getDistanceTimestamp(Date startDate, Date endDate) {
		long daysBetween = (endDate.getTime() - startDate.getTime() + 1000000)
				/ (3600 * 24 * 1000);
		return daysBetween;
	}

	/**
	 * 判断二个时间是否为同年同月
	 */
	public static Boolean compareIsSameMonth(Date date1, Date date2) {
		boolean flag = false;
		int year1 = getYear(date1);
		int year2 = getYear(date2);
		if (year1 == year2) {
			int month1 = getMonth(date1);
			int month2 = getMonth(date2);
			if (month1 == month2)
				flag = true;
		}
		return flag;
	}

	/**
	 * 获得两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTime(Date one, Date two) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {

			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTime(String str1, String str2) {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MI_SS);
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 两个时间之间相差距离多少天
	 */
	public static Long getDistanceDays(String str1, String str2) {
		DateFormat df = new SimpleDateFormat(YYYYMMDD);
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 */
	public static Date getDayBeginTime(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 */
	public static Date getDayEndTime(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	private static boolean regex(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 自动识别转换时间字符串
	 */
	public static Date strToDate(String strTime) {
		String yyyyMMmmRegex = "\\d{4}\\.(0[1-9]{1}|1[0-2]{1}|[1-9]{1})\\.([1-9]{1}|0[1-9]{1}|(1|2)\\d{1}|3(0|1))";
		//2011-09-09
		String regex = "\\d{4}-([1-9]{1}|0[1-9]{1}|1[0-2]{1})-([1-9]{1}|0[1-9]{1}|(1|2)\\d{1}|3(0|1))";

		if (strTime == null || strTime.isEmpty())
			return null;
		String format = null;
		//2019-09-01 12:12:12
		String regex1 = "\\d{4}-([1-9]{1}|0[1-9]{1}|1[0-2]{1})-([1-9]{1}|0[1-9]{1}|(1|2)\\d{1}|3(0|1)) ((0|1)\\d{1}|2[0-3]{1}):([0-5]{1}\\d{1}):([0-5]{1}\\d{1})";
		if (regex(
				strTime,
				regex1)) {
			format = YYYY_MM_DD_HH_MI_SS;
		} else if (regex(
				strTime,
				"\\d{4}-(0[1-9]{1}|1[0-2]{1})-(0[1-9]{1}|(1|2)\\d{1}|3(0|1)) ((0|1)\\d{1}|2[0-3]{1}):([0-5]{1}\\d{1})")) {
			format = YYYY_MM_DD_HH_MI;
		} else {
			if (regex(strTime,
					regex)) {
				format = YYYY_MM_DD;
			} else if (regex(strTime, "\\d{4}-(0[1-9]{1}|1[0-2]{1})")) {
				format = YYYY_MM;
			} else {

				if (regex(strTime,
						yyyyMMmmRegex)) {
					format = POINT_YYYYMMDD;
				} else if (regex(strTime, "\\d{4}\\.(0[1-9]{1}|1[0-2]{1})")) {
					format = POINT_YYYYMM;
				} else if (regex(strTime,
						"\\d{4}\\\\(0[1-9]{1}|1[0-2]{1})\\\\(0[1-9]{1}|(1|2)\\d{1}|3(0|1))")) {
					format = BACKSLASH_YYYYMMDD;
				} else if (regex(strTime, "\\d{4}\\\\(0[1-9]{1}|1[0-2]{1})")) {
					format = BACKSLASH_YYYYMM;
				} else if (regex(strTime,
						"\\d{4}/(0[1-9]{1}|1[0-2]{1})/(0[1-9]{1}|(1|2)\\d{1}|3(0|1))")) {
					format = SLASH_YYYYMMDD;
				} else if (regex(strTime, "\\d{4}/(0[1-9]{1}|1[0-2]{1})")) {
					format = SLASH_YYYYMM;
				} else if (regex(
						strTime,
						"\\d{4}(0[1-9]{1}|1[0-2]{1})(0[1-9]{1}|(1|2)\\d{1}|3(0|1))((0|1)\\d{1}|2[0-3]{1})([0-5]{1}\\d{1})([0-5]{1}\\d{1})")) {
					format = YYYYMMDDHHMISS;
				} else if (regex(
						strTime,
						"\\d{4}(0[1-9]{1}|1[0-2]{1})(0[1-9]{1}|(1|2)\\d{1}|3(0|1))((0|1)\\d{1}|2[0-3]{1})([0-5]{1}\\d{1})")) {
					format = YYYYMMDDHHMI;
				} else if (regex(strTime,
						"\\d{4}(0[1-9]{1}|1[0-2]{1})(0[1-9]{1}|(1|2)\\d{1}|3(0|1))")) {
					format = YYYYMMDD;
				} else if (regex(strTime, "\\d{4}(0[1-9]{1}|1[0-2]{1})")) {
					format = YYYYMM;
				} else if (regex(
						strTime,
						"\\d{4}年(0[1-9]{1}|1[0-2]{1})月(0[1-9]{1}|(1|2)\\d{1}|3(0|1))日((0|1)\\d{1}|2[0-3]{1})时([0-5]{1}\\d{1})分([0-5]{1}\\d{1})秒")) {
					format = YYYY年MM月DD日HH时MI分SS秒;
				} else if (regex(
						strTime,
						"\\d{4}年(0[1-9]{1}|1[0-2]{1})月(0[1-9]{1}|(1|2)\\d{1}|3(0|1))日((0|1)\\d{1}|2[0-3]{1})时([0-5]{1}\\d{1})分")) {
					format = YYYY年MM月DD日HH时MI分;
				} else if (regex(
						strTime,
						"\\d{4}年(0[1-9]{1}|1[0-2]{1})月(0[1-9]{1}|(1|2)\\d{1}|3(0|1))日 ((0|1)\\d{1}|2[0-3]{1}):([0-5]{1}\\d{1}):([0-5]{1}\\d{1})")) {
					format = YYYY年MM月DD日HH_MI_SS;
				} else if (regex(
						strTime,
						"\\d{4}年(0[1-9]{1}|1[0-2]{1})月(0[1-9]{1}|(1|2)\\d{1}|3(0|1))日 ((0|1)\\d{1}|2[0-3]{1}):([0-5]{1}\\d{1})")) {
					format = YYYY年MM月DD日HH_MI;
				} else if (regex(strTime,
						"\\d{4}年(0[1-9]{1}|1[0-2]{1})月(0[1-9]{1}|(1|2)\\d{1}|3(0|1))日")) {
					format = YYYY年MM月DD日;
				} else if (regex(strTime, "\\d{4}年(0[1-9]{1}|1[0-2]{1})月")) {
					format = YYYY年MM月;
				} else if (regex(strTime, "\\d{4}年(0[1-9]{1}|1[0-2]{1})月")) {
					format = YYYY年MM月;
				} else if (regex(strTime, "\\d{4}年")) {
					format = YYYY年;
				} else if (regex(strTime, "\\d{4}")) {
					format = YYYY;
				}
			}
		}
		if (format == null)
			return null;
		Date date = parseStrToDate(strTime, format);
		return date;
	}

	/**
	 * 验证字符串是否为日期
	 */
	public static boolean validateIsDate(String strTime) {
		Date date = strToDate(strTime);
		return date != null;
	}

	public static boolean hasDate(String strTime) {
		boolean has = validateIsDate(strTime);
		if (!has) {
			Pattern pattern = Pattern.compile("[0-9]{4}年.+季度");
			Matcher matcher = pattern.matcher(strTime);
			if (matcher.find()) {
				has = true;
			}
		}
		return has;
	}

	/**
	 * 获取月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayByMonth(String date) {
		if (date.contains("-")) {
			date = date.replaceAll("-", "");
		} else if (date.length() < 6 || date.length() > 10) {
			System.out.println("日期错误！");
		}
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		Calendar cal = Calendar.getInstance();
		//清空cal缓存，避免2月份取到的值不正确
        cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		//获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
//		cal.set(Calendar.DAY_OF_MONTH,
//				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}
	
	/**
	 * 获取季的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayByFrequncy(String date) {
		if (date.contains("-")) {
			date = date.replaceAll("-", "");
		} else if (date.length() < 6 || date.length() > 10) {
			System.out.println("日期错误！");
		}
		String lastDay = null;
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		if(month>=1 && month<=3){
			lastDay = year +"0331";
		}else if(month>=4 && month<=6){
			lastDay = year +"0630";
		}else if(month>=7 && month<=9){
			lastDay = year +"0930";
		}else if(month>=10 && month<=12){
			lastDay = year +"1231";
		}
		return lastDay;
	}
	
	/**
	 * 获取季的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFristDayByFrequncy(String date) {
		if (date.contains("-")) {
			date = date.replaceAll("-", "");
		} else if (date.length() < 6 || date.length() > 10) {
			System.out.println("日期错误！");
		}
		String lastDay = null;
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		if(month>=1 && month<=3){
			lastDay = year +"0101";
		}else if(month>=4 && month<=6){
			lastDay = year +"0401";
		}else if(month>=7 && month<=9){
			lastDay = year +"0701";
		}else if(month>=10 && month<=12){
			lastDay = year +"1001";
		}
		return lastDay;
	}
	
	/**
	 * 获取年的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayByYear(String date) {
		if (date.contains("-")) {
			date = date.replaceAll("-", "");
		} else if (date.length() < 6 || date.length() > 10) {
//			System.out.println("日期错误！");
		}
		String lastDay = null;
		int year = Integer.parseInt(date.substring(0, 4));
		lastDay = year +"1231";
		return lastDay;
	}
	
	/**
	 * 获取年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFristDayByYear(String date) {
		if (date.contains("-")) {
			date = date.replaceAll("-", "");
		} else if (date.length() < 6 || date.length() > 10) {
			System.out.println("日期错误！");
		}
		String lastDay = null;
		int year = Integer.parseInt(date.substring(0, 4));
		lastDay = year +"0101";
		return lastDay;
	}

	/**
	 * 根据给定日期判断上一旬的开始结束日期
	 * 
	 * @param nowDate
	 * @return
	 * @throws ParseException
	 */
	public static String[] getLastXun(String timeId) {
		if(timeId.length()<7){
			timeId = getLastDayByMonth(timeId);
		}
		Date nowDate = parseStrToDate(timeId, YYYYMMDD);
		String[] xunArr = new String[2];

		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

		int day = Integer.parseInt(dayFormat.format(nowDate));
		if (day <= 10) {
			xunArr[0] = monthFormat.format(nowDate) + "01";
			xunArr[1] = monthFormat.format(nowDate) + "10";
		} else if (day <= 20) {
			// 如果是下旬，查找中旬
			xunArr[0] = monthFormat.format(nowDate) + "11";
			xunArr[1] = monthFormat.format(nowDate) + "20";
		} else {
			// 如果是上旬，查找上个月的下旬
			// Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.MONTH, 1);
			xunArr[0] = monthFormat.format(nowDate) + "21";

			// calendar.setTime(format.parse((monthFormat.format(nowDate)+"01")));
			// calendar.add(Calendar.DATE, -1);
			xunArr[1] = getLastDayByMonth(timeId);
		}
		System.out.println(xunArr[0]);
		System.out.println(xunArr[01]);
		return xunArr;
	}
	
	/**
	 *  根据频率获取最后一天
	 * @param timeId
	 * @param frequency
	 * @return
	 */
	public static String getLastDayByFrequency(String timeId,String frequency){
		if("Y".equals(frequency)){
			return getLastDayByYear(timeId);
		}else if("Q".equals(frequency)){
			return getLastDayByFrequncy(timeId);
		}else if("M".equals(frequency)){
			return getLastDayByMonth(timeId);
		}else if("X".equals(frequency)){
			return getLastXun(timeId)[1];
		}else if("W".equals(frequency)){
			return getWeekDayEndTime(timeId);
		}else{
			if(timeId.length()<=4){
				return getLastDayByYear(timeId);
			}else if(timeId.length()<=6){
				return getLastDayByMonth(timeId);
			}else{
				return timeId;
			}
			
		}
		
	}
	
	/**
	 *  根据频率获取名字
	 * @param timeId
	 * @param frequency
	 * @return
	 */
	public static String getShowNameFrequency(String timeId,String frequency){
		String name = "";
		String year = timeId.substring(0,4);
		Integer month = 0;
		if(timeId.length()>=6){
			month = Integer.parseInt(timeId.substring(4,6));
		}
		Integer day = 0;
		if(timeId.length()>=8){
			day = Integer.parseInt(timeId.substring(6,8));
		}
		if("Y".equals(frequency)){
			name = year + "年";
		}else if("Q".equals(frequency)){
			name = year + "年";
			if(month >= 1 && month<=3){
				name += "第一季度";
			}else if(month >= 4 && month<=6){
				name += "第二季度";
			}else if(month >= 7 && month<=9){
				name += "第三季度";
			}else if(month >= 10 && month<=12){
				name += "第四季度";
			}
		}else if("M".equals(frequency)){
			name = year + "年" +month +"月";
		}else if("X".equals(frequency)){
			name = year + "年" +month +"月";
			if(day<=10){
				name+="上旬";
			}else if(day<=20){
				name+="中旬";
			}else {
				name+="下旬";
			}
		}else if("W".equals(frequency)){
			name = year + "年" +month +"月" + day+"日";
		}else{
			name = year + "年" +month +"月" + day+"日";
		}
		return name;
	}
	/**
	 * 根据频率增加时间
	 * @param time
	 * @param frequency
	 * @param num
	 * @return
	 */
	public static String addDateByFrequency(String time,String frequency,Integer num){
		time = time.replaceAll("-", "");
		Date date = null;
		if("Y".equals(frequency)){
			date = parseStrToDate(time.substring(0,4), YYYY);
			date = addDate(date,num,0,0,0,0,0,0);
			time= parseDateToStr(date,YYYY);
		}else if("Q".equals(frequency)){
			date = parseStrToDate(time.substring(0,6), YYYYMM);
			date = addDate(date,0,num*3,0,0,0,0,0);
			time= parseDateToStr(date,YYYYMM);
		}else if("M".equals(frequency)){
			date = parseStrToDate(time.substring(0,6), YYYYMM);
			date = addDate(date,0,num,0,0,0,0,0);
			time= parseDateToStr(date,YYYYMM);
		}else if("X".equals(frequency)){
			if(time.length()<7){
				time = time+"01";
			}
			date = parseStrToDate(time, YYYYMMDD);
			date = addDate(date,0,0,num*10,0,0,0,0);
			time= parseDateToStr(date,YYYYMMDD);
			time= getLastXun(time)[1];
		}else if("W".equals(frequency)){
			if(time.length()<7){
				time = time+"01";
			}
			date = parseStrToDate(time, YYYYMMDD);
			date = addDate(date,0,0,7*num,0,0,0,0);
			time= parseDateToStr(date,YYYYMMDD);
		}else{
			if(time.length()<7){
				time = time+"01";
			}
			date = parseStrToDate(time, YYYYMMDD);
			date = addDate(date,0,0,num,0,0,0,0);
			time= parseDateToStr(date,YYYYMMDD);
		}
		return time;
	} 
	
	/** 
	 * 去年同期的时间
	 * @param timeId
	 * @return
	 */
	public static String getLastYearTime(String timeId){
		Date date = parseStrToDate(timeId, YYYYMMDD);
		date = addDate(date,-1,0,0,0,0,0,0);
		return parseDateToStr(date,YYYYMMDD);
	}
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(getDaysOfXun("20191022"));
		System.out.println(getLastDayByMonth("201902"));
		// System.out.println(getWeekDayStartTime("20190909"));
		// System.out.println(getWeekDayEndTime("20190909"));
	}

}
