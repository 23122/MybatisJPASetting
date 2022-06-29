package com.baekhwa.cho.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import lombok.ToString;

@ToString(exclude = {"categoryItemEntities","itemOrderEntities"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "item")
public class ItemEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long itemNo;
	
	@Column(nullable = false)
	private int itemPrice;
	
	@Column(nullable = false)
	private int itemStock;
	
	@Column(nullable = false)
	private String itemName;
	
	@Builder.Default
	@OneToMany(mappedBy = "itemEntity")
	private List<ItemOrderEntity> itemOrderEntities=new ArrayList<ItemOrderEntity>();
	
	@Builder.Default
	@OneToMany(mappedBy = "itemEntity",fetch = FetchType.LAZY)
	private List<CategoryItemEntity> categoryItemEntities=new ArrayList<CategoryItemEntity>();

	public ItemEntity addCategoryItemEntity(CategoryItemEntity categoryItemEntity) {
		categoryItemEntities.add(categoryItemEntity);
		return this;
	}
}
