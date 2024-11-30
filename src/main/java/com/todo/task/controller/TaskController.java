package com.todo.task.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.task.models.Task;
import com.todo.task.services.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	@GetMapping
	public List<Task> findAllTasks() {
		System.out.println(taskService);
		return taskService.findAllTask();
	}
	
	@GetMapping("/{taskId}")
	public Task findTaskById(@PathVariable UUID taskId) throws Exception{
		Task task = taskService.findTaskById(taskId);
		return task;
	}
	
	
	@PostMapping("/auth")
	public User loginEmailAndPassword(User user) {
		return user;
	}
	
	@PostMapping("/save")
	public Task saveTask(@RequestBody Task task ) {
		Task response = taskService.insertTask(task);
		return response;
	}
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) {
		Task taskResponse = taskService.updateTaskById(task);
		return taskResponse;
	}
	
}
