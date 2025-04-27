package com.LMS.LMSBsckend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class LmsBsckendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsBsckendApplication.class, args);
		String userp="1234";
		String Admib="1111";
	}
}
//hell
//select course_provider,course_name,description,deuration,price,imgname,what_you_learn1,what_you_learn2,what_you_learn3,what_you_learn4 from course_det;
//<!--https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-tomcat
//	<dependency>
//		<groupId>org.springframework.boot</groupId>
//		<artifactId>spring-boot-starter-tomcat</artifactId>
//		<version>3.4.1</version>
//	</dependency> 
//	<dependency>
//		<groupId>org.jcodec</groupId>
//		<artifactId>jcodec</artifactId>
//		<version>0.2.5</version>
//	</dependency>-->
