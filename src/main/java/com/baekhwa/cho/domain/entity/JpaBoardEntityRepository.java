package com.baekhwa.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                             //<entity,id dataType->wrapperClass>
public interface JpaBoardEntityRepository extends JpaRepository<JpaBoardEntity, Long>{

}
