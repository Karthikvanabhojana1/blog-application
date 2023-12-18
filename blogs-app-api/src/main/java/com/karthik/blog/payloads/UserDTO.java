package com.karthik.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String country;
	private String provices;
	private String zipCode;
	private String streetAddress;
	private String userName;
	private String city;
	private String about;
	private String password;
}
