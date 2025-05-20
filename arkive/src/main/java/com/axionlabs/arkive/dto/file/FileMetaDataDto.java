package com.axionlabs.arkive.dto.file;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Data
public class FileMetaDataDto {
    private UUID fileMetaDataId;

    @NotEmpty(message = "Content Type cannot be empty")
    private String contentType;

    @NotEmpty(message = "Size cannot be empty")
    private Long sizeInBytes;

    @NotEmpty(message = "Checksum cannot be empty")
    private String checksum;

    @NotEmpty(message = "Checksum Type cannot be empty")
    private String checksumType;

    @NotEmpty(message = "Encryption Algorithm cannot be empty")
    private String encryptionAlgorithm;

}
