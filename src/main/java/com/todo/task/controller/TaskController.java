package com.todo.task.controller;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<Task> findTaskById(@PathVariable UUID taskId) throws Exception{
		try {
		Task task = taskService.findTaskById(taskId);
		return ResponseEntity.ok(task);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@PostMapping("/auth")
	public User loginEmailAndPassword(User user) {
		return user;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Task> saveTask(@RequestBody Task task ) {
		try {
		Task response = taskService.insertTask(task);
		return ResponseEntity.created(null).body(response);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) {
		Task taskResponse = taskService.updateTaskById(task);
		return taskResponse;
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<String> deleteById(@PathVariable UUID taskId) throws Exception{
		Boolean deleted = taskService.deleteTaskById(taskId);
		if(deleted) {
			return ResponseEntity.ok("Task excluida com sucesso");			
		}
		return ResponseEntity.notFound().build();
	}

}
