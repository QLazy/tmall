package com.qlazy.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<CategoryDTO> list(){
		return categoryService.fillAllCategory();
	}
}
