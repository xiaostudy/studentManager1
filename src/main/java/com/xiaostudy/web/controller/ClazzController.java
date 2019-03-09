package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Clazz;
import com.xiaostudy.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/clazz")
@Controller
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @RequestMapping(value = "/showClazzList", method = RequestMethod.GET)
    public String showClazzList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Clazz> clazzAll = clazzService.getClazzAll();

        if (clazzAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "clazz/error";
        }

        request.setAttribute("clazzAll", clazzAll);
        return "clazz/show";
    }

    @RequestMapping(value = "/showClazzNameList", method = RequestMethod.GET)
    public String showClazzNameList(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/clazz/showClazzNameListAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showClazzNameListAjax", method = RequestMethod.GET)
    public void showClazzNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        String gradeName = request.getParameter("gradeName");
        JSONObject jsonObject = new JSONObject();

        List<Clazz> clazzList = null;
        if (gradeName != null || gradeName.trim().length() > 0) {
            clazzList = clazzService.getClazzByGradeName(gradeName.trim());
        } else {
            clazzList = clazzService.getClazzAll();
        }

        for (int i = 0; i < clazzList.size(); i++) {
            jsonObject.put("" + i, clazzList.get(i).getClazzName());
        }

        try {
            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateClazz(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/clazz/updateAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateClazzAjax(HttpServletRequest request, HttpServletResponse response) {
        String newClazzNumber = request.getParameter("newClazzNumber");
        String newClazzName = request.getParameter("newClazzName");
        String newGradeName = request.getParameter("newGradeName");
        String newTeacherName = request.getParameter("newTeacherName");
        String oldClazzNumber = request.getParameter("oldClazzNumber");

        System.out.println("newClazzNumber:" + newClazzNumber);
        System.out.println("newClazzName:" + newClazzName);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newTeacherName:" + newTeacherName);
        System.out.println("oldClazzNumber:" + oldClazzNumber);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");
        if (oldClazzNumber == null || oldClazzNumber.trim().length() <= 0 || oldClazzNumber.trim().length() > 40 || !oldClazzNumber.trim().matches("[0-9]*")) {
            jsonObject.put("message", "非法操作！原班号");
            b = false;
        }

        Clazz clazz = null;
        if (b) {
            clazz = clazzService.getClazzByClazzNumber(oldClazzNumber.trim());
            jsonObject.put("oldClazzNumber", clazz.getClazzNumber());
            jsonObject.put("oldClazzName", clazz.getClazzName());
            jsonObject.put("oldGradeName", clazz.getGrade().getGradeName());
            jsonObject.put("oldTeacherName", clazz.getTeacher().getTeacherName());
        }

        if (b) {
            if (newClazzNumber == null || newClazzNumber.trim().length() <= 0) {
                jsonObject.put("message", "班号为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (!newClazzNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "班号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newClazzNumber.trim().length() > 11) {
                jsonObject.put("message", "班号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (!newClazzNumber.trim().equals(oldClazzNumber.trim()) && clazzService.getClazzByClazzNumber(newClazzNumber.trim()) != null) {
                jsonObject.put("message", "班号已存在！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName == null || newClazzName.trim().length() <= 0) {
                jsonObject.put("message", "班级名称为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName.trim().length() > 40) {
                jsonObject.put("message", "班级名称长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级！如果没有年级请创建年级！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName.trim().length() > 40 || clazzService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！年级");
                b = false;
            }
        }

        if (b) {
            if (!clazz.getGrade().getGradeName().equals(newGradeName.trim()) || !clazz.getClazzName().equals(newClazzName.trim())) {
                if (clazzService.isClazzInGradeNameClazzName(newGradeName, newClazzName)) {
                    jsonObject.put("message", "该年级的班级已存在！");
                    b = false;
                }
            }
        }

        if (b) {
            if (newTeacherName == null || newTeacherName.trim().length() <= 0) {
                jsonObject.put("message", "请选择班主任！如果没有教师请创建教师");
                b = false;
            }
        }

        if (b) {
            if (newTeacherName.trim().length() > 40 || clazzService.isClazzInTeacherName(newTeacherName.trim()) == false) {
                jsonObject.put("message", "非法操作！班主任");
                b = false;
            }
        }

        if (b) {
            Clazz clazz1 = clazzService.setClazz(newClazzNumber.trim(), newClazzName.trim(), newGradeName.trim(), newTeacherName.trim());
            if (clazz1 != null && clazz.equals(clazz1)) {
                jsonObject.put("message", "班级信息没有改变！");
                jsonObject.put("status", "ok");
            } else {
                boolean b1 = clazzService.insertClazz(clazz1);
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("status", "ok");
                    jsonObject.put("message", "班级信息修改成功！");
                }
            }
        }

        System.out.println("jsonObject:" + jsonObject);

        try {
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertClazz(HttpServletRequest request, String gradeId, String gradeName) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/clazz/insertAjax";
        }

        return "redirect:/grade/showGradeList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxClazz(HttpServletRequest request, HttpServletResponse response) {

        String newClazzNumber = request.getParameter("newClazzNumber");
        String newClazzName = request.getParameter("newClazzName");
        String newGradeName = request.getParameter("newGradeName");
        String newTeacherName = request.getParameter("newTeacherName");

        System.out.println("newClazzNumber:" + newClazzNumber);
        System.out.println("newClazzName:" + newClazzName);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newTeacherName:" + newTeacherName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        jsonObject.put("status", "error");
        if (b) {
            if (newClazzNumber == null || newClazzNumber.trim().length() <= 0) {
                jsonObject.put("message", "班级号为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (!newClazzNumber.matches("[0-9]*")) {
                jsonObject.put("message", "班级号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newClazzNumber.trim().length() > 11) {
                jsonObject.put("message", "班级号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (clazzService.getClazzByClazzNumber(newClazzNumber.trim()) != null) {
                jsonObject.put("message", "班级号已存在！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName == null || newClazzName.trim().length() <= 0) {
                jsonObject.put("message", "班级名称为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (newClazzName.trim().length() > 40) {
                jsonObject.put("message", "班级名称长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if (b) {
            if (newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级！如果没有年级请先创建年级");
                b = false;
            }
        }

        if (b) {
            if (newGradeName.trim().length() > 40 || clazzService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            /*if(clazzService.getClazzByClazzName(newClazzName.trim()) != null && clazzService.getClazzByClazzName(newClazzName.trim()).getGrade().getGradeName().equals(newGradeName.trim())) {
                jsonObject.put("message", "该年级的班级已存在！");
                b = false;
            }*/
        }

        if (b) {
            if (newTeacherName == null || newTeacherName.trim().length() <= 0) {
                jsonObject.put("message", "请选择班主任！如果没有教师请先创建教师");
                b = false;
            }
        }

        if (b) {
            /*if(clazzService.getClazzByClazzName(newClazzName.trim()) != null && clazzService.getClazzByClazzName(newClazzName.trim()).getTeacher().getTeacherName().equals(newTeacherName.trim())) {
                jsonObject.put("message", "该班级已有该班主任");
                b = false;
            }*/
        }

        if (b) {
            if (newTeacherName.trim().length() > 40 || clazzService.isTeacherName(newTeacherName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if (b) {
            Clazz clazz = clazzService.setClazz(newClazzNumber.trim(), newClazzName.trim(), newGradeName.trim(), newTeacherName.trim());
            if (clazz != null) {
                boolean b1 = clazzService.insertClazz(clazz);
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "创建班级成功！");
                    jsonObject.put("status", "ok");
                }
            }
        }

        try {
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteClazz(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/clazz/deleteAjax";
        }

        return "redirect:/clazz/showClazzList";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxClazz(HttpServletRequest request, HttpServletResponse response) {

        String clazzNumber = request.getParameter("clazzNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (clazzNumber == null || clazzNumber.trim().length() <= 0) {
                    jsonObject.put("message", "班级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级号是否只有数字
                if (!clazzNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "班级号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (clazzService.getClazzByClazzNumber(clazzNumber.trim()) == null) {
                    jsonObject.put("message", "该班级已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b2 = clazzService.deleteClazz(clazzNumber.trim());
                if (b2 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "删除班级成功！");
                    jsonObject.put("status", "ok");
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
