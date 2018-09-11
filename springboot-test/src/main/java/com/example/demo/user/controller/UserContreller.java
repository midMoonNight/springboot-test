package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.user.entity.dto.UserQueryDTO;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserContreller 
{
	@Autowired
	private IUserService userService;
	
	
	@GetMapping
	public Page<User> getPage(UserQueryDTO userQueryDTO , ExtjsPageRequest pageRequest) 
	{
		System.out.println(userQueryDTO.toString());
		return userService.findAll(UserQueryDTO.getWhereClause(userQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{id}")
	public User getOne(@PathVariable("id") Long id) 
	{
		return userService.findById(id).get();
	}
	
	@PostMapping
	public ExtAjaxResponse save(@RequestBody User User) 
	{
		try {
			userService.saveOrUpdate(User);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"保存失败！");
		}
	}
	
	//  /User/1
	/**
	 * 根据id删除一个User
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) 
	{
		try {
			if(id!=null) {
				userService.deleteById(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				System.out.println(ids.length);
				userService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id") Long myId,@RequestBody User dto) 
	{
		try {
			User entity = userService.findById(myId).get();
			//使用自定义的BeanUtils
			BeanUtils.copyProperties(dto, entity);
			userService.saveOrUpdate(entity);
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"更新失败！");
		}
	}
	
}
