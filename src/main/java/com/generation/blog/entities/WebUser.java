package com.generation.blog.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Named WebUser instead of User to avoid DB reserved word fuckery
 * TODO add followed users/ blogs, favourited posts/ comments
 * TODO add user status: BANNED, ACTIVE, SUSPENDED idk
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
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Blog> blogs;
	
	@NotBlank
	private String role; //TODO temporary change later

	
}
