package com.ezen.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.Paging;
import com.ezen.dto.QnaVO;

@Mapper
public interface IQnaDao {

	int getAllCount(String key);
	List<QnaVO> getQnaList(Paging paging, String key);
	QnaVO getQnaByQnaNum(int qna_num);
	void qnaDelete(int qna_num);
	void qnaInsert(@Valid QnaVO qnavo, int result);
	int getMemberBymnum(String id);
	void plusOneReadCount(int qna_num);
	void updateQna(@Valid QnaVO qnavo);
	void addReply(int qna_num, String reply);

}
