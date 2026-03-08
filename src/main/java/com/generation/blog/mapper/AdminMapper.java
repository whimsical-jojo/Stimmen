package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.model.Admin;

@Mapper(componentModel="spring")
public interface AdminMapper
{
	AdminDTO toDTO(Admin admin);
	List<AdminDTO> toDTOs(List<Admin> admins);
	
	Admin toEntity(AdminDTO dto);
	List<Admin> toEntities(List<AdminDTO> dtos);
}
