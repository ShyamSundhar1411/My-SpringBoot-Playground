package com.axionlabs.arkive.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.mapper.FileMapper;
import com.axionlabs.arkive.repository.FileRepository;
import com.axionlabs.arkive.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service

public class IFileService implements FileService {
    private final FileRepository fileRepository;
    private final AmazonS3 amazonS3;
    private final String s3BucketName;
    private final IURLService iurlService;
    private final FileMapper fileMapper;
    @Autowired
    public IFileService(FileRepository fileRepository, AmazonS3 amazonS3, String s3BucketName, IURLService iurlService, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.amazonS3 = amazonS3;
        this.s3BucketName = s3BucketName;
        this.iurlService = iurlService;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileDto uploadFile(MultipartFile file) throws IOException {
        try{
            String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            PutObjectRequest request = new PutObjectRequest(s3BucketName,fileName,file.getInputStream(),metadata);
            amazonS3.putObject(request);
            String presignedUrl = iurlService.generatePreSignedAccessUrl(fileName, 60);
            FileDto fileDto = new FileDto();
            fileDto.setFileName(fileName);
            fileDto.setFileUrl(presignedUrl);
            File savedFile = fileMapper.toFileEntity(fileDto);
            fileRepository.save(savedFile);
            return fileMapper.toFileDto(savedFile);
        }catch (IOException e){
            throw new RuntimeException("Failed to Upload file",e);
        }


    }
}
