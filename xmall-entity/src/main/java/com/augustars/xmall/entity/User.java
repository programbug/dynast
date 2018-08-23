package com.augustars.xmall.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String username;
	private String loginName;
	private String password;
	private String status;
	private Role role;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//多对一模式,告知JPA的目标类型（targetEntity）,所查询的信息如何进行封装，与那个实体类对应
	//			  JPA的延迟加载特性（FetchType.LAZY）：仅当所需时，才会进行加载，提高运行效率
	//			级联操作（cascade）：当删除数据表中的某元素时，可能会造成该元素相关的元素数据表被删除
	//				CascadeType.PERSIST保存，保证数据库的数据是完整的
	//				CascadeType.MERGE更新，
	@ManyToOne(targetEntity=Role.class,fetch=FetchType.LAZY,
			cascade={CascadeType.PERSIST,CascadeType.MERGE})
	//指明数据库中的操作对象，当查询到该位置时，会将该元素相关的数据信息获得出来，再将其Set进该位置
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
