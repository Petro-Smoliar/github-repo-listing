package com.example.github_repo_listing.mapper;

import com.example.github_repo_listing.config.MapperConfig;
import com.example.github_repo_listing.dto.RepositoryDto;
import com.example.github_repo_listing.model.Repository;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RepositoryMapper {
    RepositoryDto toDto(Repository repository);
}
