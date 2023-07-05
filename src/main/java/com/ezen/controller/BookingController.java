package com.ezen.controller;


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

import com.ezen.flight_info.Item;
import com.ezen.service.BookingService;


@Controller
public class BookingController {
	
	
	@Autowired
	BookingService bs;

	
	@RequestMapping("searchForm")
	public String joinForm() {
		System.out.println("1");
		return "booking/searchPage_0";
		
	}
	
	//신정우작성-2023/07/05///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("flightInfo")// 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력  
	 public String flightInfo1( @ModelAttribute("dto") @Valid Item item,BindingResult result,Model model, HttpServletRequest request,
				@RequestParam(value="passenNum",required = false) String passenNum,@RequestParam(value="flag",required = false) String flag) {
		
		String url = "booking/routeList_1";
		
		if(result.getFieldError("depAirportNm") !=null)
		model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
		else if(result.getFieldError("arrAirportNm") !=null)
		model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
		else {
			HttpSession session = request.getSession();
			HashMap<String, Object>list = new HashMap<String, Object>();
			//우선 현재 넘어오는 플래그는 없으니 플래그를 1로 수동설정
			flag="1";	// flag : 1 -> 편도 controller -> routeList_1 -> insertPassen
						// flag : 2 -> 왕복 controller -> routeList_1(flag="3"가지고 감) -> Controller insertPassen
			
			// 출발일정 노선을 불러오기 
			list = bs.getInfo(item,flag);
			System.out.println( "출발하는날"+ list.get("dep_list") );
			System.out.println("승객   " +  passenNum + "명");
			
			if(flag=="1") {	//편도인 경우
				session.setAttribute("dep_list",list.get("dep_list") );
				session.setAttribute("depAirportNm",list.get("depAirportNm") );
				session.setAttribute("arrAirportNm",list.get("arrAirportNm") );
				model.addAttribute("flag","1");
			}else {	//왕복인 경우
				session.setAttribute(flag, url);
				session.setAttribute("dep_list",list.get("dep_list") );
				session.setAttribute("return_list",list.get("return_list"));	
				session.setAttribute("depAirportNm",list.get("depAirportNm") );
				session.setAttribute("arrAirportNm",list.get("arrAirportNm") );
			}
			
			System.out.println("가는날 항공기편명:   " + item.getVihicleId());
		}
		return url;
}
	@RequestMapping("flightInfo2")
	public String flightInfo2( @RequestParam("flag") String flag) {
		if(flag !=null) {
			return "redirect:/insertPassen";
		}
		return "booking/routeList_2";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	
	@RequestMapping("flightInfo1")
	// 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력  
	
	// 가는날 노선 
	   public String flightInfo1( @ModelAttribute("dto") @Valid Item item,BindingResult result,Model model, HttpServletRequest request,
			   												@RequestParam("passenNum") String passenNum  ) {
	      
	      if(result.getFieldError("depAirportNm") !=null)
	         model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
	      else if(result.getFieldError("arrAirportNm") !=null)
	         model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
	      else {
	        
	    	  HttpSession session = request.getSession();
		    	 String depAirportNm = item.getDepAirportNm(); // 출발 공항명 가져오기
		    	 String arrAirportNm = item.getArrAirportNm(); // 도착 공항명 가져오기
		    	 Long depPlandTime = item.getDepPlandTime(); //가는날 
		    	 Long returnPlandTime = item.getReturnPlandTime(); // 오는날 
		    	 String airPlaneId1 = item.getVihicleId(); // 가는날 항공기 ID

		    	 session.setAttribute("depAirportNm", depAirportNm); // 세션 속성에 출발 공항명 설정
		    	 session.setAttribute("arrAirportNm", arrAirportNm); // 
		    	 session.setAttribute("depPlandTime", depPlandTime); // 
		    	 session.setAttribute("returnPlandTime", returnPlandTime); // 
		    	 session.setAttribute("passenNum", passenNum); //
		    	 session.setAttribute("airPlaneId1", airPlaneId1); // 가는날 항공기 ID
		    	
	    	 
	         HashMap<String, Object>list = new HashMap<String, Object>();
	        
	         
	         // 출발일정 노선을 불러오기 
	         list = bs.getInfo(depAirportNm,arrAirportNm,depPlandTime, returnPlandTime);
	         System.out.println( "출발하는날"+ list.get("dep_list") );
	         System.out.println("승객   " +  passenNum + "명");
	         
	         
	         System.out.println("가는날 항공기편명:   " +  airPlaneId1);
	         model.addAttribute("dep_list",list.get("dep_list") );
	         
	        
	          model.addAttribute("depAirportNm",list.get("depAirportNm") );
	          model.addAttribute("arrAirportNm",list.get("arrAirportNm") );
	       
	         
	      }
	      return "booking/routeList_1";
	}
	
	
	@PostMapping("flightInfo2")
	// 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력  
	
	// 오는날 노선 
	   public String flightInfo2( @ModelAttribute("dto") @Valid Item item,
			   				BindingResult result,Model model,
			   				HttpServletRequest request,
			   				@RequestParam("depAirportNm") String depAirportNm1,
			   			    @RequestParam("arrAirportNm") String arrAirportNm1,
			   				@RequestParam("depPlandTime") String depPlandTime1,
			   				@RequestParam("arrPlandTime") String arrPlandTime1,
			   				@RequestParam("airlineNm") String airlineNm1,
			   				@RequestParam("economyCharge") String economyCharge1,
			   				@RequestParam("prestigeCharge") String prestigeCharge1
			   				) {
	      
	      if(result.getFieldError("depAirportNm") !=null)
	         model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
	      else if(result.getFieldError("arrAirportNm") !=null)
	         model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
	      else {
	        
	    	  HttpSession session = request.getSession();
		    	 String depAirportNm = (String) session.getAttribute("depAirportNm"); // 출발 공항명 가져오기
		    	 String arrAirportNm = (String) session.getAttribute("arrAirportNm");// 도착 공항명 가져오기
		    	 Long depPlandTime = (Long) session.getAttribute("depPlandTime");//가는날 
		    	 Long returnPlandTime = (Long) session.getAttribute("returnPlandTime");// 오는날 
		    	 
		    	 String airPlaneId2 = item.getVihicleId();  // 오는날 항공기 ID
		    	 session.setAttribute("airPlaneId1", airPlaneId2); // 오는날 항공기 ID
		    	 System.out.println("오는날 항공기편명:   " +  airPlaneId2);
		    	 
		    // 가는날 노선일정을 세션에 저장하기 
		    	 
		    	 session.setAttribute("depAirportNm1", depAirportNm1); // 출발지 
		    	 session.setAttribute("arrAirportNm1", arrAirportNm1); // 도착지 
		    	 session.setAttribute("depPlandTime1", depPlandTime1); // 출발일시 
		    	 session.setAttribute("arrPlandTime1", arrPlandTime1); // 도착일시 
		    	 session.setAttribute("airlineNm1", airlineNm1); //  항공사 이름 
		    	 session.setAttribute("economyCharge1", economyCharge1); // 이코노미석 
		    	 session.setAttribute("prestigeCharge1", prestigeCharge1); // 프레스티지석 
		    	 
 
	         HashMap<String, Object>list = new HashMap<String, Object>();
	        
	         
	      // 돌아오는날 일정 노선을 불러오기 
	         list = bs.getInfo(depAirportNm,arrAirportNm,depPlandTime, returnPlandTime);
	        
	         
	       	  System.out.println("돌아오는날"+list.get("return_list") );
	       	  
	       	  System.out.println("가는날 일정 : "+depAirportNm1+""+arrAirportNm1 +""+ depPlandTime1+""+ arrPlandTime1+""+ airlineNm1+""+ economyCharge1+""+prestigeCharge1);
	          model.addAttribute("return_list",list.get("return_list") );
	          model.addAttribute("depAirportNm",list.get("depAirportNm") );
	          model.addAttribute("arrAirportNm",list.get("arrAirportNm") );
	          
	          
	          model.addAttribute("depAirportNm1", depAirportNm1); // 출발지 
	          model.addAttribute("arrAirportNm1", arrAirportNm1); // 도착지 
	          model.addAttribute("depPlandTime1", depPlandTime1); // 출발일시 
	          model.addAttribute("arrPlandTime1", arrPlandTime1); // 도착일시 
	          model.addAttribute("airlineNm1", airlineNm1); //  항공사 이름 
	          model.addAttribute("economyCharge1", economyCharge1); // 이코노미석 
	          model.addAttribute("prestigeCharge1", prestigeCharge1); // 프레스티지석 
	       
	         
	      }
	      return "booking/routeList_2";
	}
*/	
	
	
	@PostMapping("insertPassen") 
	// 선택한 노선 저장하고  출력하기 	
	// 결제를 완료하고 나서 선택한 노선을 저장함 
	// 그전에는 세션에 보관 
	// 좌석정보 알려주기 (일반석 잔여좌석 :00개, 프레스티지석 잔여좌석 :00개)
     public ModelAndView insertPassen (HttpServletRequest request,@ModelAttribute("dto") @Valid Item item,
    		
																		@RequestParam("depAirportNm") String depAirportNm2,
																	    @RequestParam("arrAirportNm") String arrAirportNm2,
																		@RequestParam("depPlandTime") String depPlandTime2,
														   				@RequestParam("arrPlandTime") String arrPlandTime2,
																		@RequestParam("airlineNm") String airlineNm2,
																		@RequestParam("economyCharge") String economyCharge2,
																		@RequestParam("prestigeCharge") String prestigeCharge2) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		/*
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if(mvo == null ) {
			
			mav.setViewName("loginForm");
			
		} else {
			*/
			//1. 가는날 노선을 불러오기 
			
	
    	 String depAirportNm1 = (String) session.getAttribute("depAirportNm1"); // 출발 공항명 가져오기
    	 String arrAirportNm1 = (String) session.getAttribute("arrAirportNm1");// 도착 공항명 가져오기
    	 String depPlandTime1 = (String) session.getAttribute("depPlandTime1");//가는날 
    	 String arrPlandTime1 =  (String) session.getAttribute("arrPlandTime1");// 오는날 
    	 String airlineNm1= (String) session.getAttribute("airlineNm1"); // 이용항공사 이름 
    	 String  economyCharge1= (String) session.getAttribute("economyCharge1"); // 이코노미석 
    	 String prestigeCharge1 = (String) session.getAttribute("prestigeCharge1"); // 프레스티지석 
    	 
    	
		
		
		//1-1. 오는날 노선을 세션에 저장하기 
    	 
    	 session.setAttribute("depAirportNm2", depAirportNm2); // 출발지 
    	 session.setAttribute("arrAirportNm2", arrAirportNm2); // 도착지 
    	 session.setAttribute("depPlandTime2", depPlandTime2); // 출발일시 
    	 session.setAttribute("arrPlandTime2", arrPlandTime2); // 도착일시  	 
    	 session.setAttribute("airlineNm2", airlineNm2); //  항공사 이름 
    	 session.setAttribute("economyCharge2", economyCharge2); // 이코노미석 
    	 session.setAttribute("prestigeCharge2", prestigeCharge2); // 프레스티지석 
    	
    	
					
		//2. 선택한 노선을 화면에 출력하기 
					
    	 // 가는날 
    	 
    	 HashMap<String,Object>paramMap = new HashMap<String,Object>();
    	 paramMap.put("depAirportNm1",depAirportNm1 );
    	 paramMap.put("arrAirportNm1",arrAirportNm1  );
    	 paramMap.put("depPlandTime1",depPlandTime1);
    	 paramMap.put("arrPlandTime1",arrPlandTime1 );
    	 paramMap.put("airlineNm1",airlineNm1 );
    	 paramMap.put("economyCharge1", economyCharge1);
    	 paramMap.put("prestigeCharge1", prestigeCharge1);
    	 
    	 
    	 
    	 // 오는날 
    	 paramMap.put("depAirportNm2",depAirportNm2 );
    	 paramMap.put("arrAirportNm2",arrAirportNm2  );
    	 paramMap.put("depPlandTime2",depPlandTime2);
    	 paramMap.put("arrPlandTime2",arrPlandTime2 );
    	 paramMap.put("airlineNm2",airlineNm2 );
    	 paramMap.put("economyCharge2", economyCharge2);
    	 paramMap.put("prestigeCharge2", prestigeCharge2);
   
    	 
    	 
			
					
			//3. 승객 입력폼으로 이동하기 
    	    mav.addObject(paramMap);
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
					
					
					
					
					// ■■■■■■■  세션에 저장한 가는날 노선정보 + 개인정보 insert
					 String depAirportNm1 = (String) session.getAttribute("depAirportNm1"); // 출발 공항명 가져오기
			    	 String arrAirportNm1 = (String) session.getAttribute("arrAirportNm1");// 도착 공항명 가져오기
			    	 String depPlandTime1 = (String) session.getAttribute("depPlandTime1");//가는날 
			    	 String arrPlandTime1 =  (String) session.getAttribute("arrPlandTime1");// 오는날 
			    	 String airlineNm1= (String) session.getAttribute("airlineNm1"); // 이용항공사 이름 
			    	 String  economyCharge1= (String) session.getAttribute("economyCharge1"); // 이코노미석 
			    	 String prestigeCharge1 = (String) session.getAttribute("prestigeCharge1"); // 프레스티지석 		    
			    	 String  airPlaneId1 = (String) session.getAttribute("airPlaneId1");
			    	 
			    	 HashMap<String,Object>paramMap1 = new HashMap<String,Object>();
			    	 // 가는날 노선 
			    	 paramMap1.put("depAirportNm1",depAirportNm1 );
			    	 paramMap1.put("arrAirportNm1",arrAirportNm1  );
			    	 paramMap1.put("depPlandTime1",depPlandTime1);
			    	 paramMap1.put("arrPlandTime1",arrPlandTime1 );
			    	 paramMap1.put("airlineNm1",airlineNm1 );
			    	 paramMap1.put("economyCharge1", economyCharge1);
			    	 paramMap1.put("prestigeCharge1", prestigeCharge1);
			    	 paramMap1.put("airPlaneId1", airPlaneId1);
			    	 // 개인정보 
			    	 paramMap1.put("name",name );
			    	 paramMap1.put("phone",phone );
			    	 paramMap1.put("email",email );			    	 
			    	 paramMap1.put("gender", gender);
			    	 paramMap1.put("sit1",sit1 );
			    	 paramMap1.put("journey","dep" );
			    	
			    	 System.out.println("paramMap1 : " + paramMap1);
			    	 
			    	 
			    	
			    	
			    	

					
					// ■■■■■■■ 세션에 저장한 오는날 노선정보 + 개인정보 insert
			    	 String depAirportNm2 = (String) session.getAttribute("depAirportNm2"); // 출발 공항명 가져오기
			    	 String arrAirportNm2 = (String) session.getAttribute("arrAirportNm2");// 도착 공항명 가져오기
			    	 String depPlandTime2 = (String) session.getAttribute("depPlandTime2");//가는날 
			    	 String arrPlandTime2 =  (String) session.getAttribute("arrPlandTime2");// 오는날 
			    	 String airlineNm2= (String) session.getAttribute("airlineNm2"); // 이용항공사 이름 
			    	 String  economyCharge2= (String) session.getAttribute("economyCharge2"); // 이코노미석 
			    	 String prestigeCharge2 = (String) session.getAttribute("prestigeCharge2"); // 프레스티지석 			
			    	 String  airPlaneId2 = (String) session.getAttribute("airPlaneId2");
			    	 //오는날 노선 
			    	 HashMap<String,Object>paramMap2 = new HashMap<String,Object>();
			    	 paramMap2.put("depAirportNm2",depAirportNm2 );
			    	 paramMap2.put("arrAirportNm2",arrAirportNm2  );
			    	 paramMap2.put("depPlandTime2",depPlandTime2);
			    	 paramMap2.put("arrPlandTime2",arrPlandTime2 );
			    	 paramMap2.put("airlineNm2",airlineNm2 );
			    	 paramMap2.put("economyCharge2", economyCharge2);
			    	 paramMap2.put("prestigeCharge2", prestigeCharge2);
			    	 paramMap2.put("airPlaneId2", airPlaneId2);
			    	 // 개인정보 
			    	 paramMap2.put("name",name );
			    	 paramMap2.put("phone",phone );
			    	 paramMap2.put("email",email );			    	 
			    	 paramMap2.put("gender", gender);
			    	 paramMap2.put("sit2",sit2 );
			    	 paramMap2.put("journey","return" );
			    	 
			    	 System.out.println("paramMap2 : " + paramMap2);
			    	 
			    	int result =0;
			    	result = bs.insertReserv(paramMap1,paramMap2);
			    	 
			    	 System.out.println("추가됨 : " + result);
					// 결제 완료창으로 이동 
					return "redirect:/finishReserv";
		
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
	
	@PostMapping("updateReserv")
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
		HashMap<String,Object>list = bs.getPassenInfo(reservNum_return,reservNum_dep);
		
		mav.addObject("list1", list.get("list1"));
		// 
		
	return mav;
	
		
	}
	
}