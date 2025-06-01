package com.axionlabs.todowoo.controller;

import com.axionlabs.todowoo.dto.BaseResponseDto;
import com.axionlabs.todowoo.dto.ErrorResponseDto;
import com.axionlabs.todowoo.dto.todo.ToDoDto;
import com.axionlabs.todowoo.dto.todo.request.ToDoCreateOrUpdateRequestDto;
import com.axionlabs.todowoo.dto.todo.response.ListToDoResponseDto;
import com.axionlabs.todowoo.dto.todo.response.ToDoResponseDto;
import com.axionlabs.todowoo.service.impl.IToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
@Tag(
        name = "ToDo APIs - TodoWoo",
        description = "Handles CRUD of Todos of a user for the TodoWoo application."
)
public class ToDoController {
    @Autowired
    private IToDoService iToDoService;

    @Operation(
            summary = "Fetch All ToDos",
            description = "Fetches all ToDos of the currently authenticated user",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
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
    @Operation(
            summary = "Fetch todo by id",
            description = "Fetches todo by id of the currently authenticated user",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo Fetched Successfully",
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
    @GetMapping("/{todoId}")
    public ResponseEntity<ToDoResponseDto> fetchToDoById(@PathVariable UUID todoId){
        ToDoDto toDo = iToDoService.fetchToDoById(todoId);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new ToDoResponseDto(
                        HttpStatus.OK,
                        "ToDo Fetched Successfully",
                        toDo
                )
        );
    }
    @Operation(
            summary = "Create todo",
            description = "Create todo",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "ToDo Created Successfully",
                    content = @Content(schema = @Schema(implementation = ToDoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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
    @PostMapping("")
    public ResponseEntity<ToDoResponseDto> createToDo(@Valid @RequestBody ToDoCreateOrUpdateRequestDto toDoCreateOrUpdateRequestDto){
        ToDoDto todo = iToDoService.createToDo(toDoCreateOrUpdateRequestDto);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new ToDoResponseDto(
                        HttpStatus.CREATED,
                        "ToDo Created Successfully",
                        todo
                )
        );
    }
    @Operation(
            summary = "Update todo",
            description = "Update todo",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo Updated Successfully",
                    content = @Content(schema = @Schema(implementation = ToDoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
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
    @PutMapping("/{toDoId}")
    public ResponseEntity<ToDoResponseDto> updateToDo(@PathVariable UUID toDoId, @Valid @RequestBody ToDoCreateOrUpdateRequestDto toDoUpdateRequestDto){
        ToDoDto toDo = iToDoService.updateToDo(toDoId,toDoUpdateRequestDto);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new ToDoResponseDto(
                        HttpStatus.OK,
                        "ToDo Updated Successfully",
                        toDo
                )
        );
    }
    @Operation(
            summary = "Delete todo",
            description = "Delete todo",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "ToDo deleted successfully.",
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User is not authenticated.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ToDo deleted unsuccessfully",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/{toDoId}")
    public ResponseEntity<BaseResponseDto> deleteToDo(@PathVariable UUID toDoId){
        boolean isDeleted = iToDoService.deleteToDo(toDoId);
        if(isDeleted){
            return ResponseEntity.status(
                    HttpStatus.NO_CONTENT
            ).body(
                    new BaseResponseDto(
                            HttpStatus.NO_CONTENT,
                            "ToDo Deleted Successfully"
                    )
            );
        }
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(
                new BaseResponseDto(
                        HttpStatus.BAD_REQUEST,
                        "ToDo Deleted Unsuccessfully"
                )
        );
    }
    @Operation(
            summary = "Mark ToDo as Completed",
            description = "Marks the specified ToDo item as completed for the currently authenticated user",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo marked as completed successfully",
                    content = @Content(schema = @Schema(implementation = ToDoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access (invalid credentials)",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PatchMapping("/{toDoId}/complete")
    public ResponseEntity<ToDoResponseDto> markAsCompleted(@PathVariable UUID toDoId) {
        ToDoDto toDo = iToDoService.markAsCompleted(toDoId);
        return ResponseEntity.ok(new ToDoResponseDto(HttpStatus.OK, "ToDo marked as completed", toDo));
    }
    @Operation(
            summary = "Mark ToDo as Incomplete",
            description = "Marks the specified ToDo item as incomplete for the currently authenticated user",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo marked as incomplete successfully",
                    content = @Content(schema = @Schema(implementation = ToDoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access (invalid credentials)",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PatchMapping("/{toDoId}/incomplete")
    public ResponseEntity<ToDoResponseDto> markAsIncomplete(@PathVariable UUID toDoId) {
        ToDoDto toDo = iToDoService.markAsIncomplete(toDoId);
        return ResponseEntity.ok(new ToDoResponseDto(HttpStatus.OK, "ToDo marked as incomplete", toDo));
    }

}
