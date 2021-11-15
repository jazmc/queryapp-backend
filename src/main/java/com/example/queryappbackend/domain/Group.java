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
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groupid;
	private String title;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Question> questions;

	// constructors
	public Group() {}
	
	public Group(String title) {
		super();
		this.title = title;
	}
	
	// getters & setters
	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
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
	
}
