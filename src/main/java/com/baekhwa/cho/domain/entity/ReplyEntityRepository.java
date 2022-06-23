package com.baekhwa.cho.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{

	List<ReplyEntity> findAllByJpaBoardEntityNo(long bno);
	
	Page<ReplyEntity> findAllByJpaBoardEntityNo(long bno,Pageable pageable);

	List<ReplyEntity> findAllByJpaBoardEntityNo(long bno, Sort by);
}
