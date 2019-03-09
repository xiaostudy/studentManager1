<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/18
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xiaostudy.domain.Student" %>
<%@page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>学生管理系统</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/subject.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/teacher.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grade.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/clazz.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/student.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/test.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/results.js"></script>

    <script type="text/javascript">
        /*window.onbeforeunload = function ()
        {
            var json = {"sessionDel": "delete"};
            ajax("in_login", "post", json, "json", true);
        }*/

    </script>
</head>
<body>
<div id="div">
    <a href="#" onclick="show('grade/showGradeList')"><span>年级列表</span></a>
    <a href="#" onclick="show('subject/showSubjectList')"><span>学科列表</span></a>
    <a href="#" onclick="show('teacher/showTeacherList')"><span>教师列表</span></a>
        <a href="#" onclick="show('clazz/showClazzList')"><span>班级列表</span></a>
        <a href="#" onclick="show('student/showStudentList')"><span>学生列表</span></a>
        <a href="#" onclick="show('test/showTestList')"><span>考试列表</span></a>
        <a href="#" onclick="show('results/showResultsList')"><span>成绩列表</span></a>
</div>


<hr/>
<div id="show">欢迎使用！</div>
</body>
</html>
