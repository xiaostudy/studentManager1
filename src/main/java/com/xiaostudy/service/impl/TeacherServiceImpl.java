package com.xiaostudy.service.impl;

import com.xiaostudy.dao.SubjectDao;
import com.xiaostudy.dao.TeacherDao;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.domain.Teacher;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TeacherService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 教师service接口实现类
 *
 * @author liwei
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GradeService gradeService;

    @Override
    public List<Teacher> getTeacherAll() {
        List<Teacher> teacherList = teacherDao.selectTeacherAll();
        for (Teacher teacher : teacherList) {
            teacher = setSubject(teacher);
        }
        return teacherList;
    }

    @Override
    public Teacher getTeacherByTeacherNumber(String teacherNumber) {
        if (teacherNumber == null || teacherNumber.trim().length() <= 0) {
            return null;
        }

        return setSubject(teacherDao.selectByTeacherNumber(teacherNumber));
    }

    @Override
    public Teacher getTeacherByTeacherName(String teacherName) {
        if (teacherName == null || teacherName.trim().length() <= 0) {
            return null;
        }

        return setSubject(teacherDao.selectByTeacherName(teacherName.trim()));
    }

    @Override
    public List<Teacher> getTeacherBySex(String sex) {
        if (sex == null || sex.trim().length() <= 0) {
            return null;
        }

        List<Teacher> teacherList = teacherDao.selectBySex(sex.trim());
        for (Teacher teacher : teacherList) {
            teacher = setSubject(teacher);
        }

        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherByBorn(Date born) {
        if (born == null) {
            return null;
        }

        List<Teacher> teacherList = teacherDao.selectByBorn(born);
        for (Teacher teacher : teacherList) {
            teacher = setSubject(teacher);
        }

        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherByHome(String home) {
        if (home == null || home.trim().length() <= 0) {
            return null;
        }

        List<Teacher> teacherList = teacherDao.selectByHome(home.trim());
        for (Teacher teacher : teacherList) {
            teacher = setSubject(teacher);
        }

        return teacherList;
    }

    @Override
    public Teacher getTeacherByContact(String contact) {
        if (contact == null || contact.trim().length() <= 0) {
            return null;
        }

        return setSubject(teacherDao.selectByContact(contact.trim()));
    }

    @Override
    public List<Teacher> getTeacherByEntryDate(Date entryDate) {
        if (entryDate == null) {
            return null;
        }

        List<Teacher> teacherList = teacherDao.selectByEntryDate(entryDate);
        for (Teacher teacher : teacherList) {
            teacher = setSubject(teacher);
        }

        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherByGradeNameSubjectName(String gradeName, String subjectName) {
        if (gradeName == null || gradeName.trim().length() <= 0 || subjectName == null || subjectName.trim().length() <= 0) {
            return null;
        }

        Subject subject = subjectService.getSubjectByGradeNameSubjectName(gradeName, subjectName);
        return getTeacherBySubject(subject);
    }

    @Override
    public List<Teacher> getTeacherBySubject(Subject subject) {
        if (subject == null) {
            return null;
        }

        List<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : getTeacherAll()) {
            if (teacher != null && subject.equals(teacher.getSubject())) {
                teacherList.add(setSubject(teacher));
            }
        }

        return teacherList;
    }

    @Override
    public List<String> getTeacherNumberList() {
        List<String> list = new ArrayList<String>();
        for (Teacher teacher : getTeacherAll()) {
            if (teacher != null) {
                list.add(teacher.getTeacherNumber());
            }
        }
        return list;
    }

    @Override
    public List<String> getTeacherNameList() {
        List<String> list = new ArrayList<>();
        for (Teacher teacher : getTeacherAll()) {
            if (teacher != null) {
                list.add(teacher.getTeacherName());
            }
        }
        return list;
    }

    @Override
    public List<String> getTeacherNameListByGradeName(String gradeName) {
        if(gradeName == null || gradeName.trim().length() <= 0 || isGradeName(gradeName) == false) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (Teacher teacher : getTeacherAll()) {
            if (teacher != null && teacher.getSubject() != null && teacher.getSubject().getGrade() != null && teacher.getSubject().getGrade().getGradeName().equals(gradeName.trim())) {
                list.add(teacher.getTeacherName());
            }
        }
        return list;
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        if (teacher == null) {
            return false;
        }

        if (teacher.getTeacherId() != null && teacherDao.selectByPrimaryKey(teacher.getTeacherId()) != null) {
            Integer integer = teacherDao.deleteByPrimaryKey(teacher.getTeacherId());
            if (integer != 0) {
                return true;
            }
        }

        if (teacher.getTeacherNumber() != null && teacherDao.selectByTeacherNumber(teacher.getTeacherNumber()) != null) {
            Integer integer = teacherDao.deleteByTeacherNumber(teacher.getTeacherNumber());
            if (integer != 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteTeacher(String teacherNumber) {
        if(teacherNumber == null || teacherNumber.trim().length() <= 0) {
            return false;
        }

        if(getTeacherByTeacherNumber(teacherNumber.trim()) != null) {
            Integer integer = teacherDao.deleteByTeacherNumber(teacherNumber.trim());
            if(integer != 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        if (isTeacherInNULL(teacher) == false) {
            return false;
        }

        if (teacher.getTeacherId() != null && teacherDao.selectByPrimaryKey(teacher.getTeacherId()) == null) {
            Integer integer = teacherDao.insert(teacher);
            if (integer != 0) {
                return true;
            }
        }

        if (teacher.getTeacherNumber() != null && teacherDao.selectByTeacherNumber(teacher.getTeacherNumber()) == null) {
            Integer integer = teacherDao.insertNoID(teacher);
            if (integer != 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        if (isTeacherInNULL(teacher) == false) {
            return false;
        }

        if (teacher.getTeacherId() != null && teacherDao.selectByPrimaryKey(teacher.getTeacherId()) != null) {
            Integer integer = teacherDao.updateByPrimaryKey(teacher);
            if (integer != 0) {
                return true;
            }
        }

        if (teacher.getTeacherNumber() != null && teacherDao.selectByTeacherNumber(teacher.getTeacherNumber()) != null) {
            Integer integer = teacherDao.updateByTeacherNumber(teacher);
            if (integer != 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isTeacherNumber(String teacherNumber) {
        if (teacherNumber == null || teacherNumber.trim().length() <= 0) {
            return false;
        }

        if (getTeacherByTeacherNumber(teacherNumber) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isTeacherName(String teacherName) {
        if (teacherName == null || teacherName.trim().length() <= 0) {
            return false;
        }

        if (teacherDao.selectByTeacherName(teacherName.trim()) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isSubjectName(String subjectName) {
        if (subjectName == null || subjectName.trim().length() <= 0) {
            return false;
        }

        if (subjectService.getSubjectBySubjectName(subjectName.trim()) != null && subjectService.getSubjectBySubjectName(subjectName.trim()).size() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isTeacherContact(String teacherContact) {
        if (teacherContact == null || teacherContact.trim().length() <= 0) {
            return false;
        }

        if (teacherDao.selectByContact(teacherContact.trim()) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isTeacherNameToSubjectName(Teacher teacher, String subjectName) {
        if (teacher == null || subjectName == null || subjectName.trim().length() <= 0) {
            return false;
        }

        if (subjectName.trim().equals(teacher.getSubject().getSubjectName())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isTeacherInNULL(Teacher teacher) {
        if (teacher == null) {
            return false;
        }

        if (teacher.getTeacherNumber() == null || teacher.getTeacherName() == null || teacher.getSex() == null || teacher.getEntryDate() == null || teacher.getHome() == null || teacher.getContact() == null || teacher.getEntryDate() == null || teacher.getSubject() == null || teacher.getSubject().getSubjectId() == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Teacher oldTeacher, Teacher newTeacher) {
        if (isTeacherInNULL(oldTeacher) == false || isTeacherInNULL(newTeacher) == false) {
            return false;
        }

        if (oldTeacher.getTeacherNumber().equals(newTeacher.getTeacherNumber()) && oldTeacher.getTeacherName().equals(newTeacher.getTeacherName()) && oldTeacher.getSex().equals(newTeacher.getSex()) && oldTeacher.getBorn().compareTo(newTeacher.getBorn()) == 0 && oldTeacher.getHome().equals(newTeacher.getHome()) && oldTeacher.getContact().equals(newTeacher.getContact()) && oldTeacher.getEntryDate().compareTo(newTeacher.getEntryDate()) == 0 && oldTeacher.getSubject().getSubjectId().equals(newTeacher.getSubject().getSubjectId())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isSubject(String gradeName, String subjectName) {
        if (subjectService.getSubjectByGradeNameSubjectName(gradeName, subjectName) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isGradeName(String gradeName) {
        if(gradeName == null || gradeName.trim().length() <= 0) {
            return false;
        }

        return gradeService.isGradeName(gradeName.trim());
    }

    @Override
    public Teacher setSubject(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        if (teacher.getSubject() != null) {
            teacher.setSubject(subjectService.getSubjectBySubjectNumber(teacher.getSubject().getSubjectNumber()));
        }

        return teacher;
    }

    @Override
    public Teacher setTeacherInSubject(Teacher teacher, Subject subject) {
        if (teacher == null || subject == null || subject.getGrade() == null) {
            return null;
        }

        teacher.setSubject(subject);
        return teacher;
    }

    @Override
    public Teacher setTeacherInSubjectByGradeNameSubjectName(Teacher teacher, String gradeName, String subjectName) {
        Subject subject = subjectService.getSubjectByGradeNameSubjectName(gradeName, subjectName);
        return setTeacherInSubject(teacher, subject);
    }

    @Override
    public Teacher setTeacher(String teacherNumber, String teacherName, String sex, Date born, String home, String contact, Date entryDate, String gradeName, String subjectName) {
        if (teacherNumber == null || teacherNumber.trim().length() <= 0 ||
                teacherName == null || teacherName.trim().length() <= 0 || teacherName.trim().length() > 40 ||
                sex == null || sex.trim().length() <= 0 ||
                born == null ||
                home == null || home.trim().length() <= 0 ||
                contact == null || contact.trim().length() <= 0 ||
                entryDate == null) {
            return null;
        }

        Teacher teacher = (Teacher) CommonUtil.getBean(Teacher.class);
        teacher.setTeacherNumber(teacherNumber);
        teacher.setTeacherName(teacherName.trim());
        teacher.setSex(sex.trim());
        teacher.setBorn(born);
        teacher.setHome(home.trim());
        teacher.setContact(contact.trim());
        teacher.setEntryDate(entryDate);
        return setTeacherInSubjectByGradeNameSubjectName(teacher, gradeName, subjectName);
    }

}
