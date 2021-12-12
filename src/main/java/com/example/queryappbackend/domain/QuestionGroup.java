package com.example.queryappbackend.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class QuestionGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qgroupid;
	private String title;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionGroup")
	@JsonIgnore
	private List<Question> questions;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionGroup")
	@JsonIgnore
	private List<Answer> answers;

	// constructors
	public QuestionGroup() {}
	
	public QuestionGroup(String title) {
		super();
		this.title = title;
	}
	
	// getters & setters
	public Long getQuestiongroupid() {
		return qgroupid;
	}

	public void setQuestiongroupid(Long qgroupid) {
		this.qgroupid = qgroupid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Long getQgroupid() {
		return qgroupid;
	}

	public void setQgroupid(Long qgroupid) {
		this.qgroupid = qgroupid;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
