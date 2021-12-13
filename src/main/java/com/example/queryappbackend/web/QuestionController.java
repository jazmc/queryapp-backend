package com.example.queryappbackend.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.queryappbackend.domain.QuestionGroup;
import com.example.queryappbackend.domain.QuestionGroupRepository;
import com.example.queryappbackend.domain.Answer;
import com.example.queryappbackend.domain.AnswerRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionRepository;

@RestController
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionGroupRepository groupRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	 * ADD METHOD:
	 */
	@CrossOrigin
	@PostMapping("/groups")
	public @ResponseBody QuestionGroup addGroupREST(String title) {
		
		QuestionGroup newGroup = new QuestionGroup(title);

		return groupRepository.save(newGroup);
	}
	
	/*
	 * POST ANSWER TO QUESTIONNAIRE:
	 */
	@CrossOrigin
	@PostMapping("/groups/{id}/answers")
	@ResponseBody
	public Answer create(@PathVariable("id") Long id, @RequestBody Answer newans) { 
		System.out.println("POSTATTU QUESTIONNAIREEN: " + id);
		System.out.println("ANSWERS: " + newans);
		Optional<QuestionGroup> desiredQg = groupRepository.findById(id);
		QuestionGroup qg = desiredQg.get();
		newans.setQuestiongroup(qg);
		newans.setTimestamp(LocalDateTime.now());
		
		return answerRepository.save(newans);
	}
	
	
	/*
	 * METHODS FOR FETCHING:
	 */
	
	// all questions
	@CrossOrigin
	@GetMapping("/questions")
	public @ResponseBody List<Question> questionsREST() {
		return (List<Question>) questionRepository.findAll();
	}
	
	// question by id
	@CrossOrigin
	@GetMapping("/questions/{id}")
	public @ResponseBody Optional<Question> findQuestionREST(@PathVariable("id") Long id) {
		return questionRepository.findById(id);
	}
	
	// all questionnaires = groups
	@CrossOrigin
	@GetMapping("/groups")
	public @ResponseBody List<QuestionGroup> groupsREST() {
		return (List<QuestionGroup>) groupRepository.findAll();
	}
	
	// questionnaire = group by id
	@CrossOrigin
	@GetMapping("/groups/{id}")
	public @ResponseBody Optional<QuestionGroup> findGroupREST(@PathVariable("id") Long id) {
		return groupRepository.findById(id);
	}
	
	// questions of a questionnaire by id
	@CrossOrigin
	@GetMapping("/groups/{id}/questions")
	public @ResponseBody List<Question> findQuestionsOfAGroupREST(@PathVariable("id") Long id) {
		Optional<QuestionGroup> qg = groupRepository.findById(id);
		QuestionGroup group = qg.get();
		return (List<Question>) group.getQuestions();
	}
	
	// answers of a questionnaire by id
	@CrossOrigin
	@GetMapping("/groups/{id}/answers")
	public @ResponseBody List<Answer> findAnswersOfAGroupREST(@PathVariable("id") Long id) {
		
		return (List<Answer>) answerRepository.findByQuestionGroupQgroupid(id);
	}
	
	
	
	
}
