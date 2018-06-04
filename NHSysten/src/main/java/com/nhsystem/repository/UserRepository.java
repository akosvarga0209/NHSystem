package com.nhsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhsystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);

	User findUserById(Long id);
	
	List<User> findAll();

	List<User> findByNameLike(String name);

}
