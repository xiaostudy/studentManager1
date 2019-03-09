package com.xiaostudy.web.controller;

import com.xiaostudy.util.MakeMD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index2")
    public String index2() {
        return "index2";
    }

    @RequestMapping("/in_login")
    public String in_login(HttpServletRequest request, HttpServletResponse response) {
        // 设置格式
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        // 获取session的Id
        String sessionId = session.getId();
        System.out.println("in_login-sessionId:" + sessionId);
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        System.out.println("in_login-username:" + username); // 值username
        System.out.println("in_login-password:" + password); // 值password
        if("delete".equals(request.getParameter("sessionDel"))) {
            session.invalidate();
            return "login";
        }
        // 判断session是不是新创建的
        if (session.isNew()) {
            System.out.println("session创建成功，session的id是：:" + sessionId);
        } else {
            System.out.println("username:" + username); // 值username
            System.out.println("password:" + password); // 值password
            System.out.println("session的id是：:" + sessionId);
            if(username == null || username.trim().length() <= 0) {
                session.invalidate();
                return "login";
            }
            if("xiaostudy".equals(username) && "1ee0e324b0445d0105b02c0876d99c00".equals(password)) {
                return "index";
            } else {
                return "login";
            }
        }

        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String md5Account = MakeMD5.getMD5(password);
        System.out.println("login-username:" + username);
        System.out.println("login-password:" + password);
        System.out.println("login-md5Account:" + md5Account);

        // 设置格式
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30*60);//设置session时间为30分钟
        // 将数据存储到session中
        if(username != null && username.trim().length() > 0) {
            session.setAttribute("username", username);
            session.setAttribute("password", md5Account);
        }
        String sessionUsername = (String) session.getAttribute("username");
        String sessionPassword = (String) session.getAttribute("password");
        // 获取session的Id
        String sessionId = session.getId();

        // 判断session是不是新创建的
        if (session.isNew()) {
            System.out.println("login-session创建成功，session的id是：:" + sessionId);
        } else {
            System.out.println("sessionUsername:" + sessionUsername); // 值username
            System.out.println("sessionPassword:" + sessionPassword); // 值password
            System.out.println("login-session的id是：:" + sessionId);
        }
        if("xiaostudy".equals(sessionUsername) && "1ee0e324b0445d0105b02c0876d99c00".equals(sessionPassword)) {
            return "index";
        } else {
            return "login";
        }
    }

    public static boolean islogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        String sessionPassword = (String) session.getAttribute("password");
        if("xiaostudy".equals(sessionUsername) && "1ee0e324b0445d0105b02c0876d99c00".equals(sessionPassword)) {
            return true;
        } else {
            return false;
        }
    }


}
