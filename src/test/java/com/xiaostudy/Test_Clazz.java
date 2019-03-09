package com.xiaostudy;

import com.xiaostudy.domain.Clazz;
import com.xiaostudy.service.ClazzService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test_Clazz {

    public static ClazzService clazzService = (ClazzService) CommonUtil.getBean("clazzService");

    public static void main(String[] agrs) {
        //insert();
        //update();
        //delete();
        //select();
    }

    private static void update() {
        Clazz clazz = clazzService.getClazzByClazzNumber("2019003");
        clazz = clazzService.setClazzInGradeName(clazz, "高三");
        System.out.println(clazzService.updateClazz(clazz));
    }

    private static void delete() {
        System.out.println(clazzService.deleteClazz("2019003"));
    }

    private static void insert() {
        Clazz clazz = clazzService.setClazz("2019003", "3班", "高二", "小老弟");
        System.out.println(clazz);
        System.out.println(clazzService.insertClazz(clazz));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(clazzService.getClazzAll());
        System.out.println("222222222222222222222222222");
        System.out.println(clazzService.getClazzByClazzNumber("2019001"));
        System.out.println("333333333333333333333333333");
        System.out.println(clazzService.getClazzByClazzName("1班"));
        System.out.println("444444444444444444444444444");
        System.out.println(clazzService.getClazzByGradeName("高一"));
        System.out.println("555555555555555555555555555");
        System.out.println(clazzService.getClazzByTeacherName("小老弟"));
    }
}
