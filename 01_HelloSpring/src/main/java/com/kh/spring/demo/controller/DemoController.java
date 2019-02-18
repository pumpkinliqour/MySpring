package com.kh.spring.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

@Controller
public class DemoController {

	@Autowired
	DemoService service;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		/*service.test();*/
		System.out.println("/demo/demo.do/controller실행!");
		//prefix+return값+suffix
		return "demo/demo";
	}
	
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest request)
	{
		String devName=request.getParameter("devName");
		int devAge=Integer.parseInt(request.getParameter("devAge"));
		String devEmail=request.getParameter("devEmail");
		String devGender=request.getParameter("devGender");
		String[] devLang=request.getParameterValues("devLang");
		
		Dev dev=new Dev(devName, devAge, devEmail,devGender,devLang);
		request.setAttribute("dev", dev);
		
		System.out.println(devName+devAge+devEmail+devGender+devLang);
		
		return "demo/result"; //폴더명/jsp이름
	}
	
	@RequestMapping("/demo/demo2.do")
	public String demo2(@RequestParam(value="devName") String devName,
			@RequestParam(value="devAge", required=false, defaultValue="0") int devAge,
			@RequestParam(value="devEmail", required=false) String devEmail,
			@RequestParam(value="devGender", required=false) String devGender,
			@RequestParam(value="devLang", required=false) String[] devLang,
								HttpServletRequest request) {
		
		request.setAttribute("dev", new Dev(devName, devAge, devEmail, devGender, devLang));
		
		System.out.println(devName+devAge+devEmail+devGender+devLang);
		
		return "demo/result";
	}
	
	@RequestMapping("/demo/demo3.do")
	public String demo3(Dev dev, Model model) { /*Model : 데이터를 공용으로 쓸 수 있는 객체*/
		
		System.out.println(dev);
		
		return "demo/result";
	}
	
	@RequestMapping("/demo/insertDev.do")
	public String insertDev(Dev dev)
	{
		int result=service.insertDev(dev);
		
		return "redirect:/"; /*리다이렉트*/
	}
	
	@RequestMapping("/demo/selectDemoList.do")
	public String devList(Model model)
	{
		List<Dev> list=service.devList();
		
		model.addAttribute("list", list);
		
		return "demo/devList";
	}
	
	@RequestMapping("/demo/updateDev.do")
	public String updateDev(int devNo, Model model)
	{
		Dev dev=service.selectOne(devNo);
		model.addAttribute("dev", dev);
		
		return "demo/form";
	}
	
	@RequestMapping("/demo/deleteDev.do")
	public String deleteDev(int devNo)
	{
		int result=service.deleteDev(devNo);
		System.out.println(devNo);
		
		return "redirect:/demo/selectDemoList.do";
	}
	
	@RequestMapping("/demo/updateDevEnd.do")
	public String updateDevEnd(Dev dev) {
		int result=service.updateDevEnd(dev);
		
		return "redirect:/demo/selectDemoList.do";
	}
	
	
}



