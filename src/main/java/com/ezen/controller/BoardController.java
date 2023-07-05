package com.ezen.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.dto.BoardVO;
import com.ezen.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService bs;

	@RequestMapping("boardList")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> result = bs.getBoardList(request);
		mav.addObject("boardList", result.get("boardList"));
		mav.addObject("paging", result.get("paging"));
		mav.addObject("key", result.get("key"));
		mav.setViewName("board/boardList");
		// Controller 는 Service 가 작업해서 보내준 결과들을 mav 에 잘 넣어서 목적지로 이동만 합니다.
		return mav;
	}

	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam("board_num") int board_num, Model model) {

		BoardVO bvo = bs.getBoard(board_num);
		model.addAttribute("dto", bvo);

		return "board/boardDetail";

	}
}
