package com.generation.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	List<User> findAll();
	
	Optional<User> findById(int id);

	Optional<User> findByUsername(String username);

	List<User> findByUsernameContainingIgnoreCase(String usernamePart);
}
