package com.gx.service;

import java.util.List;

import com.gx.po.RobotContact;




public interface RobotContactService {
// 
	void insert(RobotContact data);
	
    List<RobotContact> findBySQLforRC(String SQL);
   
}
