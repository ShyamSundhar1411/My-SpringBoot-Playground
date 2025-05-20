package com.axionlabs.arkive.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="aws.config")
@Getter @Setter
public class AWSConfigProperties {

        private String accessKey;
        private String secretAccessKey;
        private String bucketName;
        private int preSignedExpiry;

}
