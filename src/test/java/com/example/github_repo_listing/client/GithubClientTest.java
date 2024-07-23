package com.example.github_repo_listing.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.github_repo_listing.cofig.TestConfig;
import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Commit;
import com.example.github_repo_listing.model.Owner;
import com.example.github_repo_listing.model.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, GithubClient.class})
public class GithubClientTest {
    @Autowired
    private MockWebServer mockWebServer;
    @Autowired
    private GithubClient githubClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetUserRepositories() throws Exception {
        Owner ownerTest = new Owner("userTest");

        Repository repo1 = new Repository("repo1", ownerTest, false, null);
        Repository repo2 = new Repository("repo2", ownerTest, false, null);
        Repository forkRepo = new Repository("forkRepo", ownerTest, true, null);
        List<Repository> repositories = List.of(repo1, repo2, forkRepo);

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(repositories))
                .addHeader("Content-Type", "application/json"));

        List<Repository> result = githubClient.getUserRepositories("testuser");

        assertThat(result).hasSize(2).contains(repo1, repo2);
    }

    @Test
    public void testGetRepositoryBranches() throws Exception {
        Commit commit1 = new Commit("sha1");
        Commit commit2 = new Commit("sha2");
        Branch branch1 = new Branch("main", commit1);
        Branch branch2 = new Branch("develop", commit2);
        List<Branch> branches = List.of(branch1, branch2);

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(branches))
                .addHeader("Content-Type", "application/json"));

        List<Branch> result = githubClient.getRepositoryBranches("testowner", "testrepo");

        assertThat(result).hasSize(2).containsExactlyInAnyOrder(branch1, branch2);
    }
}
