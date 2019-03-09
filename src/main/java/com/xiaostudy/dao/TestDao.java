package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Test;

/**
 * 考试dao接口
 * 
 * @author liwei
 *
 */
public interface TestDao {
	
	public List<Test> selectTestAll();
	
	public Test selectByPrimaryKey(Integer testId);
	
	public Test selectByTestNumber(String testNumber);
	
	public List<Test> selectByTestName(String testName);

	public Integer deleteByPrimaryKey(Integer testId);
	
	public Integer deleteByTestNumber(String testNumber);

	public Integer insert(Test test);

	public Integer insertNoID(Test test);

	public Integer updateByPrimaryKey(Test test);
	
	public Integer updateByTestNumber(Test test);
}