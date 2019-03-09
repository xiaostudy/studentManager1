package com.xiaostudy.service;

import java.util.List;

import com.xiaostudy.domain.Login;

/**
 * 登录service接口
 * 
 * @author liwei
 *
 */
public interface LoginService {
	
	public List<Login> getLoginAll();
	
	public Login getLoginByLoginId(Integer loginId);
	
	public List<Login> getLoginByName(String name);
	
	public boolean deleteByLoginId(Integer loginId);

	public boolean insert(Login login);
	
	public boolean updateLoginIdByLoginId(Integer oldLoginId, Integer newLoginId);
	
	public boolean updateNameByLoginId(Integer loginId, String newName);

	public boolean updatePasswordByLoginId(Integer loginId, String newPassword);

	public boolean updatePasswordPromptByLoginId(Integer loginId, String newPasswordPrompt);
}