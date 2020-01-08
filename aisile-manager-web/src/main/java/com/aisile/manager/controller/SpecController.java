package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.entity.PageResult;
import com.aisile.entity.Result;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojogroup.Specification;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.SpecService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/spec")
public class SpecController {

	@Reference
	private SpecService specService;
	
	@RequestMapping("/search")
	public PageResult list(int page,int rows,@RequestBody TbSpecification tbSpecification){
		return specService.findPage(page, rows,tbSpecification);
	}
	@RequestMapping("/findPage")
	public PageResult list(int page,int rows){
		return specService.findPage(page, rows);
	}
	@RequestMapping("/list")
	public List<TbBrand> list(){
		return specService.findAll();
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody Specification specification) {
		try {
			System.out.println(specification);
			specService.add(specification);
			
			return new Result(true,"增加成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "增加失败");
		}
	}
	
	@RequestMapping("/findOne")
	public Specification findOne(Long id) {
		return specService.findOne(id);
	}
	@RequestMapping("/update")
	public Result update(@RequestBody Specification specification) {
		try {
			int updateBrand = specService.update(specification);
			
			return new Result(true,"修改成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "修改失败");
		}
	}
	
	@RequestMapping("/delete")
	public Result del(Long[] ids) {
		try {
			specService.delBrand(ids);
			
			return new Result(true,"删除成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "删除失败");
		}
	}
	
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return specService.selectOptionList();
	}
}
