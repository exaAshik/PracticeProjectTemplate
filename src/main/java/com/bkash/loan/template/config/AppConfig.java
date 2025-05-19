package com.bkash.loan.template.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.net.URI;

@Configuration
public class AppConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.access.key}")
    private String access_key;

    @Value("${aws.secret.key}")
    private String secret_key;

    @Bean
    public SecretsManagerClient secretsManagerClient() {

        return SecretsManagerClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(access_key, secret_key)
                ))
                .build();
    }
}
