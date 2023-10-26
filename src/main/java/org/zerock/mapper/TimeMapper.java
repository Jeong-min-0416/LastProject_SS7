package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	// SELECT sysdate from dual;
	@Select("SELECT sysdate from dual")
	public String getTime();
	
	
	// 마이바티스의 XML 형식으로 처리할 getTime2() 메서드를 선언함
	public String getTime2();
	
	
}
