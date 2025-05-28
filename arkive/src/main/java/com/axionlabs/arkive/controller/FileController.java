package com.axionlabs.arkive.controller;

import com.axionlabs.arkive.dto.ErrorResponseDto;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import com.axionlabs.arkive.dto.file.response.FileListResponseDto;
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
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
                    content = @Content(schema = @Schema(implementation = FileResponseDto.class))
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
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponseDto> uploadFileToS3(@ModelAttribute FileUploadRequestDto fileUploadRequest) throws IOException {
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
    @Operation(
            summary = "Fetch All Files",
            description = "Fetches all files of the currently authenticated user",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Files Fetched Successfully",
                    content = @Content(schema = @Schema(implementation = FileListResponseDto.class))
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
    @GetMapping(value = "/files/")
    public ResponseEntity<FileListResponseDto> getAllFiles(){
        List<FileDto> filesData = iFileService.fetchAllFiles();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new FileListResponseDto(
                        HttpStatus.OK,
                        "Files Fetched Successfully",
                        filesData
                )
        );
    }
    @GetMapping(value = "/files/{fileId}")
    @Operation(
            summary = "Fetch File by id",
            description = "Fetches file of the currently authenticated user by id",
            security = { @SecurityRequirement(name="bearerAuth")}

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Files Fetched Successfully",
                    content = @Content(schema = @Schema(implementation = FileResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access (invalid credentials).",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource Not Found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<FileResponseDto> getFileById(@PathVariable UUID fileId){
        FileDto fileDto = iFileService.fetchFileById(fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new FileResponseDto(
                                HttpStatus.OK,
                                "File fetched successfully",
                                fileDto
                        )
                );
    }



}
