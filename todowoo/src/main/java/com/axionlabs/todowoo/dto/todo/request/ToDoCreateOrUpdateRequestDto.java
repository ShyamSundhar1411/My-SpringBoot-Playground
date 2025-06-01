package com.axionlabs.todowoo.dto.todo.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ToDoCreateOrUpdateRequestDto {
    @Size(min = 2, max = 255, message = "The length of the title should be between 2 and 255")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @Size(max = 2048, message = "The length of the memo should be less than 2048")
    private String memo;
    private boolean isImportant;
}
