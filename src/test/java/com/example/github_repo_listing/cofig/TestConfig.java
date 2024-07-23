package com.example.github_repo_listing.cofig;

import okhttp3.mockwebserver.MockWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TestConfig {

    @Bean
    public WebClient webClient(MockWebServer mockWebServer) {
        return WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
    }

    @Bean
    public MockWebServer mockWebServer() {
        return new MockWebServer();
    }
}
