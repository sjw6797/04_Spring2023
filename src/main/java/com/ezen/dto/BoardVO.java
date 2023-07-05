package com.ezen.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	private int board_num;
	private String title;
	private String content;
	private Timestamp indate;
	private String image;
}
