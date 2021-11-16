package com.example.queryappbackend.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@JsonManagedReference
	private Questiongroup questiongroup;
	
	public Answer() {}
	
	public Answer(String nickname, String answers, Questiongroup questiongroup) {
		super();
		this.nickname = nickname;
		this.answers = answers;
		this.questiongroup = questiongroup;
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

	public Questiongroup getQuestiongroup() {
		return questiongroup;
	}

	public void setQuestiongroup(Questiongroup questiongroup) {
		this.questiongroup = questiongroup;
	}
}
