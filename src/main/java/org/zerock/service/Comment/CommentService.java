package org.zerock.service.Comment;

import java.util.List;

import org.zerock.dto.CommentDto;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.ReplyDto;

public interface CommentService {

	public int register(ReplyDto vo);

	public ReplyDto get(Long rno);

	public int modify(ReplyDto vo);

	public int remove(Long rno);

	public List<ReplyDto> getList(CriteriaDto cri, Long bno);

	public CommentDto getListPage(CriteriaDto cri, Long bno);

}
