package com.xiaostudy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xiaostudy.domain.Student;
import com.xiaostudy.domain.Test;
import com.xiaostudy.service.StudentService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TestService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaostudy.dao.ResultsDao;
import com.xiaostudy.domain.Results;
import com.xiaostudy.service.ResultsService;

/**
 * 成绩service接口实现类
 * 
 * @author liwei
 *
 */
@Service("resultsService")
public class ResultsServiceImpl implements ResultsService {

	@Autowired
	private ResultsDao resultsDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TestService testService;

	@Autowired
	private SubjectService subjectService;

	@Override
	public List<Results> getResultsAll() {
		List<Results> list = resultsDao.selectResultsAll();
		for(Results results : list) {
			if(results != null && results.getStudent() != null && results.getTest() != null ) {
				results = setResultsInStudentTestSubject(results);
			}
		}
		return list;
	}

	@Override
	public Results getResultsByResultsId(Integer resultsId) {
		if(resultsId == null) {
			return null;
		}

		return setResultsInStudentTestSubject(resultsDao.selectByPrimaryKey(resultsId));
	}

	@Override
	public Results getResultsByResultsNumber(String resultsNumber) {
		if(resultsNumber == null || resultsNumber.trim().length() <= 0) {
			return null;
		}

		return setResultsInStudentTestSubject(resultsDao.selectByResultsNumber(resultsNumber.trim()));
	}

	@Override
	public List<Results> getResultsByStudentName(String gradeName, String studentName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getTest().getSubject().getSubjectName().equals(studentName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsByTestName(String gradeName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getTest().getTestName().equals(testName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}
		return list;
	}

	@Override
	public List<Results> getResultsByStudentNameTestName(String gradeName, String studentName, String testName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getTest().getTestName().equals(testName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getStudent().getClazz().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getStudent().getStudentName().equals(studentName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsBySubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getTest().getSubject().getSubjectName().equals(subjectName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
				list.add(results);
			}
		}
		return list;
	}

	@Override
	public List<Results> getResultsByStudentNameSubjectName(String gradeName, String studentName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || studentName == null || studentName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getStudent().getStudentName().equals(studentName.trim()) &&
					results.getStudent().getClazz().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getTest().getSubject().getSubjectName().equals(subjectName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public List<Results> getResultsByTestNameSubjectName(String gradeName, String testName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || testName == null || testName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Results> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getTest().getTestName().equals(testName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getTest().getSubject().getSubjectName().equals(subjectName.trim())) {
				list.add(results);
			}
		}

		return list;
	}

	@Override
	public Results getResultsByStudentNameTestNameSubjectName(String gradeName, String studentName, String testName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				studentName == null || studentName.trim().length() <= 0 || 
				testName == null || testName.trim().length() <= 0 || 
				subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getTest() != null &&
					results.getStudent().getStudentName().equals(studentName.trim()) &&
					results.getStudent().getClazz().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getTest().getTestName().equals(testName.trim()) &&
					results.getTest().getSubject().getGrade().getGradeName().equals(gradeName.trim()) &&
					results.getTest().getSubject().getSubjectName().equals(subjectName.trim())) {
				return results;
			}
		}

		return null;
	}

	@Override
	public List<Results> getResultsByScore(Integer score) {
		if(score == null) {
			return null;
		}

		List<Results> list = resultsDao.selectByScore(score);
		for(Results results : list) {
			results = setResultsInStudentTestSubject(results);
		}

		return list;
	}

	@Override
	public List<Integer> getResultsIdList() {
		List<Integer> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			list.add(results.getResultsId());
		}

		return list;
	}

	@Override
	public List<String> getResultsNumberList() {
		List<String> list = new ArrayList<>();
		for(Results results : getResultsAll()) {
			list.add(results.getResultsNumber());
		}

		return list;
	}

	@Override
	public boolean deleteResults(Results results) {
		if(results == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) != null) {
			Integer integer = resultsDao.deleteByPrimaryKey(results.getResultsId());
			if(integer != 0) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) != null) {
			return deleteResults(results.getResultsNumber());
		}

		return false;
	}

	@Override
	public boolean deleteResults(String resultsNumber) {
		if(resultsNumber == null || resultsNumber.trim().length() <= 0) {
			return false;
		}
		Integer integer = resultsDao.deleteByResultsNumber(resultsNumber.trim());
		if(integer != 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean insert(Results results) {
		if(results == null || results.getStudent() == null || results.getTest() == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) == null) {
			Integer integer = resultsDao.insert(results);
			if(integer != 0) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) == null) {
			Integer integer = resultsDao.insertNoID(results);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateResults(Results results) {
		if(results == null || results.getStudent() == null || results.getTest() == null) {
			return false;
		}

		if(results.getResultsId() != null && getResultsByResultsId(results.getResultsId()) != null) {
			Integer integer = resultsDao.updateByPrimaryKey(results);
			if(integer != 0) {
				return true;
			}
		}

		if(results.getResultsNumber() != null && getResultsByResultsNumber(results.getResultsNumber()) != null) {
			Integer integer = resultsDao.updateByResultsNumber(results);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isStudentName(String studentName) {
		if(studentName == null || studentName.trim().length() <= 0) {
			return false;
		}

		return studentService.isStudentName(studentName.trim());
	}

	@Override
	public boolean isTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		return testService.isTestName(testName.trim());
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		return subjectService.isSubjectName(subjectName.trim());
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return subjectService.isGradeName(gradeName.trim());
	}

	@Override
	public boolean isResultsInStudentName(String studentName) {
		if(studentName == null || studentName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && results.getStudent() != null && results.getStudent().getStudentName().equals(studentName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInTestName(String testName) {
		if(testName == null || testName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && results.getTest() != null && results.getTest().getTestName().equals(testName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if (results != null && results.getTest().getSubject() != null && results.getTest().getSubject().getSubjectName().equals(subjectName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isResultsInGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		for(Results results : getResultsAll()) {
			if(results != null && results.getStudent() != null && results.getStudent().getClazz() != null && results.getStudent().getClazz().getGrade() != null && results.getStudent().getClazz().getGrade().getGradeName().equals(gradeName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Results setResultsInStudentTestSubject(Results results) {
		if(results == null) {
			return null;
		}

		if(results.getStudent() != null && results.getTest() != null) {
			results.setStudent(studentService.getStudentByStudentNumber(results.getStudent().getStudentNumber()));
			results.setTest(testService.getTestByTestNumber(results.getTest().getTestNumber()));
			return results;
		}

		return null;
	}

	@Override
	public Results setResults(String resultsNumber, String gradeName, String studentName, String testName, String subjectName, Integer score) {
		if(gradeName == null || gradeName.trim().length() <= 0 ||
				resultsNumber == null || resultsNumber.trim().length() <= 0 ||
				studentName == null || studentName.trim().length() <= 0 ||
				testName == null || testName.trim().length() <= 0 ||
				subjectName == null || subjectName.trim().length() <= 0 || score == null) {
			return null;
		}

		Results results = (Results) CommonUtil.getBean(Results.class);
		results.setResultsNumber(resultsNumber.trim());
		results.setScore(score);
		Student student = studentService.getStudentByStudentName(studentName.trim());
		System.out.println(student);
		if(student != null && student.getClazz().getGrade().getGradeName().equals(gradeName.trim())) {
			results.setStudent(student);
		} else {
			return null;
		}

		for(Test test : testService.getTestByTestName(testName.trim())) {
			if(test != null && test.getSubject() != null && test.getSubject().getSubjectName().equals(subjectName.trim()) && test.getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
				results.setTest(test);
				return results;
			}
		}

		return null;
	}
}
