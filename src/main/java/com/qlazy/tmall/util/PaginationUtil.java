package com.qlazy.tmall.util;

import com.qlazy.tmall.dto.PaginationDTO;
import com.qlazy.tmall.dto.PaginationTempDTO;

import lombok.Data;
@Data
public class PaginationUtil {
	private int totalCount;
	private int page;
	private int size;
	private int pageStartData;
	private int totalPages;

	public void pagination(PaginationTempDTO dto,PaginationDTO<?> paginationDTO) {
		
		// 查询Category总数量
		totalCount = dto.getTotalCount();
		//赋值页数与页码
		page = dto.getPage();
		size = dto.getSize();
		
		// 计算总页数
		totalPages = (int) Math.ceil(totalCount * 1.0 / size);
		// 之所以将这些写在外边，是为了分页查询需要
		if (page > totalPages) {
			page = totalPages;
		} else if (page < 1) {
			page = 1;
		}

		page = page == 0 ? 1 : page;
		totalPages = totalPages == 0 ? 1 : totalPages;

		pageStartData = size * (page - 1);

		paginationDTO.setPage(page);
		paginationDTO.setSize(size);
		paginationDTO.setPageStartData(pageStartData);
		paginationDTO.pagination(totalPages, page);
		
	}
	
}
