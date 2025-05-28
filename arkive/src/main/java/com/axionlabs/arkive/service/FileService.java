package com.axionlabs.arkive.service;


import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import com.axionlabs.arkive.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {
    FileDto uploadFile(FileUploadRequestDto fileUploadRequestDto) throws IOException;
    List<FileDto> fetchAllFiles();
    FileDto fetchFileById(UUID fileId);
    boolean deleteFileById(UUID fileId);
}
