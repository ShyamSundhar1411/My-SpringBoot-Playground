package com.axionlabs.todowoo.controller;

import com.axionlabs.todowoo.dto.ErrorResponseDto;
import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.dto.todo.response.ListToDoResponseDto;
import com.axionlabs.todowoo.service.impl.IToDoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Tag(
        name = "ToDo APIs - TodoWoo",
        description = "Handles CRUD of Todos of a user for the TodoWoo application."
)
public class ToDoController {
    @Autowired
    private IToDoService iToDoService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All Todos Fetched Successfully",
                    content = @Content(schema = @Schema(implementation = ListToDoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access (invalid credentials).",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/")
    public ResponseEntity<ListToDoResponseDto> fetchAllTodos(){
        List<ToDoDto> toDos = iToDoService.fetchAllToDos();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new ListToDoResponseDto(
                        HttpStatus.OK,
                        "All Todos fetched successfully",
                        toDos
                )
        );

    }
}
