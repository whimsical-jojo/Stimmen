package com.generation.blog.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
    //TODO add image

    @ManyToOne
    @JoinColumn(name="owner_id")
    private WebUser owner;

    //TODO test that deleting a blog doesn't delete the user
    @ManyToMany(mappedBy = "collaboratedBlogs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WebUser> collaborators;

    //All the posts with different collaborators
    @OneToMany(mappedBy="blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogPost> posts;

    //TODO Add tags
}
