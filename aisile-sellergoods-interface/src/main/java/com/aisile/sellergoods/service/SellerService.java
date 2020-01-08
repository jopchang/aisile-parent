package com.aisile.sellergoods.service;

import java.util.List;

import com.aisile.entity.PageResult;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSeller;
import com.aisile.pojogroup.Specification;

public interface SellerService {

	//商家注册
	boolean add(TbSeller tbSeller);
	
	boolean updateStatus(String sellerId,String status);

	//查看商家（包括未注册的商家）
	PageResult findPage(int page, int rows, TbSeller tbSeller);
	
	//商家详情
	TbSeller findOne(String sellerId);
	
	
}
