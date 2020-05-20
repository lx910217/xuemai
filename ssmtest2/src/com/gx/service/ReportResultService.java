package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ReportResult;
import com.gx.po.Result;

public interface ReportResultService {

	void updateByPrimaryKey(ReportResult record);

	void insert(ReportResult record);

	ReportResult selectByPrimaryKey(Integer userid);

	/**
	 * 自定义方法
	 */
	List<ReportResult> findAll();

	List<ReportResult> findBySQL(String SQL);

	void deleteByDATE(String deleteByDATE);

	//
	// List<AiUserRole> findRoleBySQL(String SQL);
	// 查询
	ReportResult findByRr(ReportResult rr);

	

}
