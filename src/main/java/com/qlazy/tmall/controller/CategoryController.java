package com.qlazy.tmall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public PaginationDTO<CategoryDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "start", defaultValue = "1") int page) {
		PaginationTempDTO dto = new PaginationTempDTO();
		dto.setPage(page);
		dto.setSize(size);
		PaginationDTO<CategoryDTO> paginationDTO = categoryService.queryCategoryByPage(dto);
		return paginationDTO;
	}

	@PostMapping("/categories")
	public category add(category target, MultipartFile image, HttpServletRequest request) throws IOException {
		categoryService.add(target);
		categoryService.saveOrUpdateImageFile(target, image, request);
		return target;
	}
}
