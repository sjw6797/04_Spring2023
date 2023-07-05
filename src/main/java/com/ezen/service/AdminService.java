package com.ezen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.dao.IAdminDao;
import com.ezen.dto.AdminVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.Paging;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public AdminVO getAdmin(String id) {
		return adao.getAdmin(id);
	}

	public ArrayList<MemberVO> getMemberList() {
		return adao.getMemberList();
	}

	public HashMap<String, Object> getMemberList(HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();

		// 다른 메뉴에서 현재 메뉴로 이동했을때, 세션에 저장된 page와 key 값을 모두 삭제합니다
		if (request.getParameter("first") != null) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}

		// 현재 페이지를 설정합니다. 파라미터로 전달된 값의 유무에 따라 이동할 페이지를 결정합니다.
		// 파라미터로 전달된 페이지가 있다면 그 페이지로,
		// 파라미터에 전달된 페이지가 없다면 세션에 저장된 페이지로,
		// 세션에 저장된 페이지도 없다면 1페이지로 이동합니다.
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if (session.getAttribute("page") != null) {
			page = (int) session.getAttribute("page");
		} else {
			page = 1;
			session.removeAttribute("page");
		}

		// 페이지와 비슷한 방식으로 검색어(key)도 설정합니다
		String key = "";
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if (session.getAttribute("key") != null) {
			key = (String) session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		}

		// Paging 객체를 설정합니다
		Paging paging = new Paging();
		paging.setPage(page);

		int count = adao.getAllCount("member", "id", key);
		paging.setTotalCount(count);
		paging.paging();

		List<MemberVO> memberList = adao.listMember(paging, key);
		result.put("memberList", memberList);
		result.put("paging", paging);
		result.put("key", key);

		return result;
	}

}
