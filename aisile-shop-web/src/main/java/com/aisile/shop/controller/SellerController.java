package com.aisile.shop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.entity.Result;
import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbSeller tbSeller) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		tbSeller.setPassword(encoder.encode(tbSeller.getPassword()));
		boolean falg = sellerService.add(tbSeller);
		return new Result(falg, falg?"成功":"失败");
	}
}
