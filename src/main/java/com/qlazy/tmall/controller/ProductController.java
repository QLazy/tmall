package com.qlazy.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.service.impl.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/categories/{cid}/products")
	public PaginationDTO<ProductDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "start", defaultValue = "1") int page, @PathVariable("cid") int cid) {
		
		PaginationTempDTO dto = new PaginationTempDTO();
		dto.setPage(page);
		dto.setSize(size);
		PaginationDTO<ProductDTO> ppaginationDTO = productService.queryProductByPage(dto, cid);

		return ppaginationDTO;
	}
	
	@GetMapping("/products/{id}")
	public ProductDTO queryProductById(@PathVariable("id")int id) {
		ProductDTO productDTO = productService.queryProductById(id);
		return productDTO;
	}
	
	@PostMapping("/products")
	public ProductDTO add(@RequestBody ProductDTO productDTO) {
		productService.add(productDTO);
		return productDTO;
	}
	
	@DeleteMapping("/products/{id}")
	public String delete(@PathVariable("id")int id) {
		productService.delete(id);
		return null;
	}
	
	@PutMapping("/products")
	public product updata(product product) {
		productService.updata(product);
		return product;
	}

}
