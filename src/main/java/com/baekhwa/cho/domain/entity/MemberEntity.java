package com.baekhwa.cho.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "member")
public class MemberEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long memberNo;
	@Column(nullable = false,unique = true)
	private String memberEmail;
	@Column(nullable = false)
	private String memberPass;
	@Column(nullable = false)
	private String memberName;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	@Builder.Default
	@OneToMany(mappedBy = "memberEntity")
	private List<OrderEntity> orderEntities=new Vector<OrderEntity>();
	
	@Builder.Default
	@OneToMany(mappedBy = "memberEntity")
	private List<JpaBoardEntity> jpaBoardEntities=new Vector<JpaBoardEntity>();
	
	public void addOrderEntity(OrderEntity orderEntity) {
		orderEntities.add(orderEntity);
	}
}
