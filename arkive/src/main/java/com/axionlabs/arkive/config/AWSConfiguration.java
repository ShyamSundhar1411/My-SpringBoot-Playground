package com.axionlabs.arkive.config;



import com.axionlabs.arkive.config.properties.AWSConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@Repository
public class AWSConfiguration {
    private final AWSConfigProperties awsConfig;
    @Autowired
    public AWSConfiguration(AWSConfigProperties awsConfig) {
        this.awsConfig = awsConfig;
    }

    @Bean
    public S3Client initializeS3() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(awsConfig.getAccessKey(), awsConfig.getSecretAccessKey());
        return S3Client.builder().region(Region.AF_SOUTH_1
        ).credentialsProvider(
                StaticCredentialsProvider.create(awsCredentials)
        ).build();
    }
    @Bean
    public S3Presigner initializeS3Presigner(){
        AwsCredentials awsCredentials = AwsBasicCredentials.create(awsConfig.getAccessKey(),awsConfig.getSecretAccessKey());
        return S3Presigner.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.AF_SOUTH_1)
                .build();
    }

}
