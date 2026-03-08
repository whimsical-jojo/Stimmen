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

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.service.BlogService;

/**
 * API for the blogs
 */
@RestController
@RequestMapping("api/blogs")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogAPI
{
	@Autowired
	BlogService service;
	
    /**
     * Create a new blog
     * @param dto
     * @return
     */
	@PostMapping
    public ResponseEntity<Object> save(@RequestBody BlogDTO dto) {
        try {
            dto = service.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * Update the blog with this id
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody BlogDTO dto) {
        try {
            dto.setId(id);
            dto = service.save(dto);
            return ResponseEntity.ok(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * Delete the blog with this id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the blog with this id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BlogDTO findById(@PathVariable int id) {
        return service.findById(id);
    }

    /**
     * Search for a blog containing this in the title
     * @param title
     * @return
     */
    @GetMapping
    public List<BlogDTO> findByTitleContaining(@RequestParam String title) {
        return service.findByTitleContaining(title);
    }
}
