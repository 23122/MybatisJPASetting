package com.baekhwa.cho.mybatis.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baekhwa.cho.domain.dto.MemberDTO;
import com.baekhwa.cho.domain.dto.MemberInsertDTO;
import com.baekhwa.cho.domain.dto.MemberUpdateDTO;

@Mapper //Marker interface for MyBatis mappers
public interface MemberMapper {
	
	@Insert("insert into member(member_email,member_name,member_pass)"
			+ "values(#{email}, #{name}, #{pass})")
	int save(MemberInsertDTO dto);
	
	//두줄이상쿼리 생성시 주의!!! 구분 뛰어쓰기 잘하세요..
	@Update("update member "
			+ " set member_pass=#{pass} where member_no=#{no}")
	int update(MemberUpdateDTO dto);

	@Select("select * from member where member_no=#{no}")
	Optional<MemberDTO> selectById(long no);

	@Select("select * from member where member_email=#{email}")
	Optional<MemberDTO> findByEmail(String email);

}
