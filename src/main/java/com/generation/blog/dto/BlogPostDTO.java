package com.generation.blog.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostDTO
{
    //Will I ever add pics to these? Maybe. Who knows.
	private int id;
    private String title;
    private String content;
    private LocalDateTime publishedOn;
    private int blogId;
}
