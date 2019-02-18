package com.kh.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.demo.model.vo.Dev;

@Repository
public class DemoDaoImpl implements DemoDao {
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int insertDev(Dev dev) {
		return session.insert("demo.insertDev", dev);
	}
	
	@Override
	public List<Dev> devList(){
		return session.selectList("demo.devList");
	}
	
	@Override
	public int deleteDev(int devNo) {
		return session.delete("demo.deleteDev", devNo);
	}
	
	@Override
	public Dev selectOne(int devNo) {
		return session.selectOne("demo.selectOne", devNo);
	}
	
	@Override
	public int updateDevEnd(Dev dev) {
		return session.update("demo.updateDevEnd", dev);
	}
}
