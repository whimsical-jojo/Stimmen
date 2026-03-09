package com.generation.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.mapper.BlogMapper;
import com.generation.blog.model.Blog;
import com.generation.blog.repository.BlogRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BlogService
{
	@Autowired
	BlogRepository repository;
	
	@Autowired
	BlogMapper mapper;
	
	public List<BlogDTO> findAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public BlogDTO findById(Integer id) {
        Blog blog = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
        return mapper.toDTO(blog);
    }

    public BlogDTO save(@Valid BlogDTO blogDTO) {
        
        Blog blog = mapper.toEntity(blogDTO);
        blog = repository.save(blog);
        return mapper.toDTO(blog);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<BlogDTO> findByTitleContaining(String title) {
        return mapper.toDTOs(repository.findByTitleContaining(title));
    }
}
