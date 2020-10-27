package com.lipeng.common.exception;

import lombok.Data;

/**
 * @Author: lipeng
 * @Date: 2020/10/27 22:14
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6412952087574316954L;

	private String code;
	private String msg;

	BusinessException() {

	}

	public BusinessException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}