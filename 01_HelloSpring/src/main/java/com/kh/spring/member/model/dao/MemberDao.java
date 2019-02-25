package com.kh.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	Map<String, String> login(Map<String, String> map);
	int memberEnroll(Member member);
	int selectEmpCount();
	List<Map<String, String>> memberList(int cPage, int numPerPage);
	int myPageUpdate(Member member);
	Member selectMemberOne(String userId);
	List<Map<String,String>> memberSearch(Map<String,String> map);
	int checkId(String userId);
}
