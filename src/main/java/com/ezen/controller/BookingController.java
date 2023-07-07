package com.ezen.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//신정우작성-2023/07/05////////////// 
	//옥솔비 수정 7/6
		@RequestMapping("flightInfo1")// 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력    
		 public String flightInfo1( @ModelAttribute("dto") @Valid Item item,BindingResult result,Model model, HttpServletRequest request,
					@RequestParam(value="passenNum",required = false) String passenNum,@RequestParam("flag") String flag) {
			System.out.println("123123" + item.getDepAirportNm());
			System.out.println("123123" + item.getArrAirportNm());
			System.out.println("123123" + item.getDepPlandTime());

			HttpSession session = request.getSession();
			//세션에 저장되어있는 검색 결과 미리 삭제 -start
			session.removeAttribute("economyList");
			session.removeAttribute("prestigeList");
			session.removeAttribute("airlineList");
			session.removeAttribute("dep_list");
			session.removeAttribute("depAirportNm");
			session.removeAttribute("arrAirportNm");
			//세션에 저장되어있는 검색 결과 미리 삭제 -end
	
			String url = "booking/routeList_1";
			
			if(result.getFieldError("depAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
			else if(result.getFieldError("arrAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
			else {
	
				HashMap<String, Object>list = new HashMap<String, Object>();
				
				// 가는날 노선을 불러오기 
				list = bs.getInfo(item,flag);
				System.out.println( "in controller 가는날 일정 : "+ list.get("dep_list") );
				System.out.println("승객   " +  passenNum + "명");
				System.out.println("flag   : " +  flag);  
				session.setAttribute("passenNum", passenNum);
			
				
				if(flag.equals("1")) {	//편도인 경우  // 가는날 일정
					 model.addAttribute("dep_list",  list.get("dep_list"));
					 session.setAttribute("dep_list", list.get("dep_list"));
					 
					 session.setAttribute("flag", flag);
					 System.out.println("in controller 편도  가는날 일정 : "+list.get("dep_list"));			

					
				}else {	//왕복인 경우  // 가는날 일정

					model.addAttribute("dep_list", list.get("dep_list"));
				    model.addAttribute("return_list", list.get("return_list"));
			        session.setAttribute("dep_list", list.get("dep_list"));
			        session.setAttribute("return_list", list.get("return_list"));
			        
			        session.setAttribute("flag", flag);
			        System.out.println("in controller 왕복  가는날 일정 : "+list.get("dep_list"));
			        System.out.println("in controller 왕복  오는날 일정 : "+ list.get("return_list"));
	   				      

				}
				System.out.println("가는날 항공기편명:   " + item.getVihicleId());
				
				session.setAttribute("depAirportNm",item.getDepAirportNm());
				session.setAttribute("arrAirportNm",item.getArrAirportNm());

			// 이코노미/프레스티지 잔여좌석(table : view_economy,view_prestige) 출력-start
				ArrayList<ReservVO> economyList = bs.getAirLine("view_economy");	//이코노미 좌석정보 조회
				ArrayList<ReservVO> prestigeList = bs.getAirLine("view_prestige");	//프레스티지 좌석정보 조회
				ArrayList<ReservVO> airlineList = bs.getAirLine("airline");			//항공사 정보 및 이미지 조회
				
				session.setAttribute("economyList", economyList);	// 세션에 담은 이유는 다음페이지에서 그대로 사용할 예정으로
				session.setAttribute("prestigeList", prestigeList);	// 부하를 줄이기위해서
				session.setAttribute("airlineList", airlineList);
			// 이코노미/프레스티지 잔여좌석(table : view_economy,view_prestige) 출력-end

			}
			
			return url;
	}
	
	
		@RequestMapping("flightInfo2") // from "booking/routeList_1";
		public String flightInfo2(HttpServletRequest request,
									@RequestParam("depAirportNm") String depAirportNm1,
					   			    @RequestParam("arrAirportNm") String arrAirportNm1,
					   				@RequestParam("depPlandTime") String depPlandTime1,
					   				@RequestParam("arrPlandTime") String arrPlandTime1,
					   				@RequestParam("airlineNm") String airlineNm1,
					   				@RequestParam("economyCharge") String economyCharge1,
					   				@RequestParam("prestigeCharge") String prestigeCharge1,
					   				@RequestParam("vihicleId") String vihicleId1
													) {
			HttpSession session = request.getSession();
			String flag = (String) session.getAttribute("flag");
			
			session.setAttribute("depAirportNm1", depAirportNm1);
		    session.setAttribute("arrAirportNm1", arrAirportNm1);
		    session.setAttribute("depPlandTime1", depPlandTime1);
		    session.setAttribute("arrPlandTime1", arrPlandTime1);
		    session.setAttribute("airlineNm1", airlineNm1);
		    session.setAttribute("economyCharge1", economyCharge1);
		    session.setAttribute("prestigeCharge1", prestigeCharge1);
		    session.setAttribute("vihicleId1", vihicleId1);
		    
			if(flag.equals("1")) {	// routeList_1로부터 flag값을 받는경우(편도라면)			
				return "redirect:/insertPassen";			
			}

			return "booking/routeList_2";
			
		}
		
		@RequestMapping("flightInfo3")  // 왕복편 - 오는날 노선정보를 출력 // from "booking/routeList_2";
		public String flightInfo3(  @ModelAttribute("dto") @Valid Item item,
													BindingResult result,HttpServletRequest request,													
													@RequestParam(value="passenNum",required = false) String passenNum,													
													Model model) {
			
			HttpSession session = request.getSession();
			String flag = (String) session.getAttribute("flag");
		
			if(flag.equals("1")) {	
				if(result.getFieldError("depAirportNm1") !=null)
					model.addAttribute("message",result.getFieldError("depAirportNm1").getDefaultMessage());
					else if(result.getFieldError("arrAirportNm1") !=null)
					model.addAttribute("message",result.getFieldError("arrAirportNm1").getDefaultMessage());
					else {
								
							HashMap<String, Object>list = new HashMap<String, Object>();

							// 오는날 노선을 불러오기 
							list = bs.getInfo(item,flag);
							System.out.println( "오는날"+ list.get("dep_list") );
							System.out.println("승객   " +  passenNum + "명");
							
							HashMap<String, Object>paramMap = new HashMap<String, Object>();
			
						
								// 오는날 일정 
								
							paramMap.put("depAirportNm2", list.get("depAirportNm"));
							paramMap.put("arrAirportNm2", list.get("arrAirportNm"));
							paramMap.put("depPlandTime2", list.get("depPlandTime"));
							paramMap.put("arrPlandTime2", list.get("arrPlandTime"));
							paramMap.put("airlineNm2", list.get("airlineNm"));
							paramMap.put("economyCharge2", list.get("economyCharge"));
							paramMap.put("prestigeCharge2", list.get("prestigeCharge"));
							System.out.println("왕복 오는날 일정 : "+paramMap);		
							model.addAttribute("return_list", paramMap);
	   				        session.setAttribute("return_list", paramMap);

				
			          }
				
			}
		
			return "redirect:/insertPassen";
		}
	
	
	@PostMapping("insertPassen") 
	// 선택한 노선 저장하고  출력하기 	
	// 결제를 완료하고 나서 선택한 노선을 저장함 
	// 그전에는 세션에 보관 
	// 좌석정보 알려주기 (일반석 잔여좌석 :00개, 프레스티지석 잔여좌석 :00개)
     public ModelAndView insertPassen (HttpServletRequest request,
    		    @RequestParam("depAirportNm") String depAirportNm1,
			    @RequestParam("arrAirportNm") String arrAirportNm1,
				@RequestParam("depPlandTime") String depPlandTime1,
				@RequestParam("arrPlandTime") String arrPlandTime1,
				@RequestParam("airlineNm") String airlineNm1,
				@RequestParam("economyCharge") String economyCharge1,
				@RequestParam("prestigeCharge") String prestigeCharge1,
				@RequestParam("vihicleId") String vihicleId1) {
		         
					ModelAndView mav = new ModelAndView();
					HttpSession session = request.getSession();						
					HashMap<String,Object>paramMap = new HashMap<String,Object>();
					
		
					String flag=(String) session.getAttribute("flag");
					
					 paramMap.put("depAirportNm1",session.getAttribute("depAirportNm1") );
			    	 paramMap.put("arrAirportNm1",session.getAttribute("arrAirportNm1") );
			    	 paramMap.put("depPlandTime1",session.getAttribute("depPlandTime1") );
			    	 paramMap.put("arrPlandTime1",session.getAttribute("arrPlandTime1") );
			    	 paramMap.put("airlineNm1",session.getAttribute("airlineNm1") );
			    	 paramMap.put("economyCharge1", session.getAttribute("economyCharge1") );
			    	 paramMap.put("prestigeCharge1", session.getAttribute("prestigeCharge1") );
			    	 session.setAttribute("paramMap1", paramMap);
			    	 
		
					if(flag.equals("1")) {

				    	// 오는날
						
				    	 mav.addObject(paramMap);
						
					}else{

				    	// 오는날
						session.setAttribute("depAirportNm2", depAirportNm1);
					    session.setAttribute("arrAirportNm2", arrAirportNm1);
					    session.setAttribute("depPlandTime2", depPlandTime1);
					    session.setAttribute("arrPlandTime2", arrPlandTime1);
					    session.setAttribute("airlineNm2", airlineNm1);
					    session.setAttribute("economyCharge2", economyCharge1);
					    session.setAttribute("prestigeCharge2", prestigeCharge1);
					    session.setAttribute("vihicleId2", vihicleId1);
					    
					    
					    
				    	 paramMap.put("depAirportNm2",depAirportNm1 );
				    	 paramMap.put("arrAirportNm2",arrAirportNm1);
				    	 paramMap.put("depPlandTime2",depPlandTime1 );
				    	 paramMap.put("arrPlandTime2",arrPlandTime1 );
				    	 paramMap.put("airlineNm2",airlineNm1);
				    	 paramMap.put("economyCharge2", economyCharge1 );
				    	 paramMap.put("prestigeCharge2", prestigeCharge1 );
				    	 
				    	 
				    	 mav.addObject(paramMap);
				    	
						
					}
		
						mav.setViewName("booking/passengerForm");
						
					
					return mav; 
					
					
		
	}
	
	
	
	
	@PostMapping("insertReservation")
	//승객이 채운 승객의 개인 정보 + 승객이 선택한 노선정보를 reservation 테이블에 저장
	// 데이터 : 승객개인정보, 선택한 좌석, 선택한 노선
	//가는날, 오는날 따로 두개를 생성하기 
	// 결제완료 폼으로 이동하기 
	// 회원인 경우 
     public String insertReservation (HttpServletRequest request,
    		 																@RequestParam("name") String name ,
    		 																@RequestParam("phone") String phone ,
    		 																@RequestParam("email") String email,
    		 																@RequestParam("gender") String gender,
    		 																@RequestParam("sit1") String sit1,
    		 																@RequestParam("sit2") String sit2) {
		

					//ModelAndView mav = new ModelAndView();
					HttpSession session = request.getSession();
					
					String flag=(String) session.getAttribute("flag");
					
					
					// ■■■■■■■  세션에 저장한 가는날 노선정보 + 개인정보 insert
					
			    	 
			    	 HashMap<String,Object>paramMap1 = (HashMap<String, Object>) session.getAttribute("paramMap1");
	 
			    	 // 개인정보 
			    	 paramMap1.put("name",name );
			    	 paramMap1.put("phone",phone );
			    	 paramMap1.put("email",email );			    	 
			    	 paramMap1.put("gender", gender);
			    	 paramMap1.put("sit1",sit1 );
			    	 paramMap1.put("journey","dep" );
			    	 paramMap1.put("vihicleId1",session.getAttribute("vihicleId1") );
			    	
			    	 System.out.println("예약테이블에 들어갈 가는날 노선 paramMap1 : " + paramMap1);
			    	 

					// ■■■■■■■ 세션에 저장한 오는날 노선정보 + 개인정보 insert
			    	
			    	 //오는날 노선 
			    	 HashMap<String,Object>paramMap2 = new HashMap<String,Object>();
			    	 paramMap2.put("depAirportNm2",session.getAttribute("depAirportNm2") );
			    	 paramMap2.put("arrAirportNm2",session.getAttribute("arrAirportNm2")  );
			    	 paramMap2.put("depPlandTime2",session.getAttribute("depPlandTime2"));
			    	 paramMap2.put("arrPlandTime2",session.getAttribute("arrPlandTime2"));
			    	 paramMap2.put("airlineNm2", session.getAttribute("airlineNm2") );
			    	 paramMap2.put("economyCharge2", session.getAttribute("economyCharge2"));
			    	 paramMap2.put("prestigeCharge2", session.getAttribute("prestigeCharge2"));
			    	 paramMap2.put("vihicleId2",session.getAttribute("vihicleId2") );
			    	 // 개인정보 
			    	 paramMap2.put("name",name );
			    	 paramMap2.put("phone",phone );
			    	 paramMap2.put("email",email );			    	 
			    	 paramMap2.put("gender", gender);
			    	 paramMap2.put("sit2",sit2 );
			    	 paramMap2.put("journey","return" );
			    	 System.out.println("예약테이블에 들어갈 오는날 노선 paramMap2 : " + paramMap2);
			    	 
			    	 
			    	int result =0;
			    	if(flag.equals("1")) {	
			    				result = bs.insertReserv1(paramMap1);
			    	}else {
			    		result = bs.insertReserv(paramMap1,paramMap2);
			    		
			    	}
			    	 System.out.println("추가됨 : " + result);
					// 결제 완료창으로 이동 
					return "redirect:/finishReserv";
		
	}
	
	
	
	@PostMapping("finishReserv")
	
	   public String finishReserv() {

		return "booking/finishReserv.jsp";
	
		
	}
	
	@PostMapping("checkReserv")
	// 예약확인 	
	   public ModelAndView checkReserv( HttpServletRequest request,
			   												@RequestParam("name") String name,
			   												@RequestParam("phone") String phone) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("name : " + name);
		System.out.println("phone : " + phone);
		// 노선 검색하기 
		HashMap<String,Object>list = bs.getReserv(name,phone);
		mav.addObject("list1", list.get("list1"));
		mav.addObject("list2", list.get("list2"));
		
		
		// 노선 출력하기 
		
		
		
		mav.setViewName("booking/reservCheckResult");		
		return mav;
		
	}
	
	@PostMapping("updateReservForm")
	// 예약수정 : 승객 개인정보만 
	   public ModelAndView updateReserv( HttpServletRequest request,
			   												@RequestParam("reservNum_return") String reservNum_return,
			   												@RequestParam("reservNum_dep") String reservNum_dep) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.setAttribute("reservNum_return", reservNum_return);
		session.setAttribute("reservNum_dep", reservNum_dep);
		System.out.println("reservNum_return : " +reservNum_return);
		System.out.println("reservNum_dep : " +reservNum_dep);
		
		
		// 예약자 정보 불러오기 (예약번호를 통해)
		HashMap<String,Object>list = bs.updatePassenInfo(reservNum_return,reservNum_dep);
		
		mav.addObject("list1", list.get("list1"));
		mav.setViewName(reservNum_dep);
		
	return mav;
	
		
	}
	

	
	
	
	
}