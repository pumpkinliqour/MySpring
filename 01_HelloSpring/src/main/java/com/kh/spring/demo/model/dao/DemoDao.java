package com.kh.spring.demo.model.dao;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {
	int insertDev(Dev dev);
	List<Dev> devList();
	int deleteDev(int devNo);
	Dev selectOne(int devNo);
	int updateDevEnd(Dev dev);
}
