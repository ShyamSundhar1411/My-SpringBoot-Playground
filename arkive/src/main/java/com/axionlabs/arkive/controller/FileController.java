package com.axionlabs.arkive.controller;

import com.axionlabs.arkive.dto.ErrorResponseDto;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import com.axionlabs.arkive.dto.file.response.FileResponseDto;
import com.axionlabs.arkive.dto.user.response.UserResponseDto;
import com.axionlabs.arkive.service.impl.IFileService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@Tag(
        name = "File Upload API - Arkive",
        description = "Handles file upload, get and delete endpoints to AWS S3 bucket"
)
public class FileController {

    private final IFileService iFileService;
    @Autowired
    public FileController(IFileService iFileService){
        this.iFileService = iFileService;
    }

    @Operation(
            summary = "Upload a file",
            description = "Uploads file into amazon s3 buckets and returns the access url if its successfully uploaded",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "File Uploaded Successfully",
                    content = @Content(schema = @Schema(implementation = FileDto.class))
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
    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto> uploadFileToS3(@Valid @RequestBody FileUploadRequestDto fileUploadRequest) throws IOException {
        FileDto fileData = iFileService.uploadFile(fileUploadRequest);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new FileResponseDto(
                        HttpStatus.CREATED,
                        "File Uploaded Successfully",
                        fileData
                )
        );
    }


}
