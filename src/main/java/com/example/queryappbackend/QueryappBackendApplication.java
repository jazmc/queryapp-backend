package com.example.queryappbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.queryappbackend.domain.QuestionGroup;
import com.example.queryappbackend.domain.QuestionGroupRepository;
import com.example.queryappbackend.domain.MaintainerUser;
import com.example.queryappbackend.domain.MaintainerUserRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionRepository;

@SpringBootApplication
public class QueryappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryappBackendApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(	QuestionRepository repository, 
									QuestionGroupRepository grep,
									MaintainerUserRepository mUserRepository) {
		return (args) -> {
			
			//Usernames are UNIQUE, so you must deleteAll in Heroku. For example urepository.deleteAll() in your CommandLineRunner.
			mUserRepository.deleteAll();
			
			//username: admin, password: admin
			MaintainerUser user1 = new MaintainerUser("admin", "$2a$10$SUx2Q1/z5Kfn60CdIw5.ouj4o.z1dvS2TRXL4z/uhTFq8BWXPUiBe", "USER");
			mUserRepository.save(user1);
			
			// create example questionnaires
			QuestionGroup g1 = new QuestionGroup("Test Questionnaire 1");
			QuestionGroup g2 = new QuestionGroup("Another test questionnaire");
			
			grep.save(g1);
			grep.save(g2);
			
			// create example questions
			Question q1 = new Question("This is an example question for group 1. Can you answer it?", "radio", new String[] {"Right", "Wrong1", "Wrong2", "Wrong3"}, g1);
			Question q2 = new Question("Question for group 2. What do you think?", "radio", new String[] {"Yay", "Wrong1", "Wrong2", "Wrong3", "Another", "Anotherother"}, g2);
			Question q3 = new Question("Another question for group 2. How cool is that?", "radio", new String[] {"Very cool", "Wrong1", "Wrong2", "Wrong3"}, g2);
			Question q4 = new Question("How about this?", "radio", new String[] {"There is...", "...no...", "...wrong...", "...answers"}, g1);
			
			System.out.println(q2.getAnswers()[2]);
			
			
			repository.save(q1);
			repository.save(q2);
			repository.save(q3);
			repository.save(q4);
		};
	}

}
