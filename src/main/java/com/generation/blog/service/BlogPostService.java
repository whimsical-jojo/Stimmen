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
        //TODO Maybe it's better to have a separate method for posting, and this one can also serve to save sketches.
        BlogPost blogPost = mapper.toEntity(blogPostDTO);
        if (blogPost.getPublishedOn() == null) {
            blogPost.setPublishedOn(java.time.LocalDateTime.now());
        }
        blogPost = repository.save(blogPost);
        return mapper.toDTO(blogPost);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<BlogPostDTO> findByTitleContaining(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTitleContaining'");
    }
}
