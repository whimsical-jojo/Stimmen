package com.generation.blog.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.mapper.BlogPostMapper;
import com.generation.blog.model.BlogPost;
import com.generation.blog.repository.BlogPostRepository;
import com.generation.blog.repository.BlogRepository;
import com.generation.blog.repository.WebUserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BlogPostService
{
	@Autowired
	BlogPostRepository repository;
	
	@Autowired
	BlogPostMapper mapper;

    @Autowired
    WebUserRepository userRepo;

    @Autowired
    BlogRepository blogRepo;
	
	public List<BlogPostDTO> findAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public BlogPostDTO findById(Integer id) {
        BlogPost blogPost = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogPost not found with id: " + id));
        return mapper.toDTO(blogPost);
    }

    //TODO add custom security check to make sure the user is either a collaborator or the owner of the blog
    public BlogPostDTO create(@Valid BlogPostDTO blogPostDTO) {
        BlogPost blogPost = mapper.toEntity(blogPostDTO);
        if (blogPost.getPublishedOn() == null) {
            blogPost.setPublishedOn(java.time.LocalDateTime.now());
        }
        //I have no idea why hibernate doesn't deal with this itself, but trying to just set the id makes it so fetching the user
        //always gives me back an empty user/ blog, so I guess I will set it manually so that it actually works
        //and if I ever bother I will go back and fix this
        blogPost.setAuthor(userRepo.findById(blogPost.getAuthor().getId()).get());
        blogPost.setBlog(blogRepo.findById(blogPost.getBlog().getId()).get());
        blogPost = repository.save(blogPost);
        return mapper.toDTO(blogPost);
    }

    //TODO add a custom security evaluator that checks that the user actually owns this
    public BlogPostDTO update (@Valid BlogPostDTO blogPostDTO) {
        //Check that the blog post dto corresponds to the persisted entity
        return blogPostDTO;
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<BlogPostDTO> findByTitleContaining(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTitleContaining'");
    }

    public List<BlogPostDTO> findPostsFromDate(String date) throws DateTimeParseException {
        LocalDate oldestDate = LocalDate.parse(date);
        return mapper.toDTOs(repository.findPostsFromDate(oldestDate));
    }

    public List<BlogPostDTO> findPostsByBlog(int blogId) {
        //Honestly I should check whether it returns null or an empty list
        List<BlogPost> posts = repository.findByBlogId(blogId);
        if (posts == null) {
            return null;
        }
        return mapper.toDTOs(posts);
    }

    //TODO find by tags
}
