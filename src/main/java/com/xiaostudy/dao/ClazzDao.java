package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Clazz;

/**
 * 班级dao接口
 * 
 * @author liwei
 *
 */
public interface ClazzDao {
	
	public List<Clazz> selectClazzAll();
	
	public Clazz selectByPrimaryKey(Integer clazzId);
	
	public Clazz selectByClazzNumber(String clazzNumber);
	
	public List<Clazz> selectByClazzName(String clazzName);
	
	public Integer deleteByPrimaryKey(Integer clazzId);
	
	public Integer deleteByClazzNumber(String clazzNumber);

	public Integer insert(Clazz clazz);

	public Integer insertNoID(Clazz clazz);

	public Integer updateByPrimaryKey(Clazz clazz);
	
	public Integer updateByClazzNumber(Clazz clazz);
}