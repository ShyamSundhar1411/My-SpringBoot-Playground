package com.axionlabs.todowoo.mapper;

import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.entity.ToDo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDo fromToDoDto(ToDoDto toDoDto);
    ToDoDto toToDoDto(ToDo toDo);
}
