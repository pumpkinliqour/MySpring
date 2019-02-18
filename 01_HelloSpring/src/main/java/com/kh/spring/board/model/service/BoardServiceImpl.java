package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao dao;

	@Override
	public int boardCount() {
		return dao.boardCount();
	}
	
	
	@Override
	public List<Map<String, String>> boardList(int cPage, int numPerPage){
		return dao.boardList(cPage, numPerPage);
	}
}
