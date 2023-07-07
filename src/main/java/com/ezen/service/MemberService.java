package com.ezen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.dao.IMemberDao;
import com.ezen.dto.BannerVO;
import com.ezen.dto.BoardVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.ProductVO;
import com.ezen.dto.QnaVO;
import com.ezen.dto.ReservVO;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public void insertMember(MemberVO membervo) {
		mdao.insertMember(membervo);
	}

	public void joinKakao(MemberVO mvo) {
		mdao.joinKakao(mvo);

	}

	public void updateMember(MemberVO membervo) {
		mdao.updateMember(membervo);

	}

	public ArrayList<String> getCountry() {
		ArrayList<String> result = new ArrayList<String>();
		result = mdao.getCountry();
		return result;
	}

	public List<BannerVO> getBannerList() {
		return mdao.getBannerList();
	}

	public List<BoardVO> getBoardList() {
		return mdao.getBoardList();
	}

	public List<QnaVO> getQnaList() {
		return mdao.getQnaList();
	}

	public List<ProductVO> getBestProduct() {
		return mdao.getBestProduct();
		
	}

}
