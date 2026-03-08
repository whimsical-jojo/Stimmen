package com.generation.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//I am tempted to take this to the back of the barn and shoot it as well. Isn't it easier to just use a final String?
@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
}
