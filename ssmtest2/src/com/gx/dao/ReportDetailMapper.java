package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ReportDetail;

public interface ReportDetailMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int insert(ReportDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int insertSelective(Result record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	ReportDetail selectByPrimaryKeyforReportDetail(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int updateByPrimaryKeySelective(Result record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int updateByPrimaryKey(Result record);
    
	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<ReportDetail> findBySQLforReportDetail(@Param(value="findBySQLforReportDetail") String findBySQL);
  
}