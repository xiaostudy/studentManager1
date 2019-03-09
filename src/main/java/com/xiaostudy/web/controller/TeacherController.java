package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Teacher;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TeacherService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/showTeacherList", method = RequestMethod.GET)
    public String showTeacherList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Teacher> teacherAll = teacherService.getTeacherAll();

        if (teacherAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "student/error";
        }

        request.setAttribute("teacherAll", teacherAll);
        return "teacher/show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTeacher(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/teacher/updateAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateTeacherAjax(HttpServletRequest request, HttpServletResponse response) {
        String oldTeacherNumber = request.getParameter("oldTeacherNumber");
        String newTeacherNumber = request.getParameter("newTeacherNumber");
        String newTeacherName = request.getParameter("newTeacherName");
        String newSex = request.getParameter("newSex");
        String newBorn = request.getParameter("newBorn");
        String newHome = request.getParameter("newHome");
        String newContact = request.getParameter("newContact");
        String newEntryDate = request.getParameter("newEntryDate");
        String newGradeName = request.getParameter("newGradeName");
        String newSubjectName = request.getParameter("newSubjectName");

        System.out.println("oldTeacherNumber:" + oldTeacherNumber);
        System.out.println("newTeacherNumber:" + newTeacherNumber);
        System.out.println("newTeacherName:" + newTeacherName);
        System.out.println("newSex:" + newSex);
        System.out.println("newBorn:" + newBorn);
        System.out.println("newHome:" + newHome);
        System.out.println("newContact:" + newContact);
        System.out.println("newEntryDate:" + newEntryDate);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newSubjectName:" + newSubjectName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台
        try {
            jsonObject.put("status", "error");
            if (oldTeacherNumber == null || oldTeacherNumber.trim().length() <= 0 || oldTeacherNumber.trim().length() > 11 || !oldTeacherNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }


            Teacher teacher = null;
            if (b) {
                teacher = teacherService.getTeacherByTeacherNumber(oldTeacherNumber.trim());
                jsonObject.put("teacherNumber", oldTeacherNumber);
                jsonObject.put("teacherName", teacher.getTeacherName());
                jsonObject.put("sex", teacher.getSex());
                jsonObject.put("born", teacher.getBorn());
                jsonObject.put("home", teacher.getHome());
                jsonObject.put("contact", teacher.getContact());
                jsonObject.put("entryDate", teacher.getEntryDate());
                jsonObject.put("gradeName", teacher.getSubject().getGrade().getGradeName());
                jsonObject.put("subjectName", teacher.getSubject().getSubjectName());
            }

            if (b) {
                if (newTeacherNumber == null || newTeacherNumber.trim().length() <= 0) {
                    jsonObject.put("message", "职工号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (!newTeacherNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "职工号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (newTeacherNumber.trim().length() > 11) {
                    jsonObject.put("message", "职工号长度大于11！");
                    b = false;
                }
            }

            Integer TeacherNumber = 0;

            if (b) {
                try {
                    TeacherNumber = new Integer(newTeacherNumber.trim());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    jsonObject.put("message", "职工号长度大于11！");
                    b = false;
                }
            }

            if(b) {
                if (!oldTeacherNumber.trim().equals(newTeacherNumber.trim()) && teacherService.isTeacherNumber(newTeacherNumber.trim())) {
                    jsonObject.put("message", "职工号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (newTeacherName == null || newTeacherName.trim().length() <= 0) {
                    jsonObject.put("message", "姓名为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newTeacherName.trim().length() > 40) {
                    jsonObject.put("message", "姓名长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if(b) {
                if(!newTeacherName.trim().equals(teacher.getTeacherName()) && teacherService.isTeacherName(newTeacherName.trim())) {
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

            if(b) {
                if(newSex.trim().length() > 40) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            if (b) {
                if(!newSex.trim().matches("[男女YyXx]")) {
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
                    jsonObject.put("message", "住址为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newHome.trim().length() > 40) {
                    jsonObject.put("message", "住址长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {
                if (newContact == null || newContact.trim().length() <= 0) {
                    jsonObject.put("message", "联系方式为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newContact.trim().length() > 40) {
                    jsonObject.put("message", "联系方式长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {
                if (!newContact.trim().equals(teacher.getContact()) && teacherService.getTeacherByContact(newContact.trim()) != null) {
                    jsonObject.put("message", "联系方式已存在！");
                    b = false;
                }
            }

            if (b) {
                if (newEntryDate == null || newEntryDate.trim().length() <= 0) {
                    jsonObject.put("message", "请选择入职日期！");
                    b = false;
                }
            }

            if (b) {
                if (newEntryDate.trim().length() > 40) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            if (b) {
                try {
                    simpleDateFormat.parse(newEntryDate.trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            if(b) {
                if(newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "请选择年级，如果没有年级请先创建年级！");
                    b = false;
                }
            }

            if (b) {
                if (newSubjectName == null || newSubjectName.trim().length() <= 0) {
                    jsonObject.put("message", "请选择学科，如果没有学科请先创建学科！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName.trim().length() > 40 || newSubjectName.trim().length() > 40 || teacherService.isSubject(newGradeName, newSubjectName) == false) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            Teacher newTeacher = null;
            if(b) {
                newTeacher = teacherService.setTeacher(newTeacherNumber, newTeacherName, newSex, simpleDateFormat.parse(newBorn.trim()), newHome, newContact, simpleDateFormat.parse(newEntryDate.trim()), newGradeName, newSubjectName);
                if(teacher.equals(newTeacher)){
                    jsonObject.put("message", "教师信息沒有改变！");
                    b = false;
                }
            }

            if(b) {
                newTeacher.setTeacherId(teacher.getTeacherId());
                boolean b1 = teacherService.updateTeacher(newTeacher);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "修改教师成功！");
                    jsonObject.put("status", "ok");
                }
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            System.out.println("jsonObject:" + jsonObject);
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertTeacher(HttpServletRequest request) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/teacher/insertAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertTeacherAjax(HttpServletRequest request, HttpServletResponse response) {
        String newTeacherNumber = request.getParameter("newTeacherNumber");
        String newTeacherName = request.getParameter("newTeacherName");
        String newSex = request.getParameter("newSex");
        String newBorn = request.getParameter("newBorn");
        String newHome = request.getParameter("newHome");
        String newContact = request.getParameter("newContact");
        String newEntryDate = request.getParameter("newEntryDate");
        String newGradeName = request.getParameter("newGradeName");
        String newSubjectName = request.getParameter("newSubjectName");

        System.out.println("newTeacherNumber:" + newTeacherNumber);
        System.out.println("newTeacherName:" + newTeacherName);
        System.out.println("newSex:" + newSex);
        System.out.println("newBorn:" + newBorn);
        System.out.println("newHome:" + newHome);
        System.out.println("newContact:" + newContact);
        System.out.println("newEntryDate:" + newEntryDate);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newSubjectName:" + newSubjectName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");

        if (newTeacherNumber == null || newTeacherNumber.trim().length() <= 0) {
            jsonObject.put("message", "职工号为空或只有空格！");
            b = false;
        }

        if(b) {
            if(!newTeacherNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "职工号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newTeacherNumber.trim().length() > 11) {
                jsonObject.put("message", "职工号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (teacherService.isTeacherNumber(newTeacherNumber.trim())) {
                jsonObject.put("message", "职工号已存在！");
                b = false;
            }
        }

        if (b) {
            if (newTeacherName == null || newTeacherName.trim().length() <= 0) {
                jsonObject.put("message", "姓名为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newTeacherName.trim().length() > 40) {
                jsonObject.put("message", "姓名长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if(b) {
            if(teacherService.isTeacherName(newTeacherName.trim())) {
                jsonObject.put("message", "姓名已存在！");
                b = false;
            }
        }

        if (b) {
            if (!newSex.trim().equals("男") && !newSex.trim().equals("女") && !newSex.trim().equalsIgnoreCase("Y") && !newSex.trim().equalsIgnoreCase("X")) {
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
            if (newContact == null || newContact.trim().length() <= 0) {
                jsonObject.put("message", "联系方式为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newContact.trim().length() > 40) {
                jsonObject.put("message", "联系方式长度大于40！");
                b = false;
            }
        }

        if(b) {
            if(teacherService.isTeacherContact(newContact.trim())) {
                jsonObject.put("message", "联系方式已存在！");
                b = false;
            }
        }

        if (b) {
            if (newEntryDate == null || newEntryDate.trim().length() <= 0) {
                jsonObject.put("message", "请选择入职日期！");
                b = false;
            }
        }

        if (b) {
            if (newEntryDate.trim().length() > 40) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            try {
                simpleDateFormat.parse(newEntryDate);
            } catch (ParseException e) {
                e.printStackTrace();
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级，如果没有年级请先创建年级！");
                b = false;
            }
        }

        if (b) {
            if (newSubjectName == null || newSubjectName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学科，如果没有学科请先创建学科！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName.trim().length() > 40 || newSubjectName.trim().length() > 40) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        try {
            if(b) {
                Teacher teacher = teacherService.setTeacher(newTeacherNumber, newTeacherName, newSex, simpleDateFormat.parse(newBorn.trim()), newHome, newContact, simpleDateFormat.parse(newEntryDate.trim()), newGradeName, newSubjectName);
                boolean b1 = teacherService.insertTeacher(teacher);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "新建教师成功！");
                    jsonObject.put("status", "ok");
                }
            }

            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTeacher(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/teacher/deleteAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteTeacherAjax(HttpServletRequest request, HttpServletResponse response) {
        String teacherNumber = request.getParameter("teacherNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (teacherNumber == null || teacherNumber.trim().length() <= 0) {
                    jsonObject.put("message", "职工号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!teacherNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "职工号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (teacherService.isTeacherNumber(teacherNumber.trim()) == false) {
                    jsonObject.put("message", "该教师已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b1 = teacherService.deleteTeacher(teacherService.getTeacherByTeacherNumber(teacherNumber.trim()));
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "删除教师成功！");
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

    @RequestMapping(value = "/showTeacherNameList", method = RequestMethod.GET)
    public String showTeacherNameList(HttpServletRequest request, HttpServletResponse response) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/teacher/showTeacherNameListAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showTeacherNameListAjax", method = RequestMethod.GET)
    public void showTeacherNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String gradeName = request.getParameter("gradeName");
        System.out.println("gradeName:" + gradeName);
        try {
            List<String> teacherTeacherNameList = null;

            if(gradeName != null && gradeName.trim().length() > 0 && teacherService.isGradeName(gradeName)) {
                teacherTeacherNameList = teacherService.getTeacherNameListByGradeName(gradeName);
            } else {
                teacherTeacherNameList = teacherService.getTeacherNameList();
            }

            for (int i = 0; i < teacherTeacherNameList.size(); i++) {
                jsonObject.put("" + i, teacherTeacherNameList.get(i));
            }
            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
