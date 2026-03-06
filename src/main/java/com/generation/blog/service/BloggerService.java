package com.generation.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.BloggerDTO;
import com.generation.blog.entities.Blogger;
import com.generation.blog.mapper.BloggerMapper;
import com.generation.blog.repository.BloggerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BloggerService
{
	@Autowired
	BloggerRepository repository;
	
	@Autowired
	BloggerMapper mapper;
	
	@Autowired
    private PasswordHasher passwordHasher;

	public List<BloggerDTO> findAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public BloggerDTO findById(Integer id) {
        Blogger blogger = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blogger not found with id: " + id));
        return mapper.toDTO(blogger);
    }

    public BloggerDTO save(@Valid BloggerDTO bloggerDTO) {
        
        bloggerDTO.setPassword(passwordHasher.toHash(bloggerDTO.getPassword()));
        Blogger blogger = mapper.toEntity(bloggerDTO);
        blogger = repository.save(blogger);
        return mapper.toDTO(blogger);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
