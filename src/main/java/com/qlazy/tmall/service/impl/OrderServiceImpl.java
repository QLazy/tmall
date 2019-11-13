package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.OrderDTO;
import com.qlazy.tmall.dto.OrderItemDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.order;
import com.qlazy.tmall.entity.orderExample;
import com.qlazy.tmall.mapper.orderMapper;
import com.qlazy.tmall.service.IService;
import com.qlazy.tmall.util.PaginationUtil;

@Service
public class OrderServiceImpl implements IService<OrderDTO> {

	@Autowired
	orderMapper orderMap;

	@Autowired
	UserServiceImpl userService;

//	分页查询order
	public PaginationDTO<OrderDTO> queryOrderByPage(PaginationTempDTO dto) {
		PaginationDTO<OrderDTO> paginationDTO = new PaginationDTO<>();
		PaginationUtil util = new PaginationUtil();

		orderExample example = new orderExample();
		dto.setTotalCount((int) orderMap.countByExample(example));
//		创建分页所需的数据
		util.pagination(dto, paginationDTO);

//		根据id倒序分页查询order
		example.setOrderByClause("id desc");
		List<order> orders = orderMap.selectByExampleWithRowbounds(example,
				new RowBounds(paginationDTO.getPageStartData(), paginationDTO.getSize()));

		List<OrderDTO> orderDTOs = orders.stream().map(order -> {
			OrderDTO orderDTO = new OrderDTO();

			BeanUtils.copyProperties(order, orderDTO);
			orderDTO.setUserDTO(userService.queryUserbyId(orderDTO.getUid()));

			return orderDTO;
		}).collect(Collectors.toList());

		paginationDTO.setData(orderDTOs);

		return paginationDTO;

	}

//	获取订单，通过id
	public OrderDTO queryOrderById(int id) {
		OrderDTO orderDTO = new OrderDTO();

		order order = orderMap.selectByPrimaryKey(id);
		BeanUtils.copyProperties(order, orderDTO);
		orderDTO.setUserDTO(userService.queryUserbyId(orderDTO.getUid()));

		return orderDTO;
	}

//	更新
	public void update(order order) {
		orderMap.updateByPrimaryKeySelective(order);
	}

//	批量移除
	public void removeOrderDTOByOrderItem(List<OrderDTO> orderDTOs) {
		for (OrderDTO orderDTO : orderDTOs) {
			removeOrderDTOByOrderItem(orderDTO);
		}
	}

//	因为转换成json会报错，防止其进入递归死循环， @JsonIgnoreProperties 注解会与redis有bug
	public void removeOrderDTOByOrderItem(OrderDTO orderDTO) {
		List<OrderItemDTO> orderItemDTOs = orderDTO.getOrderItemDTO();

		for (OrderItemDTO itemDTO : orderItemDTOs) {
			itemDTO.setOrderDTO(null);
		}
	}
}
