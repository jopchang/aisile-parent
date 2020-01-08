package com.aisile.sellergoods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbGoodsDescMapper;
import com.aisile.mapper.TbGoodsMapper;
import com.aisile.pojogroup.Goods;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
	@Autowired
	TbGoodsMapper goodsMapper;
	
	/**
	 * 增加
	 */
	@Override
	public void add(Goods goods) {
		goods.getGoods().setAuditStatus("0");//设置未申请状态
		goodsMapper.insert(goods.getGoods());		
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());//设置ID
		goodsDescMapper.insert(goods.getGoodsDesc());//插入商品扩展数据
	}

}
