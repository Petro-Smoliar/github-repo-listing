package com.example.github_repo_listing.dto;

import com.example.github_repo_listing.model.Branch;
import com.example.github_repo_listing.model.Owner;
import java.util.List;
import lombok.Data;

@Data
public class RepositoryDto {
    private String name;
    private Owner owner;
    private List<Branch> branches;
}
