package com.ezen.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.AdminVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.Paging;

@Mapper
public interface IAdminDao {

	AdminVO getAdmin(String id);

	ArrayList<MemberVO> getMemberList();

	int getAllCount(String string, String string2, String key);

	List<MemberVO> listMember(Paging paging, String key);

}
