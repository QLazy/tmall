package com.qlazy.tmall.util;

import com.qlazy.tmall.enums.ResultStatusEnum;

import lombok.Data;

@Data
public class Result {

	private int status;
	private String message;
	private Object data;
	
	public Result(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static Result success() {
		return new Result(ResultStatusEnum.success.getStatus(),null,null);
	}
	
	public static Result success(Object data) {
		return new Result(ResultStatusEnum.success.getStatus(), "", data);
	}
	
	public static Result fail(String message) {
		return new Result(ResultStatusEnum.fail.getStatus(),message,null);
	}
	
	
	
}
