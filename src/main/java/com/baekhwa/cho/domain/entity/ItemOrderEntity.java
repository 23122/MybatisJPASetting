package com.baekhwa.cho.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "cart")
public class ItemOrderEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartNo")
	@Id
	private long itemOrderNo;
	@Column(name = "cartPrice",nullable = false)
	private int itemOrderPrice;
	@Column(name = "cartCount",nullable = false)
	private int itemOrderCount;
	
	@JoinColumn(name = "orderNo",nullable = false)
	@ManyToOne(cascade = CascadeType.PERSIST)
	OrderEntity orderEntity;
	
	@JoinColumn(name = "itemNo",nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH)
	ItemEntity itemEntity;
}
