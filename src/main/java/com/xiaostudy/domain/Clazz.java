package com.xiaostudy.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 班级domain类
 *
 * @author liwei
 */
@Component
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clazzId;
    private String clazzNumber;
    private String clazzName;
    private Grade grade;
    private Teacher teacher;

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzId=" + clazzId +
                ", clazzNumber='" + clazzNumber + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", grade=" + grade +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Clazz clazz = (Clazz) o;
        if(clazzId != null && clazz.clazzId != null) {
            return Objects.equals(clazzId, clazz.clazzId) &&
                    Objects.equals(clazzNumber, clazz.clazzNumber) &&
                    Objects.equals(clazzName, clazz.clazzName) &&
                    grade.equals(clazz.grade) &&
                    teacher.equals(clazz.teacher);
        }
        return Objects.equals(clazzNumber, clazz.clazzNumber) &&
                Objects.equals(clazzName, clazz.clazzName) &&
                grade.equals(clazz.grade) &&
                teacher.equals(clazz.teacher);
    }

}
