package com.example.queryappbackend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/*
	 * ADD METHOD:
	 */
	@CrossOrigin
	@PostMapping("/groups")
	public @ResponseBody Questiongroup addGroupREST(String title) {
		
		Questiongroup newGroup = new Questiongroup(title);

		return grep.save(newGroup);
	}
	
	
	/*
	 * METHODS FOR FETCHING:
	 */
	
	// all questions
	@CrossOrigin
	@GetMapping("/questions")
	public @ResponseBody List<Question> questionsREST() {
		return (List<Question>) repository.findAll();
	}
	
	// question by id
	@CrossOrigin
	@GetMapping("/questions/{id}")
	public @ResponseBody Optional<Question> findQuestionREST(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	// all questionnaires = groups
	@CrossOrigin
	@GetMapping("/groups")
	public @ResponseBody List<Questiongroup> groupsREST() {
		return (List<Questiongroup>) grep.findAll();
	}
	
	// questionnaire = group by id
	@CrossOrigin
	@GetMapping("/groups/{id}")
	public @ResponseBody Optional<Questiongroup> findGroupREST(@PathVariable("id") Long id) {
		return grep.findById(id);
	}
	
	// questions of a questionnaire by id
	@CrossOrigin
	@GetMapping("/groups/{id}/questions")
	public @ResponseBody List<Question> findQuestionsOfAGroupREST(@PathVariable("id") Long id) {
		
		Optional<Questiongroup> qg = grep.findById(id);
		Questiongroup group = qg.get();
		
		return (List<Question>) group.getQuestions();
		
	}
}
