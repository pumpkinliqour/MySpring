package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

public interface BoardDao {
	int boardCount();	
	List<Map<String, String>> boardList(int cPage, int numPerPage);
}
