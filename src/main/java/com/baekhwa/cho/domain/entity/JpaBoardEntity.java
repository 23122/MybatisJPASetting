package com.baekhwa.cho.domain.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class JpaBoardEntity {//jpa_board_entity

	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	@Id
	private long no;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "text not null")
	private String content;
	
	@Column(nullable = false)
	private String writer;
	
	@Column
	private int readCount;
	
	//@EnableJpaAuditing -> @CreatedDate @LastModifiedDate
	@CreatedDate
	@Column(columnDefinition = "timestamp", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedDate;
	
	//PK---FK 설정
	//1:1 1:N N:1 N:M
	@Builder.Default
	@OneToMany(mappedBy = "jpaBoardEntity", cascade = CascadeType.ALL)//1:N 설정
	private Collection<ReplyEntity> replies=new Vector<ReplyEntity>();
	
	
	//수정처리
	public JpaBoardEntity update(JpaBoardUpdateDTO dto){
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;
	}

}
