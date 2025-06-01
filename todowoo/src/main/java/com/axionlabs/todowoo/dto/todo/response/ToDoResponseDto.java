package com.axionlabs.todowoo.dto.todo.response;

import com.axionlabs.todowoo.dto.BaseResponseDto;
import com.axionlabs.todowoo.dto.todo.ToDoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor
public class ToDoResponseDto extends BaseResponseDto {
    private ToDoDto data;
    public ToDoResponseDto(HttpStatus statusCode, String statusMessage, ToDoDto data){
        super(statusCode,statusMessage);
        this.data = data;
    }
}
