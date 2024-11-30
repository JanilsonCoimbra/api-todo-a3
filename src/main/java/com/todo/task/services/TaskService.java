package com.todo.task.services;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.todo.task.models.Task;
import com.todo.task.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional
	public Task insertTask(Task task) throws Exception {
		try {
		Task response = taskRepository.saveAndFlush(task);
		return response;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Task> findAllTask() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Task findTaskById(UUID taskId) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(taskId).get();
		if(task == null) {
			throw new RuntimeException("Tarefa não encontra");
		}
		return task;
	}
	
	@Transactional
	public Task updateTaskById(Task task) {
		// TODO Auto-generated method stub		
		Task findTask = taskRepository.findById(task.getIdTask()).get();
		
		if(findTask == null) {
			throw new RuntimeException("Tarefa não encontra");
		}
		
		findTask.setTitle(task.getTitle());
		findTask.setDescription(task.getDescription());
		findTask.setDone(task.isDone());
		findTask.setDate(task.getDate());
		
		Task response = taskRepository.saveAndFlush(findTask);
		
		
		return response;
	}
	
	@Transactional
	public Boolean deleteTaskById(UUID taskId) {
		try {
		
		Task task = findTaskById(taskId);
		taskRepository.deleteById(taskId);
		return true;
		
		} catch(Exception e) {
			return false;
		}
		
	}

}
