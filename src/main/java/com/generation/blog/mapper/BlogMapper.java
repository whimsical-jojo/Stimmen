package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.model.Blog;
import com.generation.blog.model.WebUser;

@Mapper(componentModel="spring", uses = WebUserMapper.class)
public interface BlogMapper
{
	//@Mapping(source = "owner.id", target = "ownerId")
	BlogDTO toDTO (Blog blog);
	List<BlogDTO> toDTOs (List<Blog> blogs);
	
	//@Mapping(source = "ownerId", target = "owner.id")
	Blog toEntity (BlogDTO dto);
	List<Blog> toEntities (List<BlogDTO> dtos);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(BlogDTO dto, @MappingTarget Blog blog);

}
