
package com.ezen.flight_info;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class Item {

	private String airlineNm;	// 항공사(대한항공)
	
	@NotEmpty(message="도착지를 입력하세요")
	private String arrAirportNm;	//도착지
	private Long arrPlandTime;	//도착시간	// long :  공공데이터포털 쪽에서 보내오는 출발 도착 시간 정보는 년월일시분까지 해서 총12자리 숫자값이고 Integer의 범위를 넘어서기 때문이다.
	private Long ReturnPlandTime;
	
	@NotEmpty(message="출발지를 입력하세요")
	private String depAirportNm;	//출발지
	private Long depPlandTime;	//출발시간
	private String vihicleId;
	private Integer economyCharge;
	private Integer prestigeCharge;
	
}

