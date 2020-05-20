package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.TotalMapper;
import com.gx.po.Total;
import com.gx.service.TotalService;

@Transactional
@Service(value = "TotalService")
public class TotalServiceImpl implements TotalService {

	@Autowired
	private TotalMapper totalMapper;

	@Override
	public void insert(Total total) {
		totalMapper.insert(total);

	}

	@Override
	public List<Total> findBySQL(String SQL) {
		return totalMapper.findBySQLforTotal(SQL);
	
	}

	@Override
	public void updateByListId(Total total) {
		// TODO Auto-generated method stub
		totalMapper.updateByListId(total);
	}

	@Override
	public Total findByListID(String list_id) {
		return totalMapper.findByListID(list_id);
	
		
	}

}
