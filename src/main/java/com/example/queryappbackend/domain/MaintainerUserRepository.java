package com.example.queryappbackend.domain;

import org.springframework.data.repository.CrudRepository;

public interface MaintainerUserRepository extends CrudRepository<MaintainerUser, Long>{
	MaintainerUser findByUsername(String username);
}
