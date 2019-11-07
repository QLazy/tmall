package com.qlazy.tmall.enums;

public enum ProductImgTypeEnum {
	single("single"),detail("detail");
	
	private String type;

	private ProductImgTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static ProductImgTypeEnum getEnum(String type) {
		if(type.equals("single")) {
			return single;
		}else {
			return detail;
		}
	}

}
