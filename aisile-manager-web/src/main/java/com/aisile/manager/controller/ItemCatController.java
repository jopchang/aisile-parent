package com.aisile.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.entity.Result;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbItemCat;
import com.aisile.sellergoods.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;

@RequestMapping("itemCat")
@RestController
public class ItemCatController {
	@Reference
	ItemCatService itemCatService;
	
	@RequestMapping("/findByParentId")
	public List<TbItemCat> findByParentId(Long parentId){	
		List<TbItemCat> findByParentId = itemCatService.findByParentId(parentId);
		return findByParentId;
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbItemCat tbItemCat) {
		try {
			int addBrand = itemCatService.add(tbItemCat);
			
			return new Result(true,"增加成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "增加失败");
		}
	}
	
	@RequestMapping("/findOne")
	public TbItemCat findOne(Long id) {
		return itemCatService.findOne(id);
	}
	
	@RequestMapping("/update")
	public Result update(@RequestBody TbItemCat tbItemCat) {
		try {
			int updateBrand = itemCatService.update(tbItemCat);
			
			return new Result(true,"修改成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "修改失败");
		}
	}
	
	@RequestMapping("/delete")
	public Result del(Long[] ids) {
		try {
			  itemCatService.del(ids);
			
			return new Result(true,"删除成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "删除失败");
		}
	}
	
	
}
