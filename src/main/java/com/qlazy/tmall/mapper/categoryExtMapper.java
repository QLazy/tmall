package com.qlazy.tmall.mapper;

import java.util.List;

import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.category;

public interface categoryExtMapper {
	
    List<category> select();

}