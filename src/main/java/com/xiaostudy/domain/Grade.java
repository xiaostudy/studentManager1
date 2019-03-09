package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 年级domain类
 *
 * @author liwei
 */
@Component
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer gradeId;
    private String gradeNumber;
    private String gradeName;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(String gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeNumber='" + gradeNumber + '\'' +
                ", gradeName='" + gradeName + '\'' +
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

        Grade grade = (Grade) o;
        if(gradeId != null && grade.gradeId != null) {
            return Objects.equals(gradeId, grade.gradeId) &&
                    Objects.equals(gradeNumber, grade.gradeNumber) &&
                    Objects.equals(gradeName, grade.gradeName);
        }
        return Objects.equals(gradeNumber, grade.gradeNumber) &&
                Objects.equals(gradeName, grade.gradeName);
    }

}
