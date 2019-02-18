package com.kh.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/boardList.do")
	public String boardList(HttpServletRequest request, Model model) {
		int cPage; //시작 페이지
		int numPerPage=10; //한 페이지당 보이는 리스트 수
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e) {
			cPage=1;
		}
		List<Map<String,String>> list=service.boardList(cPage, numPerPage);
		
		int empCount=service.boardCount();
		
		int pageSize=(int)Math.ceil((double)empCount/numPerPage);
		int pageBarSize=5;
		String pageBar="";
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		pageBar+="<ul class='pagination "
				+ "justify-content-center pagination-sm'>";
		//이전
		if(pageNo==1) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a class='page-link' href='#' tabindex='-1'>이전</a>";
			pageBar+="</li>";
		}
		else
		{
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='javascript:void(0);' "
					+ "onclick='fn_paging("+(pageNo-1)+")'>이전</a>";
			pageBar+="</li>";
		}
		//본문
		while(!(pageNo>pageEnd||pageNo>pageSize)) {
			if(cPage==pageNo)
			{
				pageBar+="<li class='page-item active'>";
				pageBar+="<a class='page-link'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a class='page-link' href='javascript:void(0);' "
						+ "onclick='fn_paging("+pageNo+")'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			pageNo++;
		}
		
		//다음
		if(pageNo>pageSize) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a class='page-link'>다음</a>";
			pageBar+="</li>";
		}
		else
		{
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='javascript:void(0);' "
					+ "onclick='fn_paging("+pageNo+")'>다음</a>";
			pageBar+="</li>";
		}
		pageBar+="</ul>";
	
		pageBar+="<script>";
		pageBar+="function fn_paging(cPage)";
		pageBar+="{ location.href='"+request.getRequestURI()+"?cPage='+cPage";
		pageBar+="}";
		pageBar+="</script>";
		
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("list", list);
		model.addAttribute("count", empCount);
		
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardWrite.do")
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping("/board/boardView.do")
	public String boardView() {
		
		return "board/boardView";
	}
	
	@RequestMapping("/board/boardWriteEnd.do")
	public String boardWriteEnd(HttpServletRequest request, Board board) {
		
		return "";
	}
}
