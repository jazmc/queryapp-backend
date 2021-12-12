package com.example.queryappbackend.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	List<Answer> findByNickname(String nickname);
	List<Answer> findByQuestionGroupQgroupid(Long qgroupid);
 
}