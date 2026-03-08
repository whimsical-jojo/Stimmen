package com.generation.blog.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
@Entity
public class Admin extends WebUser
{
	@NotNull
	@PastOrPresent
	LocalDate lastPasswordChange;
	
	public Admin () {
		setRole("Admin");
	}
}
