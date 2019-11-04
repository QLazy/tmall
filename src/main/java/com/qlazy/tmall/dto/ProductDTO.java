package com.qlazy.tmall.dto;

import java.util.Date;

import com.qlazy.tmall.entity.category;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer id;
    private String name;
    private String subtitle;
    private Float originalprice;
    private Float promoteprice;
    private Integer stock;
    private Integer cid;
    private Date createdate;
    private category category;
    
    public void change() {
    	cid = category.getId();
    }
}
