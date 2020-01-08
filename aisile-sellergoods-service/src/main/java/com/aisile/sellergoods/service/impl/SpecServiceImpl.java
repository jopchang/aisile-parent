package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aisile.entity.PageResult;
import com.aisile.mapper.TbSpecificationMapper;
import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationExample;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbSpecificationOptionExample.Criteria;
import com.aisile.pojogroup.Specification;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.SpecService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class SpecServiceImpl implements SpecService{

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper tbSpecificationOptionMapper;
	
	
	@Override
	public PageResult findPage(int page, int rows, TbSpecification tbSpecification) {
		PageHelper.startPage(page, rows);
		
		TbSpecificationExample example = new TbSpecificationExample();
		com.aisile.pojo.TbSpecificationExample.Criteria criteria = example.createCriteria();
		if(tbSpecification!=null) {
			if(tbSpecification.getSpecName()!=null && !tbSpecification.getSpecName().equals("")) {
				criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
			}
		}
		
		Page<TbSpecification> pages = (Page<TbSpecification>) specificationMapper.selectByExample(example);
		return new PageResult(pages.getTotal(),pages.getResult());
	}

	@Override
	public PageResult findPage(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbBrand> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 根据id查询主表
	 * 再根据id查询附表添加到list中
	 * 封装到集成对象
	 */
	@Override
	public Specification findOne(Long id) {
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria create = example.createCriteria();
		create.andSpecIdEqualTo(id);
		List<TbSpecificationOption> selectByExample = tbSpecificationOptionMapper.selectByExample(example);
		Specification specification = new Specification();
		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionList(selectByExample);
		return specification;
	}

	/**
	 * 根据id修改主表
	 * 根基id删除附表数据然后循环添加
	 */
	@Override
	public int update(Specification specification) {
		try {
			//根据id修改主表
			specificationMapper.updateByPrimaryKey(specification.getSpecification());
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(specification.getSpecification().getId());
			//根基id删除附表数据
			tbSpecificationOptionMapper.deleteByExample(example);
			//循环添加
			for (TbSpecificationOption tbSpecificationOption  : specification.getSpecificationOptionList()) {
				tbSpecificationOption.setSpecId(specification.getSpecification().getId());
				System.out.println(tbSpecificationOption.getId()+">>>>>>>>>");
				System.out.println(tbSpecificationOption.getOrders()+">>>>>>>>>");
				System.out.println(tbSpecificationOption.getOptionName()+">>>>>>>>>");
				System.out.println(tbSpecificationOption.getSpecId()+">>>>>>>>>");
				tbSpecificationOptionMapper.insert(tbSpecificationOption);
			}
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void delBrand(Long[] ids) {
		specificationMapper.deletes(ids);
		for (Long id : ids) {
			TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
			tbSpecificationOptionExample.createCriteria().andSpecIdEqualTo(id);
			tbSpecificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
		}
	}

	@Override
	public void add(Specification specification) {
		try {
			specificationMapper.insert(specification.getSpecification());
			for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
				tbSpecificationOption.setSpecId(specification.getSpecification().getId());
				tbSpecificationOptionMapper.insert(tbSpecificationOption);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}


}
