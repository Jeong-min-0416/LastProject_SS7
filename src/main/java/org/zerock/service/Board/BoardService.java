package org.zerock.service.Board;

import java.util.List;

import org.zerock.dto.BoardDto;
import org.zerock.dto.CriteriaDto;

public interface BoardService {
	
	public void register(BoardDto board);
	
	public BoardDto get(Long bno);
	
	public boolean modify(BoardDto board);
	
	public boolean remove(Long bno);
	
	public List<BoardDto> getList(CriteriaDto cri);
	
	public int getTotal(CriteriaDto cri);

}
