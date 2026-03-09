package com.generation.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blog.dto.WebUserDTO;
import com.generation.blog.mapper.WebUserMapper;
import com.generation.blog.model.WebUser;
import com.generation.blog.repository.WebUserRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service used to find and read users.
 */
@Service
public class WebUserService
{
	@Autowired
	WebUserRepository userRepository;
	
	@Autowired
	WebUserMapper userMapper;
	

	public List<WebUserDTO> findAll() {
        return userMapper.toDTOs(userRepository.findAll());
    }

    public WebUserDTO findById(Integer id) {
        WebUser user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }


    public WebUserDTO findByUsername(String username) {
        WebUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));
        return userMapper.toDTO(user);
    }

    public List<WebUserDTO> findByUsernameContaining(String username) {
        return userMapper.toDTOs(userRepository.findByUsernameContaining(username));
    }

    /**
     * Looks up bloggers by their nickname
     * @param nickname
     * @return
     */
    public List<WebUserDTO> findByNickname(String nickname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNickname'");
    }

}
