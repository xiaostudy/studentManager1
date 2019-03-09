package com.xiaostudy.service;

import java.util.List;

import com.xiaostudy.domain.Grade;

/**
 * 年级service接口
 * 
 * @author liwei
 * 
 */
public interface GradeService {

	public List<Grade> getGradeAll();

	public Grade getGradeByGradeId(Integer gradeId);

	public Grade getGradeByGradeNumber(String gradeNumber);

	public Grade getGradeByGradeName(String gradeName);

	public List<String> getGradeNumberList();

	public List<String> getGradeNameList();

	public boolean deleteGrade(Grade grade);

	public boolean deleteGrade(String gradeNumber);

	public boolean insertGrade(Grade grade);

	public boolean updateGrade(Grade grade);

	public boolean isGradeNumber(String gradeNumber);

	public boolean isGradeName(String gradeName);

}