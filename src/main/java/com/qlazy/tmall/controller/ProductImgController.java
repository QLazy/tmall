package com.qlazy.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qlazy.tmall.dto.ProductDTO;
import com.qlazy.tmall.dto.ProductImgDTO;
import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.enums.ProductImgTypeEnum;
import com.qlazy.tmall.service.impl.ProductImgServiceImpl;
import com.qlazy.tmall.service.impl.ProductServiceImpl;
import com.qlazy.tmall.util.ImageUtil;

@RestController
public class ProductImgController {

	@Autowired
	ProductImgServiceImpl productImgService;

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/products/{pid}/productImgs")
	public List<ProductImgDTO> list(@RequestParam("type") String type, @PathVariable("pid") int pid) {
		ProductDTO productDTO = productService.queryProductById(pid);

//		根据类型查询出相应的图片
		List<ProductImgDTO> productImgDTOs = productImgService.queryProductImgByType(productDTO,
				ProductImgTypeEnum.getEnum(type));

		return productImgDTOs;
	}

	@PostMapping("/productImgs")
	public ProductImgDTO add(@RequestParam("type")String type,@RequestParam("pid")int pid,@RequestParam("image") MultipartFile image,
			HttpServletRequest request) {
//		创建对象
		ProductImgDTO imgDTO = new ProductImgDTO();
		product product = new product();

//		赋值给imgDTO
		imgDTO.setPid(pid);
		imgDTO.setType(type);
		ProductDTO productDTO = productService.queryProductById(imgDTO.getPid());
		BeanUtils.copyProperties(productDTO, product);
		imgDTO.setProduct(product);

//		将imgDTO增加到数据库
		productImgService.add(imgDTO);

//		将图片保存到本地地址
		String folder = "/img";
//		命名保存图片的文件夹
		if (ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			folder += "productSingle";
		} else {
			folder += "productDetail";
		}
//		图片保存地址
		File imgFolder = new File(request.getServletContext().getRealPath(folder));
		System.out.println(imgFolder);
//		用ID命名图片保存
		File file = new File(imgFolder, imgDTO.getId() + ".jpg");
		String fileName = file.getName();
//		判断父文件夹是否存在，不存在则创建
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
//		将图片转化成JPG格式后存到相应文件夹
		try {
			image.transferTo(file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, fileName, file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
//		若是single图片，则需要保存大号与小号两种图片
		if (ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			// 创建对应的文件夹
			String imgFolderSmall = request.getServletContext().getRealPath("/img/ProductSingle_small");
			String imgFolderMiddle = request.getServletContext().getRealPath("/img/ProductSingle_middle");

			File fileSmall = new File(imgFolderSmall, fileName);
			File fileMiddle = new File(imgFolderMiddle, fileName);
			// 创建父文件夹
			fileSmall.getParentFile().mkdirs();
			fileMiddle.getParentFile().mkdirs();
			// 调整图片大小
			ImageUtil.resizeImage(fileSmall, 56, 56, fileSmall);
			ImageUtil.resizeImage(fileMiddle, 217, 190, fileMiddle);
		}
		return imgDTO;
	}
	
	@DeleteMapping("/productImgs/{id}")
	public Object delete(@PathVariable("id")int id,HttpServletRequest request) {
		//删除数据库数据
		productImgService.delete(id);
		ProductImgDTO imgDTO = productImgService.queryProductImgById(id);
		
		//删除服务器保存图片
		String folder = "/img";
		if(ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			folder += "ProductSingle";
		}else {
			folder += "ProductDetail";
		}
		
		File imgFolder = new File(request.getServletContext().getRealPath(folder));
		File file = new File(imgFolder,imgDTO.getId()+".jpg");
		String fileName = file.getName();
		file.delete();
		
		if(ProductImgTypeEnum.single.getType().equals(imgDTO.getType())) {
			String imgFolderSmall = request.getServletContext().getRealPath("/img/ProductSingle_small");
			String imgFolderMiddle = request.getServletContext().getRealPath("/img/ProductSingle_middle");
			
			File fileSmall = new File(imgFolderSmall,fileName);
			File fileMiddle = new File(imgFolderMiddle,fileName);
			
			fileSmall.delete();
			fileMiddle.delete();
		}
		return null;
	}
}
