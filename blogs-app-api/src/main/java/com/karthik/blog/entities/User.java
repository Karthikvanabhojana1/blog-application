package com.karthik.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class User implements UserDetails{
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
	@JsonManagedReference
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> post=new ArrayList<>();
	@JsonManagedReference
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Comment> comment=new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns =@JoinColumn(name = "user", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
	private Set<Role> roles=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
