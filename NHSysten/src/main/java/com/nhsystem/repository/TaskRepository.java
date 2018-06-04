package com.nhsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhsystem.entities.Task;
import com.nhsystem.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);

}
