package com.generation.blog.api;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.dto.LoginDTO;
import com.generation.blog.dto.TokenDTO;
import com.generation.blog.dto.WebUserDTO;
import com.generation.blog.service.AccountManagementService;

/**
 * AccountManagementAPI so users can manage their own accounts
 * TODO add security stuff?
 */
@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountManagementAPI
{
	@Autowired
	AccountManagementService service;
	
	@PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {
        return service.login(loginDTO);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody WebUserDTO dto) {
        try {
            dto = (WebUserDTO) service.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody WebUserDTO dto) {
        try {
            dto.setId(id);
            dto = (WebUserDTO) service.save(dto);
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
}
