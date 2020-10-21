package com.lipeng.common.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lipeng
 * @Date: 2020/10/21 19:37
 */
public class CalculateDemo {

	public static void main(String[] args) {
		List<Double> list = new ArrayList<>(
				Arrays.asList(new Double(3.3), new Double(3.3), new Double(3.3)));
		double sum = list.stream().mapToDouble(Double::doubleValue).sum();
		System.out.println(sum); // 9.899999999999999 错误计算方式

		Double aDouble = list.stream().map(x -> new BigDecimal(String.valueOf(x)))
				.reduce((a, b) -> a.add(b))
				.map(BigDecimal::doubleValue).orElse(0d);
		System.out.println(aDouble); // 9.9

		List<BigDecimal> decimals = new ArrayList<>(
				Arrays.asList(new BigDecimal("3.3"), new BigDecimal("3.3"), new BigDecimal("3.3")));
		double d = decimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
		System.out.println(d); // 9.9
	}

}