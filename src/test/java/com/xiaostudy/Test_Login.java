package com.xiaostudy;

import com.xiaostudy.domain.Grade;
import com.xiaostudy.domain.Login;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.service.LoginService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_Login {

    public static LoginService loginService = (LoginService) CommonUtil.getBean("loginService");

    public static void main(String[] agrs) {
        //select();
        //insert();
        //delete();
        //update();
    }

    private static void update() {
        System.out.println("111111111111111111111111111");
        System.out.println(loginService.updateLoginIdByLoginId(456, 654));
        System.out.println("222222222222222222222222222");
        System.out.println(loginService.updateNameByLoginId(654, "haha"));
        System.out.println("333333333333333333333333333");
        System.out.println(loginService.updatePasswordByLoginId(654, "321456"));
        System.out.println("444444444444444444444444444");
        System.out.println(loginService.updatePasswordPromptByLoginId(654, "llllllllll"));
    }

    private static void delete() {
        System.out.println(loginService.deleteByLoginId(456));
    }

    private static void insert() {
        Login login = (Login)CommonUtil.getBean(Login.class);
        login.setId(4);
        login.setLoginId(456);
        login.setName("qqqqqq");
        login.setPassword("123456");
        login.setPasswordPrompt("654321");
        System.out.println(loginService.insert(login));
    }

    private static void select() {
        System.out.println("111111111111111111111111111");
        System.out.println(loginService.getLoginAll());
        System.out.println("222222222222222222222222222");
        System.out.println(loginService.getLoginByLoginId(1409402052));
        System.out.println("333333333333333333333333333");
        System.out.println(loginService.getLoginByName("xiaostudy"));
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
