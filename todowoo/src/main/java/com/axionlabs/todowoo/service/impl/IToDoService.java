package com.axionlabs.todowoo.service.impl;

import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.dto.todo.request.ToDoCreateOrUpdateRequestDto;
import com.axionlabs.todowoo.entity.ToDo;
import com.axionlabs.todowoo.entity.User;
import com.axionlabs.todowoo.exception.ResourceNotFoundException;
import com.axionlabs.todowoo.mapper.ToDoMapper;
import com.axionlabs.todowoo.repository.ToDoRepository;
import com.axionlabs.todowoo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                () -> new UsernameNotFoundException("User not authenticated")
        );
        return toDoRepository.getAllToDosFromUser(user)
                .stream().map(toDoMapper::toToDoDto).toList();

    }

    @Override
    public ToDoDto fetchToDoById(UUID todoId) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo todo = toDoRepository.getToDoById(user,todoId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Todo","todoId",todoId.toString()
                )
        );
        return toDoMapper.toToDoDto(todo);
    }

    @Override
    public ToDoDto createToDo(ToDoCreateOrUpdateRequestDto toDoDto) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo todo = toDoMapper.fromToDoCreateOrUpdateDto(toDoDto,user);
        toDoRepository.save(todo);
        return toDoMapper.toToDoDto(todo);
    }

    @Override
    public ToDoDto updateToDo(UUID todoId, ToDoCreateOrUpdateRequestDto toDoDto) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo todo = toDoRepository.getToDoById(user,todoId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Todo","todoId",todoId.toString()
                )
        );
        ToDo updatedToDo = toDoMapper.fromToDoCreateOrUpdateDto(toDoDto,todo);
        toDoRepository.save(updatedToDo);
        return toDoMapper.toToDoDto(updatedToDo);
    }

    @Override
    public boolean deleteToDo(UUID todoId) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo toDo = toDoRepository.getToDoById(user,todoId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Todo","todoId",todoId.toString()
                )
        );
        toDoRepository.delete(toDo);
        return true;
    }

    @Override
    public ToDoDto markAsCompleted(UUID todoId) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo toDo = toDoRepository.getToDoById(user,todoId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Todo","todoId",todoId.toString()
                )
        );
        toDo.setCompleted(true);
        toDo.setCompletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        toDoRepository.save(toDo);
        return toDoMapper.toToDoDto(toDo);
    }

    @Override
    public ToDoDto markAsIncomplete(UUID todoId) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );
        ToDo toDo = toDoRepository.getToDoById(user,todoId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Todo","todoId",todoId.toString()
                )
        );
        toDo.setCompleted(false);
        toDo.setCompletedAt(null);
        toDoRepository.save(toDo);
        return toDoMapper.toToDoDto(toDo);

    }

    @Override
    public List<ToDoDto> searchToDos(String searchTerm, boolean completed) {
        User user = iUserService.getAuthenticatedUser().orElseThrow(
                () -> new UsernameNotFoundException("User not authenticated")
        );

        return toDoRepository.searchTodos(user,searchTerm,completed)
                .stream().map(toDoMapper::toToDoDto).toList();


    }


}
