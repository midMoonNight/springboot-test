package com.example.demo.user.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.user.entity.User;

/**
 * Query DTO  封装查询条件 数据传输对象
 */
public class UserQueryDTO 
{
	private String userName;	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
	private Date createTimeStart;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
	private Date createTimeEnd;
	
	@SuppressWarnings({ "serial"})
	public static Specification<User> getWhereClause(final UserQueryDTO userQueryDTO) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(userQueryDTO.getUserName())) {
					predicate.add(criteriaBuilder.like(root.get("userName").as(String.class),
							"%" + userQueryDTO.getUserName() + "%"));
				}
				if (null!=userQueryDTO.getCreateTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(Date.class),
							userQueryDTO.getCreateTimeStart()));
				}
				if (null!=userQueryDTO.getCreateTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday").as(Date.class),
							userQueryDTO.getCreateTimeEnd()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	/*//提供static的工具方法：根据当前 userQueryDTO 对象来组装动态查询条件
	public static Specification<User> getSpecification(UserQueryDTO userQueryDTO)
	{
		Specification<User> spec = new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//1.Predicate查询条件集合
				 List<Predicate> list = new ArrayList<Predicate>(); 
				 
				//2.根据 QueryDTO数据字段的值进行判断以及条件的组装
				 if(null != userQueryDTO && !StringUtils.isEmpty(userQueryDTO.getUserName())) {
					 Predicate  p1 =  cb.like(root.get("userName").as(String.class),"%"+ userQueryDTO.getUserName() + "%");
					 list.add(p1);
				 }
				 if(null != userQueryDTO && !StringUtils.isEmpty(userQueryDTO.getPassword())) {
					 Predicate  p2 =  cb.like(root.get("password").as(String.class),"%"+ userQueryDTO.getPassword() + "%");
					 list.add(p2);
				 }
				 //3.Predicate查询条件集合的 size 创建对应的Predicate查询条件数组
				 Predicate[] p = new Predicate[list.size()];  
				 //4.CriteriaBuilder的and 函数组装 查询条件数组
				 return cb.and(list.toArray(p));  
			}
		};
		return spec;
	}*/
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	@Override
	public String toString() {
		return "UserQueryDTO [userName=" + userName + ", createTimeStart=" + createTimeStart + ", createTimeEnd="
				+ createTimeEnd + "]";
	}
	
}
