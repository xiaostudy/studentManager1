package com.xiaostudy;

import com.xiaostudy.domain.Student;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.domain.Teacher;
import com.xiaostudy.service.StudentService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TeacherService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Test_Teacher {

    public static TeacherService teacherService = (TeacherService) CommonUtil.getBean("teacherService");

    public static SubjectService subjectService = (SubjectService) CommonUtil.getBean("subjectService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        select();
    }

    private static void update() {
        Teacher teacher = teacherService.getTeacherByTeacherNumber("2019003");
        teacher.setTeacherName("小白");
        System.out.println(teacherService.updateTeacher(teacher));
    }

    private static void delete() {
        System.out.println(teacherService.deleteTeacher(teacherService.getTeacherByTeacherNumber("2019003")));
    }

    private static void insert() {
        Teacher teacher = (Teacher)CommonUtil.getBean(Teacher.class);
        teacher.setTeacherNumber("2019003");
        teacher.setTeacherName("大白");
        teacher.setSex("男");
        teacher.setBorn(new Date());
        teacher.setHome("广州");
        teacher.setContact("13800000003");
        teacher.setEntryDate(new Date());
        teacher.setSubject(subjectService.getSubjectBySubjectNumber("002"));
        System.out.println(teacherService.insertTeacher(teacher));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(teacherService.getTeacherAll());
        System.out.println("222222222222222222222222222");
        System.out.println(teacherService.getTeacherByTeacherNumber("2019002"));
        System.out.println("333333333333333333333333333");
        System.out.println(teacherService.getTeacherByTeacherName("小老弟"));
        System.out.println("444444444444444444444444444");
        System.out.println(teacherService.getTeacherBySex("男"));
        System.out.println("555555555555555555555555555");
        System.out.println(teacherService.getTeacherByBorn(new Date("Thu Jan 31 00:00:00 CST 2019")));
        System.out.println("666666666666666666666666666");
        System.out.println(teacherService.getTeacherByHome("上海"));
        System.out.println("777777777777777777777777777");
        System.out.println(teacherService.getTeacherByContact("13800000001"));
        System.out.println("888888888888888888888888888");
        System.out.println(teacherService.getTeacherByEntryDate(new Date("Thu Jan 31 00:00:00 CST 2019")));
        System.out.println("999999999999999999999999999");
        System.out.println(teacherService.getTeacherByGradeNameSubjectName("高一", "数学"));
        System.out.println("101010101010101010101010101");
        System.out.println(teacherService.getTeacherNumberList());
        System.out.println("---------------------------");
        System.out.println(teacherService.getTeacherNameList());
    }


    private static void test() {
        System.out.println("111111111111111111111111111");
        System.out.println("222222222222222222222222222");
        System.out.println("333333333333333333333333333");
        System.out.println("444444444444444444444444444");
        System.out.println("555555555555555555555555555");
        System.out.println("666666666666666666666666666");
        System.out.println("777777777777777777777777777");
        System.out.println("888888888888888888888888888");
        System.out.println("999999999999999999999999999");
        System.out.println("101010101010101010101010101");
        System.out.println("---------------------------");
    }


}
