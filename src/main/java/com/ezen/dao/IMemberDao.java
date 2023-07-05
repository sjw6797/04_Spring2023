package com.ezen.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.MemberVO;

@Mapper
public interface IMemberDao {

	void insertMember(MemberVO membervo);

	MemberVO getMember(String id);

	void joinKakao(MemberVO mvo);

	void updateMember(MemberVO membervo);

	ArrayList<String> getCountry();
}
