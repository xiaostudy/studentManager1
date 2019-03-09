<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xiaostudy.domain.Grade" %>
<%@page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示年级</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grade.js"></script>
</head>
<body>
<div >
    <a href="#" id="addGrade" onclick="addGrade()" style="width: auto">添加年级</a>
    <a href="#" id="inAddGrade" onclick="inAddGrade()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="年级号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="年级名称" readonly="readonly"></td>
    </tr>
    <c:forEach var="grade" items="${gradeAll}" step="1">
        <tr id="deleteGrade${grade.gradeId}">
            <td ><input type="text" id="gradeId${grade.gradeId}" class="gradeEdit${grade.gradeId}" value="${grade.gradeId}" readonly="true"></td>
            <td ><input type="text" id="gradeName${grade.gradeId}" class="gradeEdit${grade.gradeId}" value="${grade.gradeName}" readonly="true"></td>
            <td ><a href="#" class="editGrade" id="editGrade${grade.gradeId}" onclick="gradeEdit(${grade.gradeId})" >编辑</a>
            <a href="#" class="saveEditGrade" id="saveEditGrade${grade.gradeId}" onclick="saveGradeEdit(${grade.gradeId})" >保存</a> </td>
            <td ><a href="#" onclick="deleteGrade(${grade.gradeId})" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>
</body>
</html>
