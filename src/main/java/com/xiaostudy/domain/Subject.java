package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 学科domain类
 *
 * @author liwei
 */
@Component
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer subjectId;
    private String subjectNumber;
    private String subjectName;
    private Grade grade;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectNumber='" + subjectNumber + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", grade=" + grade +
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

        Subject subject = (Subject) o;
        if (subjectId != null && subject.getSubjectId() != null) {
            return Objects.equals(subjectId, subject.subjectId) &&
                    Objects.equals(subjectNumber, subject.subjectNumber) &&
                    Objects.equals(subjectName, subject.subjectName) &&
                    grade.equals(subject.grade);
        }

        return Objects.equals(subjectNumber, subject.subjectNumber) &&
                Objects.equals(subjectName, subject.subjectName) &&
                grade.equals(subject.grade);
    }

}
