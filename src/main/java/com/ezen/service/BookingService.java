package com.ezen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.dao.IBookingDao;
import com.ezen.dto.ReservVO;
import com.ezen.flight_info.FlightInfo;
import com.ezen.flight_info.FlightInfoService;
import com.ezen.flight_info.Item;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Service
public class BookingService {
	
	@Autowired
	IBookingDao bdao;
   
         // 주어진 출발지, 도착지, 출발일자 조건에 대한 검색결과 건수 값을 읽어옴
         // 내부적으로는 출발지, 도착지, 출발일자 외에 페이지 1로 지정하여 FlightInfo 객체를 가져와 거기에서 totalCount 값을 가져오는
         // 방식을 취한다. 주어진 검색조건에 대하여 검색결과가 0건이든 10건이든 또는 100건이든 기본적으로 1페이지의 결과는 반환하기 때문이다.
         // 다만, 서버에서 반환하는 Json 형식에 문제가 있어 0건일 때는 JsonSyntaxException이 발생하여 getFlightInfo()가
         // null을 반환받는 결과가 되므로 이에 주의해야 한다.
      
      
      
         public static int getTotalCount(String depAirportNm, String arrAirportNm, String depPlandTime) {

            int totalCount = 0;

            FlightInfo flightInfo = getFlightInfo(depAirportNm, arrAirportNm, depPlandTime, 1);

            if (flightInfo == null) // 조회결과 건수가 0일때 JsonSyntaxException으로 인해 null 반환하므로
               totalCount = 0;
            else
               
               totalCount = flightInfo.getResponse().getBody().getTotalCount();

            return totalCount;
         }

         
         
         
         public static FlightInfo getFlightInfo(String depAirportNm, String arrAirportNm, String depPlandTime, int page) {
            // 1. URL 주소로부터 스트림을 연결
            FlightInfo flightInfo = null;
            StringBuilder sb = null;
            
            
            // 토큰요청 주소 url설정 
            try {
               URL url = new URL(
                     "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey"
                     + "=83umnw1r6dNQ5bxKMvcWYE54g9c%2FnRV81xsRdpYJ3uf2c5VCaBHx1%2"
                     + "BJ%2FS0bkx62BxgaOuuSdnBsIz6%2BpPeUuEw%3D%3D&numOfRows=5000&pageNo="
                           + page + "&depAirportId=" + FlightInfoService.airPortId.get(depAirportNm) + "&arrAirportId="
                           + FlightInfoService.airPortId.get(arrAirportNm) + "&depPlandTime=" + depPlandTime
                           + "&_type=json");
               //Stream 연결
               HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();   // import java.net.HttpURLConnection;
               
               // 따로 인증절차없이 항공Info수신 // 공공데이터 기관에서 서버로 데이터를 보낼때 어떤식으로 응답을 받을것인지 
               BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

               sb = new StringBuilder();   //조각난 String을 조립하기 위한 객체

               String input = "";
               
               // 연산순서
               // 1) input = br.readLine() : input에 값 대입 -> 얻은 데이터를 하나씩 읽어와서(readLinge메소드) input에 저장하고 그걸 -> sb객체에 저장하기   sb.append(input);
               // 2) input != null : input이 null 과 다른지 비교하여 다르면 while문 본체 실행. null인 경우 while문 벗어남
               while ((input = br.readLine()) != null) {
                  sb.append(input);
                  System.out.println("sb"+input);   //수신된 토큰을 console에 출력
               }

               br.close();
               conn.disconnect();
               
               // 2. 스트림으로부터 읽어온 JSON 데이터를 Gson을 이용하여 Java 클래스 객체들로 변환시켜 출력
               Gson gson = new Gson();
               //토큰내용 그대로 만든 Dto
               flightInfo = gson.fromJson(sb.toString(), FlightInfo.class);

            } catch (JsonSyntaxException e) {
               /*
                * Gson은 items의 value값으로 JSON 객체가 오기를 기대하고 오브젝트의 시작을 표시하는 '{'가 올거로 예상하고 있는데 특이하게
                * 조회결과 건수가 0일 때는 공공데이터포털 서버는 items의 value값으로 ""라는 빈 문자열을 반환한다. 즉, JSON 오브젝트가
                * 와야할 자리에 문자열이 오고 있기 때문에 JsonSyntaxException 에러를 발생시키게 되는 것이다. 이 예외 발생시
                * gson.fromJson()에서 예외 발생하고 이때는 flightInfo는 채 값이 세팅되지 못해 null을 반환하게 된다.
                */
               System.out.println(sb.toString());
            } catch (IOException e) {
               e.printStackTrace();
            }
            System.out.println("flightInfo값 : " + flightInfo);
            return flightInfo;
            
         }


		

		public int insertReserv(HashMap<String, Object> paramMap1,
				HashMap<String, Object> paramMap2) {
			
			int result =2;
			bdao.insertReserv_dep(paramMap1);
			bdao.insertReserv_return(paramMap2);
			return result;
		}


		public HashMap<String, Object> getReserv(String name, String phone) {
			
			
			HashMap<String, Object>result = new HashMap<String, Object>();
			System.out.println("서비스 매개변수  :  " + name+" / "+phone);
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("name", name);
			paramMap.put("phone",phone);
			
			ArrayList <ReservVO> list1 = bdao.getReserv1(paramMap);
			ArrayList <ReservVO> list2 = bdao.getReserv2(paramMap);
			System.out.println("가는날 일정 내역  :  " + list1);
			System.out.println("오늘날 일정 내역  :  " + list2);

            

			if (list1.isEmpty() && list2.isEmpty()) {	    
			    result.put("message", "예약내역이 존재하지 않습니다.");
			} else {
				result.put("list1", list1);
				result.put("list2", list2);
			}
           
			return result;
		}

		
		
		
		  public HashMap<String, Object> getInfo(Item item, String flag ) {
         //String depAirportNm, String arrAirportNm, Long depPlandTime, Long ReturnPlandTime
         HashMap<String, Object> result = new HashMap<String, Object>();
         String depAirportNm = item.getDepAirportNm();	// 출발공항
         String arrAirportNm = item.getArrAirportNm();	// 도착공항
         String depPlandTime = item.getDepPlandTime();	// 출발시간
         String ReturnPlandTime = item.getReturnPlandTime();	// 돌아오는 날짜         
         // 2가지의 경우를 생각 (단편, 왕복)
         // flag가 1인경우(단편) 
         if(flag.equals("1")) {
        	// ♧♣♧♣ 가는날 일정 ♧♣♧♣
             int totalCount1 = getTotalCount(depAirportNm, arrAirportNm, depPlandTime);   // 결과레코드 토탈 rowNum
             
             // 바로 위의 getTotalCount() 호출에서 결과가 0건인 경우에는 아래 if문만 실행하고 종료함
             if (totalCount1 == 0) {
                System.out.println("가는날 일정 :  출발지:" + depAirportNm + " / 도착지:" + arrAirportNm + " / 가는날일자:" + depPlandTime);
                System.out.println("지정한 조건에 대한 비행편이 존재하지 않습니다.");
                result.put("message", "지정한 조건에 대한 비행편이 존재하지 않습니다.");
                return result;
             }
             
             FlightInfo depflightInfo = getFlightInfo(depAirportNm, arrAirportNm, depPlandTime, 1);
             
             ArrayList<Item> flightItems1 = (ArrayList<Item>) depflightInfo.getResponse().getBody().getItems().getItem();
             
             /* 남는좌석 호출 -start 0711 */
             /* 절차
              * 1. getAirLine메소드로 이코노미/프레스티지(남은좌석) 리스트(A라 칭함)를 불러옴 
              * 2. flightItem1(받아온 노선정보)로 부터 A리스트와 IDENTITY를 비교한다
              * 3. 비교했을 때 같을 경우 A리스트의 남은좌석 정보를 flightItem1(VO)에 매칭해서 넣는다 
              * 4. 비교했을 때 다른 경우 flightItem1(VO)에 수동적으로 값을 넣어준다.
              * */
             ArrayList<ReservVO> economy_List = bdao.getAirLine("view_economy_distinct");
             ArrayList<ReservVO> prestige_List = bdao.getAirLine("view_prestige_distinct");
             Item eitem = new Item();
             for (int i=0; i<flightItems1.size(); i++) { 
            	 eitem = flightItems1.get(i);
            	 String tmp1=eitem.getVihicleId();
            	 String tmp2=eitem.getDepPlandTime();
            	 String tmp3= tmp1+tmp2;
            	 
	               for(ReservVO rvo2 : economy_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) 
	            	   {
	            		   eitem.setRemain_economy( rvo2.getRemain_economy() );
	            		   break;
	                   }
	                   else {
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_economy(50); break; case "에어부산" : eitem.setRemain_economy(49); break;
	                	   case "이스타항공" : eitem.setRemain_economy(40); break; case "제주항공" : eitem.setRemain_economy(45); break;
	                	   case "진에어" : eitem.setRemain_economy(44); break; case "대한항공" : eitem.setRemain_economy(51); break;
	                	   case "티웨이항공" : eitem.setRemain_economy(46); break; case "에어서울" : eitem.setRemain_economy(47); break;
	                	   case "하이에어" : eitem.setRemain_economy(30); break;
	                	   default : eitem.setRemain_economy(10);
	                	   }
	                   }
	               }
	               
	               for(ReservVO rvo2 : prestige_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) {
	            		   eitem.setRemain_prestige(rvo2.getRemain_prestige());
	            		   break;
	                   }
	                   else {
	                	   eitem.setRemain_prestige(50);
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_prestige(10); break; case "에어부산" : eitem.setRemain_prestige(9); break;
	                	   case "이스타항공" : eitem.setRemain_prestige(6); break; case "제주항공" : eitem.setRemain_prestige(9); break;
	                	   case "진에어" : eitem.setRemain_prestige(9); break; case "대한항공" : eitem.setRemain_prestige(10); break;
	                	   case "티웨이항공" : eitem.setRemain_prestige(9); break; case "에어서울" : eitem.setRemain_prestige(8); break;
	                	   case "하이에어" : eitem.setRemain_prestige(6); break;
	                	   default : eitem.setRemain_prestige(5);
	                	   }
	                   }
	               }
             }
             /* 남는좌석 호출 -end */
             result.put("dep_list",flightItems1); //가는날
  
         }else {
        	// flag가 2인경우(왕복) 	
        	 System.out.println("2");
        	 
        	// ♧♣♧♣ 가는날 일정 ♧♣♧♣
             int totalCount1 = getTotalCount(depAirportNm, arrAirportNm, depPlandTime);   // 결과레코드 토탈 rowNum
             // 바로 위의 getTotalCount() 호출에서 결과가 0건인 경우에는 아래 if문만 실행하고 종료함
             if (totalCount1 == 0) {
                System.out.println("가는날 일정 :  출발지:" + depAirportNm + " / 도착지:" + arrAirportNm + " / 출발일자:" + depPlandTime);
                System.out.println("지정한 조건에 대한 비행편이 존재하지 않습니다.");
                result.put("message", "지정한 조건에 대한 비행편이 존재하지 않습니다.");
                return result;
             }
             FlightInfo depflightInfo = getFlightInfo(depAirportNm, arrAirportNm, depPlandTime, 1);
             ArrayList<Item> flightItems1 = (ArrayList<Item>) depflightInfo.getResponse().getBody().getItems().getItem();	//최종적으로 저장되는 리스트 
             
             /* 남는좌석 호출 -start */
             /* 절차
              * 1. getAirLine메소드로 이코노미/프레스티지(남은좌석) 리스트(A라 칭함)를 불러옴 
              * 2. flightItem1(받아온 노선정보)로 부터 A리스트와 IDENTITY를 비교한다
              * 3. 비교했을 때 같을 경우 A리스트의 남은좌석 정보를 flightItem1(VO)에 매칭해서 넣는다 
              * 4. 비교했을 때 다른 경우 flightItem1(VO)에 수동적으로 값을 넣어준다.
              * */
             ArrayList<ReservVO> economy_List = bdao.getAirLine("view_economy_distinct");
             ArrayList<ReservVO> prestige_List = bdao.getAirLine("view_prestige_distinct");
             Item eitem = new Item();
             for (int i=0; i<flightItems1.size(); i++) { 
            	 eitem = flightItems1.get(i);
            	 String tmp1=eitem.getVihicleId();
            	 String tmp2=eitem.getDepPlandTime();
            	 String tmp3= tmp1+tmp2;
            	 
	               for(ReservVO rvo2 : economy_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) 
	            	   {
	            		   eitem.setRemain_economy( rvo2.getRemain_economy() );
	            		   break;
	                   }
	                   else {
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }  
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_economy(50); break; case "에어부산" : eitem.setRemain_economy(49); break;
	                	   case "이스타항공" : eitem.setRemain_economy(40); break; case "제주항공" : eitem.setRemain_economy(45); break;
	                	   case "진에어" : eitem.setRemain_economy(44); break; case "대한항공" : eitem.setRemain_economy(51); break;
	                	   case "티웨이항공" : eitem.setRemain_economy(46); break; case "에어서울" : eitem.setRemain_economy(47); break;
	                	   case "하이에어" : eitem.setRemain_economy(30); break;
	                	   default : eitem.setRemain_economy(10);
	                	   }
	                   }
	               }
	               
	               for(ReservVO rvo2 : prestige_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) {
	            		   eitem.setRemain_prestige(rvo2.getRemain_prestige());
	            		   break;
	                   }
	                   else {
	                	   eitem.setRemain_prestige(50);
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_prestige(10); break; case "에어부산" : eitem.setRemain_prestige(9); break;
	                	   case "이스타항공" : eitem.setRemain_prestige(6); break; case "제주항공" : eitem.setRemain_prestige(9); break;
	                	   case "진에어" : eitem.setRemain_prestige(9); break; case "대한항공" : eitem.setRemain_prestige(10); break;
	                	   case "티웨이항공" : eitem.setRemain_prestige(9); break; case "에어서울" : eitem.setRemain_prestige(8); break;
	                	   case "하이에어" : eitem.setRemain_prestige(6); break;
	                	   default : eitem.setRemain_prestige(5);
	                	   }
	                   }
	               }
             }
             /* 남는좌석 호출 -end */
     
        	 
        	// ♧♣♧♣ 돌아오는 일정 ♧♣♧♣
            int totalCount2 = getTotalCount(arrAirportNm, depAirportNm, ReturnPlandTime);   // 결과레코드 토탈 rowNum
            // 바로 위의 getTotalCount() 호출에서 결과가 0건인 경우에는 아래 if문만 실행하고 종료함
            if (totalCount2 == 0) {
               System.out.println("돌아오는날 일정 : 출발지:" + arrAirportNm + " / 도착지:" + depAirportNm + " / 출발일자:" +  ReturnPlandTime);
               System.out.println("지정한 조건에 대한 비행편이 존재하지 않습니다.");
               result.put("list", "");
               return result;
            }
            
            FlightInfo arrflightInfo = getFlightInfo(arrAirportNm, depAirportNm, ReturnPlandTime, 1);
            ArrayList<Item> flightItems2 = (ArrayList<Item>) arrflightInfo.getResponse().getBody().getItems().getItem();
            
            /* 남는좌석 호출 -start */
            /* 절차
             * 1. getAirLine메소드로 이코노미/프레스티지(남은좌석) 리스트(A라 칭함)를 불러옴 
             * 2. flightItem1(받아온 노선정보)로 부터 A리스트와 IDENTITY를 비교한다
             * 3. 비교했을 때 같을 경우 A리스트의 남은좌석 정보를 flightItem1(VO)에 매칭해서 넣는다 
             * 4. 비교했을 때 다른 경우 flightItem1(VO)에 수동적으로 값을 넣어준다.
             * */
            eitem = new Item();
            for (int i=0; i<flightItems2.size(); i++) { 
           	 eitem = flightItems2.get(i);
           	 String tmp1=eitem.getVihicleId();
           	 String tmp2=eitem.getDepPlandTime();
           	 String tmp3= tmp1+tmp2;
           	 
	               for(ReservVO rvo2 : economy_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) 
	            	   {
	            		   eitem.setRemain_economy( rvo2.getRemain_economy() );
	            		   break;
	                   }
	                   else {
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_economy(50); break; case "에어부산" : eitem.setRemain_economy(49); break;
	                	   case "이스타항공" : eitem.setRemain_economy(40); break; case "제주항공" : eitem.setRemain_economy(45); break;
	                	   case "진에어" : eitem.setRemain_economy(44); break; case "대한항공" : eitem.setRemain_economy(51); break;
	                	   case "티웨이항공" : eitem.setRemain_economy(46); break; case "에어서울" : eitem.setRemain_economy(47); break;
	                	   case "하이에어" : eitem.setRemain_economy(30); break;
	                	   default : eitem.setRemain_economy(10);
	                	   }
	                   }
	               }
	               
	               for(ReservVO rvo2 : prestige_List) {
	            	   if( eitem.getVihicleId() !=null && rvo2.getIdentity().equals( tmp3 ) ) {
	            		   eitem.setRemain_prestige(rvo2.getRemain_prestige());
	            		   break;
	                   }
	                   else {
	                	   eitem.setRemain_prestige(50);
	                	   String aname=eitem.getAirlineNm();
	                	   if(aname==null) {
	                		   aname="";
	                	   }
	                	   switch(aname) {
	                	   case "아시아나항공" : eitem.setRemain_prestige(10); break; case "에어부산" : eitem.setRemain_prestige(9); break;
	                	   case "이스타항공" : eitem.setRemain_prestige(6); break; case "제주항공" : eitem.setRemain_prestige(9); break;
	                	   case "진에어" : eitem.setRemain_prestige(9); break; case "대한항공" : eitem.setRemain_prestige(10); break;
	                	   case "티웨이항공" : eitem.setRemain_prestige(9); break; case "에어서울" : eitem.setRemain_prestige(8); break;
	                	   case "하이에어" : eitem.setRemain_prestige(6); break;
	                	   default : eitem.setRemain_prestige(5);
	                	   }
	                   }
	               }
            }
            /* 남는좌석 호출 -end */
            	
            System.out.println("오는날 일정 :  " +flightItems2);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"); 
            System.out.println("오는날 :  "); 
            for (Item list : flightItems2) { 
				/*
				 * System.out.println("항공사 : " + list.getAirlineNm());
				 * System.out.println("출발지 : " + list.getDepAirportNm());
				 * System.out.println("도착지 : " + list.getArrAirportNm());
				 * System.out.println("출발시간 : " + list.getDepPlandTime());
				 * System.out.println("도착시간 : " + list.getArrPlandTime());
				 * System.out.println("일반석요금 : " + list.getEconomyCharge());
				 * System.out.println("비즈니스석요금 : " + list.getPrestigeCharge());
				 * System.out.println();
				 */
            }
            result.put("dep_list",flightItems1); //가는날
            result.put("return_list",flightItems2); //오는날
       
        }
         result.put("depAirportNm", depAirportNm);
         result.put("arrAirportNm", arrAirportNm);
         return result;
 
      }
    	 // 하나의 FlightInfo 객체 안에는 최대 50개까지의 검색결과 건수가 담길 수 있으므로 아래 for문으로 회전하면서 하나씩 출력함
         // 주어진 출발지, 도착지, 출발일자 조건에 대한 검색결과 건수 값을 읽어옴
         // 내부적으로는 출발지, 도착지, 출발일자 외에 페이지 1로 지정하여 FlightInfo 객체를 가져와 거기에서 totalCount 값을 가져오는
         // 방식을 취한다. 주어진 검색조건에 대하여 검색결과가 0건이든 10건이든 또는 100건이든 기본적으로 1페이지의 결과는 반환하기 때문이다.
         // 다만, 서버에서 반환하는 Json 형식에 문제가 있어 0건일 때는 JsonSyntaxException이 발생하여 getFlightInfo()가
         // null을 반환받는 결과가 되므로 이에 주의해야 한다.


		public int insertReserv1(HashMap<String, Object> paramMap1) {

				int result =1;
				bdao.insertReserv_dep(paramMap1);
				
				return result;
			
		}


		public HashMap<String, Object> getPassenInfo(String reservNum_dep) {
			HashMap<String, Object>result = new HashMap<String, Object>();
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("reservNum_dep",reservNum_dep);
			ArrayList <ReservVO> list = bdao.getPassenInfo(paramMap); // 화면에 출력할 용도
			System.out.println("IN 서비스 || 예약변경페이지에 뜨는 승객 개인정보 :  "+ list);
			result.put("list", list);
			return result;

		}
          

		/*
		public int deletePassenInfo(String reservNum_return, String reservNum_dep) {
			
			int result=2;
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("reservNum_return",reservNum_return);
			paramMap.put("reservNum_dep",reservNum_dep);
			bdao.deletePassenInfo1(paramMap);
			bdao.deletePassenInfo2(paramMap);
			return result;
		}
		*/


		public int deletePassenInfo1(String reservNum_dep) {
			int result=1;
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			/* paramMap.put("reservNum_dep",reservNum_dep); */
			paramMap.put("reserv_num",reservNum_dep); 
			bdao.deletePassenInfo1(reservNum_dep);
			return result;
		}


		


		public int deletePassenInfoInAdmin(String reservNum) {
			int result=1;
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("reservNum",reservNum);
			bdao.deletePassenInfoInAdmin(paramMap);
			
			return result;
		}
		
		
		
		
		public HashMap<String, Object> getPassenList() { // 관리자 테이블에서 승객목록 불러오기 
			HashMap<String, Object>result = new HashMap<String, Object>();
			ArrayList <ReservVO> list = bdao.getPassenList();
			System.out.println("IN 서비스 || 관리자 페이지에 뜨는 예약정보들  :  "+ list);
			if (list.isEmpty()) {	
				
			    result.put("message", "예약내역이 존재하지 않습니다.");
			} else {
				
				result.put("passenlist", list);
			
			}
			return result;
		}
		
		
		
		/*
		public HashMap<String, Object> getReservAdmin(String name, String phone) {
			
			
			HashMap<String, Object>result = new HashMap<String, Object>();
			System.out.println("서비스 매개변수  :  " + name+" / "+phone);
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("name", name);
			paramMap.put("phone",phone);
			
			ArrayList <ReservVO> list = bdao.getReservAdmin(paramMap);
			System.out.println(name+ "의일정 내역  :  " + list);
			
			

			if (list.isEmpty()) {			    
			    result.put("message", "예약내역이 존재하지 않습니다.");
			} else {
				result.put("passenlist", list);
			
			}
           
			return result;
		}
		*/
	
		//신정우 작성 -start
		public ArrayList<ReservVO> getAirLine(String grade) {
			ArrayList<ReservVO> result = new ArrayList<ReservVO>();
			result = bdao.getAirLine(grade);
			
			return result;
		}
		//신정우 작성 -end

}