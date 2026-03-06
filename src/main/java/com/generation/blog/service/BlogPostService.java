package com.generation.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.entities.BlogPost;
import com.generation.blog.mapper.BlogPostMapper;
import com.generation.blog.repository.BlogPostRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BlogPostService
{
	@Autowired
	BlogPostRepository repository;
	
	@Autowired
	BlogPostMapper mapper;
	
	public List<BlogPostDTO> findAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public BlogPostDTO findById(Integer id) {
        BlogPost blogPost = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogPost not found with id: " + id));
        return mapper.toDTO(blogPost);
    }

    public BlogPostDTO save(@Valid BlogPostDTO blogPostDTO) {
        
        BlogPost blogPost = mapper.toEntity(blogPostDTO);
        blogPost = repository.save(blogPost);
        return mapper.toDTO(blogPost);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
