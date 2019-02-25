package com.kh.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public Map<String, String> login(Map<String, String> map) {
		return session.selectOne("member.memberLogin", map);
	}
	
	@Override
	public int memberEnroll(Member member) {
		return session.insert("member.memberEnroll", member);
	}
	
	@Override
	public int selectEmpCount() {
		return session.selectOne("member.selectEmpCount");
	}
	
	@Override
	public List<Map<String, String>> memberList(int cPage, int numPerPage){
		RowBounds rows=new RowBounds((cPage-1)*numPerPage, numPerPage);
		return session.selectList("member.memberList", null, rows);
	}
	
	@Override
	public Member selectMemberOne(String userId) {
		return session.selectOne("member.selectMemberOne", userId);
	}
	
	@Override
	public int myPageUpdate(Member member) {
		return session.update("member.myPageUpdate", member);
	}
	
	@Override
	public List<Map<String,String>> memberSearch(Map<String,String> map){
		return session.selectList("member.memberSearch", map);
	}
	
	@Override
	public int checkId(String userId) {
		return session.selectOne("member.selectUserId", userId);
	}
}
