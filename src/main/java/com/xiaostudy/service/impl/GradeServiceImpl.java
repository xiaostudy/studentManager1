package com.xiaostudy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaostudy.dao.GradeDao;
import com.xiaostudy.domain.Grade;
import com.xiaostudy.service.GradeService;

/**
 * 年级service接口实现类
 * 
 * @author liwei
 *
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;

	@Override
	public List<Grade> getGradeAll() {
		return gradeDao.selectGradeAll();
	}

	@Override
	public Grade getGradeByGradeId(Integer gradeId) {
		if(gradeId == null) {
			return null;
		}
		return gradeDao.selectByPrimaryKey(gradeId);
	}

	@Override
	public Grade getGradeByGradeNumber(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return null;
		}
		return gradeDao.selectByGradeNumber(gradeNumber);
	}

	@Override
	public Grade getGradeByGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return null;
		}
		return gradeDao.selectByGradeName(gradeName);
	}

	@Override
	public List<String> getGradeNumberList() {
		List<Grade> gradeList = getGradeAll();
		List<String> list = new ArrayList<String>();
		if(gradeList == null || gradeList.size() <= 0) {
			return list;
		}
		for(Grade grade : gradeList) {
			if(grade != null && grade.getGradeNumber() != null) {
				list.add(grade.getGradeNumber());
			}
		}
		return list;
	}

	@Override
	public List<String> getGradeNameList() {
		List<Grade> gradeList = getGradeAll();
		List<String> list = new ArrayList<String>();
		if(gradeList == null || gradeList.size() <= 0) {
			return list;
		}

		for(Grade grade : gradeList) {
			if(grade != null && grade.getGradeName() != null && grade.getGradeName().trim().length() > 0) {
				list.add(grade.getGradeName());
			}
		}
		return list;
	}


	@Override
	public boolean deleteGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeId() != null && gradeDao.selectByPrimaryKey(grade.getGradeId()) != null) {
			Integer integer = gradeDao.deleteByPrimaryKey(grade.getGradeId());
			if(integer != 0) {
				return true;
			}
		}

		return deleteGrade(grade.getGradeNumber());
	}

	@Override
	public boolean deleteGrade(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return false;
		}

		if(getGradeByGradeNumber(gradeNumber.trim()) != null) {
            Integer integer = gradeDao.deleteByGradeNumber(gradeNumber.trim());
            if(integer != 0) {
                return true;
            }
        }

        return false;
	}

	@Override
	public boolean insertGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeNumber() == null || grade.getGradeName() == null) {
			return false;
		}

		if(grade.getGradeId() != null && gradeDao.selectByPrimaryKey(grade.getGradeId()) == null) {
			Integer integer = gradeDao.insertGrade(grade);
			if(integer != 0) {
				return true;
			}
		}

		if(grade.getGradeNumber() != null && gradeDao.selectByGradeNumber(grade.getGradeNumber()) == null) {
			Integer integer = gradeDao.insertGradeNoID(grade);
			if(integer != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateGrade(Grade grade) {
		if(grade == null) {
			return false;
		}

		if(grade.getGradeNumber() == null || grade.getGradeName() == null) {
			return false;
		}

		if(grade.getGradeId() != null && gradeDao.selectByPrimaryKey(grade.getGradeId()) != null) {
			Integer integer = gradeDao.updateByPrimaryKey(grade);
			if(integer != 0) {
				return true;
			}
		}
		if(grade.getGradeNumber() != null && gradeDao.selectByGradeNumber(grade.getGradeNumber()) != null){
			Integer integer = gradeDao.updateByGradeNumber(grade);
			if(integer != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isGradeNumber(String gradeNumber) {
		if(gradeNumber == null || gradeNumber.trim().length() <= 0) {
			return false;
		}

		Grade grade = gradeDao.selectByGradeNumber(gradeNumber);
		if(grade != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isGradeName(String gradeName) {
		if(gradeName == null || gradeName.trim().length() <= 0) {
			return false;
		}

		Grade grade = gradeDao.selectByGradeName(gradeName.trim());
		if(grade != null) {
			return true;
		}

		return false;
	}

}
