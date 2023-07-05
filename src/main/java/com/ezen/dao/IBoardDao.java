package com.ezen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.BoardVO;
import com.ezen.dto.Paging;

@Mapper
public interface IBoardDao {

	int getAllCount(String string, String string2, String key);

	List<BoardVO> listBoard(Paging paging, String key);

	BoardVO getBoard(int board_num);

}
