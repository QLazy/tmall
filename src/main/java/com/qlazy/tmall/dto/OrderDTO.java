package com.qlazy.tmall.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
//	表字段
	private Integer id;
	private String ordercode;
	private String address;
	private String post;
	private String receiver;
	private String mobile;
	private String usermessage;
	private Date createdate;
	private Date paydate;
	private Date deliverydate;
	private Date confirmdate;
	private Integer uid;
	private String status;
	
//	新增字段
	private UserDTO userDTO;
	private List<OrderItemDTO> orderItemDTO;
//	总数量
	private Integer totalNumber;
//	总金额
	private float totalAmount;
	private String statusDesc;
	
	public String getStatusDesc() {
		
		return statusDesc;
	}
}
