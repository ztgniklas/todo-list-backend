package com.taigezhang.todolist;

import com.taigezhang.todolist.model.User;
import com.taigezhang.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListBackendApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoListBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() <= 0) {
            userRepository.save(new User("nikk", "pwd"));
        }
    }
}
