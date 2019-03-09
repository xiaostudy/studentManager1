package com.xiaostudy.service.impl;

import com.xiaostudy.dao.ClazzDao;
import com.xiaostudy.domain.Clazz;
import com.xiaostudy.domain.Grade;
import com.xiaostudy.domain.Teacher;
import com.xiaostudy.service.ClazzService;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.service.TeacherService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级service接口实现类
 * 
 * @author liwei
 *
 */
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzDao clazzDao;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private TeacherService teacherService;

	@Override
	public List<Clazz> getClazzAll() {
		List<Clazz> clazzAll = clazzDao.selectClazzAll();
		for(Clazz clazz : clazzAll) {
			if(clazz != null && clazz.getTeacher() != null) {
				clazz = setClazzInTeacher(clazz);
			}
		}

		return clazzAll;
	}

	@Override
	public Clazz getClazzByClazzId(Integer clazzId) {
		if(clazzId == null) {
			return null;
		}

		return setClazzInTeacher(clazzDao.selectByPrimaryKey(clazzId));
	}

	@Override
	public Clazz getClazzByClazzNumber(String clazzNumber) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0) {
			return null;
		}

		return setClazzInTeacher(clazzDao.selectByClazzNumber(clazzNumber.trim()));
	}

	@Override
	public List<Clazz> getClazzByClazzName(String clazzName) {
		if(clazzName == null || clazzName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = clazzDao.selectByClazzName(clazzName);
		for(Clazz clazz : clazzList) {
			if(clazz != null && clazz.getTeacher() != null) {
				clazz = setClazzInTeacher(clazz);
			}
		}

		return clazzList;
	}

	@Override
	public List<Clazz> getClazzByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = new ArrayList<>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getGrade() != null && gradeName.trim().equals(clazz.getGrade().getGradeName())) {
				clazzList.add(clazz);
			}
		}


		return clazzList;
	}

	@Override
	public List<Clazz> getClazzByTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		List<Clazz> clazzList = new ArrayList<>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getTeacher() != null && teacherName.trim().equals(clazz.getTeacher().getTeacherName())) {
				clazzList.add(clazz);
			}
		}

		return clazzList;
	}

	@Override
	public List<String> getClazzNumberList() {
		List<String> list = new ArrayList<String>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getClazzNumber() != null && clazz.getClazzNumber().trim().length() > 0) {
				list.add(clazz.getClazzNumber());
			}
		}

		return list;
	}

	@Override
	public List<String> getClazzNameList() {
		List<String> list = new ArrayList<String>();
		for(Clazz clazz : getClazzAll()) {
			if(clazz != null && clazz.getClazzName() != null && clazz.getClazzName().trim().length() > 0) {
				list.add(clazz.getClazzName());
			}
		}

		return list;
	}


	@Override
	public boolean deleteClazz(Clazz clazz) {
		if(clazz == null) {
			return false;
		}

		if(clazz.getClazzId() != null && getClazzByClazzId(clazz.getClazzId()) != null) {
			Integer integer = clazzDao.deleteByPrimaryKey(clazz.getClazzId());
			if(integer != 0) {
				return true;
			}
		}

		return deleteClazz(clazz.getClazzNumber());
	}

	@Override
	public boolean deleteClazz(String clazzNumber) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0) {
			return false;
		}

		if(getClazzByClazzNumber(clazzNumber.trim()) != null) {
			Integer integer = clazzDao.deleteByClazzNumber(clazzNumber.trim());
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean insertClazz(Clazz clazz) {
		if(isClazz(clazz) == false) {
			return false;
		}

		if(clazz.getClazzId() != null && getClazzByClazzId(clazz.getClazzId()) == null) {
			Integer integer = clazzDao.insert(clazz);
			if(integer != 0) {
				return true;
			}
		}

		if(getClazzByClazzNumber(clazz.getClazzNumber()) == null) {
			Integer integer = clazzDao.insertNoID(clazz);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateClazz(Clazz clazz) {
		if(isClazz(clazz) == false) {
			return false;
		}

		if(clazz.getClazzId() != null && getClazzByClazzId(clazz.getClazzId()) != null) {
			Integer integer = clazzDao.updateByPrimaryKey(clazz);
			if(integer != 0) {
				return true;
			}
		}

		if(getClazzByClazzNumber(clazz.getClazzNumber()) != null) {
			Integer integer = clazzDao.updateByClazzNumber(clazz);
			if(integer != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isClazzInGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		return getClazzByGradeName(gradeName.trim()).size() > 0;
	}

	@Override
	public boolean isClazzInTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return false;
		}

		return getClazzByTeacherName(teacherName.trim()).size() > 0;
	}

	@Override
	public boolean isClazzInGradeNameClazzName(String gradeName, String clazzName) {
		if(gradeName == null || gradeName.trim().length() <= 0 || clazzName == null || clazzName.trim().length() <= 0) {
			return false;
		}

		for(Clazz clazz : getClazzByGradeName(gradeName.trim())) {
			if(clazz != null && clazz.getClazzName().equals(clazzName.trim())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isClazzInGradeNameTeacherName(String gradeName, String teacherName) {
		if(isClazzInGradeName(gradeName) && teacherName != null && teacherName.trim().length() > 0) {
			List<Clazz> clazzList = getClazzByGradeName(gradeName.trim());
			for(Clazz clazz : clazzList) {
				if(clazz != null && clazz.getGrade() != null && clazz.getTeacher() != null && teacherName.trim().equals(clazz.getTeacher().getTeacherName())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		List<String> gradeNameList = gradeService.getGradeNameList();
		if(gradeNameList != null && gradeNameList.size() > 0){
			return gradeNameList.contains(gradeName.trim());
		}

		return false;
	}

	@Override
	public boolean isTeacherName(String teacherName) {
		if(teacherName == null || teacherName.trim().length() <= 0) {
			return false;
		}

		List<String> teacherNameList = teacherService.getTeacherNameList();
		if(teacherNameList != null && teacherNameList.size() > 0) {
			return teacherNameList.contains(teacherName.trim());
		}

		return false;
	}

	@Override
	public boolean isClazz(Clazz clazz) {
		if(clazz == null ||
				clazz.getClazzNumber() == null || clazz.getClazzNumber().trim().length() <= 0 ||
				clazz.getClazzName() == null || clazz.getClazzName().trim().length() <= 0 ||
				clazz.getGrade() == null ||
				clazz.getTeacher() == null) {
			return false;
		}

		return true;
	}

	@Override
	public Clazz setClazzInTeacher(Clazz clazz) {
		if(clazz == null || clazz.getTeacher() == null) {
			return clazz;
		}

		clazz.setTeacher(teacherService.getTeacherByTeacherNumber(clazz.getTeacher().getTeacherNumber()));
		return clazz;
	}

	@Override
	public Clazz setClazzInTeacherName(Clazz clazz, String teacherName) {
		if(clazz == null || teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		if(teacherService.getTeacherByTeacherName(teacherName.trim()) != null) {
			clazz.setTeacher(teacherService.getTeacherByTeacherName(teacherName.trim()));
			return clazz;
		}

		return null;
	}

	@Override
	public Clazz setClazzInGradeName(Clazz clazz, String gradeName) {
		if(clazz == null || gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}

		if(gradeService.getGradeByGradeName(gradeName.trim()) != null) {
			clazz.setGrade(gradeService.getGradeByGradeName(gradeName.trim()));
			return clazz;
		}

		return null;
	}

	@Override
	public Clazz setClazz(String clazzNumber, String clazzName, String gradeName, String teacherName) {
		if(clazzNumber == null || clazzNumber.trim().length() <= 0 ||
				clazzName == null || clazzName.trim().length() <= 0 ||
				gradeName == null || gradeName.trim().length() <= 0 ||
				teacherName == null || teacherName.trim().length() <= 0) {
			return null;
		}

		Grade grade = gradeService.getGradeByGradeName(gradeName.trim());
		Teacher teacher = teacherService.getTeacherByTeacherName(teacherName.trim());
		if(grade != null && teacher != null) {
			Clazz clazz = (Clazz)CommonUtil.getBean(Clazz.class);
			clazz.setClazzNumber(clazzNumber.trim());
			clazz.setClazzName(clazzName.trim());
			clazz.setGrade(grade);
			clazz.setTeacher(teacher);
			return clazz;
		}

		return null;
	}

}
