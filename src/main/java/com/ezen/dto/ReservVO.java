package com.ezen.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class ReservVO {


	private String reserv_num;
    private String departures;
    private String arrivals;
    private String dep_time;
    private String return_time;
    private String airplane_name;
    private String r_name;
    private String r_email;
    private String r_gender;
    private String r_class;
    private String r_phone;
    private Timestamp indate;
    
    // airLine
    private String vihicleid;
    private String airline_name;
    private String image;
    private Integer economy_sit;
    private Integer prestige_sit;
    private Integer remain_economy;
    private Integer remain_prestige;
    private String identity;
	
    public java.util.Date getDep_timeDate() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    	try {
			return sdf.parse(dep_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public java.util.Date getReturn_timeDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    	try { 
			return sdf.parse(return_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	
}
