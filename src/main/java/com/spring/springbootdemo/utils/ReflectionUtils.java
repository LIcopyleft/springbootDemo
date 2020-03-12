package com.spring.springbootdemo.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/9
 */
public class ReflectionUtils {

    public static Object mapToField(Map<String, String> map, Object datas, Set set) throws InvocationTargetException, IllegalAccessException {

        // 合并
        // Set set = Contant.filedValueSet();
        //遍历 Map中key
        //proNo ： 项目标号，编号，
        // 合并后打印出所有内容
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                //   if (next.contains(key) /*|| keyIsContains(key, next)*/) {
                if (keyIsContains(key, next)) {
                    //   DataContentWithBLOBs datas = new DataContentWithBLOBs();
                    //   Field[] declaredFields = datas.getClass().getDeclaredFields();
                    BeanInfo beanInfo;
                    try {
                        beanInfo = Introspector.getBeanInfo(datas.getClass());
                    } catch (IntrospectionException e) {
                        return datas;
                    }
                    List<PropertyDescriptor> descriptors = Arrays.stream(beanInfo.getPropertyDescriptors()).filter(p -> {
                        String name = p.getName();
                        //过滤掉不需要修改的属性
                        return !"class".equals(name) && !"id".equals(name);
                    }).collect(Collectors.toList());

                    for (PropertyDescriptor descriptor : descriptors) {
                        //descriptor.getWriteMethod()方法对应set方法
                        if (descriptor.getName().equals(next.split(":")[0])) {
                            Method writeMethod = descriptor.getWriteMethod();
                            writeMethod.invoke(datas, entry.getValue());
                        }
                    }
                }
            }
        }

        return datas;
    }

    private static boolean keyIsContains(String key, String fieldStr) {
   /*     if(key.contains("采购人") && fieldStr.contains("采购人")){
            logger.info("");
        }
*/
        Pattern p_script = Pattern.compile("[^\u4e00-\u9fa5]", Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(key);
        key = m_script.replaceAll(""); // 过滤script标签

        //首个字符为中文 一 , 二等
        key = key.replaceFirst("[\\u4e00 \\u4e8c \\u4e09 \\u56db \\u4e94 \\u516d \\u4e03 \\u516b \\u4e5d \\u5341]{1,2}", "");

        //清楚中间空白
        key = StrUtil.cleanBlank(key);

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


}
