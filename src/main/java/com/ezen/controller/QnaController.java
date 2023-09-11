package com.ezen.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.dto.MemberVO;
import com.ezen.dto.QnaVO;
import com.ezen.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	QnaService qs;
	
	// 고객지원 클릭한 경우 메인페이지
	@RequestMapping(value="/qnaList")
	public ModelAndView qnaList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		/* 상단배너로부터 qnaList이동 시 */
		if(request.getParameter("first") !=null) {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		/* 검색key가 ""공백으로 온다면 세션에 저장되어있는 page,key초기화 */
		if(request.getParameter("key") == "") {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			System.out.println("null");
			mav.setViewName("member/loginForm");
		}else {
		//	paging, key, QnaList를 받아와야하므로 HashMap을 사용한다
		HashMap<String, Object> list = qs.getQna(request);
		mav.addObject("qnaList",list.get("qnaList"));
		mav.addObject("paging",list.get("paging"));
		mav.setViewName("qna/qnaList");
		}

		return mav;
	}
	
	// 게시글 선택/클릭 시
	@RequestMapping("/qnaDetail")
	public String qnaDetail( @RequestParam("qna_num") int qna_num,HttpServletRequest request,Model model ) {
		System.out.println("2");
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
		HashMap<String, Object> list =  qs.getQnaView( qna_num );
		model.addAttribute("qnaVO",list.get("qnaVO"));
		model.addAttribute("loginUser",mvo);
		}
		System.out.println("3");
		return "qna/qnaDetail";
	}
	
	@RequestMapping(value="/qnaDelete")
	@ResponseBody
	public String qnaDelete( @RequestParam("qna_num") int qna_num,HttpServletRequest request) {
		String result ="성공";
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			qs.qnaDelete(qna_num);
		}
		return result;
		
	}
	
	@RequestMapping(value="qnaWriteForm")
	public String qnaWriteForm( HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
		model.addAttribute("loginUser",mvo);
		return "qna/qnaWriteForm";
		}
	}
	
	@RequestMapping(value="/qnaInsert",method=RequestMethod.POST)
	public String qnaInsert(@ModelAttribute("dto") @Valid QnaVO qnavo,HttpServletRequest request,
			@RequestParam(value="pass",required=false) String pass,
			@RequestParam(value="check",required=false) String check) {
		System.out.println("2");
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
		return "member/loginForm";
		}else {
			if(check == null) {//체크박스에 체크를 안했다면
				qnavo.setPasscheck("N");
				qnavo.setPass("");
			}else {
				qnavo.setPasscheck("Y");
				qnavo.setPass(pass);
			}
			//사용자 id를 추출해서 qnaInsert매개변수로 넣어준다
			qs.qnaInsert( qnavo,mvo.getId() );
		}
			System.out.println("3");
		return "redirect:/qnaList";
	}
	
	@RequestMapping("/qnaUpdateForm")
	public ModelAndView qnaUpdateForm( @RequestParam("qna_num")int qna_num, HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		HashMap<String, Object> list =  qs.getQnaWithoutCount( qna_num ); 	// readcount 증가 xx
		
		mav.addObject("loginUser",mvo);
		mav.addObject("qnaVO",list.get("qnaVO"));
		
		mav.setViewName("qna/qnaUpdateForm");
		return mav;
		
	}
	
	//파일업로드 start
	@RequestMapping("/selectimg")
	public String selectimg() {
		System.out.println("selectimg스타트");
		return "qna/selectimg";
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/fileupload",method=RequestMethod.POST)
	public String fileupload( @RequestParam("image") MultipartFile file, HttpServletRequest request,Model model) {
		
		String path = context.getRealPath("/upload");	// 프로젝트 안에 webapp아래
		// 출력
		/*path=D:\JAVA01\springboot\SpringBoot_G16\src\main\webapp\ upload
		multipartFile=org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@7cf9eb2b*/
		
		//내용이 다른데 이름이 같으면 스프링부트 multipartfile에는 중복제거정책이 없어 ★현재시간 밀리초를 사용해서 파일이름뒤에 달아준다.★
		Calendar today = Calendar.getInstance();
		long t = today.getTimeInMillis();
		String filename = file.getOriginalFilename();	//파일이름 추출
		String fn1 = filename.substring(0,filename.indexOf("."));	// 파일이름과 확장자 분리
		String fn2 = filename.substring(filename.indexOf(".")+1);
		
		if( !file.isEmpty() ) {	// 업로드할 파일이 존재한다면
			String uploadPath = path + "/" + fn1 + t + "." + fn2;
			System.out.println("파일 저장 경로="+uploadPath);
			try {
				file.transferTo(new File(uploadPath)); //외부적인 요인이 있을 수 있으므로 예외처리가 있음
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		model.addAttribute("image",fn1 + t + "." + fn2);
		return "qna/completupload";
	}
	//파일업로드 end
	
	@RequestMapping("/qnaDetailWithoutCount")
	public ModelAndView qnaDetailWithoutCount( @RequestParam("qna_num") int qna_num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO mvo =(MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			/*if(avo != null) {	//관리자로 로그인 되었을때
				HashMap<String, Object> list =  qs.getQnaWithoutCount( qna_num );
				mav.addObject("qnaVO",list.get("qnaVO"));
				mav.setViewName("admin/qna/adminQnaDetail");
				return mav;
			}*/
			mav.setViewName("member/loginForm");
		}else {
		HashMap<String, Object> list =  qs.getQnaWithoutCount( qna_num );
		mav.addObject("qnaVO",list.get("qnaVO"));
		mav.setViewName("qna/qnaDetail");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value="/qnaUpdate", method=RequestMethod.POST)
	public String qnaUpdate(@ModelAttribute("dto") @Valid QnaVO qnavo,BindingResult result,Model model,
			@RequestParam(value="oldfilename",required=false) String oldfilename
	) {
		System.out.println("스타트");
		System.out.println("getfielderror: " + result.getFieldError("title"));
		System.out.println(qnavo.getContent());
		System.out.println(qnavo.getImgfilename());
		String url="qna/qnaUpdateForm";

		if(result.getFieldError("title") != null) {	// 타이틀에서 무슨에러가 넘어온다 -> 그래서 updateForm으로 돌아간다
			model.addAttribute("message",result.getFieldError("title").getDefaultMessage());
		}else if ( result.getFieldError("content") !=null ) {
			model.addAttribute("message","내용을 입력하세요");
		}else {
			if(qnavo.getImgfilename()==null||qnavo.getImgfilename().equals(""))
				qnavo.setImgfilename(oldfilename);
			
			qs.updateQna(qnavo);
			System.out.println("업데이트완료");
			url = "redirect:/qnaDetailWithoutCount?qna_num=" + qnavo.getQna_num();
			 } 
		System.out.println("이동하기전 url :"  +url);
		return url;
	}
	
	@RequestMapping(value="/passCheck")
	public ModelAndView passCheck( @RequestParam("qna_num") int qna_num) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qna_num",qna_num);
		mav.setViewName("qna/checkPass");
		return mav;
	}
	
	@RequestMapping(value="/qnaCheckPass", method=RequestMethod.POST)
	public String qnaCheckPass( @RequestParam("qna_num")int qna_num, @RequestParam("pass")String pass, Model model) {
		HashMap<String, Object> list = new HashMap<String, Object>();
		list = qs.getQnaWithoutCount( qna_num );
		System.out.println(list.get("qnaVO"));
		QnaVO qvo = (QnaVO) list.get("qnaVO");	// 게시글의 패스워드를 추출해서 아래에서, 입력한 비밀번호와 비교하기위함 
		
		if( qvo.getPass().equals(pass)) {
			model.addAttribute("qna_num",qna_num);
			return "qna/checkPassSuccess";
		}else {
			model.addAttribute("message","비밀번호가 맞지않습니다");
			return "qna/checkPass";
		}	
	}
	
}

