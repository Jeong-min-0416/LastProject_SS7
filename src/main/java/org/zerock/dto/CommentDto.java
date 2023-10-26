package org.zerock.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class CommentDto {

	private int replyCnt;
	private List<ReplyDto> list;
	
}