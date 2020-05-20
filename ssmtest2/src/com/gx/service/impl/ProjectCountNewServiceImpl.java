package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ProjectCountNewMapper;
import com.gx.po.ProjectCountNew;
import com.gx.service.ProjectCountNewService;



@Transactional
@Service(value="projectCountNewService")
public class ProjectCountNewServiceImpl implements ProjectCountNewService{

	@Autowired
	private ProjectCountNewMapper projectCountNewMapper;
	
	@Override
	public List<ProjectCountNew> FindAllMission(String findBySQL) {
		// TODO Auto-generated method stub
		return projectCountNewMapper.FindAllMission(findBySQL);
	}


	

}
