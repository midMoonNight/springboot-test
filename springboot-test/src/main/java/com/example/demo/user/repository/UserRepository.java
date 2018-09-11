package com.example.demo.user.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.user.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor<User>
{
	
}
