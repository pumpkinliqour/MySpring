package com.kh.spring.notice.model.dao;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
	int noticeCount();	
	List<Map<String, String>> noticeList(int cPage, int numPerPage);
}
