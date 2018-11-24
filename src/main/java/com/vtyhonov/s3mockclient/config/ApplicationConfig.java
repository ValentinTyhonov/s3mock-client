package com.vtyhonov.s3mockclient.config;

import com.vtyhonov.s3mockclient.config.properties.ApplicationProperties;
import com.vtyhonov.s3mockclient.config.properties.AwsS3Properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    ApplicationProperties.class,
    AwsS3Properties.class
})
@ComponentScan
public class ApplicationConfig {

}
