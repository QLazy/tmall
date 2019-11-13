package com.qlazy.tmall.enums;

public enum OrderStatusEnum {

	waitPay("待付款"),waitDelivery("待发货"),waitConfirm("待收货"),waitReview("待评论"),finish("完成"),delete("删除");
	private String status;
	
	private OrderStatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
