package com.aisile.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.entity.PageResult;
import com.aisile.entity.Result;
import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/seller")
public class sellerController {
	
	@Reference
	private SellerService sellerService;
	
	@RequestMapping("/search")
	public PageResult list(int page,int rows,@RequestBody TbSeller tbSeller){
		return sellerService.findPage(page, rows,tbSeller);
	}
	
	@RequestMapping("/findOne")
	public TbSeller findOne(String sellerId) {
		return sellerService.findOne(sellerId);
	}
	
	@RequestMapping("/update")
	public Result updateStatus(String sellerId,String status) {
		boolean flag = sellerService.updateStatus(sellerId, status);
		return new Result(flag,flag?"成功":"失败");
	}
}

