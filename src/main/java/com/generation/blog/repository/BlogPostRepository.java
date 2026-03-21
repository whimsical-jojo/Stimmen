package com.generation.blog.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.generation.blog.model.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>
{
    //TODO add tags and comments
    List<BlogPost> findByTitleContaining(String title);

    @Query("SELECT p FROM BlogPost p WHERE p.publishedOn >= :oldestDate")
    List<BlogPost> findPostsFromDate(LocalDate oldestDate);

    List<BlogPost> findByBlogId(int blogId);

}
