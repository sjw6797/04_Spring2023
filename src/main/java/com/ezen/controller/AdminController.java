package com.ezen.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.dto.AdminVO;
import com.ezen.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService as;

	@RequestMapping("admin")
	public String admin() {
		return "admin/loginForm";
	}

	@PostMapping("adminLogin")
	public String adminLogin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, Model model,
			HttpServletRequest request) {
		String url = "admin/loginForm";

		AdminVO avo = as.getAdmin(id);

		if (avo == null) {
			model.addAttribute("message", "아이디가 없습니다");
		} else if (!pwd.equals(avo.getPwd())) {
			model.addAttribute("message", "비밀번호가 틀렸습니다");
		} else {
			url = "redirect:/adminMemberList";
			HttpSession session = request.getSession();
			session.setAttribute("adminLogin", id);
		}
		return url;
	}

	@RequestMapping("adminMemberList")
	public ModelAndView adminMemberList(HttpServletRequest request) {
		System.out.println("1");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin");
		
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			System.out.println("2");
			HashMap<String, Object> result = as.getMemberList(request);
			mav.addObject("memberList", result.get("memberList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/member/memberList");
			// Controller 는 Service 가 작업해서 보내준 결과들을 mav 에 잘 넣어서 목적지로 이동만 합니다.
		}
		return mav;
	}

}
