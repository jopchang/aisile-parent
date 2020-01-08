package com.aisile.sellergoods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbUserMapper;
import com.aisile.sellergoods.service.LoginService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
}
