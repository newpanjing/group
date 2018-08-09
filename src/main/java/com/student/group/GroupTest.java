package com.student.group;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @auther: panjing
 * @date: 2018/8/9 09:39
 * @project group
 */
public class GroupTest {

    public static void main(String[] args) throws Exception {

        String str = readFile();

        String[] array = str.split("\n");

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (int i = 0; i < array.length; i++) {
            String line = array[i];
            if (line.replace(" ", "").length() == 0) {
                continue;
            }
            String[] s = line.split(",");
            Map<String, String> item = new LinkedHashMap<String, String>();
            item.put("id", s[0]);//id
            item.put("week", s[1]);//周
            item.put("wd", s[2]);//周N
            item.put("cid", s[3]);//第N节课
            item.put("name", s[4]);//名字
            item.put("sid", s[5]);//学号
            data.add(item);
            System.out.println(item);
        }

        analyze(data);
    }

    /**
     * 分析数据
     */
    private static void analyze(List<Map<String, String>> data) {

        /**
         *
         * a=  周1 ，1，3，4
         * b=  周1    4，5，6
         * c=  周1    2，4，6
         * d=  周1    1，2，4
         * e=  周1    7 没人要 丢出去，最后处理
         */


        //对人 按周分组
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //key= 学号+周N
        for (Map<String, String> item : data) {

            String cid = item.get("cid");
            String wd = item.get("wd");

            String name = item.get("name");
            String key = name + "-" + wd;

            List<String> list = null;
            if (map.containsKey(key)) {
                list = map.get(key);
            } else {
                list = new ArrayList<String>();
                map.put(key, list);
            }
            String value = wd + cid;
            //去重,有的人 一天 多节课重复
            if (!list.equals(value)) {
                list.add(wd + cid);
            }

            //取排课最多的一天，去和其他人

        }


        //取出所有编号
        Map<Integer, Integer> numbers = new LinkedHashMap<Integer, Integer>();

        for (String key : map.keySet()) {

            List<String> values = map.get(key);
            System.out.println(key + "=" + values);
            //计算课和周出现次数

            for (String n : values) {
                Integer num = Integer.parseInt(n);

                Integer val = 0;
                if (numbers.containsKey(num)) {
                    val = numbers.get(num);
                    val += 1;
                } else {
                    val += 1;
                }

                numbers.put(num, val);
            }
        }

        //对key 升序排序
        //对value 降序排序

        //冒泡开始...
        //map转list

        int[] keys = new int[numbers.size()];
        int[] values = new int[numbers.size()];
        int index = 0;
        for (Integer key : numbers.keySet()) {
            keys[index] = key;
            values[index] = numbers.get(key);
            index++;
        }
        System.out.println(Arrays.toString(keys));
        System.out.println(Arrays.toString(values));

        sort(keys, values, map);

    }

    //冒泡排序
    private static void sort(int[] keys, int[] values, Map<String, List<String>> map) {

        //对key 排序，value 要跟着变,升序

        for (int i = 0; i < keys.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < keys.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (keys[j] > keys[j + 1]) {
                    int temp = keys[j];
                    int temp2 = values[j];

                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];

                    keys[j + 1] = temp;
                    values[j + 1] = temp2;
                }
            }
        }
        System.out.println("keys排序后：" + Arrays.toString(keys));
        System.out.println("values排序后：" + Arrays.toString(values));

        //对values排序,升序， key 跟着变
        for (int i = 0; i < values.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < values.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (values[j] < values[j + 1]) {
                    int temp = values[j];
                    int temp2 = keys[j];

                    values[j] = values[j + 1];
                    keys[j] = keys[j + 1];

                    values[j + 1] = temp;
                    keys[j + 1] = temp2;
                }
            }
        }

        System.out.println("keys排序后：" + Arrays.toString(keys));
        System.out.println("values排序后：" + Arrays.toString(values));

        group(keys, values, map);
    }


    /**
     * 开始分组
     *
     * @param keys
     * @param values
     */
    private static void group(int[] keys, int[] values, Map<String, List<String>> map) {

        Factory factory = new Factory(map);

        //keys 是排名，按照排名来取人
        List<String> students = new ArrayList<String>();

        for (int i = 0; i < keys.length; i++) {

            int num = keys[i];
            List<String> names = factory.getStudent(num);
            System.out.println("编号：" + num + " 人数：" + names);
            students.addAll(names);
        }

        //按照周一到周日 分组,一天5个人

        int page = (students.size() + 5 - 1) / 5;

        System.out.println("总共天数：" + page);

        //开始排

        int index = 0;
        for (int i = 1; i <= page; i++) {
            System.out.println("周"+i+"的人为：");
            for (int k = 0; k < 5; k++) {
                if (index == students.size()) {
                    break;
                }
                System.out.print(students.get(index)+" ");
                index++;
            }
            System.out.println();
        }

    }

    private static String readFile() throws IOException {
        InputStream in = GroupTest.class.getResourceAsStream("/abc.txt");

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        return sb.toString();
    }
}
