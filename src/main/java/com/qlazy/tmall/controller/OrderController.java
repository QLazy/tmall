package com.qlazy.tmall.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qlazy.tmall.dto.OrderDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.order;
import com.qlazy.tmall.enums.OrderStatusEnum;
import com.qlazy.tmall.service.impl.OrderItemServiceImpl;
import com.qlazy.tmall.service.impl.OrderServiceImpl;
import com.qlazy.tmall.util.Result;

@RestController
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;

	@Autowired
	OrderItemServiceImpl orderItemService;

	@GetMapping("/orders")
	public PaginationDTO<OrderDTO> list(@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "size", defaultValue = "1") int page) {
		PaginationTempDTO dto = new PaginationTempDTO();

		dto.setPage(page);
		dto.setSize(size);

		PaginationDTO<OrderDTO> orderDTOs = orderService.queryOrderByPage(dto);
		orderItemService.fill(orderDTOs.getData());
		orderService.removeOrderDTOByOrderItem(orderDTOs.getData());
		return orderDTOs;
	}

	@PutMapping("/deliveryOrder/{oid}")
	public Result deliveryOrder(@PathVariable("oid") int oid) {
		order order = new order();

		OrderDTO orderDTO = orderService.queryOrderById(oid);
		orderDTO.setDeliverydate(new Date());
		orderDTO.setStatus(OrderStatusEnum.waitConfirm.getStatus());
		BeanUtils.copyProperties(orderDTO, order);
		orderService.update(order);

		return Result.success();

	}

}
