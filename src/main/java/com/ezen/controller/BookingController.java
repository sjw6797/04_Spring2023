package com.ezen.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		return "booking/searchPage_0";
		
	}
	
	
	//신정우작성-2023/07/05////////////// 
	//옥솔비 수정 7/6
		@RequestMapping("flightInfo1")// 출발지, 도착지, 날짜검색한걸 기반으로 노선리스트 출력    
		 public String flightInfo1( @ModelAttribute("dto") @Valid Item item,BindingResult result,Model model, HttpServletRequest request,
					@RequestParam(value="passenNum",required = false) String passenNum,@RequestParam("flag") String flag) {
			
	
			String url = "booking/routeList_1";
			
			if(result.getFieldError("depAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
			else if(result.getFieldError("arrAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
			else {
						HttpSession session = request.getSession();
						HashMap<String, Object>list = new HashMap<String, Object>();
						
						// 가는날 노선을 불러오기 
						list = bs.getInfo(item,flag);
						System.out.println( "in controller 가는날 일정 : "+ list.get("dep_list") );
						System.out.println("승객   " +  passenNum + "명");
						System.out.println("flag   : " +  flag);  
						session.setAttribute("passenNum", passenNum);
						
						model.addAttribute("depAirportNm",  list.get("depAirportNm"));
						model.addAttribute("arrAirportNm",  list.get("arrAirportNm"));

						
						
						
						
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
			}
			return url;
	}
	
	
		@RequestMapping("flightInfo2")  /// 신정우님 작성 7/5 // from "booking/routeList_1";
		public String flightInfo2(HttpServletRequest request, 
									@RequestParam("depAirportNm") String depAirportNm1,
					   			    @RequestParam("arrAirportNm") String arrAirportNm1,
					   				@RequestParam("depPlandTime") String depPlandTime1,
					   				@RequestParam("arrPlandTime") String arrPlandTime1,
					   				@RequestParam("airlineNm") String airlineNm1,
					   				@RequestParam("economyCharge") String economyCharge1,
					   				@RequestParam("prestigeCharge") String prestigeCharge1,
					   				@RequestParam("vihicleId") String vihicleId1,
					   				Model model
													) {
			HttpSession session = request.getSession();
			String flag = (String) session.getAttribute("flag");
			
			model.addAttribute("depAirportNm",  depAirportNm1);
			model.addAttribute("arrAirportNm", arrAirportNm1);
			// 편도 : 가는날 
			session.setAttribute("depAirportNm1", depAirportNm1);
		    session.setAttribute("arrAirportNm1", arrAirportNm1);
		    session.setAttribute("depPlandTime1", depPlandTime1);
		    session.setAttribute("arrPlandTime1", arrPlandTime1);
		    session.setAttribute("airlineNm1", airlineNm1);
		    session.setAttribute("economyCharge1", economyCharge1);
		    session.setAttribute("prestigeCharge1", prestigeCharge1);
		    session.setAttribute("vihicleId1", vihicleId1);
			if(flag.equals("1")) {	// routeList_1로부터 flag값을 받는경우(편도라면)			
				return "redirect:/insertPassen1";			
			}

			return "booking/routeList_2";
			
		}
	
		
		
		
		
		
		@RequestMapping("flightInfo3")  // 왕복편 - 오는날 노선정보를 출력 // from "booking/routeList_2";
		public String flightInfo3(  @ModelAttribute("dto") @Valid Item item,
													BindingResult result,HttpServletRequest request,													
												//	@RequestParam(value="passenNum",required = false) String passenNum,													
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
							//	System.out.println("승객   " +  passenNum + "명");
								
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
		
		@RequestMapping("/insertPassen1")  // 편도 - 승객폼으로 이동 
		// 편도는 오는날 일정 받아오는 데이터가 없기 때문에 따로 분리를 시킴 
		// 편도 : 가는날 일정 
	     public ModelAndView insertPassen1 (HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();						
			HashMap<String,Object>paramMap = new HashMap<String,Object>();
			
			
			 paramMap.put("depAirportNm1",session.getAttribute("depAirportNm1") );
	    	 paramMap.put("arrAirportNm1",session.getAttribute("arrAirportNm1") );
	    	 paramMap.put("depPlandTime1",session.getAttribute("depPlandTime1") );
	    	 paramMap.put("arrPlandTime1",session.getAttribute("arrPlandTime1") );
	    	 paramMap.put("airlineNm1",session.getAttribute("airlineNm1") );
	    	 paramMap.put("economyCharge1", session.getAttribute("economyCharge1") );
	    	 paramMap.put("prestigeCharge1", session.getAttribute("prestigeCharge1") );
	    	 paramMap.put("passenNum", session.getAttribute("passenNum") );
	    	 session.setAttribute("paramMap1", paramMap);
	    	 mav.addObject(paramMap);
	    	 mav.setViewName("booking/passengerForm1");
			return mav;
		}
	
		
		
	
	@RequestMapping("insertPassen") 
	// 선택한 노선 저장하고  출력하기 	
	// 결제를 완료하고 나서 선택한 노선을 저장함 
	// 그전에는 세션에 보관 
	// 좌석정보 알려주기 (일반석 잔여좌석 :00개, 프레스티지석 잔여좌석 :00개)
     public ModelAndView insertPassen (HttpServletRequest request,
    		 // 왕복편 오는날 정보를 받아옴 
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
			    	 paramMap.put("passenNum", session.getAttribute("passenNum") );
			    	 session.setAttribute("paramMap1", paramMap);
			    	 
		
				    
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

		
						mav.setViewName("booking/passengerForm");
						
					
					return mav; 
					
					
		
	}
	
	
	
	
	

	





	@RequestMapping("insertReservation")
	//승객이 채운 승객의 개인 정보 + 승객이 선택한 노선정보를 reservation 테이블에 저장
	// 데이터 : 승객개인정보, 선택한 좌석, 선택한 노선
	//가는날, 오는날 따로 두개를 생성하기 
	// 결제완료 폼으로 이동하기 
	// ☆★☆★☆★☆★☆★ 승객이 1명만 남은 경우 
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
	
	
	
	@RequestMapping("/finishReserv")
	
	   public String finishReserv() {

		return "booking/finishReserv.jsp";
	
		
	}
	
	@RequestMapping("checkReserv")
	// 예약확인 	
	   public ModelAndView checkReserv( HttpServletRequest request,
			   												@RequestParam("name") String name,
			   												@RequestParam("phone") String phone) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("name : " + name);
		System.out.println("phone : " + phone);
		// 노선 검색하기 
		HashMap<String,Object>list = bs.getReserv(name,phone);
		
		if (list.get("list1")== null && list.get("list2") == null) {			    
			mav.addObject("message", "예약내역이 존재하지 않습니다.");
		}else {
			mav.addObject("list1", list.get("list1"));
			mav.addObject("list2", list.get("list2"));
		}
       
		mav.addObject("list1", list.get("list1"));
		mav.addObject("list2", list.get("list2"));
		
		
		// 노선 출력하기 
		
		
		
		mav.setViewName("booking/reservCheckResult");		
		return mav;
		
	}
	
	
	
	@RequestMapping("/regulation")
	public String regulation(HttpServletRequest request,
				@RequestParam("reservNum_return") String reservNum_return,
				@RequestParam("reservNum_dep") String reservNum_dep) {
	
		HttpSession session = request.getSession();
		session.setAttribute("reservNum_return", reservNum_return);
		session.setAttribute("reservNum_dep", reservNum_dep);
		System.out.println("reservNum_return : " +reservNum_return);
		System.out.println("reservNum_dep : " +reservNum_dep);
		
		
		
		
		return "redirect:/passenInfoForReserv";
		
	}
	
	
	
	@RequestMapping("/passenInfoForReserv")
	// 예약수정 : 승객 개인정보만 
	   public ModelAndView updateReservForm( HttpServletRequest request,
													   @RequestParam("reservNum_return") String reservNum_return,
														@RequestParam("reservNum_dep") String reservNum_dep,
														Model model) {

		ModelAndView mav = new ModelAndView();	
		HttpSession session = request.getSession();	
		session.setAttribute("reservNum_return", reservNum_return);
		session.setAttribute("reservNum_dep", reservNum_dep);
		//String reservNum_dep = (String) session.getAttribute("reservNum_dep");
		
		// 예약자 정보 불러오기 (예약번호를 통해)
		HashMap<String,Object>list = bs.getPassenInfo(reservNum_dep);
		System.out.println("In controller || 예약변경페이지에 뜨는 승객 개인정보 :" +list+"///"+list.get("list"));
		model.addAttribute("list", list.get("list"));
		mav.addObject("list", list.get("list"));
		mav.setViewName("booking/updatepassenger");
		
	return mav;
	
		
	}
	
	
	@RequestMapping("updateReservForm")
	public String updateReservForm(){
		return "booking/updatepassenger";
	}
	
	
	
	@PostMapping("updateReserv")
	// 예약수정 : 승객 개인정보만 
	   public String updateReserv( HttpServletRequest request, 
														    @RequestParam("name") String name ,
															@RequestParam("phone") String phone ,
															@RequestParam("email") String email,
															@RequestParam("gender") String gender) {
	
					HttpSession session = request.getSession();
				
					String reservNum_return = (String) session.getAttribute("reservNum_return");	
					String reservNum_dep = (String) session.getAttribute("reservNum_dep");
					
					// 개인정보 수정하기 
					int result = bs.updatePassenInfo(reservNum_return,reservNum_dep);
					System.out.println("업데이트 완료됨 : " + result);
				
					
		
	return "booking/finishUpdate.jsp";
	
		
	}
	

	
	@RequestMapping("deleteReserv")
	public ModelAndView deleteReserv(HttpServletRequest request,
			    @RequestParam(required = false, value = "reservNum_return")String reservNum_return,
				@RequestParam("reservNum_dep") String reservNum_dep) {
		
				HttpSession session = request.getSession();	
				ModelAndView mav= new ModelAndView();
				session.setAttribute("reservNum_return", reservNum_return);
				session.setAttribute("reservNum_dep", reservNum_dep);
				mav.setViewName("index");
						if (reservNum_return== null ) {
							int result1 = bs.deletePassenInfo1(reservNum_dep);
							System.out.println("편도 삭제여부 :  " + result1);	
							
						}else {
							int result = bs.deletePassenInfo(reservNum_return,reservNum_dep);
							System.out.println("왕복 삭제여부 :  " + result);		
							
						}
						
				return mav;
	
	}
	
	
	
	@RequestMapping("passenList") // 관리자가 승객목록 
	public ModelAndView passenList(HttpServletRequest request) {
					ModelAndView mav = new ModelAndView();
					HashMap<String,Object>list = bs.getPassenList();
			if (list.get("passenlist")== null && list.get("list2") == null) {			    
						mav.addObject("message", "예약내역이 존재하지 않습니다.");
			}else {
				mav.addObject("passenlist", list.get("passenlist"));
				System.out.println("IN 컨트롤러 || 관리자 페이지에 뜨는 예약정보들  :  "+ list.get("passenlist"));
			}
		
			mav.setViewName("admin/reservation/pessengerList");		
			return mav;
		
	}
	
	/*
	@RequestMapping("/passenList1")
	public ModelAndView passenList1(HttpServletRequest request) {
					ModelAndView mav = new ModelAndView();
					
		
			      mav.setViewName("admin/reservation/pessengerList");		
			return mav;
		
	}
	
	*/
	
	
	@RequestMapping("/searchPassenAdmin")
	public ModelAndView searchPassenAdmin(HttpServletRequest request,
										@RequestParam("name") String name,
										@RequestParam("phone") String phone) {
					
					ModelAndView mav = new ModelAndView();
					System.out.println("name : " + name);
					System.out.println("phone : " + phone);
					
					HashMap<String,Object>list = bs.getReservAdmin(name,phone);
					
					if (list.get("list")== null ) {			    
						mav.addObject("message", "예약내역이 존재하지 않습니다.");
					}else {
						
						mav.addObject("passenlist", list.get("list"));
						
						
					}
					mav.setViewName("admin/reservation/pessengerList");		
		
				
			return mav;
		
	}
	
	
	
	
	@RequestMapping("deletePassenger")
	public ModelAndView deletePassenger(HttpServletRequest request,
								@RequestParam(required = false, value = "reserv_nums")String reserv_nums) {
		HttpSession session = request.getSession();	
		ModelAndView mav= new ModelAndView();
		System.out.println("삭제될 예약번호들  :  " + reserv_nums);	
		
				if (reserv_nums== null ) {
					System.out.println("예약번호를 전달받지 못했습니다");	
					
				}else {
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




















