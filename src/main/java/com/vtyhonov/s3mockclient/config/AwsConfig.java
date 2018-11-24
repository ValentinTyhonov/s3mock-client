package com.vtyhonov.s3mockclient.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.vtyhonov.s3mockclient.config.properties.AwsS3Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    private final AwsS3Properties awsS3Properties;

    @Autowired
    public AwsConfig(final AwsS3Properties awsS3Properties) {
        this.awsS3Properties = awsS3Properties;
    }

    @Bean
    public AmazonS3 s3Client() {
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(
            awsS3Properties.getEndpoint(), awsS3Properties.getRegion()
        );
        return AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(endpoint)
            .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
            .build();
    }
}
