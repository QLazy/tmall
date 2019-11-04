package com.qlazy.tmall.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qlazy.tmall.dto.CategoryDTO;
import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;
import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.entity.categoryExample;
import com.qlazy.tmall.mapper.categoryExtMapper;
import com.qlazy.tmall.mapper.categoryMapper;
import com.qlazy.tmall.service.IService;
import com.qlazy.tmall.util.ImageUtil;
import com.qlazy.tmall.util.PaginationUtil;

@Service
public class CategoryServiceImpl implements IService<category>{

	@Autowired
	categoryExample categoryExp;

	@Autowired
	categoryMapper categoryMap;

	@Autowired
	categoryExtMapper categoryExtMap;
	
	public PaginationDTO<CategoryDTO> queryCategoryByPage(PaginationTempDTO dto) {
		PaginationUtil paginationUtil = new PaginationUtil();
		PaginationDTO<CategoryDTO> paginationDTO = new PaginationDTO<>();
		// 查询Category总数量
		dto.setTotalCount((int) categoryMap.countByExample(categoryExp));

		// 处理分页相应的数据
		paginationUtil.pagination(dto, paginationDTO);

		// 升序分页查询
		categoryExp.setOrderByClause("id desc");
		List<category> categories = categoryMap.selectByExampleWithRowbounds(categoryExp,
				new RowBounds(paginationUtil.getPageStartData(), paginationUtil.getSize()));

		// 将查询到的数据封装到CategoryDTO中
		List<CategoryDTO> list = categories.stream().map(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(category, categoryDTO);
			return categoryDTO;
		}).collect(Collectors.toList());

		// 将分页查询到的数据封装到DTO中传输
		paginationDTO.setData(list);
		return paginationDTO;
	}

	// 上传数据到数据库
	public void add(category target) {
		// 修改了相应的XML文件，防止后续更新，创建一个新的XML
		categoryExtMap.insert(target);
	}

	// 根据ID删除数据
	public void delete(int id) {
		categoryMap.deleteByPrimaryKey(id);
	}

	// 根据ID查询相应的数据
	public category queryCategoryById(int id) {
		category category = categoryMap.selectByPrimaryKey(id);
		return category;
	}

	// 更新数据
	public void updata(category category) {
		categoryMap.updateByPrimaryKey(category);
	}

	// 将上传的文件保存到本地
	public void saveOrUpdateImageFile(category target, MultipartFile image, HttpServletRequest request)
			throws IOException {
		File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder, target.getId() + ".jpg");
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		image.transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
	}
}
