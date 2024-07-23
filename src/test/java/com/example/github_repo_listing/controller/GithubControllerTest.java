package com.example.github_repo_listing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.exception.CustomGlobalExceptionHandler;
import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Commit;
import com.example.github_repo_listing.service.GithubService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class GithubControllerTest {

    @Mock
    private GithubService githubService;

    @InjectMocks
    private GithubController githubController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(githubController)
                .setControllerAdvice(new CustomGlobalExceptionHandler()) // Добавление глобального обработчика исключений
                .build();
    }

    @Test
    public void testGetRepositoriesByUsername() throws Exception {
        String username = "octocat";

        Branch branch1 = new Branch("main", new Commit("sha123"));
        Branch branch2 = new Branch("dev", new Commit("sha456"));

        RepositoryDto repo1 = new RepositoryDto();
        repo1.setName("Repo1");
        repo1.setOwner("octocat");
        repo1.setBranches(Arrays.asList(branch1, branch2));

        RepositoryDto repo2 = new RepositoryDto();
        repo2.setName("Repo2");
        repo2.setOwner("octocat");
        repo2.setBranches(Collections.singletonList(branch1));

        List<RepositoryDto> repositoryDtos = Arrays.asList(repo1, repo2);

        when(githubService.findRepositoriesByUsername(username)).thenReturn(repositoryDtos);

        String json = """
                [
                    {
                        "name": "Repo1",
                        "owner": "octocat",
                        "branches": [
                            {
                                "name": "main",
                                "commit": {
                                    "sha": "sha123"
                                }
                            },
                            {
                                "name": "dev",
                                "commit": {
                                    "sha": "sha456"
                                }
                            }
                        ]
                    },
                    {
                        "name": "Repo2",
                        "owner": "octocat",
                        "branches": [
                            {
                                "name": "main",
                                "commit": {
                                    "sha": "sha123"
                                }
                            }
                        ]
                    }
                ]
                """;

        mockMvc.perform(get("/user/{username}/repositories", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void testGetRepositoriesByUsernameNotFound() throws Exception {
        String username = "unknownuser";

        when(githubService.findRepositoriesByUsername(username))
                .thenThrow(new WebClientResponseException(
                        "Not found", HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null));

        String json = """
                {
                    "status": "NOT_FOUND",
                    "message": "Repository not found for the given username"
                }
                """;
        mockMvc.perform(get("/user/{username}/repositories", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(json));
    }
}
