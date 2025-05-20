package com.axionlabs.arkive.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.axionlabs.arkive.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

@Service
public class IURLService implements URLService {
    private final AmazonS3 amazonS3;
    private final String s3BucketName;

    @Autowired
    public IURLService(AmazonS3 amazonS3, String s3BucketName){
        this.amazonS3 = amazonS3;
        this.s3BucketName = s3BucketName;
    }

    @Override
    public String generatePreSignedUploadUrl(String fileName, int expirationMinutes) {
        Date expiration = new Date(System.currentTimeMillis() + (long) expirationMinutes * 60 * 1000);
        GeneratePresignedUrlRequest presignedUrlRequest = new GeneratePresignedUrlRequest(s3BucketName, fileName)
                .withExpiration(expiration)
                .withMethod(HttpMethod.PUT);
        URL url = amazonS3.generatePresignedUrl(presignedUrlRequest);
        return url.toString();
    }

    @Override
    public String generatePreSignedAccessUrl(String fileName, int expirationMinutes) {
        Date expiration = new Date(System.currentTimeMillis() + (long) expirationMinutes * 60 * 1000);
        GeneratePresignedUrlRequest presignedUrlRequest = new GeneratePresignedUrlRequest(s3BucketName, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(presignedUrlRequest);
        return url.toString();
    }
}
