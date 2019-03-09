package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Grade;
import com.xiaostudy.service.GradeService;
import com.xiaostudy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/grade")
@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "/showGradeList", method = RequestMethod.GET)
    public String showGradeList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Grade> gradeAll = gradeService.getGradeAll();

        if (gradeAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "grade/error";
        }

        request.setAttribute("gradeAll", gradeAll);
        return "grade/show";
    }

    @RequestMapping(value = "/in_insert", method = RequestMethod.GET)
    public String in_insertGrade() {
        return "grade/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertGrade(HttpServletRequest request, String gradeNumber, String gradeName) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/grade/insertAjax";
        }

        return "redirect:/grade/showGradeList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxGrade(HttpServletRequest request, HttpServletResponse response) {

        String newGradeNumber = request.getParameter("newGradeNumber");
        String newGradeName = request.getParameter("newGradeName");

        System.out.println("newGradeNumber:" + newGradeNumber);
        System.out.println("newGradeName:" + newGradeName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (newGradeNumber == null || newGradeNumber.trim().length() <= 0) {
                    jsonObject.put("message", "年级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (!newGradeNumber.matches("[0-9]*")) {
                    jsonObject.put("message", "年级号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeNumber.trim().length() > 11) {
                    jsonObject.put("message", "年级号长度大于11！");
                    b = false;
                }
            }

            if (b) {
                if (gradeService.isGradeNumber(newGradeNumber.trim())) {
                    jsonObject.put("message", "年级号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "年级名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName.trim().length() > 40) {
                    jsonObject.put("message", "年级名称长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {
                if (gradeService.isGradeName(newGradeName.trim())) {
                    jsonObject.put("message", "年级名称已存在！");
                    b = false;
                }
            }

            if (b) {
                Grade grade = (Grade) CommonUtil.getBean(Grade.class);
                grade.setGradeNumber(newGradeNumber.trim());
                grade.setGradeName(newGradeName.trim());
                boolean b1 = gradeService.insertGrade(grade);
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "添加年级成功！");
                    jsonObject.put("status", "ok");
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteGrade(HttpServletRequest request) {

        String gradeNumber = request.getParameter("gradeNumber");

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/grade/deleteAjax";
        }

        return "redirect:/grade/showGradeList";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxGrade(HttpServletRequest request, HttpServletResponse response) {

        String gradeNumber = request.getParameter("gradeNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (gradeNumber == null || gradeNumber.trim().length() <= 0) {
                    jsonObject.put("message", "年级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级号是否只有数字
                if (!gradeNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "年级号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (gradeService.isGradeNumber(gradeNumber.trim()) == false) {
                    jsonObject.put("message", "该年级已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b2 = gradeService.deleteGrade(gradeService.getGradeByGradeNumber(gradeNumber.trim()));
                if (b2 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "删除年级成功！");
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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGrade(HttpServletRequest request) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/grade/updateAjax";
        }

        return "redirect:/grade/showGradeList";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateGradeAJAX(HttpServletRequest request, HttpServletResponse response) {

        String newGradeNumber = request.getParameter("newGradeNumber");
        String newGradeName = request.getParameter("newGradeName");
        String oldGradeNumber = request.getParameter("oldGradeNumber");

        System.out.println("newGradeNumber:" + newGradeNumber);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("oldGradeNumber:" + oldGradeNumber);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台
        try {
            jsonObject.put("status", "error");
            if (oldGradeNumber == null || oldGradeNumber.trim().length() <= 0 || oldGradeNumber.trim().length() > 11 || !oldGradeNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }

            Grade oldGrade = null;
            if (b) {
                oldGrade = gradeService.getGradeByGradeNumber(oldGradeNumber.trim());
                jsonObject.put("oldGradeNumber", oldGradeNumber);
                jsonObject.put("oldGradeName", oldGrade.getGradeName());
            }


            if (b) {//检查年级号是否为空或只有空格
                if (newGradeNumber == null || newGradeNumber.trim().length() <= 0) {
                    jsonObject.put("message", "年级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级号是否只有数字
                if (!newGradeNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "年级号含有非数字！");
                    b = false;
                }
            }

            if (b) {//检查年级号长度是否大于11
                if (newGradeNumber.trim().length() > 11) {
                    jsonObject.put("message", "年级号长度大于11！");
                    b = false;
                }
            }

            if (b) {//检查年级名称是否为空或只有空格
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "年级名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级名称长度是否大于40
                if (newGradeName.trim().length() > 40) {
                    jsonObject.put("message", "年级名称长度过长，中文长度大于39或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {//检查年级信息是否改变
                if (oldGradeNumber.trim().equals(newGradeNumber.trim()) && oldGrade.getGradeName().equals(newGradeName.trim())) {
                    jsonObject.put("message", "年级信息没变！");
                    jsonObject.put("status", "ok");
                    b = false;
                }
            }

            if (b) {//检查年级号改变后是否与其他已存在的相同
                if (!oldGradeNumber.trim().equals(newGradeNumber.trim()) && gradeService.isGradeNumber(newGradeNumber.trim())) {
                    jsonObject.put("message", "年级号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (!newGradeName.trim().equals(oldGrade.getGradeName()) && gradeService.isGradeName(newGradeName.trim())) {
                    jsonObject.put("message", "年级名称已存在！");
                    b = false;
                }
            }

            if(b) {
                oldGrade.setGradeNumber(newGradeNumber.trim());
                oldGrade.setGradeName(newGradeName.trim());
                boolean b1 = gradeService.updateGrade(oldGrade);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "年级信息修改成功！");
                    jsonObject.put("status", "ok");
                    b = false;
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/showGradeNameList", method = RequestMethod.GET)
    public String showGradeNameList(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/grade/showGradeNameListAjax";
        }

        List<String> gradeNameList = gradeService.getGradeNameList();
        request.setAttribute("gradeNameList", gradeNameList);
        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showGradeNameListAjax", method = RequestMethod.GET)
    public void showGradeNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject jsonObject = new JSONObject();
            List<String> gradeNameList = gradeService.getGradeNameList();

            for (int i = 0; i < gradeNameList.size(); i++) {
                jsonObject.put("" + i, gradeNameList.get(i));
            }
            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
