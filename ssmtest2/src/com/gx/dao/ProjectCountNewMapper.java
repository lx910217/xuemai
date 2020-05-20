package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ProjectCountNew;



public interface ProjectCountNewMapper {


	List<ProjectCountNew> FindAllMission(@Param(value="FindAllMission") String findBySQL);
	
	
	
	
}