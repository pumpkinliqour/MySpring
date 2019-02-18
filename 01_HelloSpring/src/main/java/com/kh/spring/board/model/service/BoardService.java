package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	int boardCount();
	List<Map<String,String>> boardList(int cPage, int numPerPage);
}
