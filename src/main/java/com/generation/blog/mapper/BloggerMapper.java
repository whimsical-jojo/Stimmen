package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.blog.dto.BloggerDTO;
import com.generation.blog.entities.Blogger;

@Mapper(componentModel="spring")
public interface BloggerMapper
{
	BloggerDTO toDTO (Blogger blogger);
	List<BloggerDTO> toDTOs (List<Blogger> bloggers);
	
	Blogger toEntity (BloggerDTO dto);
	List<Blogger> toEntities (List<BloggerDTO> dtos);
}
