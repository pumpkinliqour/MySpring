package com.kh.spring.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int noticeCount() {
		return session.selectOne("notice.noticeCount");
	}
			
	@Override
	public List<Map<String, String>> noticeList(int cPage, int numPerPage){
		RowBounds rows=new RowBounds((cPage-1)*numPerPage, numPerPage);
		return session.selectList("notice.noticeList", null, rows);
	}
}
