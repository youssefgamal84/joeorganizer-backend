package com.joe.joeorganizer.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joe.joeorganizer.users.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity(name="task")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="deadline")
    @FutureOrPresent
    private Date deadline;

    @Column(name="priority")
    @Max(value=5,message = "Priority can't be more than 5")
    @Min(value=1,message = "Priority can't be less than 1")
    private int priority;

    @Column(name = "task_name")
    @NotNull
    @NotEmpty
    private String taskName;

    @ManyToOne
    @JoinColumn(name="user_email")
    private User user;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int compareTo(Task task) {
        if(task.getPriority() != this.getPriority())
            return -this.getPriority()+task.getPriority();
        else{
            return this.getDeadline().compareTo(task.getDeadline());
        }
    }
}
