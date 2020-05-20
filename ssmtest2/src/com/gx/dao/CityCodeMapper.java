package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.CityCode;




public interface CityCodeMapper {

	CityCode selectByCityName(String cityname);
	
	CityCode selectLocateCityName(@Param(value="locateSQL") String locateSQL);
	

	
}