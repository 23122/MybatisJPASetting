package com.baekhwa.cho.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.ToString;

/*@ToString(exclude = "categoryEntities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "item")*/
public class ItemEntity2 {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int itemNo;
	
	@Column(nullable = false)
	private int itemPrice;
	
	@Column(nullable = false)
	private int itemStock;
	
	@Column(nullable = false)
	private String itemName;
	
//	@Builder.Default
	@OneToMany(mappedBy = "itemEntity")
	private List<ItemOrderEntity> itemOrderEntities=new ArrayList<ItemOrderEntity>();
	
	@JoinTable(
			name="categoryItem",//연계테이블 명
			joinColumns = @JoinColumn(name="itemNo"),//item테이블의 pk : FK(item_no)
			inverseJoinColumns = @JoinColumn(name = "categoryNo"))//category테이블의 pk : FK(category_no)
	@ManyToMany
//	@Builder.Default
	private List<CategoryEntity> categoryEntities=new Vector<CategoryEntity>();
	//추가매서드
	public ItemEntity2 addCategory(CategoryEntity categoryEntity) {
		categoryEntities.add(categoryEntity);
		return this;
	}
	//삭제메서드
	public ItemEntity2 removeCategory(CategoryEntity categoryEntity) {
		categoryEntities.remove(categoryEntity);
		return this;
	}

	
}
