package com.xiaostudy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xiaostudy.dao.SubjectDao;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaostudy.dao.TestDao;
import com.xiaostudy.domain.Test;
import com.xiaostudy.service.TestService;

/**
 * 考试service接口实现类
 * 
 * @author liwei
 *
 */
@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Autowired
	private SubjectService subjectService;

	@Override
	public List<Test> getTestAll() {
		List<Test> testAll = testDao.selectTestAll();
		for(Test test : testAll) {
			if(test != null && test.getSubject() != null) {
				test = setTestInSubject(test);
			}
		}

		return testAll;
	}

	@Override
	public Test getTestByTestId(Integer testId) {
		if(testId == null) {
			return null;
		}

		return setTestInSubject(testDao.selectByPrimaryKey(testId));
	}

	@Override
	public Test getTestByTestNumber(String testNumber) {
		if(testNumber == null || testNumber.trim().length() <= 0) {
			return null;
		}

		return setTestInSubject(testDao.selectByTestNumber(testNumber.trim()));
	}

	@Override
	public List<Test> getTestByTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Test> testList = testDao.selectByTestName(testName.trim());
		for(Test test : testList) {
			if(test != null && test.getSubject() != null) {
				test = setTestInSubject(test);
			}
		}

		return testList;
	}

	@Override
	public List<Test> getTestByGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Test> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getGrade().getGradeName().equals(gradeName.trim()) && test.getSubject().getSubjectName().equals(subjectName.trim())) {
				list.add(test);
			}
		}
		return list;
	}

	@Override
	public List<String> getTestNameByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getGrade() != null && test.getSubject().getGrade().getGradeName().equals(gradeName.trim()) && list.contains(test.getTestName()) == false) {
				list.add(test.getTestName());
			}
		}

		return list;
	}

	@Override
	public List<String> getSubjectNameByGradeNameTestName(String gradeName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				testName == null || testName.trim().length() <= 0 ||
				isGradeName(gradeName.trim()) == false || isTestName(testName.trim()) == false) {
			return null;
		}

		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getGrade() != null && test.getSubject().getGrade().getGradeName().equals(gradeName.trim()) && test.getTestName().equals(testName.trim())) {
				list.add(test.getSubject().getSubjectName());
			}
		}

		return list;
	}

	@Override
	public List<String> getTestNumberList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getTestNumber() != null && test.getTestNumber().trim().length() > 0) {
				list.add(test.getTestNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getTestNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getTestName() != null && test.getTestName().trim().length() > 0 && list.contains(test.getTestName()) == false) {
				list.add(test.getTestName());
			}
		}
		return list;
	}

	@Override
	public List<String> getSubjectNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getSubjectName() != null && test.getSubject().getSubjectName().trim().length() > 0) {
				list.add(test.getSubject().getSubjectName());
			}
		}

		return list;
	}

	@Override
	public List<String> getGradeNameList() {
		List<String> list = new ArrayList<>();
		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getGrade() != null && list.contains(test.getSubject().getGrade().getGradeName()) == false) {
				list.add(test.getSubject().getGrade().getGradeName());
			}
		}

		return list;
	}

	@Override
	public boolean deleteTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) != null) {
			Integer integer = testDao.deleteByPrimaryKey(test.getTestId());
			if(integer != 0) {
				return true;
			}
		}

		return deleteTest(test.getTestNumber());
	}

	@Override
	public boolean deleteTest(String testNumber) {
		if(testNumber == null || testNumber.trim().length() <= 0) {
			return false;
		}

		if(getTestByTestNumber(testNumber.trim()) != null) {
			Integer integer = testDao.deleteByTestNumber(testNumber.trim());
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean insertTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) == null &&
				test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) == null &&
				test.getSubject() != null && isSubjectName(test.getSubject().getSubjectName())) {
			Integer integer = testDao.insert(test);
			if(integer != 0) {
				return true;
			}
		}

		if(test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) == null &&
				test.getSubject() != null && isSubjectName(test.getSubject().getSubjectName())) {
			Integer integer = testDao.insertNoID(test);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateTest(Test test) {
		if(test == null) {
			return false;
		}

		if(test.getTestId() != null && getTestByTestId(test.getTestId()) != null) {
			Integer integer = testDao.updateByPrimaryKey(test);
			if(integer != 0) {
				return true;
			}
		}

		if(test.getTestNumber() != null && getTestByTestNumber(test.getTestNumber()) != null) {
			Integer integer = testDao.updateByTestNumber(test);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Test setTestInSubject(Test test) {
		if(test == null) {
			return null;
		}

		if(test.getSubject() != null && test.getSubject().getSubjectNumber() != null && subjectService.getSubjectBySubjectNumber(test.getSubject().getSubjectNumber()) != null) {
			test.setSubject(subjectService.getSubjectBySubjectNumber(test.getSubject().getSubjectNumber()));
		}

		return test;
	}

	@Override
	public boolean isTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		return getTestNameList().contains(testName.trim());
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return subjectService.getGrade(gradeName.trim()) != null;
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		return subjectService.getSubjectNameList().contains(subjectName.trim());
	}

	@Override
	public boolean isTestInGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		for(Test test : getTestAll()) {
			if(test != null && test.getSubject() != null && test.getSubject().getSubjectName().equals(subjectName.trim()) && test.getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isTestNameGradeNameSubjectName(String testName, String gradeName, String subjectName) {
		if(testName == null || testName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		List<Test> testList = getTestByTestName(testName.trim());
		if(testList != null && testList.size() > 0) {
			for(Test test : testList) {
				if(test != null && test.getSubject() != null && test.getSubject().getSubjectName().equals(subjectName.trim()) && test.getSubject().getGrade() != null && test.getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Test setTest(String testNumber, String testName, String gradeName, String subjectName) {
		if(testNumber == null || testNumber.trim().length() <= 0 || testName == null || testName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		Test test = (Test) CommonUtil.getBean(Test.class);
		test.setTestNumber(testNumber.trim());
		test.setTestName(testName.trim());
		return setTest(test, gradeName, subjectName);
	}

	@Override
	public Test setTest(Test test, String gradeName, String subjectName) {
		if(test == null || gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		Subject subject = subjectService.getSubjectByGradeNameSubjectName(gradeName.trim(), subjectName.trim());
		if(subject != null) {
			test.setSubject(subject);
			return test;
		}

		return null;
	}
}
