package com.xiaostudy.service;

import java.util.List;

import com.xiaostudy.domain.Clazz;

/**
 * 班级service接口
 * 
 * @author liwei
 *
 */
public interface ClazzService {
	
	public List<Clazz> getClazzAll();
	
	public Clazz getClazzByClazzId(Integer clazzId);

	public Clazz getClazzByClazzNumber(String clazzNumber);

	public List<Clazz> getClazzByClazzName(String clazzName);
	
	public List<Clazz> getClazzByGradeName(String gradeName);
	
	public List<Clazz> getClazzByTeacherName(String teacherName);

	public List<String> getClazzNumberList();

	public List<String> getClazzNameList();

	public boolean deleteClazz(Clazz clazz);

	public boolean deleteClazz(String clazzNumber);

	public boolean insertClazz(Clazz clazz);
	
	public boolean updateClazz(Clazz clazz);
	
	public boolean isClazzInGradeName(String gradeName);

	public boolean isClazzInTeacherName(String teacherName);

	public boolean isClazzInGradeNameClazzName(String gradeName, String clazzName);

	public boolean isClazzInGradeNameTeacherName(String gradeName, String teacherName);

	public boolean isGradeName(String gradeName);

	public boolean isTeacherName(String teacherName);

	public boolean isClazz(Clazz clazz);

	public Clazz setClazzInTeacher(Clazz clazz);

	public Clazz setClazzInTeacherName(Clazz clazz, String teacherName);

	public Clazz setClazzInGradeName(Clazz clazz, String gradeName);

	public Clazz setClazz(String clazzNumber, String clazzName, String gradeName, String teacherName);
}