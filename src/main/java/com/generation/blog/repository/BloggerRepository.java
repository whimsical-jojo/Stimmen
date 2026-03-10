package com.generation.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.generation.blog.model.Blogger;
import com.generation.blog.model.WebUser;

public interface BloggerRepository extends JpaRepository<Blogger, Integer>{
    public List<WebUser> findByNicknameContaining(String nickname);
}
