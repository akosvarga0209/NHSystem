package com.nhsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhsystem.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);

}
