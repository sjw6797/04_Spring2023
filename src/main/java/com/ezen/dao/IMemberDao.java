package com.ezen.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.BannerVO;
import com.ezen.dto.BoardVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.ProductVO;
import com.ezen.dto.QnaVO;
import com.ezen.dto.ReservVO;

@Mapper
public interface IMemberDao {

	void insertMember(MemberVO membervo);

	MemberVO getMember(String id);

	void joinKakao(MemberVO mvo);

	void updateMember(MemberVO membervo);

	ArrayList<String> getCountry();

	List<BannerVO> getBannerList();

	List<BoardVO> getBoardList();

	List<QnaVO> getQnaList();

	List<ProductVO> getBestProduct();
}
