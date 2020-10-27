package com.lipeng.controller;

import com.lipeng.common.exception.BusinessException;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng
 * @Date: 2020/10/27 22:32
 */
@RestController
@RequestMapping("/error")
public class TestExceptionController {

	@RequestMapping("/test1")
	public void test1() {
		throw new BusinessException("400", "BusinessException");
	}

	@RequestMapping("/test2")
	public void test2() throws SQLException {
		throw new SQLException("SQLException");
	}


	@RequestMapping("/test3")
	public void test3() throws Exception {
		throw new Exception("Exception");
	}

}