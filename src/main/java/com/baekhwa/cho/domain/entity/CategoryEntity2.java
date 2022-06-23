package com.baekhwa.cho.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*@ToString(exclude = "itemEntities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "category")*/
public class CategoryEntity2 {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int categoryNo;
	
	@Column(nullable = false)
	private String categoryName;
	
	@ManyToMany(mappedBy = "categoryEntities")
//	@Builder.Default
	private List<ItemEntity> itemEntities=new Vector<ItemEntity>();

}
