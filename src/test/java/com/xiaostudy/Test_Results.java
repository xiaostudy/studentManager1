package com.xiaostudy;

import com.xiaostudy.domain.Login;
import com.xiaostudy.domain.Results;
import com.xiaostudy.service.LoginService;
import com.xiaostudy.service.ResultsService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_Results {

    public static ResultsService resultsService = (ResultsService) CommonUtil.getBean("resultsService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        select();
    }

    private static void update() {
        Results results = resultsService.getResultsByResultsNumber("20190202020002");
        results.setScore(98);
        System.out.println(resultsService.updateResults(results));
    }

    private static void delete() {
        //System.out.println(resultsService.deleteResults("20190202020002"));
        System.out.println(resultsService.deleteResults(resultsService.getResultsByResultsNumber("20190202020002")));
    }

    private static void insert() {
        Results results = resultsService.setResults("20190202020002", "高二", "张三", "2018下学期期中", "语文",86);
        if(results != null) {
            System.out.println(resultsService.insert(results));
        }
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(resultsService.getResultsAll());
        System.out.println("222222222222222222222222222");
        System.out.println(resultsService.getResultsByResultsNumber("20190127010001"));
        System.out.println("333333333333333333333333333");
        System.out.println(resultsService.getResultsByTestName("高二", "2018下学期期初"));
        System.out.println("444444444444444444444444444");
        System.out.println(resultsService.getResultsBySubjectName("高二", "语文"));
        System.out.println("555555555555555555555555555");
        System.out.println(resultsService.getResultsByTestNameSubjectName("高二", "2018下学期期初", "语文"));
        System.out.println("666666666666666666666666666");
        System.out.println(resultsService.getResultsByStudentNameTestNameSubjectName("高二", "李四", "2018下学期期初", "语文"));
        System.out.println("777777777777777777777777777");
        System.out.println(resultsService.getResultsByScore(85));
        System.out.println("888888888888888888888888888");
        System.out.println(resultsService.getResultsByStudentNameTestName("高二", "李四", "2018下学期期初"));
        System.out.println("999999999999999999999999999");
        System.out.println(resultsService.getResultsByStudentNameSubjectName("高二", "李四", "语文"));
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
