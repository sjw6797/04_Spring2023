
package com.ezen.flight_info;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class Item {

   private String airlineNm;   // 항공사(대한항공)
   
   @NotEmpty(message="도착지를 입력하세요")
   private String arrAirportNm;   //도착지
   private Long arrPlandTime;   // long :  공공데이터포털 쪽에서 보내오는 출발 도착 시간 정보는 년월일시분까지 해서 총12자리 숫자값이고 Integer의 범위를 넘어서기 때문이다.
   private Long ReturnPlandTime;
   
   @NotEmpty(message="출발지를 입력하세요")
   private String depAirportNm;   //출발지
   private Long depPlandTime;
   private String vihicleId;
   private Integer economyCharge;
   private Integer prestigeCharge;
   
   
   private Integer remain_economy;
   private Integer remain_prestige;
   

   
   // long자료형 String자료형으로 바꾼후 리턴
   public String getDepPlandTime() {
		return String.valueOf(depPlandTime);
	}
   public String getReturnPlandTime() {
		return String.valueOf(ReturnPlandTime);
	} 
   public String getArrPlandTime() {
		return String.valueOf(arrPlandTime);
	}
   
   
	public Date getDepPlandTimeDate() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	    try {
	        return sdf.parse(String.valueOf(depPlandTime));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public Date getArrPlandTimeDate() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	    try {
	        return sdf.parse(String.valueOf(arrPlandTime));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
   
}
