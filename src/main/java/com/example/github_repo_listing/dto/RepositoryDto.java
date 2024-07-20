package com.example.github_repo_listing.dto;

import com.example.github_repo_listing.model.Branch;
import java.util.List;
import lombok.Data;

@Data
public class RepositoryDto {
    private String name;
    private String owner;
    private List<Branch> branches;
}
