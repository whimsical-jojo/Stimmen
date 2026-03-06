package com.generation.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.LoginDTO;
import com.generation.blog.dto.TokenDTO;
import com.generation.blog.dto.UserDTO;
import com.generation.blog.entities.User;
import com.generation.blog.mapper.UserMapper;
import com.generation.blog.repository.UserRepository;
import com.generation.blog.security.JwtService;

import jakarta.validation.Valid;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
    private PasswordHasher passwordHasher;

	public List<UserDTO> findAll() {
        return userMapper.toDTOs(userRepository.findAll());
    }

    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    public UserDTO save(@Valid UserDTO userDTO) {
        
        userDTO.setPassword(passwordHasher.toHash(userDTO.getPassword()));
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
    
    public TokenDTO login(LoginDTO loginDTO) {

        //Fixed it back to this by making it so the repo is joined and there is only one
		User user = userRepository.findByUsername(loginDTO.getUsername())
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

}
