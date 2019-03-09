package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Subject;
import com.xiaostudy.service.SubjectService;
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

@RequestMapping("/subject")
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/showSubjectList", method = RequestMethod.GET)
    public String showSubjectList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        String page = request.getParameter("page");

        System.out.println("page:" + page);

        Integer i = 1;
        if(page == null  || page.trim().length() <= 0 || !page.matches("[0-9]*")) {
        } else {
            i = new Integer(page.trim());
        }
        List<Subject> subjectAll = subjectService.getSubjectPages(i);

        if (subjectAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "subject/error";
        }

        List<Integer> pages = subjectService.getPages();

        request.setAttribute("subjectAll", subjectAll);
        request.setAttribute("pages", pages);
        return "subject/show";
    }

    @ResponseBody
    @RequestMapping(value = "/pagesAjax", method = RequestMethod.GET)
    public void pagesAjax(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page");

        System.out.println("page:" + page);


        JSONObject jsonObject = new JSONObject();
        boolean b = true;
        jsonObject.put("status", "error");
        if(b) {
            if(!page.matches("[0-9]*")) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }
        if(b) {
            List<Subject> subjectAll = subjectService.getSubjectPages(new Integer(page.trim()));
            jsonObject.put("status", "ok");
            jsonObject.put("list", subjectAll);
        }

        try {
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/in_insert", method = RequestMethod.GET)
    public String in_insertSubject() {
        return "subject/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertSubject(HttpServletRequest request) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/subject/insertAjax";
        }

        return "redirect:/subject/showSubjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxSubject(HttpServletRequest request, HttpServletResponse response) {

        String newSubjectNumber = request.getParameter("newSubjectNumber");
        String newSubjectName = request.getParameter("newSubjectName");
        String newGradeName = request.getParameter("newGradeName");

        System.out.println("newSubjectNumber:" + newSubjectNumber);
        System.out.println("newSubjectName:" + newSubjectName);
        System.out.println("newGradeName:" + newGradeName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (newSubjectNumber == null || newSubjectNumber.trim().length() <= 0) {
                    jsonObject.put("message", "学科号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (!newSubjectNumber.matches("[0-9]*")) {
                    jsonObject.put("message", "学科号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (newSubjectNumber.trim().length() > 11) {
                    jsonObject.put("message", "学科号长度大于11！");
                    b = false;
                }
            }

            if (b) {
                if (subjectService.isSubjectNumber(newSubjectNumber.trim())) {
                    jsonObject.put("message", "学科号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "请选择年级！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName.trim().length() > 40 || subjectService.isGradeName(newGradeName.trim()) == false) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            if (b) {
                if (newSubjectName == null || newSubjectName.trim().length() <= 0) {
                    jsonObject.put("message", "学科名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newSubjectName.trim().length() > 40) {
                    jsonObject.put("message", "学科名称长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {
                if(subjectService.isSubjectNameInGradeName(newSubjectName, newGradeName)) {
                    jsonObject.put("message", "该年级的学科名称已存在！");
                    b = false;
                }
            }

            if (b) {
                Subject subject = subjectService.setSubjectInGrade(newSubjectNumber.trim(), newSubjectName, newGradeName);
                boolean b2 = subjectService.insertSubject(subject);
                if (b2 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                }else {
                    jsonObject.put("message", "添加学科成功！");
                    jsonObject.put("status", "ok");
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSubject(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/subject/deleteAjax";
        }

        return "redirect:/subject/showSubjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxSubject(HttpServletRequest request, HttpServletResponse response) {

        String subjectNumber = request.getParameter("subjectNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (subjectNumber == null || subjectNumber.trim().length() <= 0) {
                    jsonObject.put("message", "学科号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!subjectNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "学科号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (subjectService.isSubjectNumber(subjectNumber.trim()) == false) {
                    jsonObject.put("message", "该学科已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b2 = subjectService.deleteSubject(subjectService.getSubjectBySubjectNumber(subjectNumber.trim()));
                if (b2 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "删除学科成功！");
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
    public String updateSubject(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/subject/updateAjax";
        }

        return "redirect:/subject/showSubjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateSubjectAJAX(HttpServletRequest request, HttpServletResponse response) {

        String newSubjectNumber = request.getParameter("newSubjectNumber");
        String newGradeName = request.getParameter("newGradeName");
        String newSubjectName = request.getParameter("newSubjectName");
        String oldSubjectNumber = request.getParameter("oldSubjectNumber");

        System.out.println("newSubjectNumber:" + newSubjectNumber);
        System.out.println("newSubjectName:" + newSubjectName);
        System.out.println("oldSubjectNumber:" + oldSubjectNumber);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台
        try {
            jsonObject.put("status", "error");
            if (oldSubjectNumber == null || oldSubjectNumber.trim().length() <= 0 || oldSubjectNumber.trim().length() > 11 || !oldSubjectNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }

            Subject subject = null;
            if (b) {
                subject = subjectService.getSubjectBySubjectNumber(oldSubjectNumber.trim());
                jsonObject.put("oldSubjectNumber", oldSubjectNumber.trim());
                jsonObject.put("oldSubjectName", subject.getSubjectName());
                jsonObject.put("oldGradeName", subject.getGrade().getGradeName());
            }


            if (b) {//检查学科号是否为空或只有空格
                if (newSubjectNumber == null || newSubjectNumber.trim().length() <= 0) {
                    jsonObject.put("message", "学科号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!newSubjectNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "学科号含有非数字！");
                    b = false;
                }
            }

            if (b) {//检查学科号长度是否大于11
                if (newSubjectNumber.trim().length() > 11) {
                    jsonObject.put("message", "学科号长度大于11！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "请选择年级！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName.trim().length() > 40 || subjectService.isGradeName(newGradeName.trim()) == false) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }

            if (b) {//检查学科名称是否为空或只有空格
                if (newSubjectName == null || newSubjectName.trim().length() <= 0) {
                    jsonObject.put("message", "学科名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科名称长度是否大于40
                if (newSubjectName.trim().length() > 40) {
                    jsonObject.put("message", "学科名称长度过长，中文长度大于39或英文长度大于40！");
                    b = false;
                }
            }

            if(b) {
                if(subjectService.isSubjectNameInGradeName(newSubjectName, newGradeName)) {
                    if(!newGradeName.trim().equals(subject.getGrade().getGradeName()) || !newSubjectName.trim().equals(subject.getSubjectName())) {
                        jsonObject.put("message", "该年级的学科已存在！");
                        b = false;
                    }
                }
            }

            if (b) {//检查学科信息是否改变
                Subject newSubject = (Subject)CommonUtil.getBean(Subject.class);
                newSubject.setSubjectName(newSubjectName.trim());
                newSubject.setSubjectNumber(newSubjectNumber.trim());
                newSubject.setGrade(subjectService.getGrade(newGradeName));
                if(subjectService.equals(subject, newSubject)) {
                    jsonObject.put("message", "学科信息没变！");
                    jsonObject.put("status", "ok");
                    b = false;
                }
            }

            if (b) {//检查学科号改变后是否与其他已存在的相同
                if (!oldSubjectNumber.trim().equals(newSubjectNumber.trim()) && subjectService.isSubjectNumber(newSubjectNumber.trim())) {
                    jsonObject.put("message", "学科号已存在！");
                    b = false;
                }
            }

            if (b) {//修改学科
                subject = subjectService.setSubjectInGradeToGradeName(subject, newGradeName);
                if(subject != null) {
                    boolean b1 = subjectService.updateSubject(subject);
                    if(b1 == false) {
                        jsonObject.put("message", "服务器出错！");
                        b = false;
                    } else {
                        jsonObject.put("message", "学科信息修改成功！");
                        jsonObject.put("status", "ok");
                    }
                } else {
                    jsonObject.put("message", "请确认信息是否正确！");
                    b = false;
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/showSubjectNameListByGradeName", method = RequestMethod.GET)
    public String showSubjectNameListByGradeName(HttpServletRequest request, HttpServletResponse response) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/subject/showSubjectNameListByGradeNameAjax";
        }

        return "subject/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showSubjectNameListByGradeNameAjax", method = RequestMethod.GET)
    public void showSubjectNameListByGradeNameAjax(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String gradeName = request.getParameter("gradeName");
        System.out.println("gradeName:" + gradeName);
        boolean b = true;
        try {
            if(b) {
                if(gradeName == null || gradeName.trim().length() <= 0 || gradeName.trim().length() > 40 || subjectService.isGradeName(gradeName) == false) {
                    jsonObject.put("error", "非法操作！");
                    b = false;
                }
            }

            if(b) {
                List<Subject> subjectList = subjectService.getSubjectByGradeName(gradeName.trim());
                for(int i = 0; i < subjectList.size(); i++ ) {
                    jsonObject.put("" + i, subjectList.get(i).getSubjectName());
                }
            }

            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
