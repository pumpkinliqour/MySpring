package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int boardCount() {
		return session.selectOne("board.boardCount");
	}
			
	@Override
	public List<Map<String, String>> boardList(int cPage, int numPerPage){
		RowBounds rows=new RowBounds((cPage-1)*numPerPage, numPerPage);
		return session.selectList("board.boardList", null, rows);
	}
}
