package com.gx.service;

import java.util.List;

import com.gx.po.Mission;

public interface MissionService {
	// int deleteByPrimaryKey(String id);

	void insert(Mission record);

	// int insertSelective(Result record);

	// ReportDetail selectByPrimaryKey(Integer id);

	Mission selectByPrimaryKeyforMission(Integer id);

	// int updateByPrimaryKey(Result record);

	// List<User> findAll();
	//
	void updateByPrimaryKey(Mission record);

	List<Mission> findBySQLforMission(String findBySQL);

	Mission findBySQLforCallTimes(Mission mm);





	//
	// List<Mission> findByPhoneNumber(Mission mission);
	//
	// RowCount rowCount(String SQL);
	//
	// List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer
	// pageSize);

}
