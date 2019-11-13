package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.OrderDTO;
import com.qlazy.tmall.dto.OrderItemDTO;
import com.qlazy.tmall.entity.orderitem;
import com.qlazy.tmall.entity.orderitemExample;
import com.qlazy.tmall.mapper.orderitemMapper;
import com.qlazy.tmall.service.IService;

@Service
public class OrderItemServiceImpl implements IService<OrderItemDTO> {

	@Autowired
	orderitemMapper orderItemMap;

	@Autowired
	ProductServiceImpl productService;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	ProductImgServiceImpl productImgService;

//	填充多个orderDTO
	public void fill(List<OrderDTO> orderDTOs) {
		for(OrderDTO orderDTO:orderDTOs) {
			fill(orderDTO);
		}
	}
//	填充单个orderDTO
	public void fill(OrderDTO orderDTO) {
		List<OrderItemDTO> orderItemDTOs = queryOrderItemByOrder(orderDTO);

		float totalAmount = 0;
		int totalNumber = 0;

		for (OrderItemDTO itemDTO : orderItemDTOs) {
			totalAmount += itemDTO.getNumber() * itemDTO.getProductDTO().getPromoteprice();
			totalNumber += itemDTO.getNumber();
			productImgService.setFirstProductImg(itemDTO.getProductDTO());
		}

		orderDTO.setTotalAmount(totalAmount);
		orderDTO.setTotalNumber(totalNumber);
		orderDTO.setOrderItemDTO(orderItemDTOs);
	}
//	根据order查询相应的orderItem
	public List<OrderItemDTO> queryOrderItemByOrder(OrderDTO orderDTO) {
		orderitemExample example = new orderitemExample();

		example.createCriteria().andOidEqualTo(orderDTO.getId());
		example.setOrderByClause("id desc");
		List<orderitem> orderItems = orderItemMap.selectByExample(example);

		List<OrderItemDTO> orderItemDTOs = orderItems.stream().map(orderItem -> {
			OrderItemDTO itemDTO = new OrderItemDTO();

			BeanUtils.copyProperties(orderItem, itemDTO);
			itemDTO.setOrderDTO(orderDTO);
			itemDTO.setProductDTO(productService.queryProductById(itemDTO.getPid()));
			itemDTO.setUserDTO(userService.queryUserbyId(itemDTO.getUid()));

			return itemDTO;
		}).collect(Collectors.toList());

		return orderItemDTOs;
	}
}
