package com.qlazy.tmall.dto;

import lombok.Data;

@Data
public class PropertyValueDTO {
	private Integer id;
//	外键，链接到product
    private Integer pid;	
//  外键，链接到property
    private Integer ptid;
    private String value;
	private PropertyDTO propertyDTO;
	private ProductDTO productDTO;
}
