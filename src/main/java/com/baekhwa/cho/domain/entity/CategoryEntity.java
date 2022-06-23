package com.baekhwa.cho.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "categoryItemEntities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "category")
public class CategoryEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long categoryNo;
	
	@Column(nullable = false)
	private String categoryName;
	
	@OneToMany(mappedBy = "categoryEntity")
	@Builder.Default
	private List<CategoryItemEntity> categoryItemEntities=new Vector<CategoryItemEntity>();

}
