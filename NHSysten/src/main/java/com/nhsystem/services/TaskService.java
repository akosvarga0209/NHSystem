package com.nhsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhsystem.entities.Task;
import com.nhsystem.entities.User;
import com.nhsystem.repository.TaskRepository;

@Service
public class TaskService{
	
	private TaskRepository taskRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public List<Task> findUserTask(User user){
		
		return taskRepository.findByUser(user);
		
	}
	
	

}
