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
        test();
  /*      List<Integer> days = new ArrayList<>();
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
        System.out.println(days);*/
    }

    public static void test() {
        List<Integer> oldList = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 6, 7));
        List<Integer> newList = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 6, 8));

        List<Integer> add = new ArrayList<>();
        List<Integer> del = new ArrayList<>();

        for (Integer temp : oldList) {
            if (!newList.contains(temp)) {
                del.add(temp);
            }
        }

        for (Integer temp : newList) {
            if (!oldList.contains(temp)) {
                add.add(temp);
            }
        }

        System.out.println(add); // 2 4 8
        System.out.println(del); // 3 5 7
    }

}