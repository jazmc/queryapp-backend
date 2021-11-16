package com.example.queryappbackend.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Questiongroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qgroupid;
	private String title;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questiongroup")
	private List<Question> questions;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questiongroup")
	private List<Answer> answers;

	// constructors
	public Questiongroup() {}
	
	public Questiongroup(String title) {
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
