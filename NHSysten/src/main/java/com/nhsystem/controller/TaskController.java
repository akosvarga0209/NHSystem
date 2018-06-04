package com.nhsystem.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nhsystem.entities.Task;
import com.nhsystem.services.TaskService;
import com.nhsystem.services.UserService;

@Controller
public class TaskController {
	
	private TaskService taskService;
	private UserService userService;
	
	
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("/addTask")
	public String taskForm(String email, Model model, HttpSession session) {
		
		session.setAttribute("email", email);
		model.addAttribute("task", new Task());
		
		return "views/taskForm";
	}
	
	@PostMapping("/addTask")
	public String addTask(@Valid Task task,BindingResult bindingResult, HttpSession session) {
	
	if (bindingResult.hasErrors()) {
		return "views/taskForm";
	}
	
	String email= (String)session.getAttribute("email");
	taskService.addTask(task, userService.findOneUser(email));
	return "redirect:/users";
	}
	
	
}
