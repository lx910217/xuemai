package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Detail;

public interface DetailMapper {


//	int deleteByPrimaryKey(String id);

	
	void insert(Detail detail);

//	int insertSelective(Result record);

	Detail findforDetail(Detail dd);
	
	
//	ReportDetail selectByPrimaryKeyforReportDetail(Integer id);

	
//	int updateByPrimaryKeySelective(Result record);

	
//	int updateByPrimaryKey(Result record);
    
//	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<Detail> findBySQLforDetail(@Param(value="findBySQLforDetail") String findBySQL);
	
	Detail findBySQLforDetailByCLSP(Detail detail);
	
	Detail findBySQLforDetailByULS(Detail detail);

	List<Detail> findBySQLforCallTimes(@Param(value="findBySQLforCallTimes")String findBySQL);
	
  
}