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
import com.ezen.dto.BannerVO;
import com.ezen.dto.BoardVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.Paging;
import com.ezen.dto.ProductVO;
import com.ezen.dto.ReservVO;

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

		// 만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if (page != 1 && !key.equals("")) {
			page = 1;
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

	public HashMap<String, Object> getBoardList(HttpServletRequest request) {
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
		// 만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if (page != 1 && !key.equals("")) {
			page = 1;
		}

		// Paging 객체를 설정합니다
		Paging paging = new Paging();
		paging.setPage(page);

		int count = adao.getAllCount("board", "title", key);
		paging.setTotalCount(count);
		paging.paging();

		List<MemberVO> boardList = adao.listBoard(paging, key);
		result.put("boardList", boardList);
		result.put("paging", paging);
		result.put("key", key);

		return result;
	}

	public void deleteMember(int member_num) {
		adao.deleteMember(member_num);
	}

	public void useMember(int member_num) {
		adao.useMember(member_num);
	}

	public void insertBoard(BoardVO boardvo) {
		adao.insertBoard(boardvo);
	}

	public BoardVO getBoard(int board_num) {
		return adao.getBoard(board_num);
	}

	public void updateBoard(BoardVO boardvo) {
		adao.updateBoard(boardvo);
	}

	public void deleteBoard(int board_num) {
		adao.deleteBoard(board_num);
	}

	public HashMap<String, Object> getBannerList(HttpServletRequest request) {
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
		// 만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if (page != 1 && !key.equals("")) {
			page = 1;
		}

		// Paging 객체를 설정합니다
		Paging paging = new Paging();
		paging.setPage(page);

		int count = adao.getAllCount("banner", "name", key);
		paging.setTotalCount(count);
		paging.paging();

		List<BannerVO> bannerList = adao.listBanner(paging, key);
		result.put("bannerList", bannerList);
		result.put("paging", paging);
		result.put("key", key);

		return result;
	}

	public void insertBanner(BannerVO bannervo) {
		adao.insertBanner(bannervo);
	}

	public int getBannerOseq(int oseq) {
		return adao.getBannerOseq(oseq);
	}

	public void updateBanner(BannerVO bannervo) {
		adao.updateBanner(bannervo);

	}

	public int selectBanner(int i) {

		return adao.selectBanner(i);

	}

	public void updateBannerOseq(int banner_num, int oseq) {
		adao.updateBannerOseq(banner_num, oseq);
	}

	public void deleteBanner(int banner_num) {
		adao.deleteBanner(banner_num);

	}

	public HashMap<String, Object> getProductList(HttpServletRequest request) {
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
		// 만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if (page != 1 && !key.equals("")) {
			page = 1;
		}

		// Paging 객체를 설정합니다
		Paging paging = new Paging();
		paging.setPage(page);

		int count = adao.getAllCount("product", "title", key);
		paging.setTotalCount(count);
		paging.paging();

		List<ProductVO> productList = adao.listProduct(paging, key);
		result.put("productList", productList);
		result.put("paging", paging);
		result.put("key", key);

		return result;
	}

	public void insertProduct(ProductVO productvo) {
		adao.insertProduct(productvo);

	}

	public void updateProduct(ProductVO productvo) {
		adao.updateProduct(productvo);

	}

	public ProductVO getProduct(int product_num) {
		return adao.getProduct(product_num);
	}

	public void deleteProduct(int product_num) {
		adao.deleteProduct(product_num);
	}

	public BannerVO getBanner(int banner_num) {
		return adao.getBanner(banner_num);

	}

	public HashMap<String, Object> getpassenList(HttpServletRequest request) {
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
		// 만약 1페이지를 제외한 다른 페이지에서 key를 가지고 검색한 경우 page를 1로 설정
		if (page != 1 && !key.equals("")) {
			page = 1;
		}

		// Paging 객체를 설정합니다
		Paging paging = new Paging();
		paging.setPage(page);

		int count = adao.getAllCountReservation("reservation", "r_name", "r_phone", key);
		paging.setTotalCount(count);
		paging.paging();

		List<ReservVO> reservList = adao.listReservation(paging, key);
		result.put("passenList", reservList);
		result.put("paging", paging);
		result.put("key", key);

		return result;
	}

	public MemberVO getAdminMember(int member_num) {
		return adao.getAdminMember(member_num);
	}

}
