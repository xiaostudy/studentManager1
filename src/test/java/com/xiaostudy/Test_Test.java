package com.xiaostudy;

import com.xiaostudy.domain.Teacher;
import com.xiaostudy.domain.Test;
import com.xiaostudy.service.TeacherService;
import com.xiaostudy.service.TestService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Test_Test {

    public static TestService testService = (TestService) CommonUtil.getBean("testService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        delete();
        select();
    }

    private static void update() {
        Test test = testService.getTestByTestNumber("2019020201");
        test.setTestName("2018下学期期末2");
        System.out.println(testService.updateTest(test));
    }

    private static void delete() {
        System.out.println(testService.deleteTest(testService.getTestByTestNumber("2019020201")));
    }

    private static void insert() {
        Test test = testService.setTest("2019020201", "2018下学期期末", "高一", "数学");
        System.out.println(testService.insertTest(test));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(testService.getTestAll());
        System.out.println("222222222222222222222222222");
        System.out.println(testService.getTestByTestNumber("2019012702"));
        System.out.println("333333333333333333333333333");
        System.out.println(testService.getTestByTestName("2018下学期期中"));
        System.out.println("444444444444444444444444444");
        System.out.println(testService.getTestByGradeNameSubjectName("高二", "语文"));
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
