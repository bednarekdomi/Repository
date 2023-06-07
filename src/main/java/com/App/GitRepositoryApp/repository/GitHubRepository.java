package com.App.GitRepositoryApp.repository;

import com.App.GitRepositoryApp.dto.RepositoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitHubRepository extends JpaRepository<RepositoryDetails, Long> {

    List<RepositoryDetails> findByRepositoryNameContainingIgnoreCase(String fullName);

}
