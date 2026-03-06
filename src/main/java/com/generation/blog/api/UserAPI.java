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
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.dto.UserDTO;
import com.generation.blog.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAPI {

    @Autowired
    private UserService service;
  
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserDTO dto) {
        try {
            dto = (UserDTO) service.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody UserDTO dto) {
        try {
            dto.setId(id);
            dto = (UserDTO) service.save(dto);
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
    public UserDTO findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/{username}")
    public UserDTO findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }
    
    @GetMapping
    public List<UserDTO> findByUsernameContaining(@PathVariable String username) {
        return service.findByUsernameContaining(username);
    }

}




