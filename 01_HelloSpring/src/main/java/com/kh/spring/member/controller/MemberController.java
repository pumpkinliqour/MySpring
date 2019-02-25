package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("userId") //세션등록

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder bpeCode;
	
	@RequestMapping("/member/memberLogin.do")
	public String login(String userId, String password, Model model)
	{
		Map<String,String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("password", password);
		
		Map<String, String> m=service.login(map);
		
		
		String msg="";
		String loc="";
		
		if(m!=null) {
			//맵으로 받아오는 데이터는 모두 대문자
			if(bpeCode.matches(password, m.get("PASSWORD"))) {
				msg="로그인 되었습니다.";
				/*session.setAttribute("userId", result.get("USERID"));*/
				model.addAttribute("userId", m.get("USERID")); //세션 등록
				}
				else {
					msg="패스워드가 일치하지 않습니다";
				}
			}
			else {
				msg="아이디가 존재하지 않습니다.";
			}
			model.addAttribute("msg",msg);
			model.addAttribute("loc",loc);
			
			return "common/msg";
	}
	
	@RequestMapping("/member/memberLogout.do")
	public String logout(SessionStatus sessionStatus, Model model) {
		String msg="";
		String loc="";
		
		msg="로그아웃 되었습니다.";
		
		sessionStatus.setComplete();
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
	
	@RequestMapping("/member/myPage.do")
	public ModelAndView myPage(String userId) {
		ModelAndView mv=new ModelAndView();
		
		Member member=service.selectMemberOne(userId);
		
		mv.addObject("member", member);
		mv.setViewName("member/myPage");
		
		return mv;
	}
	
	@RequestMapping("/member/memberUpdate.do")
	public ModelAndView myPageUpdate(ModelAndView mv, Member member) {
		mv=new ModelAndView();
		String rawPw=member.getPassword();
		member.setPassword(bpeCode.encode(rawPw));
		
		int result=service.myPageUpdate(member);
		
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="정보변경이 완료되었습니다.";
		}
		else {
			msg="정보변경에 실패하였습니다.";
		}
		
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/member/member.do")
	public String member() {
		
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnroll.do")
	public ModelAndView memberEnroll(Member member, ModelAndView mv) {
		//패스워드 암호화
		String rawPw=member.getPassword();
		member.setPassword(bpeCode.encode(rawPw));
		
		
		int result=service.memberEnroll(member);
		
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="회원가입이 완료되었습니다.";
		}
		else
		{
			msg="회원가입에 실패하였습니다.";
			loc="/member/member.do";
		}
		
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/member/memberCheckId.do")
	public ModelAndView checkId(String userId, ModelAndView mv) throws Exception{
		/*JSON으로 아이디 값 중복 확인하기*/
		Map<String,Boolean> map=new HashMap<String,Boolean>();
		boolean isId=service.checkId(userId)==0?false:true; /*만약 userId가 0이면 false, 1이면 true*/
		map.put("isId", isId);
		
		mv.addAllObjects(map); //맵의 모든 값을 한번에 보냄.
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@RequestMapping("/member/memberList.do")
	public String memberList(HttpServletRequest request, Model model) {
		int cPage; //시작 페이지
		int numPerPage=10; //한 페이지당 보이는 리스트 수
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e) {
			cPage=1;
		}
		List<Map<String,String>> list=service.memberList(cPage, numPerPage);
		
		int empCount=service.selectEmpCount();
		
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
		
		return "member/memberList";
	}
	
	@RequestMapping("/member/memberSearch.do")
	public ModelAndView memberSearch(HttpServletRequest request, ModelAndView mv, String searchType, String keyword) {
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		List<Map<String,String>> list=service.memberSearch(map);
		
		mv.addObject("list", list);
		mv.setViewName("member/memberList");
		
		return mv;
	}
	
	
}
