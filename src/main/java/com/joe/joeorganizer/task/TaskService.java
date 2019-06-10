package com.joe.joeorganizer.task;

import com.joe.joeorganizer.exceptions.UnauthorizedActionException;
import com.joe.joeorganizer.users.User;
import com.joe.joeorganizer.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Task> getTasksByEmail(String email) {
        User user = userRepo.getOne(email);
        return user.getTasks();
    }

    public void saveTask(String email, Task task) {
        User user = userRepo.getOne(email);
        task.setUser(user);
        taskRepo.save(task);
    }

    public void deleteTask(String email, int id) throws UnauthorizedActionException, TaskNotfoundException {
        try {
            if (email.equals(taskRepo.getOne(id).getUser().getEmail()))
                this.taskRepo.deleteById(id);
            else throw new UnauthorizedActionException();
        } catch (EntityNotFoundException ex) {
            throw new TaskNotfoundException();
        }
    }

    public void update(String email, int id, Task task) throws UnauthorizedActionException {
        task.setId(id);
        User user = userRepo.getOne(email);
        Task savedTask = taskRepo.getOne(id);
        if (!savedTask.getUser().getEmail().equals(user.getEmail()))
            throw new UnauthorizedActionException();
        task.setUser(user);
        taskRepo.save(task);
    }
}
