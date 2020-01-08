package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.entity.PageResult;
import com.aisile.mapper.TbBrandMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbBrandExample;
import com.aisile.pojo.TbBrandExample.Criteria;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private TypeTemplateService typeTemplateService;
	
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public int addBrand(TbBrand tbBrand) {
		return brandMapper.insertSelective(tbBrand);
	}

	/**
	 * 修改品牌，id不变，通过id查询出一个对象，然后把要修改的值和查询的对象一同传到typeService中
	 */
	@Override
	public int updateBrand(TbBrand tbBrand) {
		
		System.out.println(">>>>>>>>>>>>>>>");
		typeTemplateService.updateBrandIdsByBrand(tbBrand);
		System.out.println("<<<<<<<<<<<<<<<");
		return brandMapper.updateByPrimaryKeySelective(tbBrand);
	}

	@Override
	public void delBrand(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult findPage(int page, int rows) {
		
		PageHelper.startPage(page, rows);
		
		Page<TbBrand> pages = (Page<TbBrand>)brandMapper.selectByExample(null);
		
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(int page, int rows, TbBrand brand) {
		PageHelper.startPage(page, rows);
		
		TbBrandExample example=new TbBrandExample();
		
		Criteria criteria = example.createCriteria();
		
		if(brand!=null) {
			if(brand.getName()!=null && !brand.getName().equals("")){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null && !brand.getFirstChar().equals("")){
				criteria.andFirstCharEqualTo(brand.getFirstChar());
			}	
		}
		
		Page<TbBrand> pages = (Page<TbBrand>)brandMapper.selectByExample(example);
		
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		return brandMapper.selectOptionList();
	}

}
