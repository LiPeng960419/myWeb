package com.lipeng.utils;

/**
 * @Author: lipeng lipeng6@ybm100.com
 * @Date: 2020/10/20 10:19
 */
public class Ip2StringUtils {

    public static void main(String[] args) {
        Integer t = ip2Int("192.168.2.3");
        String ip = int2Ip(t);
    }

    public static int ip2Int(String ipString) {
        // 取 ip 的各段
        String[] ipSlices = ipString.split("\\.");
        int rs = 0;
        for (int i = 0; i < ipSlices.length; i++) {
            // 将 ip 的每一段解析为 int，并根据位置左移 8 位
            int intSlice = Integer.parseInt(ipSlices[i]) << 8 * i;
            // 求与
            System.out.println(intSlice);
            rs = rs | intSlice;
        }
        return rs;
    }

    public static String int2Ip(int ipInt) {
        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            // 每 8 位为一段，这里取当前要处理的最高位的位置
            int pos = i * 8;
            // 取当前处理的 ip 段的值
            int and = ipInt & (255 << pos);
            // 将当前 ip 段转换为 0 ~ 255 的数字，注意这里必须使用无符号右移
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);
    }

}
