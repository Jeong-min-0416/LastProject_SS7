package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

	// 1. 접근 권한이 없는 경우 알림
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denide 접근이 거부 되었습니다! ==> " + auth);
		model.addAttribute("msg", "AccessDenied 접근 거부");
	}

	// 2. 로그인 관련 메시지를 제공
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error 에러발생" + error);
		log.info("logout 로그아웃" + logout);

		if (error != null) {
			model.addAttribute("error",
			"Login Error Check Your Account: 고객님의 계정 로그인 에러를 확인해 주시기 바랍니다!");
		}
		if (logout != null) {
			model.addAttribute("logout", "logout 로그아웃!");
		}
	}

	// 3. 로그아웃을 수행하는 페이지에 대한 처리
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout 사용자 정의 로그아웃");
	}

}
