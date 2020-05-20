package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ResultMapper;
import com.gx.po.Result;
import com.gx.service.HighWordService;


@Transactional
@Service(value = "HighWordService")
public class HighWordServiceImpl implements HighWordService {

	
	
	@Autowired
	private ResultMapper resultMapper;
	
	@Override
	public List<Result> SelectHighWords(String SQL) {
		
		return resultMapper.SelectHighWords(SQL);
	
	}

}
