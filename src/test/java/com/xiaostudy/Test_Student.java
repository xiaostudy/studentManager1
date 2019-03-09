package com.xiaostudy;

import com.xiaostudy.domain.Results;
import com.xiaostudy.domain.Student;
import com.xiaostudy.service.ResultsService;
import com.xiaostudy.service.StudentService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Test_Student {

    public static StudentService studentService = (StudentService) CommonUtil.getBean("studentService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        //select();
    }

    private static void update() {
        Student student = studentService.getStudentByStudentNumber("20190201");
        student.setStudentName("黄五他弟");
        System.out.println(studentService.updateStudent(student));
    }

    private static void insert() {
        Student student = studentService.setStudent("20190201", "黄五", "女", new Date(), "四川", "黄五老爷子", "13800000008", new Date(), "高二", "2班");
        System.out.println(studentService.insertStudent(student));
    }

    private static void delete() {
        System.out.println(studentService.deleteStudent(studentService.getStudentByStudentNumber("20190201")));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(studentService.getStudentAll());
        System.out.println("222222222222222222222222222");
        System.out.println(studentService.getStudentByStudentNumber("201910101"));
        System.out.println("333333333333333333333333333");
        System.out.println(studentService.getStudentByStudentName("张三"));
        System.out.println("444444444444444444444444444");
        System.out.println(studentService.getStudentBySex("男"));
        System.out.println("555555555555555555555555555");
        System.out.println(studentService.getStudentByBorn(new Date("Thu Jan 31 00:00:00 CST 2019")));
        System.out.println("666666666666666666666666666");
        System.out.println(studentService.getStudentByHome("广东广州"));
        System.out.println("777777777777777777777777777");
        System.out.println(studentService.getStudentByHomeName("李四老爹"));
        System.out.println("888888888888888888888888888");
        System.out.println(studentService.getStudentByHomeContact("13800000000"));
        System.out.println("999999999999999999999999999");
        System.out.println(studentService.getStudentByAdmissionDate(new Date("Thu Jan 31 00:00:00 CST 2019")));
        System.out.println("101010101010101010101010101");
        System.out.println(studentService.getStudentByClazzName("1班"));
    }

    private static void test() {
        System.out.println("111111111111111111111111111");
        System.out.println("222222222222222222222222222");
        System.out.println("333333333333333333333333333");
        System.out.println("444444444444444444444444444");
        System.out.println("555555555555555555555555555");
        System.out.println("666666666666666666666666666");
        System.out.println("777777777777777777777777777");
    }


}
