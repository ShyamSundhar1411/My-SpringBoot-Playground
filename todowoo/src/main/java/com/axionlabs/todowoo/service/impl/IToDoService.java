package com.axionlabs.todowoo.service.impl;

import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.entity.ToDo;
import com.axionlabs.todowoo.entity.User;
import com.axionlabs.todowoo.mapper.ToDoMapper;
import com.axionlabs.todowoo.repository.ToDoRepository;
import com.axionlabs.todowoo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IToDoService implements ToDoService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private ToDoMapper toDoMapper;
    @Override
    public List<ToDoDto> fetchAllToDos() {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return toDoRepository.getAllToDosFromUser(user)
                .stream().map(toDoMapper::toToDoDto).toList();

    }
}
