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
import com.aisile.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

	@Reference
	private BrandService brandService;
	//a
	@RequestMapping("/serach")
	public PageResult list(int page,int rows,@RequestBody TbBrand brand){
		return brandService.findPage(page, rows,brand);
	}
	@RequestMapping("/findPage")
	public PageResult list(int page,int rows){
		return brandService.findPage(page, rows);
	}
	@RequestMapping("/list")
	public List<TbBrand> list(){
		return brandService.findAll();
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand tbBrand) {
		try {
			int addBrand = brandService.addBrand(tbBrand);
			
			return new Result(true,"增加成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "增加失败");
		}
	}
	
	@RequestMapping("/findOne")
	public TbBrand findOne(Long id) {
		return brandService.findOne(id);
	}
	@RequestMapping("/update")
	public Result update(@RequestBody TbBrand tbBrand) {
		try {
			int updateBrand = brandService.updateBrand(tbBrand);
			
			return new Result(true,"修改成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "修改失败");
		}
	}
	
	@RequestMapping("/delete")
	public Result del(Long[] ids) {
		try {
			  brandService.delBrand(ids);
			
			return new Result(true,"删除成功",20000);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new Result(false , "删除失败");
		}
	}
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return brandService.selectOptionList();
	}
}
