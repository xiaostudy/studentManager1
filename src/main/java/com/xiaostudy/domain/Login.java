package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 登录domain类
 * 
 * @author liwei
 *
 */
@Component
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer loginId;
	private String name;
	private String password;
	private String passwordPrompt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordPrompt() {
		return passwordPrompt;
	}

	public void setPasswordPrompt(String passwordPrompt) {
		this.passwordPrompt = passwordPrompt;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", loginId=" + loginId + ", name=" + name
				+ ", password=" + password + ", passwordPrompt="
				+ passwordPrompt + "]";
	}

}
