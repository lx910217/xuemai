package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.RobotContact;




public interface RobotContactMapper {

	void insert(RobotContact data);
	
	List<RobotContact> findBySQLforRC(@Param(value="findBySQLforRC") String findBySQL);
  
	
}