package com.ezen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.dto.ReservVO;
import com.ezen.flight_info.Item;
import com.ezen.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	BookingService bs;

	@RequestMapping("searchForm")
	public String joinForm() {
		return "booking/searchPage_0.jsp";

	}

	// 신정우작성-2023/07/05//////////////
	// 옥솔비 수정 7/6
	@RequestMapping("flightInfo1") // 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력
	public String flightInfo1(@ModelAttribute("dto") @Valid Item item, BindingResult result, Model model,
			HttpServletRequest request, @RequestParam(value = "passenNum", required = false) String passenNum,
			@RequestParam(value = "flag", required = false) String flag) {

		HttpSession session = request.getSession();
		// 세션에 저장되어있는 검색 결과 미리 삭제 -start
		session.removeAttribute("airlineList");
		session.removeAttribute("dep_list");
		session.removeAttribute("depAirportNm");
		session.removeAttribute("arrAirportNm");
		// 세션에 저장되어있는 검색 결과 미리 삭제 -end

		String url = "booking/routeList_1";

		if (result.getFieldError("depAirportNm") != null)
			model.addAttribute("message", result.getFieldError("depAirportNm").getDefaultMessage());
		else if (result.getFieldError("arrAirportNm") != null)
			model.addAttribute("message", result.getFieldError("arrAirportNm").getDefaultMessage());
		else {

			HashMap<String, Object> list = new HashMap<String, Object>();

			// 가는날 노선을 불러오기
			list = bs.getInfo(item, flag);
			System.out.println("in controller 가는날 일정 : " + list.get("dep_list"));
			System.out.println("승객   " + passenNum + "명");
			System.out.println("flag   : " + flag);
			session.setAttribute("passenNum", passenNum);

			if (flag.equals("1")) { // 편도인 경우 // 가는날 일정
				model.addAttribute("dep_list", list.get("dep_list"));
				session.setAttribute("dep_list", list.get("dep_list"));
				session.setAttribute("flag", flag);
				System.out.println("in controller 편도  가는날 일정 : " + list.get("dep_list"));

			} else { // 왕복인 경우 // 가는날 일정

				model.addAttribute("dep_list", list.get("dep_list"));
				model.addAttribute("return_list", list.get("return_list"));
				session.setAttribute("dep_list", list.get("dep_list"));
				session.setAttribute("return_list", list.get("return_list"));

				session.setAttribute("flag", flag);
				System.out.println("in controller 왕복  가는날 일정 : " + list.get("dep_list"));
				System.out.println("in controller 왕복  오는날 일정 : " + list.get("return_list"));

			}
			System.out.println("가는날 항공기편명:   " + item.getVihicleId());

			session.setAttribute("depAirportNm", item.getDepAirportNm());
			session.setAttribute("arrAirportNm", item.getArrAirportNm());

			// 항공사 정보 및 이미지 조회 -start
			ArrayList<ReservVO> airlineList = bs.getAirLine("airline");
			session.setAttribute("airlineList", airlineList);
			// 항공사 정보 및 이미지 조회-end

		}

		return url;
	}

	@RequestMapping("flightInfo2") // from "booking/routeList_1";
	public String flightInfo2(HttpServletRequest request, @RequestParam("depAirportNm") String depAirportNm1,
			@RequestParam("arrAirportNm") String arrAirportNm1, @RequestParam("depPlandTime") String depPlandTime1,
			@RequestParam("arrPlandTime") String arrPlandTime1, @RequestParam("airlineNm") String airlineNm1,
			@RequestParam("economyCharge") String economyCharge1,
			@RequestParam("prestigeCharge") String prestigeCharge1, @RequestParam("vihicleId") String vihicleId1) {
		HttpSession session = request.getSession();
		String flag = (String) session.getAttribute("flag");

		// 편도 : 가는날
		session.setAttribute("depAirportNm1", depAirportNm1);
		session.setAttribute("arrAirportNm1", arrAirportNm1);
		session.setAttribute("depPlandTime1", depPlandTime1);
		session.setAttribute("arrPlandTime1", arrPlandTime1);
		session.setAttribute("airlineNm1", airlineNm1);
		session.setAttribute("economyCharge1", economyCharge1);
		session.setAttribute("prestigeCharge1", prestigeCharge1);
		session.setAttribute("vihicleId1", vihicleId1);

		if (flag.equals("1")) { // routeList_1로부터 flag값을 받는경우(편도라면)
			return "redirect:/insertPassen1";
		}

		return "booking/routeList_2";

	}

	@RequestMapping("flightInfo3") // 왕복편 - 오는날 노선정보를 출력 // from "booking/routeList_2";
	public String flightInfo3(@ModelAttribute("dto") @Valid Item item, BindingResult result, HttpServletRequest request,
			@RequestParam(value = "passenNum", required = false) String passenNum, Model model) {

		HttpSession session = request.getSession();
		String flag = (String) session.getAttribute("flag");

		if (flag.equals("1")) {
			if (result.getFieldError("depAirportNm1") != null)
				model.addAttribute("message", result.getFieldError("depAirportNm1").getDefaultMessage());
			else if (result.getFieldError("arrAirportNm1") != null)
				model.addAttribute("message", result.getFieldError("arrAirportNm1").getDefaultMessage());
			else {

				HashMap<String, Object> list = new HashMap<String, Object>();

				// 오는날 노선을 불러오기
				list = bs.getInfo(item, flag);
				System.out.println("오는날" + list.get("dep_list"));

				HashMap<String, Object> paramMap = new HashMap<String, Object>();

				// 오는날 일정

				paramMap.put("depAirportNm2", list.get("depAirportNm"));
				paramMap.put("arrAirportNm2", list.get("arrAirportNm"));
				paramMap.put("depPlandTime2", list.get("depPlandTime"));
				paramMap.put("arrPlandTime2", list.get("arrPlandTime"));
				paramMap.put("airlineNm2", list.get("airlineNm"));
				paramMap.put("economyCharge2", list.get("economyCharge"));
				paramMap.put("prestigeCharge2", list.get("prestigeCharge"));
				System.out.println("왕복 오는날 일정 : " + paramMap);
				model.addAttribute("return_list", paramMap);
				session.setAttribute("return_list", paramMap);

			}
		}
		return "redirect:/insertPassen";
	}

	@RequestMapping("/insertPassen1") // 편도 - 승객폼으로 이동
	// 편도는 오는날 일정 받아오는 데이터가 없기 때문에 따로 분리를 시킴
	// 편도 : 가는날 일정
	public ModelAndView insertPassen1(HttpServletRequest request,
			@RequestParam(value = "passenNum", required = false) String passenNum) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		System.out.println("in controller / insertPassen1 / request로 받아온 승객명수 : " + passenNum);

		String flag = (String) session.getAttribute("flag");
		session.setAttribute("passenNum1", passenNum);

		// 신정우 0711 - 출발날짜/도착날짜 데이터 입력될때 start
		String dep_time = (String) session.getAttribute("depPlandTime1"); // 출발날짜 세션정보 저장
		String arr_time = (String) session.getAttribute("arrPlandTime1"); // 도착날짜 세션정보 저장

		session.setAttribute("depPlandTime2", dep_time);
		session.setAttribute("arrPlandTime2", arr_time);

		String dep_time1 = dep_time.substring(0, 4);
		String dep_time2 = dep_time.substring(5, 7);
		String dep_time3 = dep_time.substring(8, 10);
		String dep_time4 = dep_time.substring(11, 13);
		String dep_time5 = dep_time.substring(14, 16);
		dep_time = dep_time1 + dep_time2 + dep_time3 + dep_time4 + dep_time5;
		System.out.println("가공후:" + dep_time);

		String arr_time1 = arr_time.substring(0, 4);
		String arr_time2 = arr_time.substring(5, 7);
		String arr_time3 = arr_time.substring(8, 10);
		String arr_time4 = arr_time.substring(11, 13);
		String arr_time5 = arr_time.substring(14, 16);
		arr_time = arr_time1 + arr_time2 + arr_time3 + arr_time4 + arr_time5;
		// 신정우 0711 -end

		paramMap.put("depAirportNm1", session.getAttribute("depAirportNm1"));
		paramMap.put("arrAirportNm1", session.getAttribute("arrAirportNm1"));
		/*
		 * paramMap.put("depPlandTime1",session.getAttribute("depPlandTime1") );
		 * paramMap.put("arrPlandTime1",session.getAttribute("arrPlandTime1") );
		 */
		paramMap.put("depPlandTime1", dep_time);
		paramMap.put("arrPlandTime1", arr_time);

		paramMap.put("airlineNm1", session.getAttribute("airlineNm1"));
		paramMap.put("economyCharge1", session.getAttribute("economyCharge1"));
		paramMap.put("prestigeCharge1", session.getAttribute("prestigeCharge1"));
		session.setAttribute("paramMap1", paramMap);
		if (flag.equals("1")) {

			if (passenNum != null) {
				System.out.println("in controller / insertPassen1 / (passenNum != null) request로 받아온 승객명수 : "
						+ session.getAttribute("passenNum1"));
				paramMap.put("passenNum", session.getAttribute("passenNum1"));
				mav.addObject(paramMap);
				mav.setViewName("booking/passengerForm1");
			} else {
				paramMap.put("passenNum", session.getAttribute("passenNum"));
				mav.addObject(paramMap);
				mav.setViewName("booking/passengerForm1");
			}
		}

		return mav;
	}

	@RequestMapping("/insertPassen")
	// 선택한 노선 저장하고 출력하기
	// 결제를 완료하고 나서 선택한 노선을 저장함
	// 그전에는 세션에 보관
	// 좌석정보 알려주기 (일반석 잔여좌석 :00개, 프레스티지석 잔여좌석 :00개)
	public ModelAndView insertPassen(HttpServletRequest request,
			// 왕복편 오는날 정보를 받아옴
			@RequestParam(value = "passenNum", required = false) String passenNum,

			@RequestParam(value = "depAirportNm", required = false) String depAirportNm1,
			@RequestParam(value = "arrAirportNm", required = false) String arrAirportNm1,
			@RequestParam(value = "depPlandTime", required = false) String depPlandTime1,
			@RequestParam(value = "arrPlandTime", required = false) String arrPlandTime1,
			@RequestParam(value = "airlineNm", required = false) String airlineNm1,
			@RequestParam(value = "economyCharge", required = false) String economyCharge1,
			@RequestParam(value = "prestigeCharge", required = false) String prestigeCharge1,
			@RequestParam(value = "vihicleId", required = false) String vihicleId1) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		// 신정우 0711 -start
		String dep_time = (String) session.getAttribute("depPlandTime1"); // 출발날짜 세션정보 저장
		String arr_time = (String) session.getAttribute("arrPlandTime1"); // 도착날짜 세션정보 저장

		String dep_time1 = dep_time.substring(0, 4);
		String dep_time2 = dep_time.substring(5, 7);
		String dep_time3 = dep_time.substring(8, 10);
		String dep_time4 = dep_time.substring(11, 13);
		String dep_time5 = dep_time.substring(14, 16);
		dep_time = dep_time1 + dep_time2 + dep_time3 + dep_time4 + dep_time5;
		System.out.println("가공후:" + dep_time);

		String arr_time1 = arr_time.substring(0, 4);
		String arr_time2 = arr_time.substring(5, 7);
		String arr_time3 = arr_time.substring(8, 10);
		String arr_time4 = arr_time.substring(11, 13);
		String arr_time5 = arr_time.substring(14, 16);
		arr_time = arr_time1 + arr_time2 + arr_time3 + arr_time4 + arr_time5;
		// 신정우 0711 -end

		// String flag=(String) session.getAttribute("flag");

		paramMap.put("depAirportNm1", session.getAttribute("depAirportNm1"));
		paramMap.put("arrAirportNm1", session.getAttribute("arrAirportNm1"));

		paramMap.put("depPlandTime1", dep_time);
		paramMap.put("arrPlandTime1", arr_time);
		/*
		 * paramMap.put("depPlandTime1",session.getAttribute("depPlandTime1") );
		 * paramMap.put("arrPlandTime1",session.getAttribute("arrPlandTime1") );
		 */

		paramMap.put("airlineNm1", session.getAttribute("airlineNm1"));
		paramMap.put("economyCharge1", session.getAttribute("economyCharge1"));
		paramMap.put("prestigeCharge1", session.getAttribute("prestigeCharge1"));
		paramMap.put("passenNum", session.getAttribute("passenNum"));
		session.setAttribute("paramMap1", paramMap);

		System.out.println("NULL인 경우 [1] :  " + depAirportNm1);

		if (depAirportNm1 == null && arrAirportNm1 == null && depPlandTime1 == null && arrPlandTime1 == null
				&& airlineNm1 == null && economyCharge1 == null && prestigeCharge1 == null && vihicleId1 == null) {

			session.setAttribute("vihicleId2", session.getAttribute("vihicleId2"));

			paramMap.put("depAirportNm2", session.getAttribute("depAirportNm2"));
			paramMap.put("arrAirportNm2", session.getAttribute("arrAirportNm2"));
			paramMap.put("depPlandTime2", session.getAttribute("depPlandTime2"));
			paramMap.put("arrPlandTime2", session.getAttribute("arrPlandTime2"));
			paramMap.put("airlineNm2", session.getAttribute("airlineNm2"));
			paramMap.put("economyCharge2", session.getAttribute("economyCharge2"));
			paramMap.put("prestigeCharge2", session.getAttribute("prestigeCharge2"));

			System.out.println("NULL인 경우" + session.getAttribute("depAirportNm2"));

		} else {
			session.setAttribute("depAirportNm2", depAirportNm1);
			session.setAttribute("arrAirportNm2", arrAirportNm1);
			session.setAttribute("depPlandTime2", depPlandTime1);
			session.setAttribute("arrPlandTime2", arrPlandTime1);
			session.setAttribute("airlineNm2", airlineNm1);
			session.setAttribute("economyCharge2", economyCharge1);
			session.setAttribute("prestigeCharge2", prestigeCharge1);
			session.setAttribute("vihicleId2", vihicleId1);

			paramMap.put("depAirportNm2", depAirportNm1);
			paramMap.put("arrAirportNm2", arrAirportNm1);
			paramMap.put("depPlandTime2", depPlandTime1);
			paramMap.put("arrPlandTime2", arrPlandTime1);
			paramMap.put("airlineNm2", airlineNm1);
			paramMap.put("economyCharge2", economyCharge1);
			paramMap.put("prestigeCharge2", prestigeCharge1);
		}

		System.out.println("in controller / insertPassen(왕복) / request로 받아온 승객명수 : " + passenNum);
		session.setAttribute("passenNum2", passenNum);

		if (passenNum != null) {
			System.out.println("in controller / insertPassen / (passenNum != null) request로 받아온 승객명수 : "
					+ session.getAttribute("passenNum2"));
			paramMap.put("passenNum1", session.getAttribute("passenNum2"));
			mav.addObject(paramMap);
			mav.addObject("passenNum1", session.getAttribute("passenNum2"));
			mav.setViewName("booking/passengerForm");

		} else {
			paramMap.put("passenNum", session.getAttribute("passenNum"));
			mav.addObject(paramMap);
			mav.setViewName("booking/passengerForm");
		}

		mav.setViewName("booking/passengerForm");

		return mav;

	}

	@RequestMapping(value = "/insertReservation", method = RequestMethod.POST)
	@ResponseBody
	// 승객이 채운 승객의 개인 정보 + 승객이 선택한 노선정보를 reservation 테이블에 저장
	// 데이터 : 승객개인정보, 선택한 좌석, 선택한 노선
	// 가는날, 오는날 따로 두개를 생성하기
	// 결제완료 폼으로 이동하기
	// ☆★☆★☆★☆★☆★ 승객이 1명만 남은 경우
	public Map<String, Object> insertReservation(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("gender") String gender, @RequestParam("sit1") String sit1,
			@RequestParam("sit2") String sit2) {

		// ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Map<String, Object> response = new HashMap<String, Object>();
		String flag = (String) session.getAttribute("flag");

		// ■■■■■■■ 세션에 저장한 가는날 노선정보 + 개인정보 insert

		System.out.println(name);
		System.out.println(session.getAttribute("paramMap1"));
		HashMap<String, Object> paramMap1 = (HashMap<String, Object>) session.getAttribute("paramMap1");

		// 개인정보
		paramMap1.put("name", name);
		paramMap1.put("phone", phone);
		paramMap1.put("email", email);
		paramMap1.put("gender", gender);
		paramMap1.put("sit1", sit1);
		paramMap1.put("journey", "dep");
		paramMap1.put("vihicleId1", session.getAttribute("vihicleId1"));

		System.out.println("예약테이블에 들어갈 가는날 노선 paramMap1 : " + paramMap1);

		// ■■■■■■■ 세션에 저장한 오는날 노선정보 + 개인정보 insert

		// 신정우 0711 -start
		String dep_time = (String) session.getAttribute("depPlandTime2"); // 출발날짜 세션정보 저장
		String arr_time = (String) session.getAttribute("arrPlandTime2"); // 도착날짜 세션정보 저장

		String dep_time1 = dep_time.substring(0, 4);
		String dep_time2 = dep_time.substring(5, 7);
		String dep_time3 = dep_time.substring(8, 10);
		String dep_time4 = dep_time.substring(11, 13);
		String dep_time5 = dep_time.substring(14, 16);
		dep_time = dep_time1 + dep_time2 + dep_time3 + dep_time4 + dep_time5;
		System.out.println("가공후:" + dep_time);

		String arr_time1 = arr_time.substring(0, 4);
		String arr_time2 = arr_time.substring(5, 7);
		String arr_time3 = arr_time.substring(8, 10);
		String arr_time4 = arr_time.substring(11, 13);
		String arr_time5 = arr_time.substring(14, 16);
		arr_time = arr_time1 + arr_time2 + arr_time3 + arr_time4 + arr_time5;
		// 신정우 0711 -end

		// 오는날 노선
		HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("depAirportNm2", session.getAttribute("depAirportNm2"));
		paramMap2.put("arrAirportNm2", session.getAttribute("arrAirportNm2"));

		// 0711 -start
		/*
		 * paramMap2.put("depPlandTime2",session.getAttribute("depPlandTime2"));
		 * paramMap2.put("arrPlandTime2",session.getAttribute("arrPlandTime2"));
		 */
		paramMap2.put("depPlandTime2", dep_time);
		paramMap2.put("arrPlandTime2", arr_time);
		// -end

		paramMap2.put("airlineNm2", session.getAttribute("airlineNm2"));
		paramMap2.put("economyCharge2", session.getAttribute("economyCharge2"));
		paramMap2.put("prestigeCharge2", session.getAttribute("prestigeCharge2"));
		paramMap2.put("vihicleId2", session.getAttribute("vihicleId2"));
		// 개인정보
		paramMap2.put("name", name);
		paramMap2.put("phone", phone);
		paramMap2.put("email", email);
		paramMap2.put("gender", gender);
		paramMap2.put("sit2", sit2);
		paramMap2.put("journey", "return");
		System.out.println("예약테이블에 들어갈 오는날 노선 paramMap2 : " + paramMap2);

		int result = 0;
		if (flag.equals("1")) {
			result = bs.insertReserv1(paramMap1);
		} else {
			result = bs.insertReserv(paramMap1, paramMap2);

		}
		System.out.println("추가됨 : " + result);
		// 결제 완료창으로 이동
		response.put("result", result);
		return response;

	}

	@RequestMapping("/finishReserv")

	public String finishReserv() {

		return "booking/finishReserv";

	}

	@GetMapping("checkReservForm")
	public String checkReservForm() {
		return "booking/reservCheck";
	}

	@GetMapping("checkReserv")
	// 예약확인
	public ModelAndView checkReserv(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone) {

		ModelAndView mav = new ModelAndView();
		System.out.println("name : " + name);
		System.out.println("phone : " + phone);
		// 노선 검색하기
		HashMap<String, Object> list = bs.getReserv(name, phone);
		HttpSession session = request.getSession();

		if (list.get("message") != null && "예약내역이 존재하지 않습니다.".equals(list.get("message"))) {
			session.removeAttribute("name");
			session.removeAttribute("phone");
			mav.addObject("message", "예약내역이 존재하지 않습니다");
			mav.setViewName("booking/reservCheck");
		} else {
			session.setAttribute("name", name);
			session.setAttribute("phone", phone);
			mav.addObject("list1", list.get("list1"));
			mav.addObject("list2", list.get("list2"));
			mav.setViewName("booking/reservCheckResult");
		}

		return mav;

	}

	@RequestMapping(value = "/regulation", method = RequestMethod.POST)
	public String regulation(HttpServletRequest request, @RequestParam("reservNum_dep") String reservNum_dep) {

		HttpSession session = request.getSession();
		session.setAttribute("reservNum_dep", reservNum_dep);
		System.out.println("reservNum_dep : " + reservNum_dep);

		// return "redirect:/regulationPage";
		return "booking/deleteRegulation";
		// return result;

		// return "redirect:/passenInfoForReserv";

	}

	@RequestMapping(value = "/regulationPage", method = RequestMethod.POST)
	public String regulationPage() {

		return "booking/deleteRegulation";

	}

	@RequestMapping("deleteReserv")
	public ModelAndView deleteReserv(HttpServletRequest request
	// @RequestParam(required = false, value = "reservNum_return")String
	// reservNum_return,
	// @RequestParam("reservNum_dep") String reservNum_dep
	) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String reservNum_dep = (String) session.getAttribute("reservNum_dep");

		// mav.setViewName("redirect:checkReserv?name=" + session.getAttribute("name") +
		// "&phone=" + session.getAttribute("phone"));
		mav.addObject("name", session.getAttribute("name"));
		mav.addObject("phone", session.getAttribute("phone"));
		mav.setViewName("redirect:/checkReserv");
		System.out.println("삭제하는 번호" + reservNum_dep);
		bs.deletePassenInfo1(reservNum_dep);

		return mav;

	}

	@RequestMapping("passenList") // 관리자가 승객목록
	public ModelAndView passenList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> list = bs.getPassenList();

		if (list.get("message") != null && "예약내역이 존재하지 않습니다.".equals(list.get("message"))) {

			System.out.println("값이 없습니다");
			mav.addObject("message", "승객이 존재하지 않습니다.");
		} else {
			mav.addObject("passenlist", list.get("passenlist"));
			System.out.println("IN 컨트롤러 || 관리자 페이지에 뜨는 예약정보들  :  " + list.get("passenlist"));
		}

		mav.setViewName("admin/reservation/pessengerList");
		return mav;

	}

	/*
	 * @RequestMapping(value="/searchPassenAdmin", method=RequestMethod.POST) // 관리자
	 * 페이지에서 승객이름을 가지고 승객의 정보를 조회 public ModelAndView
	 * searchPassenAdmin(HttpServletRequest request,
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam("phone") String phone) {
	 * 
	 * ModelAndView mav = new ModelAndView(); System.out.println("name : " + name);
	 * System.out.println("phone : " + phone);
	 * 
	 * HashMap<String,Object>list = bs.getReservAdmin(name,phone);
	 * mav.addObject("passenlist", list.get("passenlist"));
	 * mav.setViewName("admin/reservation/pessengerList"); return mav;
	 * 
	 * }
	 */

	@RequestMapping("deletePassenger") // In 관리자 페이지 : 승객 삭제
	public ModelAndView deletePassenger(HttpServletRequest request,
			@RequestParam(required = false, value = "reserv_nums") String reserv_nums) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		System.out.println("삭제될 예약번호들  :  " + reserv_nums);

		if (reserv_nums == null) {
			System.out.println("예약번호를 전달받지 못했습니다");

		} else {
			String[] reservNumArray = reserv_nums.split(",");
			for (String reservNum : reservNumArray) {
				System.out.println("삭제될 예약번호 : " + reservNumArray);
				int result = bs.deletePassenInfoInAdmin(reservNum);
				System.out.println("왕복 삭제여부 :  " + result);
			}

			mav.setViewName("redirect:/passenList");
		}

		return mav;

	}

}