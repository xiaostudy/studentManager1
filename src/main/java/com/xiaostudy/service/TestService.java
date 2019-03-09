package com.xiaostudy.service;

import java.util.List;

import com.xiaostudy.domain.Test;

/**
 * 考试service接口
 * 
 * @author liwei
 *
 */
public interface TestService {
	
	public List<Test> getTestAll();
	
	public Test getTestByTestId(Integer testId);

	public Test getTestByTestNumber(String testNumber);

	public List<Test> getTestByTestName(String testName);
	
	public List<Test> getTestByGradeNameSubjectName(String gradeName, String subjectName);

	public List<String> getTestNameByGradeName(String gradeName);

	public List<String> getSubjectNameByGradeNameTestName(String gradeName, String testName);

	public List<String> getTestNumberList();

	public List<String> getTestNameList();

	public List<String> getSubjectNameList();

	public List<String> getGradeNameList();

	public boolean deleteTest(Test test);

	public boolean deleteTest(String testNumber);

	public boolean insertTest(Test test);
	
	public boolean updateTest(Test test);

	public Test setTestInSubject(Test test);

	public boolean isTestName(String testName);

	public boolean isGradeName(String gradeName);

	public boolean isSubjectName(String subjectName);

	public boolean isTestInGradeNameSubjectName(String gradeName, String subjectName);

	public boolean isTestNameGradeNameSubjectName(String testName, String gradeName, String subjectName);

	public Test setTest(String testNumber, String testName, String gradeName, String subjectName);

	public Test setTest(Test test, String gradeName, String subjectName);

}