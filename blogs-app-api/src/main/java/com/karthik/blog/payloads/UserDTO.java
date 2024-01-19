package com.karthik.blog.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.karthik.blog.entities.Comment;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.Role;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

	private Integer id;
	@Email(message = "Your Email Address is not Valid !!")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;
	@NotEmpty
	@Size(min = 5, message = "First Name must have Minimum of 5 character !!")
	private String firstName;
	@NotEmpty
	@Size(min = 2, message = "Last Name must have Minimum of 5 character !!")
	private String lastName;
	@NotEmpty(message = "Your Country name must not be Empty !!")
	private String country;
	@NotEmpty(message = "Your Provices name must not be Empty !!")
	private String provices;
	@NotEmpty(message = "Your Provices name must not be Empty !!")
	private String zipCode;
	@NotEmpty(message = "Your zipCode must not be Empty !!")
	private String streetAddress;
	@NotEmpty(message = "Your userName must not be Empty !!")
	private String userName;
	@NotEmpty(message = "Your city name must not be Empty !!")
	private String city;
	@NotEmpty(message = "Your about details must not be Empty !!")
	private String about;
	@NotEmpty(message = "Your Password  must not be Empty !!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and include at least one alphabetical character, one numeric character, and one special character (@, $, !, %, *, #, ?, or &)")
	private String password;
	private List<Post> post=new ArrayList<>();
	private Set<Role> roles=new HashSet<>();
	private List<Comment> comments=new ArrayList<>();

 
}
