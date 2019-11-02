package com.qlazy.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public PaginationDTO<CategoryDTO> list(@RequestParam(value = "start", defaultValue = "5") int size,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		PaginationTempDTO dto = new PaginationTempDTO();
		dto.setPage(page);
		dto.setSize(size);
		List<CategoryDTO> queryAllCategory = categoryService.queryAllCategory();
		PaginationDTO<CategoryDTO> paginationDTO = categoryService.queryCategoryByPage(dto);
		return paginationDTO;
	}
}
