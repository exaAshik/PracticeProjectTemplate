package com.bkash.loan.template.authentication;

import com.bkash.loan.template.constant.AppConstant;
import com.bkash.loan.template.model.AwsSecretManagerSecret;
import com.bkash.loan.template.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Service
public class UserService implements UserDetailsService {

    private final SecretsManagerClient secretsManagerClient;

    private final ObjectMapper objectMapper;

    @Value("${aws.secret.manager.name}")
    private String secretManagerName;

    public UserService(SecretsManagerClient secretsManagerClient) {

        this.secretsManagerClient = secretsManagerClient;
        this.objectMapper = new ObjectMapper()
                            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        AwsSecretManagerSecret secret = loadSecret();
        String password;
        String role;

        if (AppConstant.AGENT_USER.equalsIgnoreCase(username)) {
            password = secret.getAgentPassword();
            role = secret.getAgentRole();
        } else if (AppConstant.DSO_USER.equalsIgnoreCase(username)) {

            password = secret.getDsoPassword();
            role = secret.getDsoRole();
        } else if (AppConstant.MERCHANT_USER.equalsIgnoreCase(username)) {

            password = secret.getMerchantPassword();
            role = secret.getMerchantRole();
        } else {

            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new User(username, password, role);

    }

    private AwsSecretManagerSecret loadSecret() {
        String secretString = secretsManagerClient.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId(secretManagerName)
                        .build()
        ).secretString();

        try {

            return objectMapper.readValue(secretString, AwsSecretManagerSecret.class);

        } catch (JsonProcessingException e) {

            throw new RuntimeException("Failed to parse secret string");
        }
    }
}
