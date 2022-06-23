package com.baekhwa.cho.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "delivery")
public class DeliveryEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long deliveryNo;
	@Column(nullable = false)
	private int zipcode;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String detail;
	
	@OneToOne(mappedBy = "deliveryEntity")
	private OrderEntity orderEntity;
}
