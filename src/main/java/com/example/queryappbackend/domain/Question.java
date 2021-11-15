package com.example.queryappbackend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description; // question text
	private String type; // radio, checkbox, text etc.
	private String[] answers; // array of answers
	private String[] rightans; // array of right answers
	
	// group, "questionnaire"
	@ManyToOne
	@JoinColumn(name = "qgroupid")
	@JsonManagedReference
	private Questiongroup questiongroup;

	// constructors
	public Question() {}
	
	public Question(String description, String type, String[] answers, String[] rightans, Questiongroup questiongroup) {
		super();
		this.description = description;
		this.type = type;
		this.answers = answers;
		this.rightans = rightans;
		this.questiongroup = questiongroup;
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

	public Questiongroup getGroup() {
		return questiongroup;
	}

	public void setGroup(Questiongroup questiongroup) {
		this.questiongroup = questiongroup;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public String[] getRight() {
		return rightans;
	}

	public void setRight(String[] rightans) {
		this.rightans = rightans;
	}
}
