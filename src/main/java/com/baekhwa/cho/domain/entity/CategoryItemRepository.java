package com.baekhwa.cho.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryItemRepository extends JpaRepository<CategoryItemEntity, Long>{

	List<CategoryItemEntity> findAllByCategoryEntityCategoryNo(long CategoryNo);
	
}
