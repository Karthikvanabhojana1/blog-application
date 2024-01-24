package com.karthik.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.karthik.blog.entities.Role;
import com.karthik.blog.payloads.AppConstant;
import com.karthik.blog.repositories.RoleRepository;

@SpringBootApplication
public class BlogsAppApiApplication implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordencoder;
	

	
	public static void main(String[] args) {
		SpringApplication.run(BlogsAppApiApplication.class, args);
	}
	
	@Autowired
	private RoleRepository rolerepository;
	
	@Bean
	public ModelMapper modelmaper(){
		return new ModelMapper();
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordencoder.encode("xyz"));
		
		try {
			Role role=new Role();
			role.setRole(AppConstant.USER_ROLE_ADMIN_NAME);
			role.setRoleId(AppConstant.USER_ROLE_ADMIN);
			Role role2=new Role();
			role2.setRole(AppConstant.USER_ROLE_NORMAL_NAME);
			role2.setRoleId(AppConstant.USER_ROLE_NORMAL);
			
			List<Role> roles=List.of(role,role2);
			List<Role> result=this.rolerepository.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getRole());
			});


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
