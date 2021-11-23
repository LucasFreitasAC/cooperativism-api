package com.ac.cooperativism.v1.domain.service.client;

import com.ac.cooperativism.v1.api.model.UserStatusModel;
import com.ac.cooperativism.v1.core.configuration.VoteConfiguration;
import com.ac.cooperativism.v1.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class UserClientService {

    private RestTemplate restTemplate;

    private VoteConfiguration voteConfiguration;

    @Autowired
    public UserClientService(RestTemplate restTemplate, VoteConfiguration voteConfiguration) {
        this.restTemplate = restTemplate;
        this.voteConfiguration = voteConfiguration;
    }

    public UserStatusModel validateUserDocument(Long document) {
        try {
            URI uri = URI.create(String.format(voteConfiguration.getUserClientUrl(), document));
            return restTemplate.getForObject(uri, UserStatusModel.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            throw new UserNotFoundException(document);
        }
    }

}
