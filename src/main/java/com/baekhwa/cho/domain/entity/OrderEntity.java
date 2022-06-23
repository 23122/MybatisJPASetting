package com.baekhwa.cho.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "orders")
public class OrderEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long orderNo;
	@Column(nullable = false)
	private String status;
	
	@Builder.Default
	@OneToMany(mappedBy = "orderEntity")
	private List<ItemOrderEntity> orderEntities =new ArrayList<ItemOrderEntity>();
	public void addItemOrderEntity(ItemOrderEntity itemOrderEntity) {
		orderEntities.add(itemOrderEntity);
	}
	
	@JoinColumn
	@ManyToOne
	MemberEntity memberEntity;
	
	@JoinColumn(name = "deliveryNo")
	@OneToOne
	private DeliveryEntity deliveryEntity;
	
}
