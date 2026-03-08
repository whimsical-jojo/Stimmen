package com.generation.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.entities.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>
{
    //TODO add tags and comments
    List<BlogPost> findByTitleContaining(String title);

}
