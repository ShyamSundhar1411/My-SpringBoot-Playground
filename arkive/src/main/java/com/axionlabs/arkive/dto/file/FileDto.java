package com.axionlabs.arkive.dto.file;

import com.axionlabs.arkive.entity.FileMetaData;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Data
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class FileDto {
    private UUID fileId;

    @NotEmpty(message = "File Name cannot be empty")
    private String fileName;

    @URL
    @NotEmpty(message = "File URL cannot be empty")
    private String fileUrl;

    private FileMetaDataDto fileMetaData;
}
