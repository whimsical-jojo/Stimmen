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

import com.generation.blog.dto.WebUserDTO;
import com.generation.blog.service.WebUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class WebUserAPI {

    @Autowired
    private WebUserService service;

    @GetMapping("/{id}")
    public WebUserDTO findById(@PathVariable int id) {
        return service.findById(id);
    }

    
    @GetMapping
    public List<WebUserDTO> findByUsernameContaining(@RequestParam String username) {
        return service.findByUsernameContaining(username);
    }

    
}




