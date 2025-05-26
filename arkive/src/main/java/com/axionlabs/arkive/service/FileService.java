package com.axionlabs.arkive.service;


import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileDto uploadFile(FileUploadRequestDto fileUploadRequestDto) throws IOException;
}
