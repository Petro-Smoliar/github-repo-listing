package com.example.github_repo_listing.controller;

import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.service.GithubService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubController {
    private final GithubService githubService;

    @GetMapping("/user/{username}/repositories")
    public List<RepositoryDto> getRepositoriesByUsername(@PathVariable String username) {
        return githubService.findRepositoriesByUsername(username);
    }
}
