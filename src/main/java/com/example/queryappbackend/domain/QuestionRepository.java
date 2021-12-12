package com.example.queryappbackend.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
 List<Question> findByDescription(String description);
 List<Question> findByQuestionGroupQgroupid(Long qgroupid);
}