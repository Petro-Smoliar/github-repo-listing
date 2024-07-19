package com.example.github_repo_listing.model;

import java.util.List;
import lombok.Data;

@Data
public class Repository {
    private String name;
    private Owner owner;
    private Boolean fork;
    private List<Branch> branches;
}
