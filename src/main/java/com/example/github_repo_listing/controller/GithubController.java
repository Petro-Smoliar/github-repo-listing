package com.example.github_repo_listing.controller;

import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.service.GithubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "GitHub Repositories", description = "Endpoints for managing GitHub repositories")
@RequiredArgsConstructor
public class GithubController {
    private final GithubService githubService;

    @Operation(summary = "Get repositories by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the repositories"),
            @ApiResponse(responseCode = "404", description = "Repositories not found")
    })
    @GetMapping("/user/{username}/repositories")
    public List<RepositoryDto> getRepositoriesByUsername(@PathVariable String username) {
        return githubService.findRepositoriesByUsername(username);
    }
}
