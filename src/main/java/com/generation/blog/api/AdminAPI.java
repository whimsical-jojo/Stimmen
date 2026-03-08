package com.generation.blog.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For managing admin actions. Admins cannot delete or modify user accounts, but can ban and unban users and posts.
 * (except if they are admins themselves)
 * TODO implement and make sure only admins can use this
 */
@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminAPI {
    @PostMapping("/ban/user/{id}")
    public ResponseEntity<Void> banUser(@PathVariable int id) {
        // TODO: Implement user banning logic
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ban/post/{id}")
    public ResponseEntity<Void> banPost(@PathVariable int id) {
        // TODO: Implement post banning logic
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unban/user/{id}")
    public ResponseEntity<Void> unbanUser(@PathVariable int id) {
        // TODO: Implement user unbanning logic
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unban/post/{id}")
    public ResponseEntity<Void> unbanPost(@PathVariable int id) {
        // TODO: Implement post unbanning logic
        return ResponseEntity.ok().build();
    }
}
