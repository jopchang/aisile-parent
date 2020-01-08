package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aisile.entity.PageResult;
import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.TbTypeTemplateExample;
import com.aisile.pojo.TbTypeTemplateExample.Criteria;
import com.aisile.sellergoods.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper tbTypeTemplateMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	@Override
	public PageResult findPage(int page, int rows, TbTypeTemplate tbTypeTemplate) {
		PageHelper.startPage(page, rows);
		TbTypeTemplateExample example = new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();
		System.out.println(tbTypeTemplate.getName());
		if(tbTypeTemplate!=null) {
			if(tbTypeTemplate.getName()!=null && !tbTypeTemplate.getName().equals("")) {
				criteria.andNameLike("%"+tbTypeTemplate.getName()+"%");
			}
		}
		Page<TbTypeTemplate> pages = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
		return new PageResult(pages.getTotal(), pages.getResult());
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

	@Override
	public TbTypeTemplate findOne(Long id) {
		return tbTypeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(TbTypeTemplate tbTypeTemplate) {
		return tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
	}

	@Override
	public void add(TbTypeTemplate tbTypeTemplate) {
		tbTypeTemplateMapper.insert(tbTypeTemplate);
	}

	/**
	 * 获取两个对象，一个是修改之前的，一个是修改之后的 通过修改之前的对象查询，并把修改之后的对象赋值进去
	 */
	@Override
	public void updateBrandIdsByBrand(TbBrand tbBrand) {
		// 通过修改之前的对象查询
		System.out.println("<><><><><><><><>");
		TbTypeTemplateExample example = new TbTypeTemplateExample();
		example.createCriteria().andBrandIdsLike("%" + tbBrand.getId() + "%");
		List<TbTypeTemplate> example2 = tbTypeTemplateMapper.selectByExample(example);//通过查询获得包含该对象的集合
		for (TbTypeTemplate tbTypeTemplate : example2) {
			//String brandIds = tbTypeTemplate.getBrandIds();
			List<Map> list = (List<Map>) JSON.parse(tbTypeTemplate.getBrandIds());//把brandIds通过json传华为list<map>格式
			for (Map map : list) {
				if( (int)map.get("id")== tbBrand.getId()) {//遍历该集合并比较两者的id
					map.put("text", tbBrand.getName());//如果id相同则赋值
				}
			}
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
//				Map map = list.get(i);
//				int id = (int) map.get("id");
//				System.out.println(id);
//				if (id == tbBrand.getId()) {
//					map.remove("text");
//					map.put("text", tbBrand.getName());
//				}
//			}
			String string = JSON.toJSONString(list);//把该集合转化为字符串
			tbTypeTemplate.setBrandIds(string);//赋值
			System.out.println(tbTypeTemplate);
			tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);//修改
		}
	}

	@Override
	public void delTypeTemplate(Long[] ids) {
		tbTypeTemplateMapper.deletes(ids);
	}

	@Override
	public List<Map> findSpecList(Long id) {
	TbTypeTemplate typeTemplate = tbTypeTemplateMapper.selectByPrimaryKey(id);
		
		List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(), Map.class)  ;
		for(Map map:list){
			//查询规格选项列表
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			 com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo( new Long( (Integer)map.get("id") ) );
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			map.put("options", options);
		}		
		return list;
	}

}
