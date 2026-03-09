package com.generation.blog.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Named WebUser instead of User to avoid DB reserved word fuckery
 * TODO add followed users/ blogs, favourited posts/ comments
 */
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class WebUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//Legal name isn't necessary. What am I? The NSA?
	//TODO add privacy settings so users can add this but still set it to private. Maybe make it a requirements for
	//admins only?
	private String firstName;
	private String lastName;
	private LocalDate dob; //same goes for date of birth

	@NotBlank
	@Size(min=4, max=20, message="Username must be between 4 and 20 characters")
	@Column(unique=true)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	@Email
	private String email;

	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Blog> ownedBlogs;

	//If a collaborator deletes their account, the blog doesn't get deleted.
	//Maybe I should use cascadeType ALL and only set orphan removal to false?
	@ManyToMany(cascade = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
		fetch = FetchType.LAZY)
	@JoinTable (
		name = "collaborator_blog",
		joinColumns = {@JoinColumn(name="collaborator_id")},
		inverseJoinColumns = {@JoinColumn(name="blog_id")}
	)
	List<Blog> collaboratedBlogs;
	
	@NotBlank
	private String role; //TODO temporary change later

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(mappedBy="author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<BlogPost> posts;
}
