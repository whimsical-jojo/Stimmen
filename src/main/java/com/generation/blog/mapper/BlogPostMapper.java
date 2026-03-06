package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.entities.BlogPost;

@Mapper(componentModel="spring")
public interface BlogPostMapper
{
	BlogPostDTO toDTO (BlogPost post);
	List<BlogPostDTO> toDTOs (List<BlogPost> posts);
	
	BlogPost toEntity (BlogPostDTO dto);
	List<BlogPost> toEntities (List<BlogPostDTO> dtos);
}
