package com.ashok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashok.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByroleName(String name);

}
