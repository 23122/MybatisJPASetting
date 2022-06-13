package com.baekhwa.cho.mybatis.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baekhwa.cho.domain.dto.BoardDTO;
import com.baekhwa.cho.domain.dto.BoardInsertDTO;
import com.baekhwa.cho.domain.dto.BoardListDTO;
import com.baekhwa.cho.domain.dto.BoardUpdateDTO;

@Mapper
public interface BoardMapper {

	@Select("select * from mybatis_board where no>0 order by no desc")
	List<BoardDTO> findAll();
	
	@Insert("insert into mybatis_board(title,content,writer,read_count) values (#{title},#{content},#{writer},0)")
	void insert(BoardInsertDTO dto);
	
	@Select("select * from mybatis_board where no=#{vno}")
	Optional<BoardDTO> detailBoard(int vno);
	
	@Update("Update mybatis_board set title=#{title},content=#{content} where no=#{no}")
	void update(BoardUpdateDTO dto);
	
	@Delete("delete from mybatis_board where no=#{no}")
	void delete(int no);
	
	@Insert("insert into mybatis_board(title,content,writer,read_count) values (#{title},#{content},#{writer},0)")
	void save(BoardInsertDTO build);
	
	@Select("select * from mybatis_board where no>0 order by no desc limit #{offset} , #{limit} ")
	List<BoardListDTO> findFive(@Param("offset") int offset,@Param("limit") int limit);
	
	@Select("select count(*) from mybatis_board where no>0")
	int selectCount();
	
	@Update("update mybatis_board set read_count=read_count+1 where no=#{vno}")
	void updateReadCount(int vno);

}
