package com.axionlabs.todowoo.dto.todo.response;

import com.axionlabs.todowoo.dto.BaseResponseDto;
import com.axionlabs.todowoo.dto.todo.ToDoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter @Setter
public class ListToDoResponseDto extends BaseResponseDto {
    private List<ToDoDto> data;
    public ListToDoResponseDto(HttpStatusCode statusCode, String statusMessage, List<ToDoDto> data){
        super(statusCode,statusMessage);
        this.data = data;
    }
}
