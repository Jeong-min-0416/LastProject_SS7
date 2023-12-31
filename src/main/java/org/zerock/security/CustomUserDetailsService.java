package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.dto.CustomUserDto;
import org.zerock.dto.MemberDto;
import org.zerock.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

   @Setter(onMethod_ = @Autowired)
   private MemberMapper memberMapper;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      log.warn("Load User By UserName 사용자 : " + username);
      MemberDto vo = memberMapper.read(username);

      log.warn("queried by member mapper 멤버 메퍼 : " + vo);
      return vo == null ? null : new CustomUserDto(vo);
   }
}