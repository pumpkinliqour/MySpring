package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	int noticeCount();
	List<Map<String,String>> noticeList(int cPage, int numPerPage);
}
