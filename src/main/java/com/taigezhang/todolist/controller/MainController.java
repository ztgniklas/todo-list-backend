package com.taigezhang.todolist.controller;

import com.taigezhang.todolist.model.AuthRequest;
import com.taigezhang.todolist.model.AuthResponse;
import com.taigezhang.todolist.model.Task;
import com.taigezhang.todolist.model.User;
import com.taigezhang.todolist.repository.TaskRepository;
import com.taigezhang.todolist.repository.UserRepository;
import com.taigezhang.todolist.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @GetMapping("users")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("tasks")
    public List<Task> getTasks(@RequestHeader("Authorization") String jwt) {
        String username = jwtTokenUtils.extractUserName(jwt);
        if (userRepository.existsById(username)) {
            return taskRepository.getTasksByUsername(username);
        }
        return Collections.emptyList();
    }

    @PostMapping("addTask")
    public ResponseEntity<String> saveTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        if (task == null || StringUtils.isEmpty(task.getId()) || StringUtils.isEmpty(task.getContent())) {
            return ResponseEntity.ok().body("Invalid data!");
        }
        String username = jwtTokenUtils.extractUserName(jwt);
        task.setUserName(username);
        this.taskRepository.save(task);
        System.out.println("Received and saved " + task.toString());
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("delTask")
    public ResponseEntity<String> delTask(@RequestBody String id) {
        if (StringUtils.isEmpty(id) || !this.taskRepository.existsById(id)) {
            return ResponseEntity.ok().body("Invalid id!");
        }
        this.taskRepository.deleteById(id);
        System.out.println("Deleted a task whose id is " + id);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        User user = null;
        try {
            if (userRepository.existsById(authRequest.getUserName())) {
                User tmpUser = userRepository.getById(authRequest.getUserName());
                if (tmpUser.getPassword().equals(authRequest.getPassword())) {
                    user = tmpUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jwt = jwtTokenUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
