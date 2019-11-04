package com.qlazy.tmall.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.service.impl.CategoryServiceImpl;

@RestController
public class CategoryController {

	@Autowired
	CategoryServiceImpl categoryService;

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

	// 删除数据
	@DeleteMapping("/categories/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request) {
		categoryService.delete(id);
		File imgFolder = new File(request.getServletContext().getRealPath("img/category"));
		File file = new File(imgFolder, id + ".jpg");
		file.delete();
		return null;
	}

	// 查询数据
	@GetMapping("/categories/{id}")
	public category queryCategoryById(@PathVariable("id") int id) {
		return categoryService.queryCategoryById(id);
	}

	// 更新数据
	@PutMapping("/categories/{id}")
	public void updataCategory(category category, MultipartFile image, HttpServletRequest request) throws IOException {
		categoryService.updata(category);
		//若不修改图片，则不保存
		if (null != image) {
			categoryService.saveOrUpdateImageFile(category, image, request);
		}
	}
}
