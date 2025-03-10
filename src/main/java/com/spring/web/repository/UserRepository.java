package com.spring.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByUsername(String username);
	User findByEmail(String email);
}
