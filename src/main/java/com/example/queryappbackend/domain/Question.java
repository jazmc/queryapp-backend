package com.example.queryappbackend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description; // question text
	private String type; // radio, checkbox, text etc.
	private String[] answers; // array of answers
	
	// group, "questionnaire"
	@ManyToOne
	@JoinColumn(name = "qgroupid")
	@JsonBackReference
	private QuestionGroup questionGroup;

	// constructors
	public Question() {}
	
	public Question(String description, String type, String[] answers, QuestionGroup questiongroup) {
		super();
		this.description = description;
		this.type = type;
		this.answers = answers;
		this.questionGroup = questiongroup;
	}
	
	// getters & setters below
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public QuestionGroup getGroup() {
		return questionGroup;
	}

	public void setGroup(QuestionGroup questiongroup) {
		this.questionGroup = questiongroup;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
