package com.example.github_repo_listing.client;

import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Repository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GithubClient {
    private final WebClient webClient;

    public List<Repository> getUserRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(Repository.class)
                .filter(repo -> !repo.isFork())
                .collectList()
                .block();
    }

    public List<Branch> getRepositoryBranches(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }
}
