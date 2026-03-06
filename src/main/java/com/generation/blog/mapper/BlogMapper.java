package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.entities.Blog;

@Mapper(componentModel="spring")
public interface BlogMapper
{
	BlogDTO toDTO (Blog blog);
	List<BlogDTO> toDTOs (List<Blog> blogs);
	
	Blog toEntity (BlogDTO dto);
	List<Blog> toEntities (List<BlogDTO> dtos);
}
