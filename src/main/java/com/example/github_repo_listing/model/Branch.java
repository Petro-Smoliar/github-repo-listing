package com.example.github_repo_listing.model;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private Commit commit;
}
