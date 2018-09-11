package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.user.entity.User;
import com.example.demo.user.entity.dto.UserQueryDTO;
import com.example.demo.user.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestApplicationTests {

	@Autowired
	private IUserService userService;
	
	@Test
	public void contextLoads() {
		User user = new User();
		user.setUserName("username");
		user.setPassword("password");
		user.setBirthday(new Date());
		userService.saveOrUpdate(user);
	}

	@Test
	public void testSaveAll() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			User user = new User();
			user.setUserName("UserName"+i+1);
			user.setPassword("password"+i+1);
			user.setBirthday(new Date());
			users.add(user);
		}
		userService.saveAll(users);
	}
	
	@Test
	public void testFindOneAndDelete() {
		User user = userService.findOne(1L);
		System.out.println(user);
		userService.delete(user);
	}
	
	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		System.out.println(users.size());
	}
	
	@Test
	public void testPage() {
		UserQueryDTO userQueryDTO = new UserQueryDTO();
		userQueryDTO.setUserName("userName");
		UserQueryDTO.getWhereClause(userQueryDTO);
		
		//Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
		Pageable pageable = PageRequest.of(0,20, Sort.Direction.DESC,"id");
		Page<User> uPage = userService.findAll(UserQueryDTO.getWhereClause(userQueryDTO), pageable);
		System.out.println("uPage.getContent()::"+uPage.getContent());
		System.out.println("uPage.getNumber()::"+uPage.getNumber());
		System.out.println("uPage.getNumberOfElements()::"+uPage.getNumberOfElements());
		System.out.println("uPage.getSize()::"+uPage.getSize());
		System.out.println("uPage.getTotalElements()::"+uPage.getTotalElements());
		System.out.println("uPage.getTotalPages()::"+uPage.getTotalPages());
		System.out.println("uPage.getPageable()::"+uPage.getPageable());
		System.out.println("uPage.getSort()::"+uPage.getSort());
		
	}

}
