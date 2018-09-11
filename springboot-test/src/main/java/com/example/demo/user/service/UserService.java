package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService
{
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}
	@Override
	public void saveAll(List<User> users) {
		userRepository.saveAll(users);
	}
	
	
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<User> users = (List<User>) userRepository.findAllById(idLists);
		if(users!=null) {
			userRepository.deleteAll(users);
		}
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public long count() {
		return userRepository.count();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}
}
