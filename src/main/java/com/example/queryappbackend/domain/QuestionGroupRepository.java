package com.example.queryappbackend.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestionGroupRepository extends CrudRepository<QuestionGroup, Long> {
	
 List<QuestionGroup> findByTitle(String title);
 
}