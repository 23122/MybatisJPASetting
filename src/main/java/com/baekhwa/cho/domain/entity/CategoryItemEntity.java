package com.baekhwa.cho.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categoryItem")
@Getter
public class CategoryItemEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long CategoryItemNo;
	
	@JoinColumn(name = "categoryNo")
	@ManyToOne
	CategoryEntity categoryEntity;
	
	@JoinColumn(name = "itemNo")
	@ManyToOne(cascade = CascadeType.ALL)
	ItemEntity itemEntity;
}
