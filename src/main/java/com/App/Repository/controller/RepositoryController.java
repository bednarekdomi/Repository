package com.App.Repository.controller;

import com.App.Repository.dto.RepositoryDetails;
import com.App.Repository.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping(value = "/repositories/{owner}/{repository-name}")
    public RepositoryDetails getRepositoryByRepositoryName(@PathVariable String owner,
                                                           @PathVariable("repository-name") String repositoryName) {
        return repositoryService.getRepositoryByName(owner, repositoryName);
    }

    @GetMapping(value = "/getUserRepositories/{owner}")
    public List<RepositoryDetails> getRepositoriesByUserName(@PathVariable String owner) {
        return repositoryService.getAllUserRepositories(owner);
    }

    @GetMapping(value = "/getOldestRepository/{owner}")
    public RepositoryDetails getOldestRepository(@PathVariable String owner) {
        return repositoryService.getOldestRepository(owner);
    }

    @GetMapping(value = "/getMostForkedRepository/{owner}")
    public RepositoryDetails getMostForkedRepository(@PathVariable String owner) {
        return repositoryService.getMostForkedRepository(owner);
    }
}
