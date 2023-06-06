package com.App.GitRepositoryApp.service;

import com.App.GitRepositoryApp.dto.RepositoryDetails;
import com.App.GitRepositoryApp.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class GitHubClient {

    @Value("${github-api.url.base}")
    private String baseUrl;
    @Value("${github-api.url.all}")
    private String allReposUrl;
    @Value("${github-api.url.one}")
    private String oneRepoUrl;

    private WebClient webClient;

    @PostConstruct
    public void setUp() {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public RepositoryDetails getRepository(String userName, String repositoryName) {

        URI uri = UriComponentsBuilder.fromUriString(oneRepoUrl)
                .buildAndExpand(userName, repositoryName)
                .toUri();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(RepositoryDetails.class)
                .block();
    }

    public User getRepositories(String username) {

        URI uri = UriComponentsBuilder.fromUriString(allReposUrl)
                .buildAndExpand(username)
                .toUri();

        List<RepositoryDetails> repositories = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(RepositoryDetails.class)
                .collectList()
                .block();



        return new User(repositories);
    }

}

