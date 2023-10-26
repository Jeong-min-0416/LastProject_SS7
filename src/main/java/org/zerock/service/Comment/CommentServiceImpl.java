package org.zerock.service.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.CommentDto;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.ReplyDto;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

	private ReplyMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;

	@Transactional
	@Override
	public int register(ReplyDto vo) {
		log.info("댓글 등록이 정상적으로 처리 되었습니다" + vo);

		boardMapper.updateReplyCnt(vo.getBno(), 1);

		return mapper.insert(vo);
	}

	@Override
	public ReplyDto get(Long rno) {
		log.info("댓글 상세보기 기능입니다" + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyDto vo) {
		log.info("댓글 수정 기능입니다" + vo);
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("댓글 삭제 기능입니다!" + rno);
		ReplyDto vo = mapper.read(rno);

		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyDto> getList(CriteriaDto cri, Long bno) {
		log.info("댓글 목록 조회 기능입니다!" + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public CommentDto getListPage(CriteriaDto cri, Long bno) {
		return new CommentDto(
			mapper.getCountByBno(bno),
			mapper.getListWithPaging(cri, bno));
	}
}
