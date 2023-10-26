package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDto;
import org.zerock.dto.CriteriaDto;

public interface BoardMapper {

  public List<BoardDto> getList();

  public List<BoardDto> getListWithPaging(CriteriaDto cri);

  public void insert(BoardDto board);

  public void insertSelectKey(BoardDto board);

  public BoardDto read(Long bno);

  public int delete(Long bno);

  public int update(BoardDto board);

  public int getTotalCount(CriteriaDto cri);

  public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}
