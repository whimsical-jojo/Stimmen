package com.generation.blog.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BlogPostDTO
{
	private int id;
    private String title;
    private String content;
    private LocalDateTime publishedOn;
    public boolean published;
    BlogDTO blog;
}
