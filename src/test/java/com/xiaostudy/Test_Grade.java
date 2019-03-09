package com.xiaostudy;

import com.xiaostudy.domain.Grade;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.util.CommonUtil;

public class Test_Grade {

    public static GradeService gradeService = (GradeService) CommonUtil.getBean("gradeService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        //select();
    }

    private static void update() {
        Grade grade = gradeService.getGradeByGradeNumber("004");
        grade.setGradeNumber("005");
        grade.setGradeName("测试修改后");
        System.out.println(gradeService.updateGrade(grade));
    }

    private static void insert() {
        Grade grade = (Grade)CommonUtil.getBean(Grade.class);
        grade.setGradeNumber("004");
        grade.setGradeName("测试1");
        System.out.println(gradeService.insertGrade(grade));
    }

    private static void delete() {
        System.out.println(gradeService.deleteGrade(gradeService.getGradeByGradeNumber("005")));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(gradeService.getGradeAll());
        System.out.println("222222222222222222222222222");
        System.out.println(gradeService.getGradeByGradeId(2));
        System.out.println("333333333333333333333333333");
        System.out.println(gradeService.getGradeByGradeNumber("001"));
        System.out.println("444444444444444444444444444");
        System.out.println(gradeService.getGradeByGradeName("高二"));
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
