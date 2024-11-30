package com.todo.task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.task.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,UUID> {

}
