package com.gx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Total;

public interface TotalMapper {

	// 插入数据
	void insert(Total record);

	// 条件查询
	List<Total> findBySQLforTotal(@Param(value = "findBySQLforTotal") String findBySQL);

	// 根据list_id查询
	Total findByListID(String list_id);

	// 根据list_id更新数据
	void updateByListId(Total total);
}
