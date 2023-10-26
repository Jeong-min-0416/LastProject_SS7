package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.ReplyDto;

public interface ReplyMapper {

	public int insert(ReplyDto vo);
	
	public ReplyDto read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyDto reply);
	
	public List<ReplyDto> getListWithPaging(
			@Param("cri") CriteriaDto cri,
			@Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
	
}
