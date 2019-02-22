package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;

@Controller
public class BoardController {
	
	private Logger logger=Logger.getLogger(BoardController.class);
	
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
	
	@RequestMapping("/board/boardForm.do")
	public String boardWrite() {
		
		return "board/boardForm";
	}
	
	@RequestMapping("/board/boardView.do")
	public String boardView() {
		
		return "board/boardView";
	}
	
	@RequestMapping("/board/boardFormEnd.do")
	public String boardFormEnd(HttpServletRequest request, String boardTitle, String boardContent, String boardWriter, MultipartFile[] upFile) {
		
		Map<String,String> board=new HashMap<String,String>(); //보드에 관한것들
		board.put("title", boardTitle);
		board.put("writer", boardWriter);
		board.put("content", boardContent);
		
		logger.debug("과연?"+board);
		
		ArrayList<Attachment> files=new ArrayList<Attachment>(); //첨부파일에 관한것들
		
		//저장경로
		String saveDir=request.getSession().getServletContext().getRealPath("/resources/upload/board");
				
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) {
				//파일명을 생성(rename)
				String oriFileName=f.getOriginalFilename();
				String ext=oriFileName.substring(oriFileName.lastIndexOf(".")); //확장자까지
				//rename규칙을 설정
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int randomV=(int)(Math.random()*1000);
				String reName=sdf.format(System.currentTimeMillis())+"_"+randomV+ext;
				try {
					f.transferTo(new File(saveDir+"/"+reName));
				}
				catch(IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				Attachment att=new Attachment();
				att.setReNamedFileName(reName);
				att.setOriginalFileName(oriFileName);
				files.add(att);
			}
		}
		int result=service.insertBoard(board, files);
		
		String msg="";
		String loc="/board/boardList.do";
		
		if(result>0) {
			msg="성공";
		}
		else {
			msg="실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		
		return "common/msg";
	}
}
