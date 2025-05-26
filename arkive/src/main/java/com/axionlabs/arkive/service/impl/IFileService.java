package com.axionlabs.arkive.service.impl;



import com.axionlabs.arkive.config.properties.AWSConfigProperties;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.mapper.FileMapper;
import com.axionlabs.arkive.repository.FileRepository;
import com.axionlabs.arkive.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service

public class IFileService implements FileService {
    private final FileRepository fileRepository;
    private final S3Client amazonS3Client;
    private final AWSConfigProperties awsConfigProperties;
    private final IURLService iurlService;
    private final FileMapper fileMapper;
    @Autowired
    public IFileService(FileRepository fileRepository, S3Client amazonS3Client, AWSConfigProperties awsConfigProperties, IURLService iurlService, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.amazonS3Client = amazonS3Client;
        this.awsConfigProperties = awsConfigProperties;
        this.iurlService = iurlService;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileDto uploadFile(FileUploadRequestDto fileUploadRequest)  {

        MultipartFile file = fileUploadRequest.getFile();
        String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();


        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsConfigProperties.getBucketName())
                .key(fileName)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();
        String presignedUrl = iurlService.generatePreSignedAccessUrl(fileName, 60);
        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileName);
        fileDto.setFileUrl(presignedUrl);
        File savedFile = fileMapper.toFileEntity(fileDto);
        fileRepository.save(savedFile);
        return fileMapper.toFileDto(savedFile);



    }
}
