package com.ezen.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProductVO {
	
	private int product_num;
	private String title;
	private String content;
	private String image;
	private String category;
	private int count;
	private Timestamp indate;

}
