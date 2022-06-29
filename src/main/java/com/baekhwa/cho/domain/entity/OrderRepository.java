package com.baekhwa.cho.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	List<OrderEntity> findAllByMemberEntityMemberNo(long memberNo);
	List<OrderEntity> findAllByMemberEntityMemberNoAndStatus(long memberNo,String status);

}
