package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.entities.Blog;
import com.generation.blog.entities.WebUser;

@Mapper(componentModel="spring")
public interface BlogMapper
{
	@Mapping(source = "author.id", target = "authorId")
	BlogDTO toDTO (Blog blog);
	List<BlogDTO> toDTOs (List<Blog> blogs);
	
	@Mapping(source = "authorId", target = "author.id")
	Blog toEntity (BlogDTO dto);
	List<Blog> toEntities (List<BlogDTO> dtos);

}
