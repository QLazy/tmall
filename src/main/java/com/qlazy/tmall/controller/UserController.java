package com.qlazy.tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.UserDTO;
import com.qlazy.tmall.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/users")
	public PaginationDTO<UserDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "start", defaultValue = "1") int page) {

		PaginationTempDTO dto = new PaginationTempDTO();

		dto.setPage(page);
		dto.setSize(size);
//		分页查询user
		PaginationDTO<UserDTO> userDTOs = userService.queryUserByPage(dto);

		return userDTOs;
	}
}
