package com.axionlabs.arkive.dto.file.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter @Setter
public class FileUploadRequestDto {
    @NotEmpty(message = "File cannot be empty")
    MultipartFile file;
}
