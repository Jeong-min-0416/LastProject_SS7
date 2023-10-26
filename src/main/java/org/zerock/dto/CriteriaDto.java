package org.zerock.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CriteriaDto {
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;

	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}

	public CriteriaDto() {
		this(1, 15);
	}

	public CriteriaDto(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}