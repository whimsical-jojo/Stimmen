package com.generation.blog.api;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
 * AccountManagementAPI so users can manage their own accounts, login, edit, change password, delete account, etc.
 * TODO add security stuff?
 * To the Jwt service I should add an extractId, maybe?
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

    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody WebUserDTO dto) {
        try {
            dto = service.create(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody WebUserDTO dto) {
        try {
            dto.setId(id);
            dto = (WebUserDTO) service.update(dto);
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

    @GetMapping("/current-user")
    public ResponseEntity<WebUserDTO> getCurrentUser(Authentication authentication) {
        WebUserDTO currentUser = service.getCurrentUser(authentication.getName());
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(currentUser);
    }

}
