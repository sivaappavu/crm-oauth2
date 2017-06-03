package com.stl.crm.repository;

import org.springframework.data.repository.CrudRepository;

import com.stl.crm.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
}
