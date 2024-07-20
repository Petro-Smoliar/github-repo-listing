package com.example.github_repo_listing.service;

import com.example.github_repo_listing.dto.RepositoryDto;
import java.util.List;

public interface GithubService {
    List<RepositoryDto> findRepositoriesByUsername(String username);
}
