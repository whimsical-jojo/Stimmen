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
    //image

    private WebUserDTO owner;
    //List of collaborators?
    //List of tags?
}
