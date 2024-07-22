package com.example.github_repo_listing.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Branch {
    private String name;
    private Commit commit;
}
