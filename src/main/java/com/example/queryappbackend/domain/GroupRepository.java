package com.example.queryappbackend.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
	
 List<Group> findByTitle(String title);
 
}