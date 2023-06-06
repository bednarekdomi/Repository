package com.App.GitRepositoryApp.service;

import com.App.GitRepositoryApp.dto.RepositoryDetails;
import com.App.GitRepositoryApp.dto.User;
import com.App.GitRepositoryApp.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService extends GitHubClient{

    private final GitHubClient gitHubClient;

    public RepositoryDetails getRepositoryByName(String userName, String repositoryName) {
        return gitHubClient.getRepository(userName, repositoryName);
    }

    public List<RepositoryDetails> getAllUserRepositories(String userName) {
        return gitHubClient.getRepositories(userName).getUsersRepositories();
    }

    public RepositoryDetails getOldestRepository(String userName) {
        return gitHubClient.getRepositories(userName).getUsersRepositories().stream().min(Comparator.comparing(RepositoryDetails::getCreatedAt))
                .orElseThrow(UserNotFoundException::new);
    }

    public RepositoryDetails getMostForkedRepository(String userName) {
        User user = gitHubClient.getRepositories(userName);
        List<RepositoryDetails> repositories = user.getUsersRepositories();
        return repositories.stream().max(Comparator.comparing(RepositoryDetails::getForks)
                .thenComparing(RepositoryDetails::getCreatedAt)).orElseThrow(UserNotFoundException::new);
    }
}
