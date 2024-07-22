package com.example.github_repo_listing;

import com.example.github_repo_listing.client.GithubClient;
import com.example.github_repo_listing.cofig.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, GithubClient.class})
class GithubRepoListingApplicationTests {

	@Test
	void contextLoads() {
	}

}
