package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.model.BlogPost;

@Mapper(componentModel="spring")
public interface BlogPostMapper
{
	@Mapping(source = "blog.id", target = "blogId")
	@Mapping(source = "author.id", target = "authorId")
	BlogPostDTO toDTO (BlogPost post);
	List<BlogPostDTO> toDTOs (List<BlogPost> posts);
	
	@Mapping(source = "blogId", target = "blog.id")
	@Mapping(source = "authorId", target = "author.id")
	BlogPost toEntity (BlogPostDTO dto);
	List<BlogPost> toEntities (List<BlogPostDTO> dtos);
}
