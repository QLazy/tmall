package com.qlazy.tmall.service;

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
import com.qlazy.tmall.util.ImageUtil;

@Service
public class CategoryService {

	@Autowired
	categoryExample categoryExp;

	@Autowired
	categoryMapper categoryMap;
	
	@Autowired
	categoryExtMapper categoryExtMap;

	public List<CategoryDTO> queryAllCategory() {

		categoryExp.setOrderByClause("id asc");
		List<category> categories = categoryMap.selectByExample(categoryExp);

		// 将查询到的categories重新封装为CategoryDTO输出，
		List<CategoryDTO> categoryDTOs = categories.stream().map(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(category, categoryDTO);
			return categoryDTO;
		}).collect(Collectors.toList());

		return categoryDTOs;
	}

	public PaginationDTO<CategoryDTO> queryCategoryByPage(PaginationTempDTO dto) {
		PaginationDTO<CategoryDTO> paginationDTO = new PaginationDTO<>();
		
		// 查询Category总数量
		int totalCount = (int) categoryMap.countByExample(categoryExp);
		//赋值页数与页码
		int page = dto.getPage();
		int size = dto.getSize();
		
		// 计算总页数
		int totalPages = (int) Math.ceil(totalCount * 1.0 / size);
		// 之所以将这些写在外边，是为了分页查询需要
		if (page > totalPages) {
			page = totalPages;
		} else if (page < 1) {
			page = 1;
		}

		page = page == 0 ? 1 : page;
		totalPages = totalPages == 0 ? 1 : totalPages;

		int pageStartData = size * (page - 1);

		//升序分页查询
		categoryExp.setOrderByClause("id asc");
		List<category> categories = categoryMap.selectByExampleWithRowbounds(categoryExp, new RowBounds(pageStartData,size));
		//将查询到的数据封装到CategoryDTO中
		List<CategoryDTO> list = categories.stream().map(category->{
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(category, categoryDTO);
			return categoryDTO;
		}).collect(Collectors.toList());
		
		//将分页查询到的数据封装到DTO中传输
		paginationDTO.setData(list);
		paginationDTO.setPage(page);
		paginationDTO.pagination(totalPages, page);
		
		return paginationDTO;
	}
	//上传数据到数据库
	public void add(category target) {
		categoryExtMap.insert(target);
	}
	
	//将上传的文件保存到本地
	public void saveOrUpdateImageFile(category target, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,target.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
}
