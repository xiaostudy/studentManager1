package com.xiaostudy.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    private static ApplicationContext application = null;

    static {
        application = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static Object getBean(String strBean) {
        Object object = null;
        object = application.getBean(strBean);
        return object;
    }

    public static Object getBean(Class cla) {
        Object object = null;
        object = application.getBean(cla);
        return object;
    }

}
