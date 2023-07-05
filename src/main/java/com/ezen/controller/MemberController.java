package com.ezen.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.dto.KakaoProfile;
import com.ezen.dto.KakaoProfile.KakaoAccount;
import com.ezen.dto.KakaoProfile.KakaoAccount.Profile;
import com.ezen.dto.MemberVO;
import com.ezen.dto.OAuthToken;
import com.ezen.flight_info.FlightInfoService;
import com.ezen.service.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;

	@RequestMapping("/")
	public String start(Model model) {
		ArrayList<String> list = new ArrayList<String>();
		FlightInfoService fs = new FlightInfoService();
		list = fs.getAirPortId();
		
		model.addAttribute("countryList",list);
		return "index";
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@RequestMapping("joinForm")
	public String joinForm() {
		return "member/joinForm";
	}

	@PostMapping("loginCheck")
	public ModelAndView loginCheck(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult rs,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/loginForm");
		if (rs.getFieldError("id") != null) {
			mav.addObject("message", rs.getFieldError("id").getDefaultMessage());
		} else if (rs.getFieldError("pwd") != null) {
			mav.addObject("message", rs.getFieldError("pwd").getDefaultMessage());
		} else {
			MemberVO mvo = ms.getMember(membervo.getId());
			if (mvo == null) {
				mav.addObject("message", "아이디가 존재하지 않습니다");
			} else if (!membervo.getPwd().equals(mvo.getPwd())) {
				mav.addObject("message", "비밀번호가 틀렸습니다");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				mav.setViewName("redirect:/");
			}
		}
		return mav;
	}

	@RequestMapping("idCheck")
	@ResponseBody
	public String idCheck(@RequestParam("id") String id, Model model) {
		ModelAndView mav = new ModelAndView();
		System.out.println(id);
		String result = null;
		if (id == null || id.equals("")) {// 아이디가 null 이거나 빈칸일경우
			result = "-1";
		} else {
			MemberVO mvo = ms.getMember(id);
			if (mvo == null) { // 아이디 사용가능
				result = "1";
			} else if (mvo.getUseyn().equals("N")) { // 탈퇴한 아이디
				result = "2";
			} else if (mvo.getId() != null) { // 이미 사용중인 아이디
				result = "0";
			}
		}
		return result;
	}

	@PostMapping("join")
	public ModelAndView join(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult rs,
			@RequestParam("reid") String reid, @RequestParam("pwdcheck") String pwdcheck, RedirectAttributes rttr) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinForm");
		mav.addObject("reid", reid);

		if (rs.getFieldError("id") != null) {
			mav.addObject("message", rs.getFieldError("id").getDefaultMessage());
		} else if (reid == null || !membervo.getId().equals(reid)) {
			mav.addObject("message", "아이디 중복확인을 해주세요");
		} else if (rs.getFieldError("pwd") != null) {
			mav.addObject("message", rs.getFieldError("pwd").getDefaultMessage());
		} else if (pwdcheck == null || !membervo.getPwd().equals(pwdcheck)) {
			mav.addObject("message", "비밀번호 확인이 틀렸습니다");
		} else if (rs.getFieldError("name") != null) {
			mav.addObject("message", rs.getFieldError("name").getDefaultMessage());
		} else if (rs.getFieldError("phone") != null) {
			mav.addObject("message", rs.getFieldError("phone").getDefaultMessage());
		} else if (rs.getFieldError("birth") != null) {
			mav.addObject("message", rs.getFieldError("brith").getDefaultMessage());
		} else if (rs.getFieldError("email") != null) {
			mav.addObject("message", rs.getFieldError("email").getDefaultMessage());
		} else if (rs.getFieldError("zip_num") != null) {
			mav.addObject("message", rs.getFieldError("zip_num").getDefaultMessage());
		} else if (rs.getFieldError("address1") != null) {
			mav.addObject("message", rs.getFieldError("address1").getDefaultMessage());
		} else if (rs.getFieldError("address2") != null) {
			mav.addObject("message", rs.getFieldError("address2").getDefaultMessage());
		} else if (rs.getFieldError("gender") != null) {
			mav.addObject("message", rs.getFieldError("gender").getDefaultMessage());
		} else {
			ms.insertMember(membervo);
			rttr.addFlashAttribute("message", "회원가입에 성공하셨습니다");
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");
		}

		return "redirect:/";
	}

	@RequestMapping("/kakaostart")
	public @ResponseBody String kakaostart() {
		String a = "<script type='text/javascript'>" + "location.href='https://kauth.kakao.com/oauth/authorize?"
				+ "client_id=7c1c96c2dfc9d980cab83b4b4a6aabd6" + "&redirect_uri=http://localhost:8070/kakaoLogin&"
				+ "response_type=code';" + "</script>";
		return a;
	}

	@RequestMapping("/kakaoLogin")
	public String loginKakao(HttpServletRequest request) throws UnsupportedEncodingException, IOException {

		// 카카오 아이디, 비번 인증 + 아이디 이메일 제공 동의 후 전송되는 암호화 코드
		String code = request.getParameter("code");
		// 전송된 암호화코드를 이용해서 토큰을 요청
		// 토큰 요청 주소 url 설정 및 파라미터
		String endpoint = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(endpoint); // import java.net.URL;
		String bodyData = "grant_type=authorization_code&";
		bodyData += "client_id=7c1c96c2dfc9d980cab83b4b4a6aabd6";
		bodyData += "&redirect_uri=http://localhost:8070/kakaoLogin&";
		bodyData += "code=" + code;
		// Stream 연결 및 토큰 수신
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // import java.net.HttpURLConnection;
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(bodyData);
		bw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String input = "";
		StringBuilder sb = new StringBuilder(); // 조각난 String 을 조립하기위한 객체
		while ((input = br.readLine()) != null) {
			sb.append(input);
			System.out.println(input); // 수신된 토큰을 콘솔에 출력합니다
		}

		// 위 토큰내용을 본따 만든 클래스에 gson 파싱으로 옮겨 담습니다( sb -> OAuthToken )
		Gson gson = new Gson();
		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);

		// oAuthToken을 이용해서 사용자 정보를 요청 수신
		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
		URL url2 = new URL(endpoint2);
		// import java.net.HttpURLConnection;
		HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
		conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
		conn2.setDoOutput(true);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
		String input2 = "";
		StringBuilder sb2 = new StringBuilder();
		while ((input2 = br2.readLine()) != null) {
			sb2.append(input2);
			System.out.println(input2);
		}

		// 전달받은 회원정보를 kakaoProfile 객체에 담습니다.( sb2 -> kakaoProfile )
		Gson gson2 = new Gson();
		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);

		KakaoAccount ac = kakaoProfile.getAccount();
		Profile pf = ac.getProfile();

		MemberVO mvo = ms.getMember(kakaoProfile.getId());
		if (mvo == null) {
			mvo = new MemberVO();
			mvo.setId(kakaoProfile.getId());
			mvo.setEmail(ac.getEmail());
			mvo.setName(pf.getNickname());
			mvo.setProvider("kakao");
			ms.joinKakao(mvo);
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);

		return "redirect:/";
	}

	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm() {
		return "member/updateForm";
	}

	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult rs,
			@RequestParam(value = "pwdcheck", required = false) String pwdcheck, HttpServletRequest request,
			Model model) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/updateForm");
		if (rs.getFieldError("pwd") != null && membervo.getProvider().equals("")) {
			mav.addObject("message", rs.getFieldError("pwd").getDefaultMessage());
		} else if ((pwdcheck == null || !membervo.getPwd().equals(pwdcheck)) && membervo.getProvider().equals("")) {
			mav.addObject("message", "비밀번호 확인이 틀렸습니다");
		} else if (rs.getFieldError("name") != null) {
			mav.addObject("message", rs.getFieldError("name").getDefaultMessage());
		} else if (rs.getFieldError("birth") != null) {
			mav.addObject("message", rs.getFieldError("brith").getDefaultMessage());
		} else if (rs.getFieldError("phone") != null) {
			mav.addObject("message", rs.getFieldError("phone").getDefaultMessage());
		} else if (rs.getFieldError("email") != null) {
			mav.addObject("message", rs.getFieldError("email").getDefaultMessage());
		} else if (rs.getFieldError("zip_num") != null) {
			mav.addObject("message", rs.getFieldError("zip_num").getDefaultMessage());
		} else if (rs.getFieldError("address1") != null) {
			mav.addObject("message", rs.getFieldError("address1").getDefaultMessage());
		} else if (rs.getFieldError("address2") != null) {
			mav.addObject("message", rs.getFieldError("address2").getDefaultMessage());
		} else if (rs.getFieldError("gender") != null) {
			mav.addObject("message", rs.getFieldError("gender").getDefaultMessage());
		} else {
			ms.updateMember(membervo);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", membervo);
			mav.setViewName("redirect:/");
		}

		return mav;

	}

}
