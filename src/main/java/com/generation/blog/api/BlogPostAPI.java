package com.generation.blog.api;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.service.BlogPostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogPostAPI
{
	@Autowired
	BlogPostService service;
	
    /**
     * Save a new post to a blog
     * @param dto
     * @return
     */
	@PostMapping
    public ResponseEntity<Object> save(@RequestBody BlogPostDTO dto) {
        try {
            dto = service.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * Modify an existing post
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody BlogPostDTO dto) {
        try {
            dto.setId(id);
            dto = service.save(dto);
            return ResponseEntity.ok(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public BlogPostDTO findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping
    public List<BlogPostDTO> findByTitleContaining(@RequestParam String title) {
        return service.findByTitleContaining(title);
    }

    /**
     * Can be useful for maybe giving an overview of an user, even if they manage multiple blogs
     * @param userId
     * @return userPosts
     */
    @GetMapping("/user/{userId}")
    public String findPostsByAuthor(@PathVariable int userId) {
        //TODO implement this
        return new String();
    }

    @PostMapping("/visibility/{id}/{visibility}") 
    public ResponseEntity<Void> changeVisibility(@PathVariable int id, @PathVariable String visibility){
        //TODO implement this
        return ResponseEntity.noContent().build();

    }
}
