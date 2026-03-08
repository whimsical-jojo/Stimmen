package com.generation.blog.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * Represents a user's blog. An user may have many blogs on many different topics
 */
@Data
@Entity
public class Blog
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private WebUser author;

    @OneToMany(mappedBy="blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<BlogPost> posts;

    //TODO Add tags
}
