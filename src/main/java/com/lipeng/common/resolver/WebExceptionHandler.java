package com.lipeng.common.resolver;

import com.lipeng.common.exception.BusinessException;
import com.lipeng.common.resp.ResultVo;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: lipeng
 * @Date: 2020/10/27 22:12
 */
public class WebExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		if (ex instanceof BusinessException) {
			BusinessException businessException = (BusinessException) ex;
			modelAndView.addObject("msg", ResultVo.fail(businessException.getMsg()));
			modelAndView.setViewName("businessError");
		} else if (ex instanceof SQLException) {
			SQLException sqlException = (SQLException) ex;
			modelAndView.addObject("msg", ResultVo.fail(sqlException.getMessage()));
			modelAndView.setViewName("sqlError");
		} else {
			modelAndView.addObject("msg", ResultVo.fail("error"));
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

}