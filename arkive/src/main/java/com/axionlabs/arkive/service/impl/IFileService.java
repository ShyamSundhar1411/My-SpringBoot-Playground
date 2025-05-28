package com.axionlabs.arkive.service.impl;



import com.axionlabs.arkive.config.properties.AWSConfigProperties;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.FileMetaDataDto;
import com.axionlabs.arkive.dto.file.request.FileUploadRequestDto;
import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.entity.FileMetaData;
import com.axionlabs.arkive.entity.User;
import com.axionlabs.arkive.exception.ResourceNotFoundException;
import com.axionlabs.arkive.mapper.FileMapper;
import com.axionlabs.arkive.mapper.FileMetaDataMapper;
import com.axionlabs.arkive.repository.FileMetaDataRepository;
import com.axionlabs.arkive.repository.FileRepository;
import com.axionlabs.arkive.repository.UserRepository;
import com.axionlabs.arkive.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class IFileService implements FileService {
    private final FileRepository fileRepository;
    private final S3Client amazonS3Client;
    private final AWSConfigProperties awsConfigProperties;
    private final IURLService iurlService;
    private final FileMapper fileMapper;
    private final UserRepository userRepository;
    private final FileMetaDataRepository fileMetaDataRepository;
    private final FileMetaDataMapper fileMetaDataMapper;
    @Autowired
    public IFileService(
            FileRepository fileRepository,
            S3Client amazonS3Client,
            AWSConfigProperties awsConfigProperties,
            IURLService iurlService, FileMapper fileMapper,
            UserRepository userRepository,
            FileMetaDataRepository fileMetaDataRepository,
            FileMetaDataMapper fileMetaDataMapper
    ) {
        this.fileRepository = fileRepository;
        this.amazonS3Client = amazonS3Client;
        this.awsConfigProperties = awsConfigProperties;
        this.iurlService = iurlService;
        this.fileMapper = fileMapper;
        this.userRepository = userRepository;
        this.fileMetaDataRepository = fileMetaDataRepository;
        this.fileMetaDataMapper = fileMetaDataMapper;
    }

    @Override
    public FileDto uploadFile(FileUploadRequestDto fileUploadRequest) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        MultipartFile file = fileUploadRequest.getFile();
        String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();


        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsConfigProperties.getBucketName())
                .key(fileName)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();
        amazonS3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        String url = amazonS3Client.utilities().getUrl(GetUrlRequest.builder()
                        .bucket(awsConfigProperties.getBucketName())
                        .key(fileName)
                .build()).toString();
        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileName);
        fileDto.setFileUrl(url);
        File savedFile = fileMapper.toFileEntity(fileDto);
        savedFile.setUser(user);
        fileRepository.save(savedFile);
        FileMetaDataDto fileMetaDataDto = extractFileMetaData(fileName);
        FileMetaData savedFileMetaData = fileMetaDataMapper.toFileMetaDataEntity(fileMetaDataDto);
        savedFileMetaData.setFile(savedFile);
        fileMetaDataRepository.save(savedFileMetaData);
        FileDto savedFileDto = fileMapper.fromFileAndFileMetaData(savedFile,fileMetaDataMapper.toFileMetaDataDto(savedFileMetaData));
        savedFileDto.setFileUrl(iurlService.generatePreSignedAccessUrl(fileName,awsConfigProperties.getPreSignedExpiry()));
        return savedFileDto;

    }

    @Override
    public List<FileDto> fetchAllFiles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        List<File> files = fileRepository.getAllFilesFromUser(user);
        return files.stream().map(
                file -> {
                    FileDto fileDto = fileMapper.fromFileAndFileMetaData(file, fileMetaDataMapper.toFileMetaDataDto(file.getFileMetaData()));
                    fileDto.setFileUrl(iurlService.generatePreSignedAccessUrl(file.getFileName(),awsConfigProperties.getPreSignedExpiry()));
                    return fileDto;
                }
        ).toList();
    }

    @Override
    public FileDto fetchFileById(UUID fileId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        File file = fileRepository.getFileById(user,fileId).orElseThrow(
                () -> new ResourceNotFoundException("File","fileId",fileId.toString())
        );
        FileDto fileDto = fileMapper.fromFileAndFileMetaData(file, fileMetaDataMapper.toFileMetaDataDto(file.getFileMetaData()));
        fileDto.setFileUrl(iurlService.generatePreSignedAccessUrl(fileDto.getFileName(),awsConfigProperties.getPreSignedExpiry()));
        return fileDto;

    }

    @Override
    public boolean deleteFileById(UUID fileId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        File file = fileRepository.getFileById(user,fileId).orElseThrow(
                () -> new ResourceNotFoundException("File","fileId",fileId.toString())
        );
        fileRepository.delete(file);
        return true;
    }

    private FileMetaDataDto extractFileMetaData(String fileName){
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(awsConfigProperties.getBucketName())
                .key(fileName)
                .build();
        HeadObjectResponse headObjectResponse = amazonS3Client.headObject(headObjectRequest);
        FileMetaDataDto fileMetaDataDto = new FileMetaDataDto();
        fileMetaDataDto.setChecksum(headObjectResponse.eTag());
        fileMetaDataDto.setContentType(headObjectResponse.contentType());
        fileMetaDataDto.setChecksumType("ETag (MD5)");
        fileMetaDataDto.setEncryptionAlgorithm(headObjectResponse.serverSideEncryptionAsString());
        fileMetaDataDto.setSizeInBytes(headObjectResponse.contentLength());
        return fileMetaDataDto;
    }

}
