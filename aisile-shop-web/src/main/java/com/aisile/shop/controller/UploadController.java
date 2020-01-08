package com.aisile.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aisile.entity.Result;
import com.aisile04.common.oss.AliyunOSSClientUtils;

@RestController
public class UploadController {
	
	@RequestMapping("upload")
	public Result upload(MultipartFile file) {
		// TODO Auto-generated method stub
		AliyunOSSClientUtils ossClientUtils = new AliyunOSSClientUtils();
		try {
			String uploadImg2Oss = ossClientUtils.uploadImg2Oss(file);
			String imgUrl = ossClientUtils.getImgUrl(uploadImg2Oss);
			return new Result(true,imgUrl);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(false,"上传失败");
		}
	}
	
	
}
