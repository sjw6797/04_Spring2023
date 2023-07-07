package com.ezen.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BookingVO {
	
	private String airlineNm;	// 항공사(대한항공)
	
	@NotEmpty(message="도착지를 입력하세요")
	private String arrAirportNm;	
	private Long arrPlandTime;	
	
	@NotEmpty(message="출발지를 입력하세요")
	private String depAirportNm;	
	private Long depPlandTime;
	
	
	
	
	
}
