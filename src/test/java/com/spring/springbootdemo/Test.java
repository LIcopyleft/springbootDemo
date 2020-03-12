package com.spring.springbootdemo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.FileUtils;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Test {


    public static void main(String args[]) {

        String blank = StrUtil.cleanBlank("中 标 金 额");
     //  "中 标 金 额"
        getFiledKey();


    }

    private static List<Element> getThree(Elements list) {
        //获取所有子节点
        List<Element> lists = new ArrayList<>();
        for (Element dept : list) {
            if (dept.children().size() == 0) {
                lists.add(dept);
            } else {
                getThree(dept.children());
            }

        }

        return lists;
    }


    private static void getFiledKey() {

        String readFile = FileUtils.readFile("key.txt");
        Set set = new LinkedHashSet<>();
        Set set2 = new LinkedHashSet<>();
        String[] split = readFile.split("\\|");
        Pattern p_script = Pattern.compile("[^\u4e00-\u9fa5]", Pattern.CASE_INSENSITIVE);
        String[] keyWords = "代码/地址/项目/编号/电话/名单/姓名/名称/成交/成员/时间/金额/单位/机构/中标/日期/供应商/联系人/单位/招标人/代理".split("/");

        for (String str : split) {
            Matcher matcher = p_script.matcher(str);
            str = matcher.replaceAll("");
            if (str.length() < 10 && str.length() > 1) {
                set.add(str);
                for (String keyword : keyWords) {
                    if (str.contains(keyword)) {
                        set2.add(str);
                        break;
                    }
                }


            }
        }

        System.err.println(JSON.toJSONString(set));
        System.err.println(JSON.toJSONString("========="));
        System.err.println(JSON.toJSONString(set2));

}

    private static List<Element> getChildTree(Elements list) {
        List<Element> childTree = new ArrayList<>();
        for (Element dept : list) {
            if (dept.children().size() == 0) {
                childTree.add(dept);
            }
        }
        return childTree;
    }


    public static String test() throws InvocationTargetException, IllegalAccessException {

        DataContentWithBLOBs data = new DataContentWithBLOBs();

        Class<? extends DataContentWithBLOBs> aClass = data.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(data.getClass());
        } catch (IntrospectionException e) {

        }


        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        List<PropertyDescriptor> descriptors = Arrays.stream(beanInfo.getPropertyDescriptors()).filter(p -> {
            String name = p.getName();

            name.equals("");
            //过滤掉不需要修改的属性
            return !"class".equals(name) && !"id".equals(name);
        }).collect(Collectors.toList());

        for (PropertyDescriptor descriptor : descriptors) {
            //descriptor.getWriteMethod()方法对应set方法
            Method writeMethod = descriptor.getWriteMethod();
            //    writeMethod.invoke(data,"zhangsan");
            Method readMethod = descriptor.getReadMethod();
            System.out.println(descriptor.getName());
            try {
                Object o = readMethod.invoke(data);
                System.out.println(o);
            } catch (IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }

        for (Field field : declaredFields) {
            field.setAccessible(true);
            field.set(null, null);
        }

        //proName :[项目名称,名称,采购项目]

        //    Field name = clazz2.getDeclaredField("name");
        // Field name = clazz2.getField("name");只能获取共有属性 故此方法会抛出NoSuchFieldException异常,所以选用getDeclaredField();
        //    name.setAccessible(true); //设置为true才能获取私有属性
        //    Object o2 = name.get(obj);
        return null;
    }

}
