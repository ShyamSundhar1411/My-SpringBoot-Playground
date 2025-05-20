package com.axionlabs.arkive.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.axionlabs.arkive.config.properties.AWSConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public class AWSConfiguration {
    private final AWSConfigProperties awsConfig;
    @Autowired
    public AWSConfiguration(AWSConfigProperties awsConfig) {
        this.awsConfig = awsConfig;
    }

    @Bean
    public AmazonS3 initializeS3(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(),awsConfig.getSecretAccessKey());
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AF_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
