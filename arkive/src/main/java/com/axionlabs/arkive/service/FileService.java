package com.axionlabs.arkive.service;


import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    FileDto uploadFile(FileUploadRequestDto fileUploadRequestDto) throws IOException;
    List<FileDto> fetchAllFiles();
}
