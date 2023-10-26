package org.zerock.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{@Override
   public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

      log.warn("Login Success 로그인 성공!");
   
      List<String> roleNames = new ArrayList<String>();
      authentication.getAuthorities().forEach(authority -> {
         roleNames.add(authority.getAuthority());
      });

      log.warn("ROLE NAMES 롤 이름 : " + roleNames);
      if (roleNames.contains("ROLE_ADMIN")) {
         response.sendRedirect("/board/list");
         return;
      }
      if (roleNames.contains("ROLE_MEMBER")) {
         response.sendRedirect("/board/list");
         return;
      }
      
      response.sendRedirect("/");
   }
}