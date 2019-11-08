package com.qlazy.tmall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.entity.productExample;
import com.qlazy.tmall.mapper.categoryMapper;
import com.qlazy.tmall.mapper.productExtMapper;
import com.qlazy.tmall.mapper.productMapper;
import com.qlazy.tmall.service.IService;
import com.qlazy.tmall.util.PaginationUtil;

@Service
public class ProductServiceImpl implements IService<ProductDTO> {

	@Autowired
	productMapper productMap;

	@Autowired
	categoryMapper categoryMap;

	@Autowired
	productExtMapper productExtMap;

//	分页查询product
	public PaginationDTO<ProductDTO> queryProductByPage(PaginationTempDTO dto, int cid) {
		PaginationUtil util = new PaginationUtil();
		PaginationDTO<ProductDTO> paginationDTO = new PaginationDTO<>();

//		根据cid统计相应的product数量
		productExample example = new productExample();
		example.createCriteria().andCidEqualTo(cid);
		dto.setTotalCount((int) productMap.countByExample(example));

		util.pagination(dto, paginationDTO);

		productExample productExample = new productExample();
		productExample.createCriteria().andCidEqualTo(cid);
		productExample.setOrderByClause("id desc");
		List<product> products = productMap.selectByExampleWithRowbounds(productExample,
				new RowBounds(util.getPageStartData(), util.getSize()));

		List<ProductDTO> productDTOs = products.stream().map(product -> {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(product, productDTO);
			return productDTO;
		}).collect(Collectors.toList());
//将查询到的数据封装到paginationDTO中
		paginationDTO.setData(productDTOs);

		return paginationDTO;
	}

//	根据ID查询相应的product，并一同获取相应的category
	public ProductDTO queryProductById(int id) {
		ProductDTO productDTO = new ProductDTO();
//		根据ID查询相应的product并将数据赋值到DTO中
		product product = productMap.selectByPrimaryKey(id);
		BeanUtils.copyProperties(product, productDTO);
//		根据ID查询相应的category，后将数据赋值到DTO
		category category = categoryMap.selectByPrimaryKey(product.getCid());
		productDTO.setCategory(category);

		return productDTO;
	}

//	增加一个product
	public void add(ProductDTO productDTO) {
//		这样做的原因是：前端传入的是带category，在这边将其组合
		product product = new product();
		productDTO.setCid(productDTO.getCategory().getId());
		productDTO.setCreatedate(new Date());
		BeanUtils.copyProperties(productDTO, product);
		productExtMap.insertSelective(product);
	}

//	删除一个product
	public void delete(int id) {
		productMap.deleteByPrimaryKey(id);
	}

//	更新一个product
	public void update(product product) {
		productMap.updateByPrimaryKeySelective(product);
	}
}
