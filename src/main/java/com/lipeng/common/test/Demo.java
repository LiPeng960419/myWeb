package com.lipeng.common.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/9 8:54
 */
public class Demo {

    public static void main(String[] args) {
        days();
        subList();
        subString();
    }

    public static void days() {
        List<Integer> days = new ArrayList<>();
        int count = 0;
        for (int i = 1; i < 100; i++) {
            if ((i % 7) % 6 == 0 || (i % 7) % 7 == 0) {
                continue;
            }
            if (++count == 2) {
                days.add(i);
                count = 0;
            }
        }
        System.out.println(days);
    }

    public static void subList() {
        List<String> list = new ArrayList<>(Arrays.asList("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24".split(",")));
        int pageSize = 10;
        int curPage = 2;
        int start = (curPage - 1) * pageSize;
        int end = Math.min((start + pageSize), list.size());
        System.out.println(list.subList(start, end));
        System.out.println(list.subList(0, 1));
    }

    public static void subString() {
        String temp = "abcdefg";
        System.out.println(temp.substring(1)); //bcdefg
        System.out.println(temp.substring(1, 3)); // bc
        System.out.println(temp.subSequence(1, 3).toString()); //bc
        System.out.println(temp.substring(1, temp.length() - 2)); //bcde
    }

}