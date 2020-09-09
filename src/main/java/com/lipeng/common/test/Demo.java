package com.lipeng.common.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/9 8:54
 */
public class Demo {

    public static void main(String[] args) {
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

}