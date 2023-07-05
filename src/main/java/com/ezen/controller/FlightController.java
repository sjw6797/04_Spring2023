package com.ezen.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.flight_info.Item;
import com.ezen.service.FlightService;

@Controller
public class FlightController {

	@Autowired
	FlightService fs;
	
	/* @RequestMapping("flightInfo") */
	public String flightInfo( @ModelAttribute("dto") @Valid Item item,BindingResult result,Model model) {
		
		if(result.getFieldError("depAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("depAirportNm").getDefaultMessage());
		else if(result.getFieldError("arrAirportNm") !=null)
			model.addAttribute("message",result.getFieldError("arrAirportNm").getDefaultMessage());
		else {
			// 서비스로 보내서 처리
			HashMap<String, Object>list = new HashMap<String, Object>();
			list = fs.getInfo(item.getDepAirportNm(),item.getArrAirportNm(),item.getDepPlandTime());	// 단편항공
			System.out.println( list.get("list") );
			model.addAttribute("flightInfo",list.get("list"));
			
		}
		return "resultPage";
	}
}
