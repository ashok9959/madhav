package com.ashok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashok.entity.UserGroup;
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

	UserGroup findByuserGroupName(String name);

	

}
