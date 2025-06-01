package com.axionlabs.todowoo.service;

import com.axionlabs.todowoo.dto.todo.ToDoDto;


import java.util.List;

public interface ToDoService {
    List<ToDoDto> fetchAllToDos();
}
