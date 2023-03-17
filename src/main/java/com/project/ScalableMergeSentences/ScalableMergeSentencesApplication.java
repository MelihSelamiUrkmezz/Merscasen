package com.project.ScalableMergeSentences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ScalableMergeSentencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalableMergeSentencesApplication.class, args);
	}

}
