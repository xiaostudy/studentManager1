package com.xiaostudy.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.domain.Results;
import com.xiaostudy.service.ResultsService;
import com.xiaostudy.service.StudentService;
import com.xiaostudy.service.SubjectService;
import com.xiaostudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/results")
@Controller
public class ResultsController {

    @Autowired
    private ResultsService resultsService;

    @RequestMapping(value = "/showResultsList", method = RequestMethod.GET)
    public String showResultsList(HttpServletRequest request) {
        boolean b = IndexController.islogin(request);
        if(b == false) {
            return "login";
        }

        List<Results> resultsAll = resultsService.getResultsAll();

        if (resultsAll == null) {
            request.setAttribute("error", "服务器出错了！");
            return "results/error";
        }

        request.setAttribute("resultsAll", resultsAll);
        return "results/show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateResults(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/results/updateAjax";
        }

        return "teacher/show";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateResultsAjax(HttpServletRequest request, HttpServletResponse response) {
        String newResultsNumber = request.getParameter("newResultsNumber");
        String newStudentName = request.getParameter("newStudentName");
        String newGradeName = request.getParameter("newGradeName");
        String newTestName = request.getParameter("newTestName");
        String newSubjectName = request.getParameter("newSubjectName");
        String newScore = request.getParameter("newScore");
        String oldResultsNumber = request.getParameter("oldResultsNumber");

        System.out.println("newResultsNumber:" + newResultsNumber);
        System.out.println("newStudentName:" + newStudentName);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newTestName:" + newTestName);
        System.out.println("newSubjectName:" + newSubjectName);
        System.out.println("newScore:" + newScore);
        System.out.println("oldResultsNumber:" + oldResultsNumber);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台

        jsonObject.put("status", "error");
        if (oldResultsNumber == null || oldResultsNumber.trim().length() <= 0 || oldResultsNumber.trim().length() > 40 || !oldResultsNumber.trim().matches("[0-9]*")) {
            jsonObject.put("message", "非法操作！");
            b = false;
        }

        Results results = null;
        if (b) {
            results = resultsService.getResultsByResultsNumber(oldResultsNumber.trim());
            jsonObject.put("oldResultsNumber", results.getResultsNumber());
            jsonObject.put("oldStudentName", results.getStudent().getStudentName());
            jsonObject.put("oldGradeName", results.getTest().getSubject().getGrade().getGradeName());
            jsonObject.put("oldTestName", results.getTest().getTestName());
            jsonObject.put("oldSubjectName", results.getTest().getSubject().getSubjectName());
            jsonObject.put("oldScore", results.getScore());
        }

        if(b) {
            if(newResultsNumber == null || newResultsNumber.trim().length() <= 0) {
                jsonObject.put("message", "成绩号为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(!newResultsNumber.trim().matches("[0-9]*")) {
                jsonObject.put("message", "成绩号含有非数字！");
                b = false;
            }
        }

        if(b) {
            if(newResultsNumber.trim().length() > 15) {
                jsonObject.put("message", "成绩号长度大于15！");
                b = false;
            }
        }

        if(b) {
            if(!newResultsNumber.trim().equals(oldResultsNumber.trim()) && resultsService.getResultsByResultsNumber(newResultsNumber.trim()) != null) {
                jsonObject.put("message", "成绩号已存在！");
                b = false;
            }
        }

        if(b) {
            if(newStudentName == null || newStudentName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学生！如果没有学生请先创建学生！");
                b = false;
            }
        }

        if(b) {
            if(newStudentName.trim().length() > 40 || resultsService.isStudentName(newStudentName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
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
            if(newGradeName.trim().length() > 40 || resultsService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newTestName == null || newTestName.trim().length() <= 0) {
                jsonObject.put("message", "请选择考试！如果没有考试请先创建考试！");
                b = false;
            }
        }

        if(b) {
            if(newTestName.trim().length() > 40 && resultsService.isTestName(newTestName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName == null || newSubjectName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学科！如果没有学科请先创建学科！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName.trim().length() > 40 || resultsService.isSubjectName(newSubjectName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newScore == null || newScore.trim().length() <= 0) {
                jsonObject.put("message", "分数为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(newScore.trim().length() > 3) {
                jsonObject.put("message", "分数长度大于3！");
                b = false;
            }
        }

        if(b) {
            if(!newScore.matches("[0-9]*")) {
                jsonObject.put("message", "分数有非数字！包括小数点！");
                b = false;
            }
        }

        if(b) {
            Results results1 = resultsService.setResults(newResultsNumber, newGradeName, newStudentName, newTestName, newSubjectName, new Integer(newScore));
            if(results.equals(results1)) {
                jsonObject.put("message", "成绩信息没有变！");
            } else {
                boolean b1 = resultsService.updateResults(results1);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "成绩修改成功！");
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
    public String insertResults(HttpServletRequest request, String subjectId, String subjectName) {
        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/results/insertAjax";
        }

        return "redirect:/subject/showSubjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxResults(HttpServletRequest request, HttpServletResponse response) {

        String newResultsNumber = request.getParameter("newResultsNumber");
        String newStudentName = request.getParameter("newStudentName");
        String newTestName = request.getParameter("newTestName");
        String newGradeName = request.getParameter("newGradeName");
        String newSubjectName = request.getParameter("newSubjectName");
        String newScore = request.getParameter("newScore");

        System.out.println("newResultsNumber:" + newResultsNumber);
        System.out.println("newStudentName:" + newStudentName);
        System.out.println("newTestName:" + newTestName);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("newSubjectName:" + newSubjectName);
        System.out.println("newScore:" + newScore);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        jsonObject.put("status", "error");
        if (b) {
            if (newResultsNumber == null || newResultsNumber.trim().length() <= 0) {
                jsonObject.put("message", "成绩号为空或只有空格！");
                b = false;
            }
        }

        if (b) {
            if (!newResultsNumber.matches("[0-9]*")) {
                jsonObject.put("message", "成绩号含有非数字！");
                b = false;
            }
        }

        if (b) {
            if (newResultsNumber.trim().length() > 15) {
                jsonObject.put("message", "成绩号长度大于15！");
                b = false;
            }
        }

        if (b) {
            if (resultsService.getResultsByResultsNumber(newResultsNumber.trim()) != null) {
                jsonObject.put("message", "成绩号已存在！");
                b = false;
            }
        }

        if(b) {
            if(newStudentName == null || newStudentName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学生！如果没有学生请先创建学生！");
                b = false;
            }
        }

        if(b) {
            if(newStudentName.trim().length() > 40 || resultsService.isStudentName(newStudentName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
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
            if(newGradeName.trim().length() > 40 || resultsService.isGradeName(newGradeName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newTestName == null || newTestName.trim().length() <= 0) {
                jsonObject.put("message", "请选择考试！如果没有考试请先创建考试！");
                b = false;
            }
        }

        if(b) {
            if(newTestName.trim().length() > 40 && resultsService.isTestName(newTestName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName == null || newSubjectName.trim().length() <= 0) {
                jsonObject.put("message", "请选择学科！如果没有学科请先创建学科！");
                b = false;
            }
        }

        if(b) {
            if(newSubjectName.trim().length() > 40 || resultsService.isSubjectName(newSubjectName.trim()) == false) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }
        }

        if(b) {
            List<Results> resultsList = resultsService.getResultsByStudentName(newGradeName.trim(), newStudentName.trim());
            if(resultsList != null && resultsList.size() > 0) {
                for(Results results2 : resultsList) {
                    if(results2 != null && newTestName.trim().equals(results2.getTest().getTestName()) && newSubjectName.trim().equals(results2.getStudent().getStudentName())) {
                        jsonObject.put("message", "该学生这年份的学科成绩已存在！");
                        b = false;
                    }
                }
            }
        }

        if(b) {
            if(newScore == null || newScore.trim().length() <= 0) {
                jsonObject.put("message", "分数为空或只有空格！");
                b = false;
            }
        }

        if(b) {
            if(newScore.trim().length() > 3) {
                jsonObject.put("message", "分数长度大于3！");
                b = false;
            }
        }

        if(b) {
            if(!newScore.trim().matches("[0-9]*")) {
                jsonObject.put("message", "分数含有非数字，包括小数点！");
                b = false;
            }
        }

        if(b) {
            Results results = resultsService.setResults(newResultsNumber, newGradeName, newStudentName, newTestName, newSubjectName, new Integer(newScore));
            if(results != null) {
                boolean b1 = resultsService.insert(results);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("status", "ok");
                    jsonObject.put("message", "创建成绩成功！");
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
    public String deleteResults(HttpServletRequest request) {

        String requestHeader = request.getHeader("X-Requested-With");
        String requestHeader2 = request.getParameter("X-Requested-With");//原生ajax设置参数是否为异步请求
        if (requestHeader != null || requestHeader2 != null) {
            return "forward:/results/deleteAjax";
        }

        return "redirect:/results/showResultsList";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxResults(HttpServletRequest request, HttpServletResponse response) {

        String resultsNumber = request.getParameter("resultsNumber");

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if (b) {
                if (resultsNumber == null || resultsNumber.trim().length() <= 0) {
                    jsonObject.put("message", "成绩号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查学科号是否只有数字
                if (!resultsNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "成绩号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (resultsService.getResultsByResultsNumber(resultsNumber.trim()) == null) {
                    jsonObject.put("message", "该成绩已不存在！");
                    b = false;
                }
            }

            if(b) {
                boolean b1 = resultsService.deleteResults(resultsNumber.trim());
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("status", "ok");
                    jsonObject.put("message", "删除成绩成功！");
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
