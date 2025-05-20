package com.axionlabs.arkive.service;

public interface URLService {
    String generatePreSignedUploadUrl(String fileName,int expirationMinutes);
    String generatePreSignedAccessUrl(String fileName,int expirationMinutes);
}
