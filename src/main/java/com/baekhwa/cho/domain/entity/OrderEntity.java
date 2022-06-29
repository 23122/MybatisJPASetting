package com.baekhwa.cho.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import lombok.ToString;

@ToString(exclude = {"memberEntity","deliveryEntity"})
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
	
	@JoinColumn(name = "memberNo",nullable = false)
	@ManyToOne
	private MemberEntity memberEntity;
	
	public OrderEntity cartMember(MemberEntity memberEntity) {
		this.memberEntity=memberEntity;
		return this;
	}
	
	@JoinColumn(name = "deliveryNo",nullable = false)
	@OneToOne
	private DeliveryEntity deliveryEntity;
	
	
}
