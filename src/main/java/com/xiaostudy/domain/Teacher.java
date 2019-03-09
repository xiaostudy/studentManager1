package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 教师domain类
 *
 * @author liwei
 */
@Component
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer teacherId;
    private String teacherNumber;
    private String teacherName;
    private String sex;
    private Date born;
    private String home;
    private String contact;
    private Date entryDate;
    private Subject subject;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", sex='" + sex + '\'' +
                ", born=" + born +
                ", home='" + home + '\'' +
                ", contact='" + contact + '\'' +
                ", entryDate=" + entryDate +
                ", subject=" + subject +
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

        Teacher teacher = (Teacher) o;
        if (teacherId != null && teacher.teacherId != null) {
            return Objects.equals(teacherId, teacher.teacherId) &&
                    Objects.equals(teacherNumber, teacher.teacherNumber) &&
                    Objects.equals(teacherName, teacher.teacherName) &&
                    Objects.equals(sex, teacher.sex) &&
                    Objects.equals(born, teacher.born) &&
                    Objects.equals(home, teacher.home) &&
                    Objects.equals(contact, teacher.contact) &&
                    Objects.equals(entryDate, teacher.entryDate) &&
                    subject.equals(teacher.subject);
        }
        return Objects.equals(teacherNumber, teacher.teacherNumber) &&
                Objects.equals(teacherName, teacher.teacherName) &&
                Objects.equals(sex, teacher.sex) &&
                Objects.equals(born, teacher.born) &&
                Objects.equals(home, teacher.home) &&
                Objects.equals(contact, teacher.contact) &&
                Objects.equals(entryDate, teacher.entryDate) &&
                subject.equals(teacher.subject);
    }

}
