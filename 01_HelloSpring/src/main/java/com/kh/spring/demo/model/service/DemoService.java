package com.kh.spring.demo.model.service;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoService {
	int insertDev(Dev dev);
	List<Dev> devList();
	Dev selectOne(int devNo);
	int deleteDev(int devNo);
	int updateDevEnd(Dev dev);
}
