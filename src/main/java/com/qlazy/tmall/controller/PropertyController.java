package com.qlazy.tmall.controller;

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
import com.qlazy.tmall.dto.PropertyDTO;
import com.qlazy.tmall.entity.property;
import com.qlazy.tmall.service.impl.PropertyServiceImpl;

@RestController
public class PropertyController {
	@Autowired
	PropertyServiceImpl proService;

	@GetMapping("/categories/{cid}/properties")
	public PaginationDTO<PropertyDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "start", defaultValue = "1") int page, @PathVariable("cid") int cid) {
		PaginationTempDTO dto = new PaginationTempDTO();
		dto.setPage(page);
		dto.setSize(size);
		PaginationDTO<PropertyDTO> paginationDTO = proService.queryPropertyByPage(dto, cid);
		return paginationDTO;
	}

	@GetMapping("/properties/{id}")
	public PropertyDTO queryPropertyById(@PathVariable("id") int id) {
		return proService.queryPropertyById(id);
	}
	
	@PostMapping("/properties")
	public PropertyDTO add(@RequestBody PropertyDTO propertyDTO) {
		proService.add(propertyDTO);
		return propertyDTO;
	}
	
	@DeleteMapping("/properties/{id}")
	public String delete(@PathVariable("id")int id) {
		proService.delete(id);
		return null;
	}
	
	@PutMapping("/properties")
	public property updata(@RequestBody property property) {
		proService.update(property);
		return property;
	}
}
