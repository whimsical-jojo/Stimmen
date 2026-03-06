package com.generation.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.entities.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>
{

}
