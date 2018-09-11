package com.example.demo.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.user.entity.User;


public interface IUserService 
{
	public void saveOrUpdate(User user);
	public void saveAll(List<User> users);
	
	public void delete(User user);
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public User findOne(Long id);
	public Optional<User> findById(Long id);
	public List<User> findAll();
	
	public boolean existsById(Long id);
	public long count();
	//动态条件查询
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
}
