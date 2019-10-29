package com.qlazy.tmall.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.entity.categoryExample;
import com.qlazy.tmall.mapper.categoryMapper;
 
 
@Service
public class CategoryService {
	
	@Autowired
	categoryExample categoryExp;
	
	@Autowired
	categoryMapper categoryMap;
	
	public List<CategoryDTO> fillAllCategory(){
		
		categoryExp.setOrderByClause("id desc");
		List<category> categories = categoryMap.selectByExample(categoryExp);
		
		//将查询到的categories重新封装为CategoryDTO输出，
		List<CategoryDTO> categoryDTOs = categories.stream().map(category->{
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(category, categoryDTO);
			return categoryDTO;
		}).collect(Collectors.toList());
		
		return categoryDTOs;
	}
}
