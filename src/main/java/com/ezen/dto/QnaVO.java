package com.ezen.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class QnaVO {

	private Integer qna_num;
	private String name;
	private String id;
	private String email;
	private Timestamp indate;
	private String result;
	private String reply;
	private String imgfilename;
	private String readcount;
	private String passcheck;
	private String pass;
	
	@NotEmpty(message="제목을 입력하세요")
	private String title;
	@NotEmpty(message="내용을 입력하세요")
	private String content;
	
}
