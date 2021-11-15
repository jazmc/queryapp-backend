package com.example.queryappbackend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.queryappbackend.domain.Questiongroup;
import com.example.queryappbackend.domain.QuestiongroupRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionRepository;

@Controller
public class QuestionController {
	@Autowired
	private QuestionRepository repository;
	@Autowired
	private QuestiongroupRepository grep;
	
	// all questions
	@GetMapping("/questions")
	public @ResponseBody List<Question> questionsREST() {
		return (List<Question>) repository.findAll();
	}
	
	// question by id
	@GetMapping("/questions/{id}")
	public @ResponseBody Optional<Question> findQuestionREST(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	// all questionnaires = groups
	@GetMapping("/groups")
	public @ResponseBody List<Questiongroup> groupsREST() {
		return (List<Questiongroup>) grep.findAll();
	}
	
	// questionnaire = group by id
	@GetMapping("/groups/{id}")
	public @ResponseBody Optional<Questiongroup> findGroupREST(@PathVariable("id") Long id) {
		return grep.findById(id);
	}
}
