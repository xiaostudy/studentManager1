package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Grade;

/**
 * 年级dao接口
 * 
 * @author liwei
 * 
 */
public interface GradeDao {
	public List<Grade> selectGradeAll();

	public Grade selectByPrimaryKey(Integer gradeId);

	public Grade selectByGradeNumber(String gradeNumber);

	public Grade selectByGradeName(String gradeName);

	public Integer deleteByPrimaryKey(Integer gradeId);

	public Integer deleteByGradeNumber(String gradeNumber);

	public Integer insertGrade(Grade grade);

	public Integer insertGradeNoID(Grade grade);

	public Integer updateByPrimaryKey(Grade grade);

	public Integer updateByGradeNumber(Grade grade);
}