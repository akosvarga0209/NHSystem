package com.nhsystem.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhsystem.entities.Role;
import com.nhsystem.entities.User;
import com.nhsystem.repository.RoleRepository;
import com.nhsystem.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public void creatUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		Role userRole = roleRepository.findByName("ROLE_USER");
		if(userRole != null) {
			user.getRoles().add(userRole);
		}else {
			user.addRole("ROLE_USER");
		}
		
		
		userRepository.save(user);
	}
	
	
	
	public void creatAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ROLE_ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public User findOneUser(String email) {
		
		return userRepository.findUserByEmail(email);
	}

	public boolean isUserPresent(String email) {
		User u= userRepository.findUserByEmail(email);
		if (u!=null) {
			return true;
		}else return false;
	}

	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}

	public List<User> findByName(String name) {
		return userRepository.findByNameLike("%"+name+"%");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		System.out.println("MEGVAN "+user.getEmail());
		return new UserDetailsImpl(user);
		
	}
	
	

}
