package com.example.github_repo_listing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.github_repo_listing.client.GithubClient;
import com.example.github_repo_listing.cofig.TestConfig;
import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.mapper.RepositoryMapper;
import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Commit;
import com.example.github_repo_listing.model.Owner;
import com.example.github_repo_listing.model.Repository;
import com.example.github_repo_listing.service.impl.GithubServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, GithubClient.class})
public class GithubServiceImplTest {

    @Mock
    private GithubClient githubClient;

    @Mock
    private RepositoryMapper repositoryMapper;

    @InjectMocks
    private GithubServiceImpl githubService;

    @Test
    void testFindRepositoriesByUsername() {
        String username = "testUser";
        List<Branch> branches = List.of(new Branch("branchTest", new Commit("sha")));

        Repository repository = new Repository();
        repository.setName("repo1");
        repository.setOwner(new Owner(username));
        repository.setFork(false);
        repository.setBranches(branches);

        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setName("repo1");
        repositoryDto.setOwner(username);
        repositoryDto.setBranches(branches);

        when(githubClient.getUserRepositories(username)).thenReturn(List.of(repository));
        when(githubClient.getRepositoryBranches(username, "repo1")).thenReturn(branches);
        when(repositoryMapper.toDto(repository)).thenReturn(repositoryDto);

        List<RepositoryDto> result = githubService.findRepositoriesByUsername(username);

        assertEquals(1, result.size());
        assertEquals("repo1", result.get(0).getName());
    }

    @Test
    void testFindRepositoriesByUsername_NoRepositories() {
        String username = "testuser";
        when(githubClient.getUserRepositories(username)).thenReturn(List.of());
        List<RepositoryDto> result = githubService.findRepositoriesByUsername(username);
        assertEquals(0, result.size());
    }
}
