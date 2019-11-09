package com.qlazy.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.PropertyValueDTO;
import com.qlazy.tmall.service.impl.ProductServiceImpl;
import com.qlazy.tmall.service.impl.PropertyValueServiceImpl;

@RestController
public class PropertyValueController {

	@Autowired
	PropertyValueServiceImpl propertyValueService;

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("products/{pid}/propertyValues")
	public List<PropertyValueDTO> list(@PathVariable("pid") int pid) {
		ProductDTO productDTO = productService.queryProductById(pid);

		propertyValueService.init(productDTO);
		List<PropertyValueDTO> propertyValueDTOs = propertyValueService.queryPropertyValueByPid(productDTO);

		return propertyValueDTOs;
	}

	@PutMapping("propertyValues")
	public PropertyValueDTO update(@RequestBody PropertyValueDTO propertyValueDTO) {
		propertyValueService.update(propertyValueDTO);
		return propertyValueDTO;
	}
}
