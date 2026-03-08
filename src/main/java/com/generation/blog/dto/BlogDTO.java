package com.generation.blog.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogDTO
{
	private int id;
    private String title;
    private String description;

    private int authorId;

    public List<BlogPostDTO> posts;
}
