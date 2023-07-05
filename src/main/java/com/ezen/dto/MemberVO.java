package com.ezen.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberVO {

	private int member_num;
	@NotNull(message = "아이디를 입력해주세요")
	@NotEmpty(message = "아이디를 입력해주세요")
	private String id;
	@NotNull(message = "비밀번호를 입력해주세요")
	@NotEmpty(message = "비밀번호를 입력해주세요")
	private String pwd;
	@NotNull(message = "이름을 입력해주세요")
	@NotEmpty(message = "이름을 입력해주세요")
	private String name;
	@NotNull(message = "전화번호를 입력해주세요")
	@NotEmpty(message = "전화번호를 입력해주세요")
	private String phone;
	@NotNull(message = "생일을 입력해주세요")
	@NotEmpty(message = "생일을 입력해주세요")
	private String birth;
	@NotNull(message = "이메일을 입력해주세요")
	@NotEmpty(message = "이메일을 입력해주세요")
	private String email;
	@NotNull(message = "우편번호를 입력해주세요")
	@NotEmpty(message = "우편번호를 입력해주세요")
	private String zip_num;
	@NotNull(message = "도로명주소를 입력해주세요")
	@NotEmpty(message = "도로명주소를 입력해주세요")
	private String address1;
	@NotNull(message = "상세주소를 입력해주세요")
	@NotEmpty(message = "상세주소를 입력해주세요")
	private String address2;
	private Timestamp indate;
	private int point;
	private String useyn;
	@NotNull(message = "성별을 입력해주세요")
	@NotEmpty(message = "성별을 입력해주세요")
	private String gender;

	private String provider;

}
