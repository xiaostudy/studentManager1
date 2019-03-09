package com.xiaostudy;

import com.xiaostudy.service.ClazzService;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.util.CommonUtil;
import org.junit.Test;

import com.xiaostudy.domain.Student;
import com.xiaostudy.service.StudentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

	@Test
    public static void test_Student(){

        StudentService studentService = (StudentService) CommonUtil.getBean("studentService");
        System.out.println(studentService);
        //Student student = studentService.getStudentBySid(1409402052);
        Student student = (Student) CommonUtil.getBean(Student.class);
        student.setStudentNumber("12345678");
        student.setStudentName("sssss");
        student.setSex("X");
        student.setBorn(new Date());
        student.setHome("gz");
        student.setHomeName("baba");
        student.setHomeContact("123");
        student.setAdmissionDate(new Date());
        //student.setId(5);
        studentService.insertStudent(student);
        System.out.println(studentService.getStudentAll());
    }
	
	public static void main(String[] args) {
        //test_Student();
        //test_Clazz();
        //test_DailyRecord();
        //test_Grade();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(simpleDateFormat.parse("2019/01/22"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        System.out.println("emmm");

    }

    private static void test_Grade() {
        GradeService gradeService = (GradeService) CommonUtil.getBean("gradeService");
        //System.out.println(gradeService.getGradeAll());
        System.out.println(gradeService.getGradeByGradeId(1));
    }

    public static void test_Clazz() {
        ClazzService clazzService = (ClazzService)CommonUtil.getBean("clazzService");

        System.out.println(clazzService.getClazzAll());
    }

}
