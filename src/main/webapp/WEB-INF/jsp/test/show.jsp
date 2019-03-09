<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xiaostudy.domain.Test" %>
<%@page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示考试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/test.js"></script>
</head>
<body>
<div >
    <a href="#" id="addTest" onclick="addTest()" style="width: auto">添加考试</a>
    <a href="#" id="inAddTest" onclick="inAddTest()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="考试号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="考试名称" readonly="readonly"></td>
        <td ><input type="text" class="top" value="年级" readonly="readonly"></td>
        <td ><input type="text" class="top" value="学科" readonly="readonly"></td>
    </tr>
    <c:forEach var="test" items="${testAll}" step="1">
        <tr id="deleteTest${test.testNumber}">
            <td ><input type="text" id="testNumber${test.testNumber}" class="testEdit${test.testNumber}" value="${test.testNumber}" readonly="true"></td>
            <td ><input type="text" id="testName${test.testNumber}" class="testEdit${test.testNumber}" value="${test.testName}" readonly="true"></td>
            <td ><input type="text" id="gradeName${test.testNumber}" class="testEdit${test.testNumber}" value="${test.subject.grade.gradeName}" readonly="true"></td>
            <td ><input type="text" id="subjectName${test.testNumber}" class="testEdit${test.testNumber}" value="${test.subject.subjectName}" readonly="true"></td>
            <td ><a href="#" class="editTest" id="editTest${test.testNumber}" onclick="testEdit('${test.testNumber}')" >编辑</a>
            <a href="#" class="saveEditTest" id="saveEditTest${test.testNumber}" onclick="saveTestEdit('${test.testNumber}')" >保存</a> </td>
            <td ><a href="#" onclick="deleteTest('${test.testNumber}')" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>
</body>
</html>
