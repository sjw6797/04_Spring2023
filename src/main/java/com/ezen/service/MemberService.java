package com.ezen.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.dao.IMemberDao;
import com.ezen.dto.MemberVO;

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
}