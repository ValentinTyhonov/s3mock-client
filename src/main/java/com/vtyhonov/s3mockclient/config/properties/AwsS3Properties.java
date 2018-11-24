package com.vtyhonov.s3mockclient.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "s3")
public class AwsS3Properties {

    private String endpoint;
    private String region;
}
