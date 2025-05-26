package com.axionlabs.arkive.service;

public interface URLService {
    String generatePreSignedAccessUrl(String fileName,int expirationMinutes);
}
