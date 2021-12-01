package com.example.queryappbackend.web;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.queryappbackend.domain.Questiongroup;
import com.example.queryappbackend.domain.QuestiongroupRepository;
import com.example.queryappbackend.domain.Answer;
import com.example.queryappbackend.domain.AnswerRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionRepository;

@RestController
public class QuestionController {
	@Autowired
	private QuestionRepository repository;
	@Autowired
	private QuestiongroupRepository grep;
	@Autowired
	private AnswerRepository arep;
	
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
	 * POST ANSWER TO QUESTIONNAIRE:
	 */
	@CrossOrigin
	@PostMapping("/groups/{id}/answers")
	@ResponseBody
	public Answer create(@PathVariable("id") Long id, @RequestBody Answer newans) {
		return newans;
		//Optional<Questiongroup> desiredQg = grep.findById(id);
		//Questiongroup qg = desiredQg.get();
		//newans.setQuestiongroup(qg);
		
		//return arep.save(newans);
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
	
	// answers of a questionnaire by id
	@CrossOrigin
	@GetMapping("/groups/{id}/answers")
	public @ResponseBody List<Answer> findAnswersOfAGroupREST(@PathVariable("id") Long id) {
		Optional<Questiongroup> qg = grep.findById(id);
		Questiongroup group = qg.get();
		return (List<Answer>) group.getAnswers();
	}
}
