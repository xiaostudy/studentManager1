package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Student;
import com.xiaostudy.service.ClazzService;
import com.xiaostudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/showStudentList", method = RequestMethod.GET)
    public String showStudentList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Student> studentAll = studentService.getStudentAll();

        if (studentAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "student/error";
        }

        request.setAttribute("studentAll", studentAll);
        return "student/show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateStudent(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/student/updateAjax";
        }

        return "student/show";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateStudentAjax(HttpServletRequest request, HttpServletResponse response) {
        String oldStudentNumber = request.getParameter("oldStudentNumber");
        String newStudentNumber = request.getParameter("newStudentNumber");
        String newStudentName = request.getParameter("newStudentName");
        String newSex = request.getParameter("newSex");
        String newBorn = request.getParameter("newBorn");
        String newHome = request.getParameter("newHome");
        String newHomeName = request.getParameter("newHomeName");
        String newHomeContact = request.getParameter("newHomeContact");
        String newAdmissionDate = request.getParameter("newAdmissionDate");
        String newGradeName = request.getParameter("newGradeName");
        String newClazzName = request.getParameter("newClazzName");

        System.out.println("oldStudentNumber:" + oldStudentNumber);
        System.out.println("newStudentNumber:" + newStudentNumber);
        System.out.println("newStudentName:" + newStudentName);
        System.out.println("newSex:" + newSex);
        System.out.println("newBorn:" + newBorn);
        System.out.println("newHome:" + newHome);
        System.out.println("newHomeName:" + newHomeName);
        System.out.println("newHomeContact:" + newHomeContact);
        System.out.println("newAdmissionDate:" + newAdmissionDate);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newClazzName:" + newClazzName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");
        if (oldStudentNumber == null || oldStudentNumber.trim().length() <= 0 || !oldStudentNumber.trim().matches("[0-9]*")) {
            jsonObject.put("message", "非法操作！");
            b = false;
        }


        Student student = null;
        if (b) {
            student = studentService.getStudentByStudentNumber(oldStudentNumber.trim());
            jsonObject.put("studentNumber", oldStudentNumber.trim());
            jsonObject.put("studentName", student.getStudentName());
            jsonObject.put("sex", student.getSex());
            jsonObject.put("born", student.getBorn());
            jsonObject.put("home", student.getHome());
            jsonObject.put("homeName", student.getHomeName());
            jsonObject.put("homeContact", student.getHomeContact());
            jsonObject.put("admissionDate", student.getAdmissionDate());
            jsonObject.put("gradeName", student.getClazz().getGrade().getGradeName());
            jsonObject.put("clazzName", student.getClazz().getClazzName());
        }

        System.out.println("jsonObject:" + jsonObject);

        System.out.println("newStudentNumber.trim().length():" + newStudentNumber.trim().length());

        if (b) {
            if (newStudentNumber == null || newStudentNumber.trim().length() <= 0) {
                jsonObject.put("message", "学号为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (!newStudentNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "学号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newStudentNumber.trim().length() > 11) {
                jsonObject.put("message", "学号长度大于11！");
                b = false;
            }
        }


        if (b) {
            try {
            } catch (NumberFormatException e) {
                e.printStackTrace();
                jsonObject.put("message", "学号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (!newStudentNumber.equals(student.getStudentNumber()) && studentService.getStudentByStudentNumber(newStudentNumber) != null) {
                jsonObject.put("message", "学号已存在！");
                b = false;
            }
        }

        if (b) {
            if (newStudentName == null || newStudentName.trim().length() <= 0) {
                jsonObject.put("message", "姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newStudentName == null || newStudentName.trim().length() <= 0) {
                jsonObject.put("message", "姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newStudentName.trim().length() > 40) {
                jsonObject.put("message", "姓名长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (!student.getStudentName().equals(newStudentName.trim()) && studentService.isStudentName(newStudentName.trim())) {
                jsonObject.put("message", "姓名已存在！");
                b = false;
            }
        }

        if (b) {
            if (newSex == null || newSex.trim().length() <= 0) {
                jsonObject.put("message", "请选择性别！");
                b = false;
            }
        }

        if (b) {
            if (!newSex.matches("[男女YyXx]")) {
                jsonObject.put("message", "请选择性别！");
                b = false;
            }
        }

        if (b) {
            if (newBorn == null || newBorn.trim().length() <= 0) {
                jsonObject.put("message", "请选择出生日期！");
                b = false;
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (b) {
            try {
                simpleDateFormat.parse(newBorn.trim());
            } catch (ParseException e) {
                e.printStackTrace();
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newHome == null || newHome.trim().length() <= 0) {
                jsonObject.put("message", "家庭住址为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHome.trim().length() > 40) {
                jsonObject.put("message", "家庭住址长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newHomeName == null || newHomeName.trim().length() <= 0) {
                jsonObject.put("message", "家长姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHomeName.trim().length() > 40) {
                jsonObject.put("message", "家长姓名长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newHomeContact == null || newHomeContact.trim().length() <= 0) {
                jsonObject.put("message", "家长联系方式为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHomeContact.trim().length() > 40) {
                jsonObject.put("message", "家长联系方式长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (!newHomeContact.trim().equals(student.getHomeContact()) && studentService.isHomeContact(newHomeContact.trim())) {
                jsonObject.put("message", "家长联系方式已存在！");
                b = false;
            }
        }

        if (b) {
            if (newAdmissionDate == null || newAdmissionDate.trim().length() <= 0) {
                jsonObject.put("message", "请选择入学日期！");
                b = false;
            }
        }

        if (b) {
            if (newAdmissionDate.trim().length() > 40) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            try {
                simpleDateFormat.parse(newAdmissionDate.trim());
            } catch (ParseException e) {
                e.printStackTrace();
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级，如果没有年级请先创建年级！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName.trim().length() > 40 || studentService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName == null || newClazzName.trim().length() <= 0) {
                jsonObject.put("message", "请选择班级，如果没有班级请先创建班级！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName.trim().length() > 40 || studentService.isClazzName(newClazzName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        try {
            if (b) {
                Student student1 = studentService.setStudent(newStudentNumber, newStudentName, newSex, simpleDateFormat.parse(newBorn.trim()), newHome, newHomeName, newHomeContact, simpleDateFormat.parse(newAdmissionDate.trim()), newGradeName, newClazzName);
                System.out.println("旧的:" + student);
                System.out.println("新的:" + student1);
                if (student.equals(student1)) {
                    jsonObject.put("message", "学生信息沒有改变！");
                } else {
                    student1.setStudentId(student.getStudentId());
                    boolean b1 = studentService.updateStudent(student1);
                    if (b1 == false) {
                        jsonObject.put("message", "服务器出错！");
                    } else {
                        jsonObject.put("message", "修改学生成功！");
                        jsonObject.put("status", "ok");
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertStudent(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/student/insertAjax";
        }

        return "student/show";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertStudentAjax(HttpServletRequest request, HttpServletResponse response) {
        String newStudentNumber = request.getParameter("newStudentNumber");
        String newStudentName = request.getParameter("newStudentName");
        String newSex = request.getParameter("newSex");
        String newBorn = request.getParameter("newBorn");
        String newHome = request.getParameter("newHome");
        String newHomeName = request.getParameter("newHomeName");
        String newHomeContact = request.getParameter("newHomeContact");
        String newAdmissionDate = request.getParameter("newAdmissionDate");
        String newGradeName = request.getParameter("newGradeName");
        String newClazzName = request.getParameter("newClazzName");

        System.out.println("newStudentNumber:" + newStudentNumber);
        System.out.println("newStudentName:" + newStudentName);
        System.out.println("newSex:" + newSex);
        System.out.println("newBorn:" + newBorn);
        System.out.println("newHome:" + newHome);
        System.out.println("newHomeName:" + newHomeName);
        System.out.println("newHomeContact:" + newHomeContact);
        System.out.println("newAdmissionDate:" + newAdmissionDate);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newClazzName:" + newClazzName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");

        if (newStudentNumber == null || newStudentNumber.trim().length() <= 0) {
            jsonObject.put("message", "学号为空或只有空格！");
            b = false;
        }

        if (b) {
            if (!newStudentNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "学号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newStudentNumber.trim().length() > 11) {
                jsonObject.put("message", "学号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (studentService.getStudentByStudentNumber(newStudentNumber.trim()) != null) {
                jsonObject.put("message", "学号已存在！");
                b = false;
            }
        }

        if (b) {
            if (newStudentName == null || newStudentName.trim().length() <= 0) {
                jsonObject.put("message", "姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newStudentName.trim().length() > 40) {
                jsonObject.put("message", "姓名长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (studentService.isStudentName(newStudentName.trim())) {
                jsonObject.put("message", "姓名已存在！");
                b = false;
            }
        }

        if (b) {
            if (!newSex.trim().matches("[男女YyXx]")) {
                jsonObject.put("message", "请选择性别！");
                b = false;
            }
        }

        if (b) {
            if (newBorn == null || newBorn.trim().length() <= 0) {
                jsonObject.put("message", "请选择出生日期！");
                b = false;
            }
        }

        if (b) {
            if (newBorn.trim().length() > 40) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (b) {
            try {
                simpleDateFormat.parse(newBorn);
            } catch (ParseException e) {
                e.printStackTrace();
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newHome == null || newHome.trim().length() <= 0) {
                jsonObject.put("message", "住址为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHome.trim().length() > 40) {
                jsonObject.put("message", "住址长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newHomeName == null || newHomeName.trim().length() <= 0) {
                jsonObject.put("message", "家长姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHomeName.trim().length() > 40) {
                jsonObject.put("message", "家长姓名长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newHomeContact == null || newHomeContact.trim().length() <= 0) {
                jsonObject.put("message", "家长联系方式为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newHomeContact.trim().length() > 40) {
                jsonObject.put("message", "家长联系方式长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newAdmissionDate == null || newAdmissionDate.trim().length() <= 0) {
                jsonObject.put("message", "请选择入学日期！");
                b = false;
            }
        }

        if (b) {
            if (newAdmissionDate.trim().length() > 40) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            try {
                simpleDateFormat.parse(newAdmissionDate);
            } catch (ParseException e) {
                e.printStackTrace();
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级！如果年级不存在请先创建年级！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName.trim().length() > 40 || studentService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName == null || newClazzName.trim().length() <= 0) {
                jsonObject.put("message", "请选择班级！如果班级不存在请先创建班级！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName.trim().length() > 40 || studentService.isClazzName(newClazzName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        try {
            if (b) {
                if (newSex.trim().equalsIgnoreCase("Y")) {
                    newSex = "男";
                } else if (newSex.trim().equalsIgnoreCase("X")) {
                    newSex = "女";
                }

                Student student = studentService.setStudent(newStudentNumber, newStudentName, newSex, simpleDateFormat.parse(newBorn.trim()), newHome, newHomeName, newHomeContact, simpleDateFormat.parse(newAdmissionDate.trim()), newGradeName, newClazzName);
                boolean b1 = studentService.insertStudent(student);
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "新建学生成功！");
                    jsonObject.put("status", "ok");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteStudent(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/student/deleteAjax";
        }

        return "student/show";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteStudentAjax(HttpServletRequest request, HttpServletResponse response) {
        String studentNumber = request.getParameter("studentNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (studentNumber == null || studentNumber.trim().length() <= 0) {
                    jsonObject.put("message", "学号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!studentNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "学号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (studentService.getStudentByStudentNumber(studentNumber.trim()) == null) {
                    jsonObject.put("message", "该学生已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b1 = studentService.deleteStudent(studentService.getStudentByStudentNumber(studentNumber.trim()));
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "删除学生成功！");
                    jsonObject.put("status", "ok");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/showStudentNameList", method = RequestMethod.GET)
    public String showStudentNameList(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/student/showStudentNameListAjax";
        }

        return "student/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showStudentNameListAjax", method = RequestMethod.GET)
    public void showStudentNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        String gradeName = request.getParameter("gradeName");
        System.out.println("gradeName:" + gradeName);
        JSONObject jsonObject = new JSONObject();
        List<String> studentNameList = new ArrayList<>();

        if (gradeName != null && gradeName.trim().length() > 0 && studentService.isGradeName(gradeName.trim())) {
            studentNameList = studentService.getStudentNameList(gradeName.trim());
        } else {
            studentNameList = studentService.getStudentNameList();
        }

        for (int i = 0; i < studentNameList.size(); i++) {
            jsonObject.put("" + i, studentNameList.get(i));
        }

        try {
            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
