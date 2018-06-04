package com.nhsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhsystem.entities.Task;
import com.nhsystem.entities.User;
import com.nhsystem.services.TaskService;
import com.nhsystem.services.UserService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NhSystenApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	
	
	@Before
	public void initDb() {
		{
			User newUser = new User((long) 1,"testuser@gmail.com","testUser","123456");
			userService.creatUser(newUser);
		}
		
		{
			User newAdmin = new User((long) 2,"testAdmin@gmail.com","testAdmin","123456");
			userService.creatAdmin(newAdmin);
		}
		
		Task userTask = new Task("03/01/2018","00:11","11:00","You need to work today");
		User user = userService.findOneUser("testuser@gmail.com");
		taskService.addTask(userTask, user);
		
	}
	
	@Test
	public void testuser() {
		User user = userService.findOneUser("testuser@gmail.com");
		assertNotNull(user);
		User admin = userService.findOneUser("testAdmin@gmail.com");
		assertEquals(admin.getEmail(), "testAdmin@gmail.com");
	}
	
	@Test
	public void testTask() {
		User user = userService.findOneUser("testuser@gmail.com");
		List<Task> task = taskService.findUserTask(user);
		assertNotNull(task);
	}

}
