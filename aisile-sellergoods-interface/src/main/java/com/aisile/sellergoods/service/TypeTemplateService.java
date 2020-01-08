package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.entity.PageResult;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojogroup.Specification;

public interface TypeTemplateService {


	PageResult findPage(int page, int rows, TbTypeTemplate tbTypeTemplate);

	PageResult findPage(int page, int rows);

	List<TbBrand> findAll();

	TbTypeTemplate findOne(Long id);

	int update(TbTypeTemplate tbTypeTemplate);

	void delTypeTemplate(Long[] ids);

	void add(TbTypeTemplate tbTypeTemplate);

	void updateBrandIdsByBrand(TbBrand brand);

	public List<Map> findSpecList(Long id);

	
}
