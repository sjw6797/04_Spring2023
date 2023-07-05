package com.ezen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
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
   
    public HashMap<String, Object> getInfo(Item item, String flag ) {
         //String depAirportNm, String arrAirportNm, Long depPlandTime, Long ReturnPlandTime
         HashMap<String, Object> result = new HashMap<String, Object>();
         String depAirportNm = item.getDepAirportNm();	// 출발공항
         String arrAirportNm = item.getArrAirportNm();	// 도착공항
         Long depPlandTime = item.getDepPlandTime();	// 출발시간
         Long ReturnPlandTime = item.getReturnPlandTime();	// 돌아오는 날짜         
         // 2가지의 경우를 생각 (단편, 왕복)
         // flag가 1인경우(단편) 
         if(flag=="1") {
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
             ArrayList<Item> flightItems1 = (ArrayList<Item>) depflightInfo.getResponse().getBody().getItems().getItem();
             System.out.println("가는날 일정 :  " + flightItems1);
             System.out.println("가는날 :  "); 
             for (Item list : flightItems1) { 
                 System.out.println("항공사 : " + list.getAirlineNm());
                 System.out.println("출발지 : " + list.getDepAirportNm());
                 System.out.println("도착지 : " + list.getArrAirportNm());
                 System.out.println("출발시간 : " + list.getDepPlandTime());
                 System.out.println("도착시간 : " + list.getArrPlandTime());
                 System.out.println("일반석요금 : " + list.getEconomyCharge());
                 System.out.println("비즈니스석요금 : " + list.getPrestigeCharge());
                 System.out.println();
              }
             result.put("dep_list",flightItems1); //가는날
         }else {
        	// flag가 2인경우(왕복) 	
        	 
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
             System.out.println("가는날 일정 :  " + flightItems1);
             System.out.println("가는날 :  "); 
             for (Item list : flightItems1) { 
                 System.out.println("항공사 : " + list.getAirlineNm());
                 System.out.println("출발지 : " + list.getDepAirportNm());
                 System.out.println("도착지 : " + list.getArrAirportNm());
                 System.out.println("출발시간 : " + list.getDepPlandTime());
                 System.out.println("도착시간 : " + list.getArrPlandTime());
                 System.out.println("일반석요금 : " + list.getEconomyCharge());
                 System.out.println("비즈니스석요금 : " + list.getPrestigeCharge());
                 System.out.println();
              }
        	 
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
            
            System.out.println("오는날 일정 :  " +flightItems2);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"); 
            System.out.println("오는날 :  "); 
            for (Item list : flightItems2) { 
                System.out.println("항공사 : " + list.getAirlineNm());
                System.out.println("출발지 : " + list.getDepAirportNm());
                System.out.println("도착지 : " + list.getArrAirportNm());
                System.out.println("출발시간 : " + list.getDepPlandTime());
                System.out.println("도착시간 : " + list.getArrPlandTime());
                System.out.println("일반석요금 : " + list.getEconomyCharge());
                System.out.println("비즈니스석요금 : " + list.getPrestigeCharge());
                System.out.println();
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
         
         public static int getTotalCount(String depAirportNm, String arrAirportNm, long depPlandTime) {

            int totalCount = 0;

            FlightInfo flightInfo = getFlightInfo(depAirportNm, arrAirportNm, depPlandTime, 1);

            if (flightInfo == null) // 조회결과 건수가 0일때 JsonSyntaxException으로 인해 null 반환하므로
               totalCount = 0;
            else   
               totalCount = flightInfo.getResponse().getBody().getTotalCount();

            return totalCount;
         }
   
         public static FlightInfo getFlightInfo(String depAirportNm, String arrAirportNm, long depPlandTime, int page) {
            // 1. URL 주소로부터 스트림을 연결
            FlightInfo flightInfo = null;
            StringBuilder sb = null;
            
            // 토큰요청 주소 url설정 
            try {
               URL url = new URL(
                     "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey"
                     + "=83umnw1r6dNQ5bxKMvcWYE54g9c%2FnRV81xsRdpYJ3uf2c5VCaBHx1%2"
                     + "BJ%2FS0bkx62BxgaOuuSdnBsIz6%2BpPeUuEw%3D%3D&numOfRows=50&pageNo="
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
			
			int result =0;
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
			
			

		    
			result.put("list1", list1);
			result.put("list2", list2);
			return result;
		}


		public HashMap<String, Object> getPassenInfo(String reservNum_return, String reservNum_dep) {
			HashMap<String, Object>result = new HashMap<String, Object>();
			HashMap<String,Object>paramMap = new HashMap<String, Object>();
			paramMap.put("reservNum_return",reservNum_return);
			paramMap.put("reservNum_dep",reservNum_dep);
			
			ArrayList <ReservVO> list = bdao.getPassenInfo(paramMap); // 화면에 출력할 용도
			 bdao.updatePassenInfo1(paramMap);
			 bdao.updatePassenInfo2(paramMap);
			
			result.put("list", list);

			return result;
		}

   }