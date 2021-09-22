package com.taigezhang.todolist.controller;

import com.taigezhang.todolist.model.AuthRequest;
import com.taigezhang.todolist.model.Task;
import com.taigezhang.todolist.model.User;
import com.taigezhang.todolist.repository.TaskRepository;
import com.taigezhang.todolist.repository.UserRepository;
import com.taigezhang.todolist.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public List<Task> getTasks() {
        return this.taskRepository.findAll();
    }

    @PostMapping("addTask")
    public ResponseEntity<String> saveTask(@RequestBody Task task) {
        if (task == null || StringUtils.isEmpty(task.getId()) || StringUtils.isEmpty(task.getContent())) {
            return ResponseEntity.ok().body("Invalid data!");
        }
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
//        UserDetails userDetails = userRepository. loadUserByUsername(authRequest.getUserName());
//        String jwt = jwtTokenUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponse(jwt));
        return ResponseEntity.ok("success");
    }
}
