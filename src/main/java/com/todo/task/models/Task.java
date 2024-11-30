package com.todo.task.models;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID idTask;
	private String title;
	private String description;
	private Boolean done;
	private Instant date;
	
	
	
	
	/**
	 * 
	 */
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param idTask
	 * @param title
	 * @param description
	 * @param done
	 * @param date
	 */
	public Task(UUID idTask, String title, String description, boolean done, Instant date) {
		super();
		this.idTask = idTask;
		this.title = title;
		this.description = description;
		this.done = done;
		this.date = date;
	}

	
	
	public UUID getIdTask() {
		return idTask;
	}



	public void setIdTask(UUID idTask) {
		this.idTask = idTask;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Boolean isDone() {
		return done;
	}



	public void setDone(boolean done) {
		this.done = done;
	}



	public Instant getDate() {
		return date;
	}



	public void setDate(Instant date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", title=" + title + ", description=" + description + ", isDone=" + done
				+ ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, description, idTask, done, title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(idTask, other.idTask) && done == other.done && Objects.equals(title, other.title);
	}
}
