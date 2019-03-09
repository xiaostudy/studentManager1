package com.xiaostudy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xiaostudy.dao.GradeDao;
import com.xiaostudy.domain.Grade;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaostudy.dao.SubjectDao;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.service.SubjectService;

/**
 * 学科service接口实现类
 * 
 * @author liwei
 * 
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private GradeService gradeService;

	@Override
	public List<Subject> getSubjectAll() {
		return subjectDao.selectSubjectAll();
	}

	@Override
	public List<Integer> getPages() {
		List<Integer> list = new ArrayList<>();
		int j = 1;
		for(int i = 0; i < getSubjectAll().size(); i+=5) {
			list.add(j);
			j++;
		}
		return list;
	}

	@Override
	public List<Subject> getSubjectPages(Integer i) {
		if(i == null) {
			return null;
		}

		return subjectDao.selectSubjectPages((i-1)*5);
	}

	@Override
	public Subject getSubjectBySubjectId(Integer subjectId) {
		if(subjectId == null) {
			return null;
		}
		return subjectDao.selectByPrimaryKey(subjectId);
	}

	@Override
	public Subject getSubjectBySubjectNumber(String subjectNumber) {
		if(subjectNumber == null) {
			return null;
		}
		return subjectDao.selectBySubjectNumber(subjectNumber);
	}

	@Override
	public List<Subject> getSubjectBySubjectName(String subjectName) {
		if (subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}
		return subjectDao.selectBySubjectName(subjectName.trim());
	}

	@Override
	public List<Subject> getSubjectByGradeNumber(String gradeNumber){
		List<Subject> subjectList = new ArrayList<>();
		if(gradeNumber == null) {
			return subjectList;
		}

		for(Subject subject : subjectDao.selectSubjectAll()) {
			if(gradeNumber.equals(subject.getGrade().getGradeNumber())) {
				subjectList.add(subject);
			}
		}

		return subjectList;
	}

	@Override
	public List<Subject> getSubjectByGradeName(String gradeName) {
		List<Subject> subjectList = new ArrayList<>();
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return subjectList;
		}

		for(Subject subject : subjectDao.selectSubjectAll()) {
			if(gradeName.trim().equals(subject.getGrade().getGradeName())) {
				subjectList.add(subject);
			}
		}

		return subjectList;
	}

	@Override
	public Subject getSubjectByGradeNameSubjectName(String gradeName, String subjectName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
			return null;
		}

		List<Subject> subjectList = subjectDao.selectBySubjectName(subjectName.trim());
		for(Subject subject : subjectList) {
			if(subject != null && gradeName.trim().equals(subject.getGrade().getGradeName())) {
				return subject;
			}
		}

		return null;
	}

	@Override
	public List<String> getSubjectNumberList() {
		List<Subject> subjectAll = getSubjectAll();
		List<String> list = new ArrayList<String>();
		for(Subject subject : subjectAll) {
			if(subject != null && subject.getSubjectNumber() != null) {
				list.add(subject.getSubjectNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getSubjectNameList() {
		List<Subject> subjectAll = getSubjectAll();
		List<String> list = new ArrayList<String>();
		for(Subject subject : subjectAll) {
			if(subject != null && subject.getSubjectName() != null && subject.getSubjectName().trim().length() > 0) {
				list.add(subject.getSubjectName());
			}
		}
		return list;
	}

	@Override
	public boolean deleteSubject(Subject subject){
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectId() != null && subjectDao.selectByPrimaryKey(subject.getSubjectId()) != null) {
			Integer integer = subjectDao.deleteByPrimaryKey(subject.getSubjectId());
			if(integer != 0) {
				return true;
			}
		}

		return deleteSubject(subject.getSubjectNumber());
	}

	@Override
	public boolean deleteSubject(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		if(getSubjectBySubjectNumber(subjectName.trim()) != null) {
			Integer integer = subjectDao.deleteBySubjectNumber(subjectName.trim());
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public boolean insertSubject(Subject subject) {
		if (subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGrade() == null) {
			return false;
		}

		if(subject.getSubjectId() != null && subjectDao.selectByPrimaryKey(subject.getSubjectId()) == null) {
			if(subject.getGrade().getGradeId() != null && gradeService.getGradeByGradeId(subject.getGrade().getGradeId()) != null) {
				Integer integer = subjectDao.insert(subject);
				if(integer != 0) {
					return true;
				}
			}
			if(subject.getGrade().getGradeNumber() != null && gradeService.getGradeByGradeNumber(subject.getGrade().getGradeNumber()) != null) {
				subject.setGrade(gradeService.getGradeByGradeNumber(subject.getGrade().getGradeNumber()));
				Integer integer = subjectDao.insert(subject);
				if(integer != 0) {
					return true;
				}
			}
		}

		if(subject.getSubjectNumber() != null && subjectDao.selectBySubjectNumber(subject.getSubjectNumber()) == null) {
			if(subject.getGrade().getGradeId() != null && gradeService.getGradeByGradeId(subject.getGrade().getGradeId()) != null) {
				Integer integer = subjectDao.insertNoID(subject);
				if(integer != 0) {
					return true;
				}
			}
			if(subject.getGrade().getGradeNumber() != null && gradeService.getGradeByGradeNumber(subject.getGrade().getGradeNumber()) != null) {
				subject.setGrade(gradeService.getGradeByGradeNumber(subject.getGrade().getGradeNumber()));
				Integer integer = subjectDao.insertNoID(subject);
				if(integer != 0) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean updateSubject(Subject subject) {
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGrade() == null) {
			return false;
		}

		if(subject.getSubjectId() != null && subjectDao.selectByPrimaryKey(subject.getSubjectId()) != null) {
			Integer integer = subjectDao.updateByPrimaryKey(subject);
			if(integer != 0) {
				return true;
			}
		}

		if(subject.getSubjectNumber() != null && subjectDao.selectBySubjectNumber(subject.getSubjectNumber()) != null) {
			Integer integer = subjectDao.updateBySubjectNumber(subject);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isSubjectNumber(String subjectNumbeer) {
		if(subjectNumbeer == null) {
			return false;
		}

		Subject subject = subjectDao.selectBySubjectNumber(subjectNumbeer);
		if(subject != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectName(String subjectName) {
		if(subjectName == null || subjectName.trim().length() <= 0) {
			return false;
		}

		List<Subject> subjectList = subjectDao.selectBySubjectName(subjectName.trim());
		if(subjectList != null && subjectList.size() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		Grade grade = gradeService.getGradeByGradeName(gradeName.trim());
		if(grade != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectNameInGradeName(String subjectName, String gradeName) {
		if(subjectName == null || subjectName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		List<Subject> subjectList = getSubjectByGradeName(gradeName.trim());
		for(Subject subject : subjectList) {
			if(subject != null && subjectName.trim().equals(subject.getSubjectName())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Subject setSubjectInGrade(String subjectNumber, String subjectName, String gradeName) {
		if(subjectNumber == null || subjectName == null || subjectName.trim().length() <= 0 || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		Subject subject = (Subject) CommonUtil.getBean(Subject.class);
		subject.setSubjectNumber(subjectNumber);
		subject.setSubjectName(subjectName.trim());
		return setSubjectInGradeToGradeName(subject, gradeName);
	}

	@Override
	public Subject setSubjectInGradeToGradeName(Subject subject, String gradeName) {
		if(subject == null || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		if(gradeService.getGradeByGradeName(gradeName.trim()) == null) {
			return null;
		}

		subject.setGrade(gradeService.getGradeByGradeName(gradeName.trim()));
		return subject;
	}

	@Override
	public boolean equals(Subject oldSubject, Subject newSubject) {
		if(isSubjectToNULL(oldSubject) == false || isSubjectToNULL(newSubject) == false) {
			return false;
		}

		if (oldSubject.getSubjectNumber().equals(newSubject.getSubjectNumber()) && oldSubject.getSubjectName().equals(newSubject.getSubjectName()) && oldSubject.getGrade().getGradeId().equals(newSubject.getGrade().getGradeId())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSubjectToNULL(Subject subject) {
		if(subject == null) {
			return false;
		}

		if(subject.getSubjectNumber() == null || subject.getSubjectName() == null || subject.getGrade() == null || subject.getGrade().getGradeId() == null) {
			return false;
		}

		return true;
	}

	@Override
	public Grade getGrade(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		return gradeService.getGradeByGradeName(gradeName.trim());
	}

}
