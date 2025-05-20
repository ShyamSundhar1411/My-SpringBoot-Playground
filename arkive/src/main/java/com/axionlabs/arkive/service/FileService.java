package com.axionlabs.arkive.service;


import com.axionlabs.arkive.dto.file.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileDto uploadFile(MultipartFile file) throws IOException;
}
