package com.gx.service;

import java.util.List;

import com.gx.po.RobotQA;



public interface RobotQAService {
// 
      List<RobotQA> findBySQLforQA(String SQL);
   
      void insert(RobotQA detail);
      
}
