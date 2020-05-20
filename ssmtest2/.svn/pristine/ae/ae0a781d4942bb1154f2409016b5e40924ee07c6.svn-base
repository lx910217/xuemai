package com.gx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Result;

public interface ResultMapper {

	// int deleteByPrimaryKey(String id);

	int insert(Result record);

	// int insertSelective(Result record);

	Result selectByPrimaryKeyforResult(String id);

	// int updateByPrimaryKeySelective(Result record);
	
	// 查询热点词汇
	List<Result> SelectHighWords(@Param(value = "findBySQLforWords")String SQL);

	// int updateByPrimaryKey(Result record);

	List<Result> findBySQLforResult(@Param(value = "findBySQLforResult") String findBySQL);

}