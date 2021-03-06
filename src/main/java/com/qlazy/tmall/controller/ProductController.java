package com.qlazy.tmall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.service.impl.ProductImgServiceImpl;
import com.qlazy.tmall.service.impl.ProductServiceImpl;

@RestController
public class ProductController {

	@Autowired
	ProductServiceImpl productService;

	@Autowired
	ProductImgServiceImpl productImgService;

	@GetMapping("/categories/{cid}/products")
	public PaginationDTO<ProductDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "start", defaultValue = "1") int page, @PathVariable("cid") int cid) {

		PaginationTempDTO dto = new PaginationTempDTO();
		dto.setPage(page);
		dto.setSize(size);
		PaginationDTO<ProductDTO> paginationDTO = productService.queryProductByPage(dto, cid);

		productImgService.setFirstProductImgs(paginationDTO.getData());
		return paginationDTO;
	}

	@GetMapping("/products/{id}")
	public ProductDTO queryProductById(@PathVariable("id") int id) {
		ProductDTO productDTO = productService.queryProductById(id);
		return productDTO;
	}

	@PostMapping("/products")
	public ProductDTO add(@RequestBody ProductDTO productDTO) {
		productService.add(productDTO);
		return productDTO;
	}

	@DeleteMapping("/products/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request) {

		productService.delete(id, request);
		return null;
	}

	@PutMapping("/products")
	public product updata(@RequestBody product product) {
		productService.update(product);
		return product;
	}

}
