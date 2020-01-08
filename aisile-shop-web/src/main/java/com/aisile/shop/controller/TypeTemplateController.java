package com.aisile.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.entity.PageResult;
import com.aisile.entity.Result;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.sellergoods.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;
	
	@RequestMapping("/search")
	public PageResult list(int page,int rows,@RequestBody TbTypeTemplate tbTypeTemplate){
		return typeTemplateService.findPage(page, rows,tbTypeTemplate);
	}
	@RequestMapping("/findPage")
	public PageResult list(int page,int rows){
		return typeTemplateService.findPage(page, rows);
	}
	@RequestMapping("/list")
	public List<TbBrand> list(){
		return typeTemplateService.findAll();
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbTypeTemplate tbTypeTemplate) {
		try {
			typeTemplateService.add(tbTypeTemplate);
			
			return new Result(true,"增加成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "增加失败");
		}
	}
	
	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id) {
		return typeTemplateService.findOne(id);
	}
	@RequestMapping("/update")
	public Result update(@RequestBody TbTypeTemplate tbTypeTemplate) {
		try {
			int updateBrand = typeTemplateService.update(tbTypeTemplate);
			
			return new Result(true,"修改成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "修改失败");
		}
	}
	
	@RequestMapping("/delete")
	public Result del(Long[] ids) {
		try {
			typeTemplateService.delTypeTemplate(ids);
			
			return new Result(true,"删除成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "删除失败");
		}
	}
	
	@RequestMapping("/findSpecList")
	public List<Map> findSpecList(Long id){
		return typeTemplateService.findSpecList(id);
	}
	
}
