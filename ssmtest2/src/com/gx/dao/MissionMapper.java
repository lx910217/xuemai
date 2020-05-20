package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Mission;



public interface MissionMapper {


	
	void insert(Mission record);

	
//	int insertSelective(Result record);

	
	Mission selectByPrimaryKeyforMission(Integer id);

	
	void updateByPrimaryKey(Mission record);

	
//	int updateByPrimaryKey(Result record);
    
	List<Mission> findBySQLforMission(@Param(value="findBySQLforMission") String findBySQL);


	Mission findBySQLforCallTimes(Mission mm);


	


	
  
	
}