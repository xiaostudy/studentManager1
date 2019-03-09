package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Results;

/**
 * 成绩dao接口
 * 
 * @author liwei
 *
 */
public interface ResultsDao {
	
	public List<Results> selectResultsAll();
	
	public Results selectByPrimaryKey(Integer resultsId);
	
	public Results selectByResultsNumber(String resultsNumber);

	public List<Results> selectByScore(Integer score);
	
	public Integer deleteByPrimaryKey(Integer resultsId);
	
	public Integer deleteByResultsNumber(String resultsNumber);

	public Integer insert(Results results);

	public Integer insertNoID(Results results);

	public Integer updateByPrimaryKey(Results results);
	
	public Integer updateByResultsNumber(Results results);
}