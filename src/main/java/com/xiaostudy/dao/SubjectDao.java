package com.xiaostudy.dao;

import java.util.List;

import com.xiaostudy.domain.Subject;

/**
 * 学科dao接口
 * 
 * @author liwei
 * 
 */
public interface SubjectDao {

	public List<Subject> selectSubjectAll();

	public List<Subject> selectSubjectPages(Integer i);

	public Subject selectByPrimaryKey(Integer subjectId);

	public Subject selectBySubjectNumber(String subjectNumber);

	public List<Subject> selectBySubjectName(String subjectName);

	public Integer deleteByPrimaryKey(Integer subjectId);

	public Integer deleteBySubjectNumber(String subjectNumber);

	public Integer insert(Subject subject);

	public Integer insertNoID(Subject subject);

	public Integer updateByPrimaryKey(Subject subject);

	public Integer updateBySubjectNumber(Subject subject);
}