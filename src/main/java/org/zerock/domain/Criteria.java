package org.zerock.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	private String type;
	private String keyword;

	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}

	public Criteria() {
		this(1, 15);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}