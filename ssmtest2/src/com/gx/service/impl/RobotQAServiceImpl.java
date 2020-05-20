package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.RobotQAMapper;
import com.gx.po.RobotQA;
import com.gx.service.RobotQAService;


@Transactional
@Service(value="robotQAService")
public class RobotQAServiceImpl implements RobotQAService{

	@Autowired
	private RobotQAMapper robotQAMapper;
	
	@Override
	public List<RobotQA> findBySQLforQA(String SQL) {
		// TODO Auto-generated method stub
		return robotQAMapper.findBySQLforQA(SQL);
	}

	@Override
	public void insert(RobotQA detail) {
		// TODO Auto-generated method stub
		robotQAMapper.insert(detail);
	}


}
