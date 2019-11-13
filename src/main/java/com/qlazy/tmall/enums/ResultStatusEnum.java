package com.qlazy.tmall.enums;

public enum ResultStatusEnum {

	success(0),fail(1);
	private int status;

	private ResultStatusEnum(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
}
