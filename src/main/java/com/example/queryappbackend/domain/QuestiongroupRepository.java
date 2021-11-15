package com.example.queryappbackend.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestiongroupRepository extends CrudRepository<Questiongroup, Long> {
	
 List<Questiongroup> findByTitle(String title);
 
}