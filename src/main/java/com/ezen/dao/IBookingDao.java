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

	void deletePassenInfo1(String reservNum_dep);

	void deletePassenInfo2(HashMap<String, Object> paramMap);

	ArrayList<ReservVO> getPassenList();
	
	ArrayList<ReservVO> getReservAdmin(HashMap<String, Object> paramMap);

	void deletePassenInfoInAdmin(HashMap<String, Object> paramMap);

	HashMap<String, Object> getReservAdmin1(HashMap<String, Object> paramMap);
	//신정우작성
	ArrayList<ReservVO> getAirLine(String grade);

	
	
}
