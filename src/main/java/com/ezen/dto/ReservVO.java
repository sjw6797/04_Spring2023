package com.ezen.dto;

import java.sql.Timestamp;

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
	
	
	
}
