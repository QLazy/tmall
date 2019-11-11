package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.PropertyDTO;
import com.qlazy.tmall.dto.PropertyValueDTO;
import com.qlazy.tmall.entity.propertyvalue;
import com.qlazy.tmall.entity.propertyvalueExample;
import com.qlazy.tmall.mapper.propertyvalueMapper;
import com.qlazy.tmall.service.IService;

@Service
public class PropertyValueServiceImpl implements IService<PropertyValueDTO> {

	@Autowired
	propertyvalueMapper propertyValueMap;

	@Autowired
	PropertyServiceImpl propertyService;

//	初始化propertyValue
	public void init(ProductDTO productDTO) {
		List<PropertyDTO> propertyDTOs = propertyService.queryPropertyByCid(productDTO.getCategory().getId());

		for (PropertyDTO propertyDTO : propertyDTOs) {
			PropertyValueDTO valueDTO = queryPValueByPidAndPTid(productDTO, propertyDTO);
//			若product与property之间未链接，则创建链接
			if (null == valueDTO) {
				propertyvalue propertyvalue = new propertyvalue();
				propertyvalue.setPid(productDTO.getId());
				propertyvalue.setPtid(propertyDTO.getId());
				propertyValueMap.insertSelective(propertyvalue);
			}
		}
	}
	
//	根据product删除相应的propertyValue
	public void deleteByProduct(int pid) {
		propertyvalueExample example = new propertyvalueExample();
		
		example.createCriteria().andPidEqualTo(pid);
		propertyValueMap.deleteByExample(example);
	}
	
//	根据property删除相应的propertyValue
	public void deleteByProperty(int ptid) {
		propertyvalueExample example = new propertyvalueExample();
		
		example.createCriteria().andPtidEqualTo(ptid);
		propertyValueMap.deleteByExample(example);
	}
	

//	更新数据
	public void update(PropertyValueDTO propertyValueDTO) {
		propertyvalue propertyValue = new propertyvalue();
		BeanUtils.copyProperties(propertyValueDTO, propertyValue);
		propertyValueMap.updateByPrimaryKey(propertyValue);
	}

//	获取数据，通过pid与ptid
	public PropertyValueDTO queryPValueByPidAndPTid(ProductDTO productDTO, PropertyDTO propertyDTO) {
		propertyvalueExample example = new propertyvalueExample();

		example.createCriteria().andPidEqualTo(productDTO.getId()).andPtidEqualTo(propertyDTO.getId());
		List<propertyvalue> propertyValues = propertyValueMap.selectByExample(example);

		List<PropertyValueDTO> propertyValueDTOs = propertyValues.stream().map(propertyValue -> {
			PropertyValueDTO valueDTO = new PropertyValueDTO();

			BeanUtils.copyProperties(propertyValue, valueDTO);
			valueDTO.setPropertyDTO(propertyDTO);
			valueDTO.setProductDTO(productDTO);

			return valueDTO;
		}).collect(Collectors.toList());
		
		if(propertyValueDTOs.isEmpty()) {
			return null;
		}else {
			return propertyValueDTOs.get(0);
		}
	}

//	获取数据，根据pid
	public List<PropertyValueDTO> queryPropertyValueByPid(ProductDTO productDTO) {
		propertyvalueExample example = new propertyvalueExample();

//		根据pid查询想用propertyValue
		example.createCriteria().andPidEqualTo(productDTO.getId());
		example.setOrderByClause("id desc");
		List<propertyvalue> propertyValues = propertyValueMap.selectByExample(example);

//		将propertyValue赋值到propertyValueDTO
		List<PropertyValueDTO> propertyValueDTOs = propertyValues.stream().map(propertyValue -> {
			PropertyValueDTO valueDTO = new PropertyValueDTO();
			PropertyDTO propertyDTO = propertyService.queryPropertyById(propertyValue.getPtid());

			BeanUtils.copyProperties(propertyValue, valueDTO);
			valueDTO.setProductDTO(productDTO);
			valueDTO.setPropertyDTO(propertyDTO);

			return valueDTO;
		}).collect(Collectors.toList());

		return propertyValueDTOs;
	}
}
