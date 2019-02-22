package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;

public interface BoardService {
	int boardCount();
	List<Map<String,String>> boardList(int cPage, int numPerPage);
	int insertBoard(Map<String, String> board, List<Attachment> files);
}
