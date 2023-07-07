package com.ezen.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.dao.IQnaDao;
import com.ezen.dto.Paging;
import com.ezen.dto.QnaVO;

@Service
public class QnaService {

	@Autowired
	IQnaDao qdao;

	public HashMap<String, Object> getQna(HttpServletRequest request) {
		
		// QNA리스트 화면 호출 시 필요한 페이징, 게시물 갯수, 게시글 내용 등을 서비스에서 전부 처리하고 controller로 리턴한다
		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		//Paging 객체작업
		int page = 1;
		if( request.getParameter("page") != null ) {
			page = Integer.parseInt( request.getParameter("page"));
			session.setAttribute("page", page);
		}else if(session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		System.out.println(request.getParameter("key"));
		String key="";
		if( request.getParameter("key") !=null) {
			key = (String) request.getParameter("key");
			session.setAttribute("key", key);
		}else if( session.getAttribute("key") !=null ){
			key =  (String) session.getAttribute("key");
		}else {
			session.removeAttribute("key");
		}
		
		//만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if( page != 1 && !key.equals("")) {
			System.out.println("53줄");
			page=1;
		}

		Paging paging = new Paging();
		paging.setPage(page);// 받은 page값이 있다면 현재페이지를 설정
		int count = qdao.getAllCount(key);
		paging.setTotalCount(count);
		paging.paging();	//private에서 public으로 바뀐 paging메소드를 수동으로 호출(기존에는 setTotal에서 paging()함수 호출)
		
		
		//작업이 끝난(paging클래스의 멤버변수 값들 계산) paging 객체로 게시물을 조회
		List<QnaVO> list = qdao.getQnaList( paging,key );
		result.put("key", key);
		result.put("qnaList", list);	//해쉬맵에 넣습니다.
		result.put("paging", paging);
	
		return result;
	}

	public HashMap<String, Object> getQnaView(int qna_num) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		// 1. 조회수를 1증가 시킵니다.
		qdao.plusOneReadCount( qna_num );
		System.out.println("---------1");
		// 2. 게시물을 조회합니다.
		QnaVO qvo =qdao.getQnaByQnaNum(qna_num);
		System.out.println("---------2");
		
		result.put("qnaVO", qvo);
		
		return result;
		
	}

	public void qnaDelete(int qna_num) {
		
		qdao.qnaDelete(qna_num);
		
	}

	public void qnaInsert(@Valid QnaVO qnavo, String id) {
		// view_qna에서 사용자 id를 이용해서 mnum을 조회한다(멤버에서해도됨)
		int result = qdao.getMemberBymnum(id);
		System.out.println(result);
		qdao.qnaInsert(qnavo,result);
		
	}

	public HashMap<String, Object> getQnaWithoutCount(int qna_num) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		QnaVO qvo =qdao.getQnaByQnaNum(qna_num);
		
		result.put("qnaVO", qvo);
		return result;
	}

	public void updateQna(@Valid QnaVO qnavo) {

		qdao.updateQna(qnavo);
		
	}

	public void addReply(int qna_num, String reply) {

		qdao.addReply(qna_num,reply);
		
	}
}
