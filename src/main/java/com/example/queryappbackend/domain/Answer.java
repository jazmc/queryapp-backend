package com.example.queryappbackend.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerid;

	private String nickname; // username
	private String answers; // answers as JSON string
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "qgroupid")
	@JsonBackReference
	private QuestionGroup questionGroup;
	
	public Answer() {}
	
	public Answer(String nickname, String answers, QuestionGroup questiongroup) {
		super();
		this.nickname = nickname;
		this.answers = answers;
		this.questionGroup = questiongroup;
		this.timestamp = LocalDateTime.now();
	}
	
	public Answer(String nickname, String answers) {
		super();
		this.nickname = nickname;
		this.answers = answers;
		this.questionGroup = null;
		this.timestamp = LocalDateTime.now();
	}

	public Long getId() {
		return answerid;
	}
	
	public void setId(Long id) {
		this.answerid = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public QuestionGroup getQuestiongroup() {
		return questionGroup;
	}

	public void setQuestiongroup(QuestionGroup questiongroup) {
		this.questionGroup = questiongroup;
	}
}
