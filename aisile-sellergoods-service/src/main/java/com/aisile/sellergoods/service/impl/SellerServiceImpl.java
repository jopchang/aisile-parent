package com.aisile.sellergoods.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.entity.PageResult;
import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.TbSellerExample;
import com.aisile.pojo.TbSellerExample.Criteria;
import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private TbSellerMapper tbSellerMapper;

	@Override
	public boolean add(TbSeller tbSeller) {
		try {
			tbSeller.setStatus("0");//注册商家为未审核
			tbSeller.setCreateTime(new Date());
			tbSellerMapper.insert(tbSeller);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PageResult findPage(int page, int rows, TbSeller tbSeller) {
		TbSellerExample example = new TbSellerExample();
		Criteria criteria = example.createCriteria();
		//判断是否是未审核状态
		if(tbSeller!=null) {
			if(tbSeller.getStatus()!=null && !tbSeller.getStatus().equals("")) {
				criteria.andStatusEqualTo(tbSeller.getStatus());
			}
			if(tbSeller.getName()!=null && !tbSeller.getName().equals("")) {
				criteria.andNameLike("%"+tbSeller.getName()+"%");
			}
			if(tbSeller.getNickName()!=null && !tbSeller.getNickName().equals("")) {
				criteria.andNickNameLike("%"+tbSeller.getNickName()+"%");
			}
		}
		PageHelper.startPage(page, rows);
		Page<TbSeller> pages = (Page<TbSeller>)tbSellerMapper.selectByExample(example);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public TbSeller findOne(String sellerId) {
		TbSeller selectByPrimaryKey = tbSellerMapper.selectByPrimaryKey(sellerId);
		return selectByPrimaryKey;
	}

	/**
	 * 修改状态
	 */
	@Override
	public boolean updateStatus(String sellerId,String status) {
		try {
			TbSeller tbSeller = this.findOne(sellerId);
			System.out.println(status);
			tbSeller.setStatus(status);
			tbSellerMapper.updateByPrimaryKey(tbSeller);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
