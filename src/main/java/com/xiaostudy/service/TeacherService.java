package com.xiaostudy.service;

import java.util.Date;
import java.util.List;

import com.xiaostudy.domain.Subject;
import com.xiaostudy.domain.Teacher;

/**
 * 教师service接口
 * 
 * @author liwei
 *
 */
public interface TeacherService {

	public List<Teacher> getTeacherAll();

	public Teacher getTeacherByTeacherNumber(String teacherNumber);

	public Teacher getTeacherByTeacherName(String teacherName);

	public List<Teacher> getTeacherBySex(String sex);

	public List<Teacher> getTeacherByBorn(Date born);

	public List<Teacher> getTeacherByHome(String home);

	public Teacher getTeacherByContact(String contact);

	public List<Teacher> getTeacherByEntryDate(Date entryDate);

	public List<Teacher> getTeacherByGradeNameSubjectName(String gradeName, String subjectName);

	public List<Teacher> getTeacherBySubject(Subject subject);

	public List<String> getTeacherNumberList();

	public List<String> getTeacherNameList();

	public List<String> getTeacherNameListByGradeName(String gradeName);

	public boolean deleteTeacher(Teacher teacher);

	public boolean deleteTeacher(String teacherNumber);

	public boolean insertTeacher(Teacher teacher);

	public boolean updateTeacher(Teacher teacher);

	public boolean isTeacherNumber(String teacherNumber);

	public boolean isTeacherName(String teacherName);

	public boolean isSubjectName(String subjectName);

	public boolean isTeacherContact(String teacherContact);

	public boolean isTeacherNameToSubjectName(Teacher teacher, String subjectName);

	public boolean isTeacherInNULL(Teacher teacher);

	public boolean equals(Teacher oldTeacher, Teacher newTeacher);

	public boolean isSubject(String gradeName, String subjectName);

	public boolean isGradeName(String gradeName);

	public Teacher setSubject(Teacher teacher);

	public Teacher setTeacherInSubject(Teacher teacher, Subject subject);

	public Teacher setTeacherInSubjectByGradeNameSubjectName(Teacher teacher, String gradeName, String subjectName);

	public Teacher setTeacher(String teacherNumber, String teacherName, String sex, Date born, String home, String contact, Date entryDate, String gradeName, String subjectName);

}
