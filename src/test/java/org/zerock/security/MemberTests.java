package org.zerock.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
      "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class MemberTests {

   @Setter(onMethod_ = @Autowired)
   private PasswordEncoder pwencoder;

   @Setter(onMethod_ = @Autowired)
   private DataSource ds;

   public void testInsertMember() {
      String sql = "INSERT INTO tbl_member(USERID, USERPW, USERNAME) VALUES(?, ?, ?)";

      for (int i = 0; i < 100; i++) { // i = 0부터 99까지 100개
         Connection con = null;
         PreparedStatement pstmt = null;

         try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(2, pwencoder.encode("pw" + i));

            if (i < 80) { // i = 0부터 79까지 80개는 user, 일반 사용자
               pstmt.setString(1, "user" + i);
               pstmt.setString(3, "일반사용자" + i);
            } else if (i < 90) { // i = 80부터 89번까지 10개는 manager, 운영자
               pstmt.setString(1, "manager" + i);
               pstmt.setString(3, "운영자" + i);
            } else { // 90부터 99까지 10개는 admin, 관리자
               pstmt.setString(1, "admin" + i);
               pstmt.setString(3, "관리자" + i);
            }
            pstmt.executeUpdate();

         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            if (pstmt != null) {
               try {
                  pstmt.close();
               } catch (SQLException e2) {
                  e2.printStackTrace();
               }
            }
            if (con != null) {
               try {
                  con.close();
               } catch (SQLException e2) {
                  e2.printStackTrace();
               }
            }
         }
      }
   }

   public void testInsertAuth() {
      String sql = "INSERT INTO tbl_member_auth(USERID, AUTH) VALUES(?, ?)";

      for (int i = 0; i < 100; i++) { // i = 0부터 99까지 100개
         Connection con = null;
         PreparedStatement pstmt = null;

         try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);

            if (i < 80) { // i = 0부터 79까지 80개는 user, 일반 사용자
               pstmt.setString(1, "user" + i);
               pstmt.setString(2, "ROLE_USER");
            } else if (i < 90) { // i = 80부터 89번까지 10개는 manager, 운영자
               pstmt.setString(1, "manager" + i);
               pstmt.setString(2, "ROLE_MEMBER");
            } else { // 90부터 99까지 10개는 admin, 관리자
               pstmt.setString(1, "admin" + i);
               pstmt.setString(2, "ROLE_ADMIN");
            }
            pstmt.executeUpdate();

         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            if (pstmt != null) {
               try {
                  pstmt.close();
               } catch (SQLException e2) {
                  e2.printStackTrace();
               }
            }
            if (con != null) {
               try {
                  con.close();
               } catch (SQLException e2) {
                  e2.printStackTrace();
               }
            }
         }

      }
   }
}