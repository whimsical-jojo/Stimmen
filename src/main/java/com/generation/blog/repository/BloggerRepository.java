package com.generation.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.entities.Blogger;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Integer>
{

	Optional<Blogger> findByUsername(String username);

}
