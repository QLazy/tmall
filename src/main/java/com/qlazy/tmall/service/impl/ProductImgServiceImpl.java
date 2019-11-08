package com.qlazy.tmall.service.impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.ProductImgDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.entity.productImg;
import com.qlazy.tmall.entity.productImgExample;
import com.qlazy.tmall.enums.ProductImgTypeEnum;
import com.qlazy.tmall.mapper.productImgExtMapper;
import com.qlazy.tmall.mapper.productImgMapper;
import com.qlazy.tmall.mapper.productMapper;
import com.qlazy.tmall.service.IService;

@Service
public class ProductImgServiceImpl implements IService<ProductImgDTO> {
	@Autowired
	productImgMapper productImgMap;

	@Autowired
	productMapper productMap;

	@Autowired
	productImgExtMapper productImgExtMap;

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

		imgExample.createCriteria().andPidEqualTo(productDTO.getId()).andTypeEqualTo(type.getType());
		imgExample.setOrderByClause("id desc");
		List<productImg> productImgs = productImgMap.selectByExample(imgExample);

		List<ProductImgDTO> productImgDTOs = productImgs.stream().map(productImg -> {
			ProductImgDTO imgDTO = new ProductImgDTO();
			BeanUtils.copyProperties(productImg, imgDTO);
			return imgDTO;
		}).collect(Collectors.toList());

		return productImgDTOs;

	}

//	查找数据，根据PID
	public List<productImg> queryProductImgByPid(int pid) {
		productImgExample imgExample = new productImgExample();

		imgExample.createCriteria().andPidEqualTo(pid);
		List<productImg> productImgs = productImgMap.selectByExample(imgExample);

		return productImgs;
	}

//	查找一个数据，根据ID
	public ProductImgDTO queryProductImgById(int id) {
		ProductImgDTO imgDTO = new ProductImgDTO();

		productImg productImg = productImgMap.selectByPrimaryKey(id);
		if (null != productImg) {
			product product = productMap.selectByPrimaryKey(productImg.getPid());

			imgDTO.setProduct(product);
			BeanUtils.copyProperties(productImg, imgDTO);
		}

		return imgDTO;
	}

//	增加一个数据
	public void add(ProductImgDTO productImgDTO) {
		productImg productImg = new productImg();
		productImgDTO.change();
		BeanUtils.copyProperties(productImgDTO, productImg);
		productImgExtMap.insertSelective(productImg);
		productImgDTO.setId(productImg.getId());
	}

//	删除一个数据,根据ID
	public Object delete(int id, HttpServletRequest request) {
		// 删除数据库数据
		ProductImgDTO imgDTO = queryProductImgById(id);
		productImgMap.deleteByPrimaryKey(id);

		// 删除服务器保存图片
		String folder = "img/";
		if (ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			folder += "productSingle";
		} else {
			folder += "productDetail";
		}

		File imgFolder = new File(request.getServletContext().getRealPath(folder));
		File file = new File(imgFolder, imgDTO.getId() + ".jpg");
		String fileName = file.getName();
		file.delete();

		if (ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			String imgFolderSmall = request.getServletContext().getRealPath("/img/ProductSingle_small");
			String imgFolderMiddle = request.getServletContext().getRealPath("/img/ProductSingle_middle");

			File fileSmall = new File(imgFolderSmall, fileName);
			File fileMiddle = new File(imgFolderMiddle, fileName);

			fileSmall.delete();
			fileMiddle.delete();
		}
		return null;
	}

//	删除一个数据,根据PID
	public void deleteImgByPid(int pid,HttpServletRequest request) {
		productImgExample imgExample = new productImgExample();
		
		imgExample.createCriteria().andPidEqualTo(pid);
		List<productImg> productImgs = productImgMap.selectByExample(imgExample);
		
		for(productImg img:productImgs) {
			delete(img.getId(),request);
		}
	}

//	更新一个数据
	public void update(productImg productImg) {
		productImgMap.updateByPrimaryKey(productImg);
	}
}
