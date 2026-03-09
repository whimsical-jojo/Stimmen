package com.generation.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.model.WebUser;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Integer>
{
	List<WebUser> findAll();
	
	Optional<WebUser> findById(int id);

	Optional<WebUser> findByUsername(String username);

	List<WebUser> findByUsernameContaining(String username);
}
