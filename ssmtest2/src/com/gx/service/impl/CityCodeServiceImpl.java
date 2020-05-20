package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.CityCodeMapper;
import com.gx.po.CityCode;
import com.gx.service.CityCodeService;



@Transactional
@Service(value="cityCodeService")
public class CityCodeServiceImpl implements CityCodeService{

	@Autowired
	private CityCodeMapper cityCodeMapper;

	@Override
	public CityCode selectByCityName(String cityname) {
		// TODO Auto-generated method stub
		return cityCodeMapper.selectByCityName(cityname);
	}

	@Override
	public CityCode selectLocateCityName(String locateSQL) {
		// TODO Auto-generated method stub
		return cityCodeMapper.selectLocateCityName(locateSQL);
	}

	

	

}
