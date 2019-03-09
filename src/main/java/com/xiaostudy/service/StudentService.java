package com.xiaostudy.service;

import java.util.Date;
import java.util.List;

import com.xiaostudy.domain.Clazz;
import com.xiaostudy.domain.Subject;
import org.springframework.stereotype.Service;

import com.xiaostudy.domain.Student;

/**
 * 学生service接口
 * 
 * @author liwei
 * 
 */
public interface StudentService {

	public List<Student> getStudentAll();

	public List<String> getStudentNameList();

	public List<String> getStudentNameList(String gradeName);

	public Student getStudentByStudentNumber(String studentNumber);

	public Student getStudentByStudentName(String studentName);

	public List<Student> getStudentBySex(String sex);

	public List<Student> getStudentByBorn(Date born);

	public List<Student> getStudentByHome(String home);

	public List<Student> getStudentByHomeName(String homeName);

	public Student getStudentByHomeContact(String homeContact);

	public List<Student> getStudentByAdmissionDate(Date admissionDate);

	public List<Student> getStudentByClazzName(String clazzName);

	public List<String> getStudentNumberList();

	public boolean deleteStudent(Student student);

	public boolean deleteStudent(String studentNumber);

	public boolean insertStudent(Student student);

	public boolean updateStudent(Student student);

	public boolean isStudentName(String studentName);

	public boolean isHomeContact(String homeContact);

	public boolean isGradeName(String gradeName);

	public boolean isClazzName(String clazzName);

	public boolean isStudentInClazzName(String clazzName);

	public boolean isStudentInStudentNameClazzName(String studentName, String clazzName);

	public Student setStudentInClazz(Student subject);

	public Student setStudent(String studentNumber, String studentName, String sex, Date born, String home, String homeName, String homeContact, Date admissionDate, String gradeName, String clazzName);

	public Student setStudent(Student student, String gradeName, String clazzName);

}