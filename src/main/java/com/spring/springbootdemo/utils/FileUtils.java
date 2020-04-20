package com.spring.springbootdemo.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 文件读写工具类
 */

public class FileUtils {

    /**
     * 读取文件
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("|");

            }
            return stringBuilder.toString();
        } catch (IOException e) {

        }
        return null;
    }

    public static List<String> readFileToList(String filePath) {
      //  StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new LinkedList<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
               /* stringBuilder.append(line);
                stringBuilder.append("|");*/
                list.add(line);
            }
            return list;
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * 创建新文件写入
     *
     * @param filePath 文件路径
     * @param contents 要写入的内容
     * @return 是否写入成功
     */
    public static boolean writeNewFile(String filePath, List<String> contents) {
        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(writer)) {
            for (String content : contents) {
                bw.write(content);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            //  log.error("writeNewFile：{}", e);
        }
        return false;
    }

    /**
     * 没有文件就创建文件写入，有就追加到文件的后面写入
     *
     * @param filePath 文件路径
     * @param contents 要写入的内容
     * @return 是否写入成功
     */
    public static boolean writeAppendFile(String filePath, Set<String> contents) {
        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            for (String content : contents) {
                bw.append(content);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            //    log.error("writeAppendFile：{}", e);
        }
        return false;
    }

    public static boolean writeAppendFile(String filePath, String contents) {
        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.append(contents);
            bw.newLine();

            return true;
        } catch (IOException e) {
            //    log.error("writeAppendFile：{}", e);
        }
        return false;
    }
}

