package com.axionlabs.todowoo.mapper;

import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.dto.todo.request.ToDoCreateOrUpdateRequestDto;
import com.axionlabs.todowoo.entity.ToDo;
import com.axionlabs.todowoo.entity.User;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDo fromToDoDto(ToDoDto toDoDto);
    ToDoDto toToDoDto(ToDo toDo);
    default ToDo fromToDoCreateOrUpdateDto(ToDoCreateOrUpdateRequestDto toDoDto, User user){
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDto.getTitle());
        toDo.setCompleted(false);
        toDo.setImportant(toDoDto.isImportant());
        toDo.setUser(user);
        toDo.setMemo(toDoDto.getMemo());
        return toDo;
    }
    default ToDo fromToDoCreateOrUpdateDto(ToDoCreateOrUpdateRequestDto toDoDto, ToDo toDo){
        toDo.setTitle(toDoDto.getTitle());
        toDo.setImportant(toDoDto.isImportant());
        toDo.setMemo(toDoDto.getMemo());
        return toDo;
    }
}
