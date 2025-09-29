package com.syed.quizApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(QuizApplication.class, args);

	}

}
