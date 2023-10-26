package org.zerock.service.Board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDto;
import org.zerock.dto.CriteriaDto;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	@Override
	public void register(BoardDto board) {
		log.info("글등록 처리 테스트 입니다 ===> " + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardDto get(Long bno) {
		log.info("글상세조회 기능이 구현됩니다!" + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardDto board) {
		log.info("글수정이 처리됩니다!" + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("글 삭제 기능을 처리합니다!" + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardDto> getList(CriteriaDto cri) {
		log.info("pageNum와 amount를 고려한 getList() 글목록 조회! : " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(CriteriaDto cri) {
		log.info("전체 데이터 숫자를 카운트해서 알려줍니다");
		return mapper.getTotalCount(cri);
	}
}
