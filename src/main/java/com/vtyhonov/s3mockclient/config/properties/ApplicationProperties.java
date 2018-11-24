package com.vtyhonov.s3mockclient.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "info.application")
public class ApplicationProperties {

    private String name;
    private String version;
    private String description;
}
