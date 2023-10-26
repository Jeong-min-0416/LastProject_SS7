package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {

	@Insert("INSERT into tbl_sample2(col2) VALUES(#{data})")
	public int insertColl(String data);
	
}
