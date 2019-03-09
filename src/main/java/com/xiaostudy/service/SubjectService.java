package com.xiaostudy.service;

import java.util.List;

import com.xiaostudy.domain.Grade;
import com.xiaostudy.domain.Subject;

/**
 * 学科service接口
 * 
 * @author liwei
 * 
 */
public interface SubjectService {

	public List<Subject> getSubjectAll();

	public List<Integer> getPages();

	public List<Subject> getSubjectPages(Integer i);

	public Subject getSubjectBySubjectId(Integer subjectId);

	public Subject getSubjectBySubjectNumber(String subjectNumber);

	public List<Subject> getSubjectBySubjectName(String subjectName);

	public List<Subject> getSubjectByGradeNumber(String gradeNumber);

	public List<Subject> getSubjectByGradeName(String gradeName);

	public Subject getSubjectByGradeNameSubjectName(String gradeName, String subjectName);

	public List<String> getSubjectNumberList();

	public List<String> getSubjectNameList();

	public boolean deleteSubject(Subject subject);

	public boolean deleteSubject(String subjectName);

	public boolean insertSubject(Subject subject);

	public boolean updateSubject(Subject subject);

	public boolean isSubjectNumber(String subjectNumber);

	public boolean isSubjectName(String subjectName);

	public boolean isGradeName(String gradeName);

	public boolean isSubjectNameInGradeName(String subjectName, String gradeName);

	public Subject setSubjectInGrade(String subjectNumber, String subjectName, String gradeName);

	public Subject setSubjectInGradeToGradeName(Subject subject, String gradeName);

	public boolean equals(Subject oldSubject, Subject newSubject);

	public boolean isSubjectToNULL(Subject subject);

	public Grade getGrade(String gradeName);

}