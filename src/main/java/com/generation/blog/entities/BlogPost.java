package com.generation.blog.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
@Entity
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @PastOrPresent
    @NotNull
    private LocalDateTime publishedOn;
    
    @Enumerated(EnumType.STRING)
    private PostVisibility visibility;
    
    @ManyToOne
    @JoinColumn(name="blog_id")
    Blog blog;
    
    //TODO add tags and comments
}
