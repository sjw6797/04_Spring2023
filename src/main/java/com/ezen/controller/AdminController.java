package com.ezen.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.dto.AdminVO;
import com.ezen.dto.BannerVO;
import com.ezen.dto.BoardVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.ProductVO;
import com.ezen.flight_info.FlightInfoService;
import com.ezen.service.AdminService;
import com.ezen.service.QnaService;

@Controller
public class AdminController {

	@Autowired
	AdminService as;

	@Autowired
	QnaService qs;

	@RequestMapping("admin")
	public String admin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");		
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
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		return url;
	}

	@RequestMapping("adminMemberList")
	public ModelAndView adminMemberList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		/* 검색key가 ""공백으로 온다면 세션에 저장되어있는 page,key초기화 */
		if (request.getParameter("key") == "") {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}

		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			HashMap<String, Object> result = as.getMemberList(request);
			mav.addObject("memberList", result.get("memberList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/member/memberList");
		}
		return mav;
	}

	@RequestMapping("adminBoardList")
	public ModelAndView adminBoardList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin");

		/* 검색key가 ""공백으로 온다면 세션에 저장되어있는 page,key초기화 */
		if (request.getParameter("key") == "") {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}

		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			HashMap<String, Object> result = as.getBoardList(request);
			mav.addObject("boardList", result.get("boardList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/board/adminBoardList");
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		return mav;
	}

	@RequestMapping("deleteMember")
	public String deleteMember(HttpServletRequest request, @RequestParam("member_num") int member_num) {
		HttpSession session = request.getSession();
		String url = "redirect:/admin";
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.deleteMember(member_num);
			url = "redirect:/adminMemberList";
		}
		return url;
	}

	@RequestMapping("useMember")
	public String useMember(HttpServletRequest request, @RequestParam("member_num") int member_num) {
		HttpSession session = request.getSession();
		String url = "redirect:/admin";
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.useMember(member_num);
			url = "redirect:/adminMemberList";
		}
		return url;
	}

	@RequestMapping("adminBoardForm")
	public String adminBoardForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String url = "redirect:/admin";
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			url = "admin/board/adminBoardForm";
		}
		return url;
	}

	@Autowired
	ServletContext context;

	@RequestMapping(value = "fileup", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup(@RequestParam("fileimage") MultipartFile file, HttpServletRequest request,
			Model model) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		String path = context.getRealPath("/upload");
		Calendar today = Calendar.getInstance();
		long t = today.getTimeInMillis();
		String filename = file.getOriginalFilename(); // 파일이름 추출
		String fn1 = filename.substring(0, filename.indexOf(".")); // 파일이름과 확장장 분리
		String fn2 = filename.substring(filename.indexOf(".") + 1);

		if (!file.isEmpty()) { // 업로드할 파일이 존재한다면
			String uploadPath = path + "/" + fn1 + t + "." + fn2;
			System.out.println("파일 저장 경로 = " + uploadPath);
			try {
				file.transferTo(new File(uploadPath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		result.put("STATUS", 1);
		result.put("FILENAME", fn1 + t + "." + fn2);
		return result;
	}

	@PostMapping("insertBoard")
	public ModelAndView insertBoard(HttpServletRequest request, @ModelAttribute("dto") BoardVO boardvo) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.insertBoard(boardvo);
			mav.setViewName("redirect:/adminBoardList");
		}
		return mav;
	}

	@RequestMapping("adminBoardDetail")
	public ModelAndView adminBoardDetail(HttpServletRequest request, @RequestParam("board_num") int board_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			BoardVO bvo = as.getBoard(board_num);
			mav.addObject("dto", bvo);
			mav.setViewName("admin/board/adminBoardDetail");
		}
		return mav;
	}

	@RequestMapping("adminBoardUpdateForm")
	public ModelAndView adminBoardUpdateForm(HttpServletRequest request, @RequestParam("board_num") int board_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			BoardVO bvo = as.getBoard(board_num);
			mav.addObject("dto", bvo);
			mav.setViewName("admin/board/adminBoardUpdateForm");
		}
		return mav;
	}

	@PostMapping("adminBoardUpdate")
	public ModelAndView adminBoardUpdate(HttpServletRequest request, @ModelAttribute("dto") BoardVO boardvo) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.updateBoard(boardvo);
			mav.setViewName("redirect:/adminBoardDetail?board_num=" + boardvo.getBoard_num());
		}
		return mav;
	}

	@RequestMapping("adminBoardDelete")
	public ModelAndView adminBoardDelete(HttpServletRequest request, @RequestParam("board_num") int board_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.deleteBoard(board_num);
			mav.setViewName("redirect:/adminBoardList");
		}
		return mav;
	}

	@RequestMapping("adminLogout")
	public String adminLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("adminLogin");
		return "redirect:/admin";
	}

	@RequestMapping("adminBannerList")
	public ModelAndView adminBannerList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			HashMap<String, Object> result = as.getBannerList(request);
			mav.addObject("bannerList", result.get("bannerList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/banner/adminBannerList");
		}
		return mav;
	}

	@RequestMapping("adminBannerForm")
	public ModelAndView adminBannerForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			mav.setViewName("admin/banner/adminbannerForm");
		}

		return mav;
	}

	@PostMapping("insertBanner")
	public ModelAndView insertBanner(HttpServletRequest request, @ModelAttribute("dto") BannerVO bannervo) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.insertBanner(bannervo);
			mav.setViewName("redirect:/adminBannerList");
		}
		return mav;
	}

	@PostMapping("updateBanner")
	public ModelAndView updateBanner(HttpServletRequest request, @ModelAttribute("dto") BannerVO bannervo,
			@RequestParam(value = "old_oseq", required = false) Integer old_oseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			int count = as.getBannerOseq(bannervo.getOseq());
			if (count > 0 && bannervo.getOseq() > 0) {
				if (count > 0) {
					int banner_num = as.selectBanner(bannervo.getOseq());
					BannerVO bvo = new BannerVO();
					bvo.setBanner_num(banner_num);
					bvo.setOseq(old_oseq);

					as.updateBanner(bvo);
					as.updateBanner(bannervo);
				}

			} else {
				as.updateBanner(bannervo);
			}
			mav.setViewName("redirect:/adminBannerList");
		}
		return mav;
	}

	@RequestMapping("adminBannerDelete")
	public ModelAndView adminBannerDelete(HttpServletRequest request, @RequestParam("banner_num") int banner_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.deleteBanner(banner_num);
			mav.setViewName("redirect:/adminBannerList");
		}
		return mav;
	}

	@RequestMapping("adminProductList")
	public ModelAndView adminProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			HashMap<String, Object> result = as.getProductList(request);
			mav.addObject("productList", result.get("productList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/product/adminProductList");
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		return mav;
	}

	@RequestMapping("adminProductForm")
	public ModelAndView adminProductForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			ArrayList<String> list = new ArrayList<String>();
			FlightInfoService fs = new FlightInfoService();
			list = FlightInfoService.getAirPortId();
			mav.addObject("countryList", list);

			mav.setViewName("admin/product/adminProductForm");
		}

		return mav;
	}

	@PostMapping("insertProduct")
	public ModelAndView insertProduct(HttpServletRequest request, @ModelAttribute("dto") ProductVO productvo) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.insertProduct(productvo);
			mav.setViewName("redirect:/adminProductList");
		}
		return mav;
	}

	@RequestMapping("adminProductUpdateForm")
	public ModelAndView adminProductUpdateForm(HttpServletRequest request,
			@RequestParam("product_num") int product_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			ProductVO pvo = as.getProduct(product_num);
			mav.addObject("dto", pvo);

			ArrayList<String> list = new ArrayList<String>();
			FlightInfoService fs = new FlightInfoService();
			list = FlightInfoService.getAirPortId();
			mav.addObject("countryList", list);

			mav.setViewName("admin/product/adminProductUpdateForm");
		}
		return mav;
	}

	@PostMapping("adminProductUpdate")
	public ModelAndView adminProductUpdate(HttpServletRequest request, @ModelAttribute("dto") ProductVO productvo) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			System.out.println(productvo);
			as.updateProduct(productvo);
			mav.setViewName("redirect:/adminProductDetail?product_num=" + productvo.getProduct_num());
		}
		return mav;
	}

	@RequestMapping("adminProductDetail")
	public ModelAndView adminProductDetail(HttpServletRequest request, @RequestParam("product_num") int product_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			ProductVO pvo = as.getProduct(product_num);
			mav.addObject("dto", pvo);
			mav.setViewName("admin/product/adminProductDetail");
		}
		return mav;
	}

	@RequestMapping("adminProductDelete")
	public ModelAndView adminProductDelete(HttpServletRequest request, @RequestParam("product_num") int product_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			as.deleteProduct(product_num);
			mav.setViewName("redirect:/adminProductList");
		}
		return mav;
	}

	@RequestMapping("adminBannerDetail")
	public ModelAndView adminBannerDetail(HttpServletRequest request, @RequestParam("banner_num") int banner_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			BannerVO bvo = as.getBanner(banner_num);
			mav.addObject("dto", bvo);
			mav.setViewName("admin/banner/adminBannerDetail");
		}
		return mav;
	}

//신정우 작성//
	@RequestMapping("/adminQnaList")
	public ModelAndView adminQnaList(HttpServletRequest request) {
		System.out.println("1");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		/* 상단배너로부터 qnaList이동 시 */
		// first==1로 오면 key,page 세션초기화
		if (request.getParameter("first") != null) {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}
		/* 검색key가 ""공백으로 온다면 세션에 저장되어있는 page,key초기화 */
		if (request.getParameter("key") == "") {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}

		/*
		 * AdminVO avo =(AdminVO) session.getAttribute("adminLogin"); if(avo == null) {
		 * mav.setViewName("admin/loginForm"); }else {
		 */
		// paging, key, QnaList를 받아와야하므로 HashMap을 사용한다
		HashMap<String, Object> list = qs.getQna(request);
		mav.addObject("qnaList", list.get("qnaList"));
		mav.addObject("paging", list.get("paging"));
		mav.addObject("key", list.get("key"));
		mav.setViewName("admin/qna/adminQnaList");
		/* } */
		System.out.println("2");
		return mav;
	}

	@RequestMapping("/adminQnaDetail")
	public String qnaDetail(@RequestParam("qna_num") int qna_num, HttpServletRequest request, Model model) {

		HashMap<String, Object> list = qs.getQnaView(qna_num);
		model.addAttribute("qnaVO", list.get("qnaVO"));
		return "admin/qna/adminQnaDetail";
	}

	@RequestMapping(value = "qnaReply", method = RequestMethod.POST) // Qna문의사항에 대한 답변작성
	public String qnaReply(@RequestParam("reply") String reply, @RequestParam("qna_num") int qna_num, Model model) {
		// 작성하면 해당 qna_num게시물에 reply-insert, readcount안올라가고, result='Y'로 변경
		qs.addReply(qna_num, reply);

		/* model.addAttribute("qna_num",qna_num); */
		return "redirect:/adminQnaDetailWithoutCount?qna_num=" + qna_num;
	}

	@RequestMapping("/adminQnaDetailWithoutCount")
	public ModelAndView qnaDetailWithoutCount(@RequestParam("qna_num") int qna_num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> list = qs.getQnaWithoutCount(qna_num);
		mav.addObject("qnaVO", list.get("qnaVO"));
		mav.setViewName("admin/qna/adminQnaDetail");

		return mav;
	}
	///////////////////////////////////////////////////////////////////////////////

	@RequestMapping("searchPassenAdmin")
	public ModelAndView searchPassenAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		/* 검색key가 ""공백으로 온다면 세션에 저장되어있는 page,key초기화 */
		if (request.getParameter("key") == "") {
			session.removeAttribute("key");
			session.removeAttribute("page");
		}

		mav.setViewName("redirect:/admin");
		String id = (String) session.getAttribute("adminLogin");
		if (id == null) {
		} else {
			HashMap<String, Object> result = as.getpassenList(request);
			mav.addObject("passenlist", result.get("passenList"));
			mav.addObject("paging", result.get("paging"));
			mav.addObject("key", result.get("key"));
			mav.setViewName("admin/reservation/pessengerList");
		}
		return mav;
	}

	// 수정부분
	@RequestMapping("adminMemberUpdateForm")
	public ModelAndView adminMemberUpdateForm(@RequestParam("member_num") int member_num) {

		ModelAndView mav = new ModelAndView();
		MemberVO mvo = as.getAdminMember(member_num);
		mav.addObject("loginUser", mvo);
		mav.setViewName("admin/member/memberUpdateForm");

		return mav;
	}

}
