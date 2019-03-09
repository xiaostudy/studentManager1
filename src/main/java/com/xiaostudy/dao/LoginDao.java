package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Login;

/**
 * 登录dao接口
 * 
 * @author liwei
 *
 */
public interface LoginDao {
	
	public List<Login> selectLoginAll();
	
	public Login selectByPrimaryKey(Integer id);
	
	public Login selectByLoginId(Integer loginId);
	
	public List<Login> selectByName(String name);
	
	public Integer deleteByPrimaryKey(Integer id);
	
	public Integer deleteByLoginId(Integer loginId);

	public Integer insert(Login login);

	public Integer updateByPrimaryKey(Login login);
	
	public Integer updateByLoginId(Login login);
}