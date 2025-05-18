package com.bkash.loan.template.authentication;

import com.bkash.loan.template.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Service
public class UserService implements UserDetailsService {

    private final SecretsManagerClient secretsManagerClient;

    public UserService(SecretsManagerClient secretsManagerClient) {

        this.secretsManagerClient = secretsManagerClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = secretsManagerClient.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId(username)
                        .build()
        ).secretString();

        String roles = secretsManagerClient.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId(username + "_roles")
                        .build()
        ).secretString();

        return new User(username,password,roles);
    }
}
