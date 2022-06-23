package com.baekhwa.cho.domain.entity;

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
@Entity(name = "itemOrder")
public class ItemOrderEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long itemOrderNo;
	@Column(nullable = false)
	private int itemOrderPrice;
	@Column(nullable = false)
	private int itemOrderCount;
	
	@JoinColumn(name = "orderNo")
	@ManyToOne
	OrderEntity orderEntity;
	
	@JoinColumn(name = "itemNo")
	@ManyToOne
	ItemEntity itemEntity;
}
