package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.ReplyDto;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	// private Long[] bnoArr = { 1312560L, 1312548L, 1312547L, 1312546L, 1312545L };

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Test
	public void testList2() {
		CriteriaDto cri = new CriteriaDto(1, 10);

		List<ReplyDto> replies = mapper.getListWithPaging(cri, 1312560L);

		replies.forEach(reply -> log.info(reply));
	}
}
