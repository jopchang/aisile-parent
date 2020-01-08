package com.aisile.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbItemCatMapper;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbItemCatExample;
import com.aisile.pojo.TbItemCatExample.Criteria;
import com.aisile.sellergoods.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TbItemCat> findByParentId(Long parentId) {
		TbItemCatExample example1=new TbItemCatExample();
		Criteria createCriteria = example1.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		return  itemCatMapper.selectByExample(example1);		
	}

	@Override
	public int add(TbItemCat tbItemCat) {
		return itemCatMapper.insert(tbItemCat);
	}

	@Override
	public TbItemCat findOne(Long id) {
		// TODO Auto-generated method stub
		return itemCatMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(TbItemCat tbItemCat) {
		// TODO Auto-generated method stub
		return itemCatMapper.updateByPrimaryKeySelective(tbItemCat);
	}

	@Override
	public void del(Long[] ids) {
		for (Long id : ids) {
			itemCatMapper.deleteByPrimaryKey(id);
		}
		
	}

}
