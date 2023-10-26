package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.dto.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//Test for Controller
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class SampleControllerTests {

   @Setter(onMethod_ = {@Autowired})
   private WebApplicationContext ctx;
   
   // MockMvc는 "가짜 mvc"라고 생각하면 됩니다. 가짜로 URL과 파라미터 등을 브라우저에서
   // 사용하는 것처럼 만들어서 Controller를 실행해 볼 수 있습니다.
   // 아래에 testList()는 MockMvcRequestBuilders라는 존재를 이용해서
   // GET 방식의 호출을 합니다. 이후에는 BoardController의 getList()에서 반환된 결과를 이용해서
   // Model에 어떤 데이터들이 담겨 있는지 확인합니다.
   // Tomcat을 통해서 프론트앤드 실행되는 확인이 아닌 백엔드 테스트 방식으로 실행하는 것과
   // 동일하게 실행 테스트 확인을 할 수 있습니다.
   private MockMvc mockMvc;
   
   // 스프링에서 WebApplicationContext를 이용하는데,
   // @Before 어노테이션이 적용된 setup() 메서드에서는 import할때 JUnit을 이용합니다.
   // @Before가 적용된 setup() 메서드는 모든 테스트 사전에(Before) 매번 실행되는 메서드가 됩니다.
   @Before
   public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
   }
   
   @Test
   public void testConvert() throws Exception {
      Ticket ticket = new Ticket();
      ticket.setTno(230519);
      ticket.setOwner("장나라");
      ticket.setGrade("VVIP");
      
      // Gson 라이브러리는 Java의 객체(ticket)를 JSON 문자열로 변환하기 위해 사용합니다.
      String jsonStr = new Gson().toJson(ticket);
      
      log.info(jsonStr);
      
      mockMvc.perform(post("/sample/ticket")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonStr))
            .andExpect(status().is(200));
   }

}