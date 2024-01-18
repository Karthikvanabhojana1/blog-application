package com.karthik.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.karthik.blog.security.CustomUserDetailsService;
import com.karthik.blog.security.JwtAuthenticationEntryPoint;
import com.karthik.blog.security.JwtAuthenticationFilter;

import jakarta.activation.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailService;
    
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf()
        .disable()          
        .authorizeHttpRequests()
        .requestMatchers("init/api/v1/auth/login").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder().
//                username("DURGESH")
//                .password(passwordEncoder().encode("DURGESH")).roles("ADMIN").
//                build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
        @Bean
        public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }   

}
