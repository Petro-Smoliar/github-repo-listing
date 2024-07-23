package com.example.github_repo_listing.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
    private List<Branch> branches;
}
