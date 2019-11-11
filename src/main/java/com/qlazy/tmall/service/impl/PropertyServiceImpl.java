package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.PropertyDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.entity.property;
import com.qlazy.tmall.entity.propertyExample;
import com.qlazy.tmall.mapper.categoryMapper;
import com.qlazy.tmall.mapper.propertyMapper;
import com.qlazy.tmall.service.IService;
import com.qlazy.tmall.util.PaginationUtil;

@Service
public class PropertyServiceImpl implements IService<PropertyDTO> {

	@Autowired
	propertyMapper propertyMap;

	@Autowired
	categoryMapper categoryMap;

	@Autowired
	PropertyValueServiceImpl propertyValueService;

	// 分页查询property
	public PaginationDTO<PropertyDTO> queryPropertyByPage(PaginationTempDTO dto, int cid) {
		PaginationUtil paginationUtil = new PaginationUtil();
		PaginationDTO<PropertyDTO> paginationDTO = new PaginationDTO<>();
		// 配置分页相应数据
		propertyExample propertyExp = new propertyExample();
		propertyExp.createCriteria().andCidEqualTo(cid);
		dto.setTotalCount((int) propertyMap.countByExample(propertyExp));
		paginationUtil.pagination(dto, paginationDTO);

//		分页查询
		propertyExample propertyExample = new propertyExample();
		propertyExample.createCriteria().andCidEqualTo(cid);
		propertyExample.setOrderByClause("id desc");
		List<property> properties = propertyMap.selectByExampleWithRowbounds(propertyExample,
				new RowBounds(paginationUtil.getPageStartData(), paginationUtil.getSize()));
//		封装到DTO
		List<PropertyDTO> propertyDTOs = properties.stream().map(property -> {
			PropertyDTO propertyDTO = new PropertyDTO();
			BeanUtils.copyProperties(property, propertyDTO);
			return propertyDTO;
		}).collect(Collectors.toList());

		paginationDTO.setData(propertyDTOs);

		return paginationDTO;
	}

	// 根据ID查询相应的property
	public PropertyDTO queryPropertyById(int id) {
		PropertyDTO propertyDTO = new PropertyDTO();
//		根据ID查询property
		property property = propertyMap.selectByPrimaryKey(id);
		BeanUtils.copyProperties(property, propertyDTO);
//		查询相应category
		category category = categoryMap.selectByPrimaryKey(property.getCid());

//		将category赋值给propertyDTO
		propertyDTO.setCategory(category);

		return propertyDTO;
	}

	// 根据ID查询相应的property
	public List<PropertyDTO> queryPropertyByCid(int cid) {
		propertyExample propertyExample = new propertyExample();

//		根据ID查询property
		propertyExample.createCriteria().andCidEqualTo(cid);
		List<property> properties = propertyMap.selectByExample(propertyExample);

		List<PropertyDTO> propertyDTOs = properties.stream().map(property -> {
			PropertyDTO propertyDTO = new PropertyDTO();
//			查询相应category
			category category = categoryMap.selectByPrimaryKey(property.getCid());
//			赋值给propertyDTO
			BeanUtils.copyProperties(property, propertyDTO);
			propertyDTO.setCategory(category);

			return propertyDTO;
		}).collect(Collectors.toList());

		return propertyDTOs;
	}

	// 增加一个property
	public void add(PropertyDTO propertyDTO) {
		property property = new property();
		BeanUtils.copyProperties(propertyDTO, property);
		property.setCid(propertyDTO.getCategory().getId());
		propertyMap.insertSelective(property);
	}

//	删除
	public void delete(int id) {
		propertyValueService.deleteByProperty(id);
		propertyMap.deleteByPrimaryKey(id);
	}

//	更新
	public void update(property property) {
		propertyMap.updateByPrimaryKeySelective(property);
	}

}
