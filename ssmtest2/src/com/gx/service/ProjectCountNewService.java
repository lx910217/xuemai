package com.gx.service;

import java.util.List;

import com.gx.po.ProjectCount;
import com.gx.po.ProjectCountNew;


public interface ProjectCountNewService {

	List<ProjectCountNew> FindAllMission(String findBySQL);
    
}
