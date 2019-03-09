package com.xiaostudy.dao;

import java.util.Date;
import java.util.List;

import com.xiaostudy.domain.Teacher;

/**
 * 教师dao接口
 * 
 * @author liwei
 * 
 */
public interface TeacherDao {

	public List<Teacher> selectTeacherAll();

	public Teacher selectByPrimaryKey(Integer teacherId);

	public Teacher selectByTeacherNumber(String teacherNumber);

	public Teacher selectByTeacherName(String teacherName);

	public List<Teacher> selectBySex(String sex);

	public List<Teacher> selectByBorn(Date born);

	public List<Teacher> selectByHome(String home);

	public Teacher selectByContact(String contact);

	public List<Teacher> selectByEntryDate(Date entryDate);

	public Integer deleteByPrimaryKey(Integer teacherId);

	public Integer deleteByTeacherNumber(String teacherNumber);

	public Integer insert(Teacher teacher);

	public Integer insertNoID(Teacher teacher);

	public Integer updateByPrimaryKey(Teacher teacher);

	public Integer updateByTeacherNumber(Teacher teacher);

}