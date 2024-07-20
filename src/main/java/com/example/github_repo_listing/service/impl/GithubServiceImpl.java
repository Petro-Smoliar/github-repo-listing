package com.example.github_repo_listing.service.impl;

import com.example.github_repo_listing.client.GithubClient;
import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.mapper.RepositoryMapper;
import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Repository;
import com.example.github_repo_listing.service.GithubService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {
    private final GithubClient githubClient;
    private final RepositoryMapper repositoryMapper;

    @Override
    public List<RepositoryDto> findRepositoriesByLogin(String username) {
        List<Repository> repositories = githubClient.getUserRepositories(username);
        return repositories.stream()
                .map(repository -> {
                    String repoName = repository.getName();
                    List<Branch> branches = githubClient.getRepositoryBranches(username, repoName);
                    repository.setBranches(branches);
                    return repositoryMapper.toDto(repository);
                })
                .toList();
    }
}
