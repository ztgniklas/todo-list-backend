package com.taigezhang.todolist.repository;

import com.taigezhang.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @Query(name = "select * from tasks where username=?1")
    List<Task> getTasksByUsername(String username);
}
