package com.axionlabs.arkive.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {
    @Value("${aws.config.access-key:}")
    private String accessKey;
    @Value("${aws.config.secret-access-key}")
    private String secretKey;
    @Value("${aws.config.bucket-name}")
    private String bucketName;
    @Bean
    public AmazonS3 initializeS3(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey,this.secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AF_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public String s3BucketName(){
        return this.bucketName;
    }
}
