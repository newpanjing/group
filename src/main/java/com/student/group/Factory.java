package com.student.group;

import java.util.*;

/**
 * @auther: panjing
 * @date: 2018/8/9 17:30
 * @project group
 */
public class Factory {

    private Map<String, List<String>> datas;//按周N 分好组的数据

    public Factory(Map<String, List<String>> datas) {
        this.datas = datas;
    }

    /**
     * 获取一个指定的数，并且移除其他相关的
     *
     * @param num
     * @return
     */
    public List<String> getStudent(Integer num) {

        List<String> names = getNames(num);

        //移除数据
        for (String name : names) {
            for (int i = 0; i < 7; i++) {
                datas.remove(name + "-" + (i + 1));
            }
        }

        return names;

    }

    public Map<String, List<String>> getDatas() {
        return datas;
    }

    /**
     * 递归获取
     *
     * @param num
     * @return
     */
    private List<String> getNames(Integer num) {


        List<String> names = new ArrayList<String>();


        Set<String> keys = this.datas.keySet();
        for (String key : keys) {
            List<String> values = this.datas.get(key);
            if (values.indexOf(String.valueOf(num)) != -1) {
                String name = key.split("-")[0];
                if (names.indexOf(names) == -1) {
                    names.add(name);
                }
            }
        }


        return names;

    }
}
