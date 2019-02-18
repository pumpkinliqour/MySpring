package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.dao.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao dao;
	
	@Override
	public int noticeCount() {
		return dao.noticeCount();
	}
	
	
	@Override
	public List<Map<String, String>> noticeList(int cPage, int numPerPage){
		return dao.noticeList(cPage, numPerPage);
	}
}
