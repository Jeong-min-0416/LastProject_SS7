package org.zerock.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUserDto extends User {
   private static final long serialVersionUID = 1L;
   private MemberDto member;

   public CustomUserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
   }

   public CustomUserDto(MemberDto vo) {
      super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream()
            .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
            .collect(Collectors.toList()));

      this.member = vo;
   }

}