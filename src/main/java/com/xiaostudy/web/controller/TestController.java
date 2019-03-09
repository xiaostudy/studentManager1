package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Test;
import com.xiaostudy.service.TestService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/showTestList", method = RequestMethod.GET)
    public String showTestList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Test> testAll = testService.getTestAll();

        if (testAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "test/error";
        }

        request.setAttribute("testAll", testAll);
        return "test/show";
    }

    @RequestMapping(value = "/showTestNameList", method = RequestMethod.GET)
    public String showTestNameList(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/test/showTestNameListAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showTestNameListAjax", method = RequestMethod.GET)
    public void showTestNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String gradeName = request.getParameter("gradeName");
        System.out.println("gradeName:" + gradeName);
        try {
            List<String> testNameList = null;

            if(gradeName != null && gradeName.trim().length() > 0) {
                testNameList = testService.getTestNameByGradeName(gradeName.trim());
            } else {
                testNameList = testService.getTestNameList();
            }

            for (int i = 0; i < testNameList.size(); i++) {
                jsonObject.put("" + i, testNameList.get(i));
            }
            response.getWriter().print(jsonObject.toString());
            System.out.println("考试名称列表：" + jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/showSubjectNameList", method = RequestMethod.GET)
    public String showSubjectNameList(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/test/showSubjectNameListAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showSubjectNameListAjax", method = RequestMethod.GET)
    public void showSubjectNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String gradeName = request.getParameter("gradeName");
        String testName = request.getParameter("testName");
        System.out.println("gradeName:" + gradeName);
        System.out.println("testName:" + testName);
        try {
            List<String> subjectNameList = null;

            if(gradeName != null && gradeName.trim().length() > 0 && testName != null && testName.trim().length() > 0 && testService.isTestName(testName.trim()) && testService.isGradeName(gradeName.trim())) {
                subjectNameList = testService.getSubjectNameByGradeNameTestName(gradeName, testName);
            } else {
                subjectNameList = testService.getSubjectNameList();
            }

            for (int i = 0; i < subjectNameList.size(); i++) {
                jsonObject.put("" + i, subjectNameList.get(i));
            }
            System.out.println(jsonObject.toString());
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
            return "forward:/test/showGradeNameListAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/showGradeNameListAjax", method = RequestMethod.GET)
    public void showGradeNameListAjax(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<String> gradeNameList = null;

            gradeNameList = testService.getGradeNameList();

            for (int i = 0; i < gradeNameList.size(); i++) {
                jsonObject.put("" + i, gradeNameList.get(i));
            }
            System.out.println(jsonObject.toString());
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTest(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/test/updateAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateTestAjax(HttpServletRequest request, HttpServletResponse response) {
        String newTestNumber = request.getParameter("newTestNumber");
        String newTestName = request.getParameter("newTestName");
        String newSubjectName = request.getParameter("newSubjectName");
        String newGradeName = request.getParameter("newGradeName");
        String oldTestNumber = request.getParameter("oldTestNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");
        if (oldTestNumber == null || oldTestNumber.trim().length() <= 0 || oldTestNumber.trim().length() > 40 || !oldTestNumber.trim().matches("[0-9]*")) {
            jsonObject.put("message", "非法操作！");
            b = false;
        }

        Test test = null;
        if (b) {
            test = testService.getTestByTestNumber(oldTestNumber.trim());
            jsonObject.put("oldTestNumber", test.getTestNumber());
            jsonObject.put("oldTestName", test.getTestName());
            jsonObject.put("oldGradeName", test.getSubject().getGrade().getGradeName());
            jsonObject.put("oldSubjectName", test.getSubject().getSubjectName());
        }

        if(b) {
            if(newTestNumber == null || newTestNumber.trim().length() <= 0) {
                jsonObject.put("message", "考试号为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(!newTestNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "考试号含有非数字！");
                b = false;
            }
        }

        if(b) {
            if(newTestNumber.trim().length() > 15) {
                jsonObject.put("message", "考试号长度大于15！");
                b = false;
            }
        }

        if(b) {
            if(!newTestNumber.trim().equals(oldTestNumber.trim()) && testService.getTestByTestNumber(newTestNumber.trim()) != null) {
                jsonObject.put("message", "考试号已存在！");
                b = false;
            }
        }

        if(b) {
            if(newTestName == null || newTestName.trim().length() <= 0) {
                jsonObject.put("message", "考试名称为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(newTestName.trim().length() > 40) {
                jsonObject.put("message", "考试名称长度过长，中文长度大于39或英文长度大于40！");
                b = false;
            }
        }

        if(b) {
            if(newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级！如果没有年级请先创建年级！");
                b = false;
            }
        }

        if(b) {
            if(newGradeName.trim().length() > 40 || testService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName == null || newSubjectName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学科！如果没有学科请创建学科");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName.trim().length() > 40 || testService.isSubjectName(newSubjectName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if (!oldTestNumber.trim().equals(newTestNumber.trim()) && testService.isTestNameGradeNameSubjectName(newTestName, newGradeName, newSubjectName)) {
                jsonObject.put("message", "该考试已存在！");
                b = false;
            }
        }

        if(b) {
            Test test1 = testService.setTest(newTestNumber, newTestName, newGradeName, newSubjectName);
            if(test.equals(test1)) {
                jsonObject.put("message", "考试信息没变！");
            } else {
                test1.setTestId(test.getTestId());
                boolean b1 = testService.updateTest(test1);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "考试信息修改成功！");
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

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertTest(HttpServletRequest request, String subjectId, String subjectName) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/test/insertAjax";
        }

        return "redirect:/subject/showSubjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxTest(HttpServletRequest request, HttpServletResponse response) {

        String newTestNumber = request.getParameter("newTestNumber");
        String newTestName = request.getParameter("newTestName");
        String newGradeName = request.getParameter("newGradeName");
        String newSubjectName = request.getParameter("newSubjectName");

        System.out.println("newTestNumber:" + newTestNumber);
        System.out.println("newTestName:" + newTestName);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newSubjectName:" + newSubjectName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        jsonObject.put("status", "error");
        if (b) {
            if (newTestNumber == null || newTestNumber.trim().length() <= 0) {
                jsonObject.put("message", "考试号为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (!newTestNumber.matches("[0-9]*")) {
                jsonObject.put("message", "考试号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newTestNumber.trim().length() > 11) {
                jsonObject.put("message", "考试号长度大于11！");
                b = false;
            }
        }

        if (b) {
            if (testService.getTestByTestNumber(newTestNumber.trim()) != null) {
                jsonObject.put("message", "考试号已存在！");
                b = false;
            }
        }

        if(b) {
            if(newTestName == null || newTestName.trim().length() <= 0) {
                jsonObject.put("message", "考试名称为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(newTestName.trim().length() > 40) {
                jsonObject.put("message", "考试名称长度过长，中文长度大于13或英文长度大于40！");
                b = false;
            }
        }

        if(b) {
            if(newGradeName == null || newGradeName.trim().length() <= 0) {
                jsonObject.put("message", "请选择年级！如果没有年级请先创建年级");
                b = false;
            }
        }

        if(b) {
            if(newGradeName.trim().length() > 40 || testService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName == null || newSubjectName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学科！如果没有学科请先创建学科");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName.trim().length() > 40 || testService.isSubjectName(newSubjectName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(testService.isTestNameGradeNameSubjectName(newTestName, newGradeName, newSubjectName)) {
                jsonObject.put("message", "该考试已存在！");
                b = false;
            }
        }

        if(b) {
            Test test = testService.setTest(newTestNumber, newTestName, newGradeName, newSubjectName);
            if(test != null) {
                boolean b1 = testService.insertTest(test);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "创建考试成功！");
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
    public String deleteTest(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/test/deleteAjax";
        }

        return "redirect:/test/showTestList";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxTest(HttpServletRequest request, HttpServletResponse response) {

        String testNumber = request.getParameter("testNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (testNumber == null || testNumber.trim().length() <= 0) {
                    jsonObject.put("message", "考试号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!testNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "考试号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (testService.getTestByTestNumber(testNumber.trim()) == null) {
                    jsonObject.put("message", "该考试已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b1 = testService.deleteTest(testNumber);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "删除考试成功！");
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
