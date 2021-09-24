package com.taigezhang.todolist.repository;

import com.taigezhang.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    default User loadUserByName(String username) {
        if (existsById(username)) {
            return getById(username);
        }
        return null;
    }
}
