package com.example.my_first_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SpringPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringPracticeApplication.class, args);
	}
	//http://localhost:8080/hello
	@Controller
	public class HellowWorldController {
		@GetMapping("/hello")
		public String hello(Model model) {
			model.addAttribute("name", "Spring Boot");
			return "hello";
		}
	}
}
