package com.xiaostudy.dao;

import java.util.Date;
import java.util.List;

import com.xiaostudy.domain.Student;

/**
 * 学生dao接口
 * 
 * @author liwei
 * 
 */
public interface StudentDao {

	public List<Student> selectStudentAll();

	public Student selectByPrimaryKey(Integer studentId);

	public Student selectByStudentNumber(String studentNumber);

	public Student selectByStudentName(String studentName);

	public List<Student> selectBySex(String sex);

	public List<Student> selectByBorn(Date born);

	public List<Student> selectByHome(String home);

	public List<Student> selectByHomeName(String homeName);

	public Student selectByHomeContact(String homeContact);

	public List<Student> selectByAdmissionDate(Date admissionDate);

	public Integer deleteByPrimaryKey(Integer studentId);

	public Integer deleteByStudentNumber(String studentNumber);

	public Integer insert(Student student);

	public Integer insertNoID(Student student);

	public Integer updateByPrimaryKey(Student student);

	public Integer updateByStudentNumber(Student student);
}