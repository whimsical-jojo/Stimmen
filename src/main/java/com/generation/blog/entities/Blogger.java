package com.generation.blog.entities;


import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Persona che scrive 
 */
@Data
@Entity
public class Blogger extends WebUser {

    private String nickName;

    public Blogger () {
    	setRole("Blogger");
    }
}
