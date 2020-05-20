package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.RobotContactMapper;
import com.gx.po.RobotContact;
import com.gx.service.RobotContactService;


@Transactional
@Service(value="robotContactService")
public class RobotContactServiceImpl implements RobotContactService{

	@Autowired
	private RobotContactMapper robotContactMapper;

	@Override
	public void insert(RobotContact data) {
		// TODO Auto-generated method stub
		robotContactMapper.insert(data);
	}

	@Override
	public List<RobotContact> findBySQLforRC(String SQL) {
		// TODO Auto-generated method stub
		return robotContactMapper.findBySQLforRC(SQL);
	}
	


}
