package com.baekhwa.cho.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;




@Configuration
public class MyBatisConfig {//baen name:  myBatisConfig //myBatisConfig
	@Autowired
	private DataSource dasource;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		System.out.println(">>>>"+dasource);
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		factoryBean.setDataSource(dasource);
		//src/main/resources 자원을 읽어들이기위한 객체
		// static/mapper 폴더가 존재해야합니다.없으면 만들어야해요
		String locationPattern="classpath:static/mapper/**/*-mapper.xml";
		Resource[] resources=applicationContext.getResources(locationPattern);
		factoryBean.setMapperLocations(resources);
		//org.apache.ibatis.session.Configuration 반영
		factoryBean.setConfiguration(mBatisConfig());
		
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	//DB의 컬럼명 java class의 필드명 mapping
	//하나의 클래스에 동일한 이름의 클래스 사용시 import는 1개만 가능
	//그래서 추가해서 사용한 클래스는 full-name으로 적용하세요
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mBatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
}