package com.example.queryappbackend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.queryappbackend.domain.Group;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description; // question text
	private String type; // radio, checkbox, text etc.
	private String[] answers; // array of answers
	private String[] right; // array of right answers
	
	// group, "questionnaire"
	@ManyToOne
	@JoinColumn(name = "groupid")
	@JsonManagedReference
	private Group group;
	
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public String[] getRight() {
		return right;
	}

	public void setRight(String[] right) {
		this.right = right;
	}
}
