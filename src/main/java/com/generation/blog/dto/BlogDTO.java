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

    private int ownerId;
    //List of collaborators?
    //List of tags?
}
