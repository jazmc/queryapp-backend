package com.example.queryappbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.queryappbackend.domain.Questiongroup;
import com.example.queryappbackend.domain.QuestiongroupRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionRepository;

@SpringBootApplication
public class QueryappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryappBackendApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(QuestionRepository repository, QuestiongroupRepository grep) {
		return (args) -> {
			// create example questionnaires
			Questiongroup g1 = new Questiongroup("Group 1 title");
			Questiongroup g2 = new Questiongroup("Group 2 title");
			
			grep.save(g1);
			grep.save(g2);
			
			// create example questions
			Question q1 = new Question("This is an example question for group 1. Can you answer it?", "radio", new String[] {"Right", "Wrong1", "Wrong2", "Wrong3"}, g1);
			Question q2 = new Question("Question for group 2. What do you think?", "radio", new String[] {"Yay", "Wrong1", "Wrong2", "Wrong3"}, g2);
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
