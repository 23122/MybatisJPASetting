package com.baekhwa.cho;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baekhwa.cho.domain.dto.BoardInsertDTO;
import com.baekhwa.cho.domain.dto.MemberDTO;
import com.baekhwa.cho.domain.dto.MemberInsertDTO;
import com.baekhwa.cho.domain.dto.MemberUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.entity.CategoryEntity;
import com.baekhwa.cho.domain.entity.CategoryItemEntity;
import com.baekhwa.cho.domain.entity.CategoryItemRepository;
import com.baekhwa.cho.domain.entity.CategoryRepository;
import com.baekhwa.cho.domain.entity.ItemEntity;
import com.baekhwa.cho.domain.entity.ItemRepository;
import com.baekhwa.cho.mybatis.mapper.BoardMapper;
import com.baekhwa.cho.mybatis.mapper.MemberMapper;
import com.baekhwa.cho.service.BoardJpaService;

@SpringBootTest
class BaekhwaProjectApplicationTests {

	@Autowired
	MemberMapper mapper;

	@Autowired
	BoardMapper boardMapper;

	// @Test
	void 멤버삽입테스트() {
		int r = mapper.save(MemberInsertDTO.builder().email("test02@test.com").name("test01").pass("1111").build());
		// update 된 rows 수 리턴
		System.out.println(r + "개의 회원정보를 삽입하였습니다.");
	}

	// @Test
	void 비밀번호수정() {
		MemberUpdateDTO dto = MemberUpdateDTO.builder().no(2).pass("2222").build();
		// 먼저 대상이 존재하는지 체크
		Optional<MemberDTO> result = mapper.selectById(dto.getNo());
		if (result.isEmpty()) {
			System.out.println(">>>존재하지않는 회원");
			return;
		}

		mapper.update(dto);
	}

//	@Test
	void 보드더미생성() {
		IntStream.rangeClosed(1, 200).forEach(i -> {
			boardMapper.save(BoardInsertDTO.builder().title("더미" + i).content("더미내용" + i).writer("master").build());
		});
	}

	@Autowired
	BoardJpaService service;

//	@Test
	void jpa더미() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			JpaBoardInsertDTO dto = new JpaBoardInsertDTO();
			dto.setTitle("더미" + i);
			dto.setContent("더미내용" + i);
			dto.setWriter("super");

			service.save(dto);
		});

	}

	@Autowired
	CategoryRepository categoryRepository;

//	@Test
	void 카테고리등록() {
		categoryRepository.save(CategoryEntity.builder().categoryName("가전").build());
	}

	@Autowired
	CategoryItemRepository categoryItemRepository;
	
//	@Test
	void 상품등록() {
		CategoryItemEntity entity=CategoryItemEntity.builder()
				.categoryEntity(categoryRepository.findById(1L).get())
				.itemEntity(ItemEntity.builder()
						.itemName("송이향버섯").itemPrice(29800).itemStock(100).build())
				.build();
		categoryItemRepository.save(entity);
//		CategoryItemEntity entity=CategoryItemEntity.builder()
//				.categoryEntity(CategoryEntity.builder().categoryNo(1).build())
//				.itemEntity(ItemEntity.builder()
//						.itemName("커피").itemPrice(2000).itemStock(1000).build())
//				.build();
//		categoryItemRepository.save(entity);
	}
	
//	@Test
	void 연계테이블_상품조회() {
		List<CategoryItemEntity> list=categoryItemRepository.findAllByCategoryEntityCategoryNo(1L);
		for(CategoryItemEntity r:list) {
			System.out.println(r.getItemEntity());
		}
	}
	
	@Transactional
	@Test
	void 카테고리이름검색_상품조회() {
		CategoryEntity result = categoryRepository.findByCategoryName("식품");
		List<CategoryItemEntity> categoryItem=result.getCategoryItemEntities();
		for(CategoryItemEntity r:categoryItem) {
			System.out.println(r.getItemEntity());
		}
	}
}
