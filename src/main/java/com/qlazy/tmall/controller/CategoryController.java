package com.qlazy.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<CategoryDTO> list(){
		return categoryService.fillAllCategory();
	}
}
