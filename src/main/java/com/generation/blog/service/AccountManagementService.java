package com.generation.blog.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.LoginDTO;
import com.generation.blog.dto.TokenDTO;
import com.generation.blog.dto.WebUserDTO;
import com.generation.blog.mapper.WebUserMapper;
import com.generation.blog.model.Admin;
import com.generation.blog.model.WebUser;
import com.generation.blog.repository.WebUserRepository;
import com.generation.blog.security.JwtService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * API to manage login, account creation, modification, and deletion.
 * TODO add security stuff
 */
@Service
public class AccountManagementService {

    @Autowired
    private PasswordHasher passwordHasher;

    @Autowired
    private WebUserRepository userRepository;

    @Autowired
    private WebUserMapper userMapper;

    @Autowired
    private JwtService jwtService;


    public TokenDTO login(LoginDTO loginDTO) {

        //TODO:
        //If admins password was last changed more than 30 days ago, have them do a password change
        //Fixed it back to this by making it so the repo is joined and there is only one
		WebUser user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        // verifico: le password corrispondono? Se non corrispondono, stessa storia
        // cambia il messaggio di errore                
        if (!user.getPassword().equals(passwordHasher.toHash(loginDTO.getPassword()))) {
            throw new RuntimeException("Invalid password");
        }

        // jwtService che è un servizio che non tocca il db, genera il token a partire dallo user
        // e lo rimanda all'utente sotto forma di TokenDTO
        // il token viene inviato al client, il client ce lo rimanderà
        return new TokenDTO(jwtService.generateToken(user));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


    /**
     * Creates a new User from a DTO
     * @param userDTO
     * @return
     */
    public WebUserDTO create(@Valid WebUserDTO userDTO) {
        
        WebUser user = userMapper.toEntity(userDTO);
        //Kind of a crappy way to deal with it but I want to have something at least somewhat functional soon
        if (user instanceof Admin) {
            ((Admin)user).setLastPasswordChange(LocalDate.now());
            user.setRole("ADMIN");
        } else {
            user.setRole("BLOGGER");
        }
        user.setPassword(passwordHasher.toHash(user.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    /**
     * Updates an existing User from a DTO
     * @param userDTO
     * @return
     */
    public WebUserDTO update(WebUserDTO userDTO) {
        WebUser user = userRepository.findById(userDTO.getId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (userDTO.getPassword() != null) {
            userDTO.setPassword(passwordHasher.toHash(userDTO.getPassword()));
            if (user instanceof Admin) {
                ((Admin)user).setLastPasswordChange(LocalDate.now());
            }
        }
        userMapper.updateFromDTO(userDTO, user);
        user = userRepository.save(user);
        return userMapper.toDTO(user); 
    }
}
