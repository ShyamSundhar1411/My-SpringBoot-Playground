package com.axionlabs.arkive.service.impl;


import com.axionlabs.arkive.config.properties.AWSConfigProperties;
import com.axionlabs.arkive.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URL;
import java.time.Duration;
import java.util.Date;

@Service
public class IURLService implements URLService {
    private final S3Presigner s3Presigner;
    private final AWSConfigProperties awsConfigProperties;

    @Autowired
    public IURLService(S3Presigner s3Presigner, AWSConfigProperties awsConfigProperties){
        this.s3Presigner = s3Presigner;
        this.awsConfigProperties = awsConfigProperties;
    }


    @Override
    public String generatePreSignedAccessUrl(String fileName, int expirationMinutes) {
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(awsConfigProperties.getBucketName())
                .key(fileName)
                .build();
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(expirationMinutes))
                .getObjectRequest(getObjectRequest)
                .build();
        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        return presignedGetObjectRequest.url().toString();
    }
}
