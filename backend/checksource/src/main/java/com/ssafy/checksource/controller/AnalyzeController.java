package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.service.AnalyzeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

	private final AnalyzeService analyzeService;

	@ApiOperation(value = "테스트")
	@GetMapping("/test")
	public void test() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n" + 
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
				"	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"	<modelVersion>4.0.0</modelVersion>\r\n" + 
				"	<parent>\r\n" + 
				"		<groupId>org.springframework.boot</groupId>\r\n" + 
				"		<artifactId>spring-boot-starter-parent</artifactId>\r\n" + 
				"		<!-- <version>2.3.0.RELEASE</version> -->\r\n" + 
				"		<version>2.4.1</version>\r\n" + 
				"		<relativePath /> <!-- lookup parent from repository -->\r\n" + 
				"	</parent>\r\n" + 
				"	<groupId>com.web</groupId>\r\n" + 
				"	<artifactId>backendsub</artifactId>\r\n" + 
				"	<version>0.0.1-SNAPSHOT</version>\r\n" + 
				"	<name>backendsub</name>\r\n" + 
				"	<description>ssafy sub pjt1</description>\r\n" + 
				"\r\n" + 
				"	<properties>\r\n" + 
				"		<java.version>1.8</java.version>\r\n" + 
				"	</properties>\r\n" + 
				"\r\n" + 
				"	<dependencies>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-web</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.mybatis.spring.boot</groupId>\r\n" + 
				"			<artifactId>mybatis-spring-boot-starter</artifactId>\r\n" + 
				"			<version>2.1.4</version>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-devtools</artifactId>\r\n" + 
				"			<scope>runtime</scope>\r\n" + 
				"			<optional>true</optional>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>mysql</groupId>\r\n" + 
				"			<artifactId>mysql-connector-java</artifactId>\r\n" + 
				"			<scope>runtime</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-test</artifactId>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<!-- jwt 로그인 -->\r\n" + 
				"		<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>io.jsonwebtoken</groupId>\r\n" + 
				"			<artifactId>jjwt</artifactId>\r\n" + 
				"			<version>0.9.1</version>\r\n" + 
				"		</dependency>\r\n" + 
				"		<!-- API, java.xml.bind module -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>jakarta.xml.bind</groupId>\r\n" + 
				"			<artifactId>jakarta.xml.bind-api</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"		<!-- Runtime, com.sun.xml.bind module -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.glassfish.jaxb</groupId>\r\n" + 
				"			<artifactId>jaxb-runtime</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"\r\n" + 
				"		<!-- swagger -->\r\n" + 
				"		<!-- springfox-swagger2 -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>io.springfox</groupId>\r\n" + 
				"			<artifactId>springfox-swagger-ui</artifactId>\r\n" + 
				"			<version>2.9.2</version>\r\n" + 
				"		</dependency>\r\n" + 
				"		<!-- springfox-swager-ui -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>io.springfox</groupId>\r\n" + 
				"			<artifactId>springfox-swagger2</artifactId>\r\n" + 
				"			<version>2.9.2</version>\r\n" + 
				"		</dependency>\r\n" + 
				"		\r\n" + 
				"		<!-- json -->\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>com.google.code.gson</groupId>\r\n" + 
				"			<artifactId>gson</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"	</dependencies>\r\n" + 
				"\r\n" + 
				"	<build>\r\n" + 
				"		<plugins>\r\n" + 
				"			<plugin>\r\n" + 
				"				<groupId>org.springframework.boot</groupId>\r\n" + 
				"				<artifactId>spring-boot-maven-plugin</artifactId>\r\n" + 
				"			</plugin>\r\n" + 
				"		</plugins>\r\n" + 
				"	</build>\r\n" + 
				"\r\n" + 
				"</project>\r\n" + 
				"";
		try {
		analyzeService.pomxmlParsing(xml);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
