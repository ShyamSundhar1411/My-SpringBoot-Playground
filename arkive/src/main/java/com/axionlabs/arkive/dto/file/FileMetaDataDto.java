package com.axionlabs.arkive.dto.file;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class FileMetaDataDto {
    private UUID fileMetaDataId;

    @NotEmpty(message = "Content Type cannot be empty")
    private String contentType;

    @NotNull(message = "Size cannot be null")
    private Long sizeInBytes;

    @NotEmpty(message = "Checksum cannot be empty")
    private String checksum;

    @NotEmpty(message = "Checksum Type cannot be empty")
    private String checksumType;

    @NotEmpty(message = "Encryption Algorithm cannot be empty")
    private String encryptionAlgorithm;
}