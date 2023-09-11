package com.ezen.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.dto.AdminVO;
import com.ezen.dto.BannerVO;
import com.ezen.dto.BoardVO;
import com.ezen.dto.MemberVO;
import com.ezen.dto.Paging;
import com.ezen.dto.ProductVO;
import com.ezen.dto.ReservVO;

@Mapper
public interface IAdminDao {

	AdminVO getAdmin(String id);

	ArrayList<MemberVO> getMemberList();

	int getAllCount(String string, String string2, String key);

	List<MemberVO> listMember(Paging paging, String key);

	List<MemberVO> listBoard(Paging paging, String key);

	void deleteMember(int member_num);

	void useMember(int member_num);

	void insertBoard(BoardVO boardvo);

	BoardVO getBoard(int board_num);

	void updateBoard(BoardVO boardvo);

	void deleteBoard(int board_num);

	List<BannerVO> listBanner(Paging paging, String key);

	void insertBanner(BannerVO bannervo);

	int getBannerOseq(int oseq);

	void updateBanner(BannerVO bannervo);

	int selectBanner(int i);

	void updateBannerOseq(int banner_num, int oseq);

	void deleteBanner(int banner_num);

	List<ProductVO> listProduct(Paging paging, String key);

	void insertProduct(ProductVO productvo);

	void updateProduct(ProductVO productvo);

	ProductVO getProduct(int product_num);

	void deleteProduct(int product_num);

	BannerVO getBanner(int banner_num);

	int getAllCountReservation(String string, String string2, String key, String key2);

	List<ReservVO> listReservation(Paging paging, String key);

	MemberVO getAdminMember(int member_num);

}
