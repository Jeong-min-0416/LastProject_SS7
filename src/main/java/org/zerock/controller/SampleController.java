package org.zerock.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController{
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람 접근 가능");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info("멤버만 접근 가능");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("관리자만 접근 가능");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void doMember2() {
		log.info("시큐리티 애노테이션 적용 멤버 로그인 확인");
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("시큐리티 애노테이션 적용 관리자 로그인 확인");
	}
	
}