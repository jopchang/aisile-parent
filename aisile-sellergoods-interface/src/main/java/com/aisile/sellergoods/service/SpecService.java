package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.entity.PageResult;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojogroup.Specification;

public interface SpecService {


	PageResult findPage(int page, int rows, TbSpecification tbSpecification);

	PageResult findPage(int page, int rows);

	List<TbBrand> findAll();

	Specification findOne(Long id);

	int update(Specification Specification);

	void delBrand(Long[] ids);

	void add(Specification specification);
	//查詢并複製下拉框
	List<Map> selectOptionList();

	
}
