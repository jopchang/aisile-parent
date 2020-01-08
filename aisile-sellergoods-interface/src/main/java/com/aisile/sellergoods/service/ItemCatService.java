package com.aisile.sellergoods.service;

import java.util.List;

import com.aisile.pojo.TbItemCat;

public interface ItemCatService {
	
	public List<TbItemCat> findByParentId(Long parentId);

	public int add(TbItemCat tbItemCat);

	public TbItemCat findOne(Long id);

	public int update(TbItemCat tbItemCat);

	public void del(Long[] ids);
	
}
