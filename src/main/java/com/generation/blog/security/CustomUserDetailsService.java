package com.generation.blog.security;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blog.model.WebUser;
import com.generation.blog.repository.WebUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    WebUserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//Only one query, as it should be
    	WebUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        //This should work and give me the user
        return CustomUserDetails.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
                .build(); // Mappa il ruolo (es. ADMIN, USER)
    }
}
