package com.qlazy.tmall.dto;

import com.qlazy.tmall.entity.product;

import lombok.Data;

@Data
public class ProductImgDTO {
	private Integer id;
	private Integer pid;
	private String type;
	private product product;
	
	public void change() {
		this.pid = this.product.getId();
	}
}
