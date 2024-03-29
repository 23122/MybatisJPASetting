package com.baekhwa.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	CategoryEntity findByCategoryName(String string);
	
}
