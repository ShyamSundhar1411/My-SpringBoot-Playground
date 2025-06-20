package com.axionlabs.todowoo.repository;

import com.axionlabs.todowoo.entity.ToDo;
import com.axionlabs.todowoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ToDoRepository extends JpaRepository<ToDo, UUID> {
    @Query("SELECT t from todo t where t.user = :user")
    List<ToDo> getAllToDosFromUser(@Param("user") User user);
    @Query("SELECT t from todo t where t.user = :user AND t.todoId = :todoId")
    Optional<ToDo> getToDoById(@Param("user") User user, @Param("todoId") UUID todoId);

    @Query("SELECT t FROM todo t WHERE t.user = :user AND " +
            "((t.isCompleted = :completed) OR (LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(t.memo) LIKE LOWER(CONCAT('%', :searchTerm, '%'))))")
    List<ToDo> searchTodos(
            @Param("user") User user,
            @Param("searchTerm") String searchTerm,
            @Param("completed") boolean completed);



}
