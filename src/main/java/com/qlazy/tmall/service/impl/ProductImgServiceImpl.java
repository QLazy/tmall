package com.qlazy.tmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.ProductImgDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.entity.productImg;
import com.qlazy.tmall.entity.productImgExample;
import com.qlazy.tmall.enums.ProductImgTypeEnum;
import com.qlazy.tmall.mapper.productImgMapper;
import com.qlazy.tmall.mapper.productMapper;
import com.qlazy.tmall.service.IService;

@Service
public class ProductImgServiceImpl implements IService<ProductImgDTO> {
	@Autowired
	productImgMapper productImgMap;

	@Autowired
	productMapper productMap;

//	设置产品图片
	public void setFirstProductImg(ProductDTO productDTO) {

		List<ProductImgDTO> imgDTOs = queryProductImgByType(productDTO, ProductImgTypeEnum.single);
		if (!imgDTOs.isEmpty()) {
			productDTO.setFirstProductImg(imgDTOs.get(0));
		} else {
			productDTO.setFirstProductImg(new ProductImgDTO());
		}
	}

//	批量设置产品图片
	public void setFirstProductImgs(List<ProductDTO> productDTOs) {
		for (ProductDTO productDTO : productDTOs) {
			setFirstProductImg(productDTO);
		}
	}

//	查找相应图片，根据类型
	public List<ProductImgDTO> queryProductImgByType(ProductDTO productDTO, ProductImgTypeEnum type) {
		productImgExample imgExample = new productImgExample();

		imgExample.createCriteria().andIdEqualTo(productDTO.getId()).andTypeEqualTo(type.getType());
		imgExample.setOrderByClause("id desc");
		List<productImg> productImgs = productImgMap.selectByExample(imgExample);

		List<ProductImgDTO> productImgDTOs = productImgs.stream().map(productImg -> {
			ProductImgDTO imgDTO = new ProductImgDTO();
			BeanUtils.copyProperties(productImg, imgDTO);
			return imgDTO;
		}).collect(Collectors.toList());

		return productImgDTOs;

	}

//	查找一个数据，根据ID
	public ProductImgDTO queryProductImgById(int id) {
		ProductImgDTO imgDTO = new ProductImgDTO();

		productImg productImg = productImgMap.selectByPrimaryKey(id);
		product product = productMap.selectByPrimaryKey(productImg.getPid());

		imgDTO.setProduct(product);
		BeanUtils.copyProperties(productImg, imgDTO);

		return imgDTO;
	}

//	增加一个数据
	public void add(ProductImgDTO productImgDTO) {
		productImg productImg = new productImg();
		productImgDTO.change();
		BeanUtils.copyProperties(productImgDTO, productImg);
		productImgMap.insertSelective(productImg);
	}

//	删除一个数据
	public void delete(int id) {
		productImgMap.deleteByPrimaryKey(id);
	}

//	更新一个数据
	public void update(productImg productImg) {
		productImgMap.updateByPrimaryKey(productImg);
	}
}
