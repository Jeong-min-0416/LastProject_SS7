package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample1Mapper {

	@Insert("INSERT into tbl_sample1(col1) VALUES(#{data})")
	public int insertColl(String data);
	
}
