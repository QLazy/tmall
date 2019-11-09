package com.qlazy.tmall.dto;

import lombok.Data;

@Data
public class PaginationTempDTO {
	private Integer page;
	private Integer size;
	private Integer totalCount;
}
