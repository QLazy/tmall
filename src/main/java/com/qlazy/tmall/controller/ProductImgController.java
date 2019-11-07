package com.qlazy.tmall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.ProductImgDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.enums.ProductImgTypeEnum;
import com.qlazy.tmall.service.impl.ProductImgServiceImpl;
import com.qlazy.tmall.service.impl.ProductServiceImpl;

@RestController
public class ProductImgController {

	@Autowired
	ProductImgServiceImpl productImgService;

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/products/{pid}/productImgs")
	public List<ProductImgDTO> list(@RequestParam("type") String type, @PathVariable("pid") int pid) {
		ProductDTO productDTO = productService.queryProductById(pid);

//		根据类型查询出相应的图片
		List<ProductImgDTO> productImgDTOs = productImgService.queryProductImgByType(productDTO,
				ProductImgTypeEnum.getEnum(type));

		return productImgDTOs;
	}

	@PostMapping("/productImgs")
	public void add(@RequestParam("type") String type, @RequestParam("pid") int pid,
			@RequestParam("image") MultipartFile image, HttpServletRequest request) {
//		创建对象
		product product = new product();
		
		ProductDTO productDTO = productService.queryProductById(pid);
		
	}
}
