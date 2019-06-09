package com.joe.joeorganizer.task;

import com.joe.joeorganizer.security.TokenUtil;
import com.joe.joeorganizer.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenUtil tokenUtil;


    @GetMapping("/")
    public List<Task> allTasksForUser(@RequestHeader("auth-x") String token) {
        String email = tokenUtil.getUserNameFromToken(token);
        List<Task> tasks = this.taskService.getTasksByEmail(email);
        Collections.sort(tasks);
        return tasks;
    }

    @PostMapping("/")
    public void addTask(@RequestHeader("auth-x") String token, @RequestBody @Valid Task task) {
        String email = tokenUtil.getUserNameFromToken(token);
        taskService.saveTask(email, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@RequestHeader("auth-x") String token, @PathVariable int id) throws UnauthorizedTaskActionException, TaskNotfoundException {
        String email = tokenUtil.getUserNameFromToken(token);
        this.taskService.deleteTask(email, id);
    }

    @PutMapping("/{id}")
    public void updateTask(@RequestHeader("auth-x") String token,@RequestBody @Valid Task task,@PathVariable int id) throws UnauthorizedTaskActionException {
        String email = tokenUtil.getUserNameFromToken(token);
        this.taskService.update(email,id,task);
    }
}
