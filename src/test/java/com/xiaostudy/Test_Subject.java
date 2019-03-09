package com.xiaostudy;

import com.xiaostudy.domain.Student;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.service.StudentService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class Test_Subject {

    public static SubjectService subjectService = (SubjectService) CommonUtil.getBean("subjectService");

    public static GradeService gradeService = (GradeService) CommonUtil.getBean("gradeService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        select();
    }

    private static void update() {
        Subject subject = subjectService.getSubjectBySubjectNumber("10");
        subject.setGrade(gradeService.getGradeByGradeName("高三"));
        System.out.println(subjectService.updateSubject(subject));
    }

    private static void delete() {
        System.out.println(subjectService.deleteSubject(subjectService.getSubjectBySubjectNumber("10")));
    }

    private static void insert() {
        Subject subject = (Subject)CommonUtil.getBean(Subject.class);
        subject.setSubjectNumber("10");
        subject.setSubjectName("物理");
        subject.setGrade(gradeService.getGradeByGradeNumber("002"));
        System.out.println(subjectService.insertSubject(subject));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(subjectService.getSubjectAll());
        System.out.println("222222222222222222222222222");
        System.out.println(subjectService.getSubjectBySubjectId(2));
        System.out.println("333333333333333333333333333");
        System.out.println(subjectService.getSubjectBySubjectNumber("002"));
        System.out.println("444444444444444444444444444");
        System.out.println(subjectService.getSubjectBySubjectName("语文"));
        System.out.println("555555555555555555555555555");
        List<Subject> subjectList = subjectService.getSubjectByGradeNumber("001");
        for(Subject subject : subjectList) {
            System.out.println(subject);
        }
        System.out.println("666666666666666666666666666");
        System.out.println(subjectService.getSubjectNumberList());
        System.out.println("777777777777777777777777777");
        System.out.println(subjectService.getSubjectNameList());
        System.out.println("888888888888888888888888888");
        System.out.println(subjectService.getSubjectByGradeName("高一"));
        System.out.println(subjectService.getPages());
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
