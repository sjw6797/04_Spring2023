package com.ezen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.ReservVO;



@Mapper
public interface IBookingDao {
	void insertReserv_dep(HashMap<String, Object> paramMap1);

	void insertReserv_return(HashMap<String, Object> paramMap2);

	

	ArrayList<ReservVO> getReserv1(HashMap<String, Object> paramMap);

	ArrayList<ReservVO> getReserv2(HashMap<String, Object> paramMap);



	ArrayList<ReservVO> getPassenInfo(HashMap<String, Object> paramMap);

	void updatePassenInfo1(HashMap<String, Object> paramMap);

	void updatePassenInfo2(HashMap<String, Object> paramMap);
	
}
