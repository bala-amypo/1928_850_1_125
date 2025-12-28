package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        // System.out.println(new BCryptPasswordEncoder().encode("123"));

		// BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		// if(encoder.matches("123","$2a$10$bDfW6xvE/b8zYEg7n4UAAeXPAe3WF98PpT4xx.70keopYoOw2uyGO")){
		// 	System.out.println("password matched");
		// }else{
		// 	System.out.println("no match");
		// }
		 }
         
    }

