package com.ezen.flight_info;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightInfoService {
	// 항공사 이름과 영문코드
    public static HashMap<String, String> airLineId = new  HashMap<String, String>();
    // 공항 이름과 영문코드
    public static HashMap<String, String> airPortId = new  HashMap<String, String>();
    
    
     static {
        setAirLineId();
        setAirPortId();
    }

    public static void setAirLineId() {
        airLineId.put("아시아나항공", "AAR");
        airLineId.put("에어부산", "ABL");
        airLineId.put("이스타항공", "ESR");
        airLineId.put("제주항공", "JJA");
        airLineId.put("진 에어", "JNA");
        airLineId.put("대한 항공", "KAL");
        airLineId.put("티웨이항공", "TWB");
    }


    public static void setAirPortId() {
        airPortId.put("무안", "NAARKJB");
        airPortId.put("광주", "NAARKJJ");
        airPortId.put("군산", "NAARKJK");
        airPortId.put("여수", "NAARKJY");
        airPortId.put("원주", "NAARKNW");
        airPortId.put("양양", "NAARKNY");
        airPortId.put("제주", "NAARKPC");
        airPortId.put("김해", "NAARKPK");
        airPortId.put("사천", "NAARKPS");
        airPortId.put("울산", "NAARKPU");
        airPortId.put("인천", "NAARKSI");
        airPortId.put("김포", "NAARKSS");
        airPortId.put("포항", "NAARKTH");
        airPortId.put("대구", "NAARKTN");
        airPortId.put("청주", "NAARKTU");
    }
    
    public static ArrayList<String> getAirPortId() {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(0, "무안");list.add(1, "광주");list.add(2, "군산");list.add(3, "여수");
    	list.add(4, "원주");list.add(5, "양양");list.add(6, "제주");list.add(7, "김해");
    	list.add(8, "사천");list.add(9, "울산");list.add(10, "인천");list.add(11, "김포");
    	list.add(12, "포항");list.add(13, "대구");list.add(14, "청주");
    	
    	return list;
    }

}
