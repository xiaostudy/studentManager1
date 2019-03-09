package com.xiaostudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaostudy.dao.LoginDao;
import com.xiaostudy.domain.Login;
import com.xiaostudy.service.LoginService;

/**
 * 登录service接口实现类
 *
 * @author liwei
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public List<Login> getLoginAll() {
		return loginDao.selectLoginAll();
	}

	@Override
	public Login getLoginByLoginId(Integer loginId) {
		if(loginId == null) {
			return null;
		}
		return loginDao.selectByLoginId(loginId);
	}

	@Override
	public List<Login> getLoginByName(String name) {
		if(name == null || name.trim().length() <= 0) {
			return null;
		}
		return loginDao.selectByName(name);
	}

	@Override
	public boolean deleteByLoginId(Integer loginId) {
		if(loginId == null) {
			return false;
		}
		Login login = loginDao.selectByLoginId(loginId);
		if(login == null) {
			return false;
		}
		Integer i = loginDao.deleteByLoginId(loginId);
		if(i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Login login) {
		if(login == null) {
			return false;
		}
		Login login2 = loginDao.selectByLoginId(login.getLoginId());
		if(login2 != null) {
			return false;
		}
		Integer i = loginDao.insert(login);
		if(i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateLoginIdByLoginId(Integer oldLoginId, Integer newLoginId) {
		if(oldLoginId == null || newLoginId == null) {
			return false;
		}
		if(oldLoginId.equals(newLoginId)) {
			return false;
		}
		Login login = loginDao.selectByLoginId(oldLoginId);
		if(login == null) {
			return false;
		}
		login.setLoginId(newLoginId);
		Integer i = loginDao.updateByPrimaryKey(login);
		if(i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateNameByLoginId(Integer loginId, String newName) {
		if(loginId == null || newName == null || newName.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.selectByLoginId(loginId);
		if(login == null) {
			return false;
		}
		if(newName.equals(login.getName())) {
			return false;
		}
		login.setName(newName);
		Integer i = loginDao.updateByLoginId(login);
		if(i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordByLoginId(Integer loginId, String newPassword) {
		if(loginId == null || newPassword == null || newPassword.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.selectByLoginId(loginId);
		if(login == null) {
			return false;
		}
		if(newPassword.equals(login.getPassword())) {
			return false;
		}
		login.setPassword(newPassword);
		Integer i = loginDao.updateByLoginId(login);
		if(i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordPromptByLoginId(Integer loginId, String newPasswordPrompt) {
		if(loginId == null || newPasswordPrompt == null || newPasswordPrompt.trim().length() <= 0) {
			return false;
		}
		Login login = loginDao.selectByLoginId(loginId);
		if(login == null) {
			return false;
		}
		if(newPasswordPrompt.equals(login.getPasswordPrompt())) {
			return false;
		}
		login.setPasswordPrompt(newPasswordPrompt);
		Integer i = loginDao.updateByLoginId(login);
		if(i != 0) {
			return true;
		}
		return false;
	}

}
