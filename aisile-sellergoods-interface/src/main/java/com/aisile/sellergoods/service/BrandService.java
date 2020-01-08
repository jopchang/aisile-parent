package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.entity.PageResult;
import com.aisile.pojo.TbBrand;

/**
 * @author 金胖子
 * */

public interface BrandService {

	List<TbBrand> findAll();
	
	PageResult findPage(int page,int rows);
	
	int addBrand(TbBrand tbBrand);
	
	int updateBrand(TbBrand tbBrand);
	
	void delBrand(Long[] ids);
	
	TbBrand findOne(Long id);

	PageResult findPage(int page, int rows, TbBrand brand);
	//查詢并複製下拉框
	List<Map> selectOptionList();
}
