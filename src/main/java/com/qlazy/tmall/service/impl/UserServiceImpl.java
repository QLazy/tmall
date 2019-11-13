package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.UserDTO;
import com.qlazy.tmall.entity.user;
import com.qlazy.tmall.entity.userExample;
import com.qlazy.tmall.mapper.userMapper;
import com.qlazy.tmall.service.IService;
import com.qlazy.tmall.util.PaginationUtil;

@Service
public class UserServiceImpl implements IService<UserDTO> {

	@Autowired
	userMapper userMap;

	public PaginationDTO<UserDTO> queryUserByPage(PaginationTempDTO dto) {

		PaginationUtil paginationUtil = new PaginationUtil();
		PaginationDTO<UserDTO> paginationDTO = new PaginationDTO<>();
		userExample userExample = new userExample();

//		设置user数据总量
		dto.setTotalCount((int) userMap.countByExample(userExample));

//		设置分页相关数据
		paginationUtil.pagination(dto, paginationDTO);

//		开始分页查询
		userExample.setOrderByClause("id desc");
		List<user> users = userMap.selectByExampleWithRowbounds(userExample,
				new RowBounds(paginationDTO.getPageStartData(), paginationDTO.getSize()));
		
//		将查询到的数据封装到DTO
		List<UserDTO> userDTOs = users.stream().map(user->{
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTO.setAnonymousName(userDTO.getAnonymousName());
			return userDTO;
		}).collect(Collectors.toList());
		
		paginationDTO.setData(userDTOs);
		
		return paginationDTO;
	}
	
	public UserDTO queryUserbyId(int id) {
		UserDTO userDTO = new UserDTO();
		
		user user = userMap.selectByPrimaryKey(id);
		
		BeanUtils.copyProperties(user, userDTO);
		
		return userDTO;
	}

}
