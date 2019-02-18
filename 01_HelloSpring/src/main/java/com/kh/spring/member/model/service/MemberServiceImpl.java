package com.kh.spring.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public Map<String, String> login(Map<String, String> map) {
		return dao.login(map);
	}
	
	@Override
	public int memberEnroll(Member member) {
		return dao.memberEnroll(member);
	}
	
	@Override
	public int selectEmpCount() {
		return dao.selectEmpCount(); 
	}
	
	@Override
	public List<Map<String, String>> memberList(int cPage, int numPerPage){
		return dao.memberList(cPage, numPerPage);
	}

	@Override
	public Member selectMemberOne(String userId) {
		return dao.selectMemberOne(userId);
	}
	
	@Override
	public int myPageUpdate(Member member) {
		return dao.myPageUpdate(member);
	}
	
	@Override
	public List<Map<String,String>> memberSearch(Map<String,String> map){
		return dao.memberSearch(map);
	}
}
