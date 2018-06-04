package com.nhsystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userService;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("akos")
										.password("pass")
										.roles("ADMIN");
		
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		
		
		/*auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select id as principal, password as credentials, true from user where id=?")
		.authoritiesByUsernameQuery("select user_id as principal, role_id as role from user_roles where user_id=?")
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_"); */
								
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/register","/reg","/","/about","/login","/css/**","/webjars/**").permitAll()
			.antMatchers("/db","/db/**").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/users","/addTask").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/profile")
		.and()
		.logout().logoutSuccessUrl("/login");
	}

}
