package com.karthik.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="mail_id", nullable = false, length = 70)
	private String email;
	@Column(name="first_name", nullable = false, length = 70)
	private String firstName;
	@Column(name="last_name", nullable = false, length = 70)
	private String lastName;
	@Column(name="country", nullable = false, length = 60)
	private String country;
	@Column(name="province", nullable = false, length = 40)
	private String provices;
	@Column(name="zip_code", nullable = false, length = 8)
	private String zipCode;
	@Column(name="street_address", nullable = false, length = 110)
	private String streetAddress;
	@Column(name="user_name", nullable = false, length = 10)
	private String userName;
	@Column(name="city", nullable = false, length = 30)
	private String city;
	@Column(name="about", nullable = false, length = 100)
	private String about;
	@Column(name="password", nullable = false, length = 20)
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> post=new ArrayList<>();
	
}
