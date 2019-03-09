<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/18
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.xiaostudy.domain.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示学生</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/student.js"></script>

    <script type="text/javascript">
        function insert() {
            alert("insert");
        }
    </script>

</head>
<body>
<div >
    <a href="#" id="addStudent" onclick="addStudent()" style="width: auto">添加学生</a>
    <a href="#" id="inAddStudent" onclick="inAddStudent()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="学号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="姓名" readonly="readonly"></td>
        <td ><input type="text" class="top" value="性别" readonly="readonly"></td>
        <td ><input type="text" class="top" value="出生日期" readonly="readonly"></td>
        <td ><input type="text" class="top" value="家庭住址" readonly="readonly"></td>
        <td ><input type="text" class="top" value="家长姓名" readonly="readonly"></td>
        <td ><input type="text" class="top" value="家长联系方式" readonly="readonly"></td>
        <td ><input type="text" class="top" value="入学日期" readonly="readonly"></td>
        <td ><input type="text" class="top" value="年级" readonly="readonly"></td>
        <td ><input type="text" class="top" value="班级" readonly="readonly"></td>
    </tr>
    <c:forEach var="student" items="${studentAll}" step="1">
        <tr id="deleteStudent${student.studentNumber}">
            <td ><input type="text" id="studentNumber${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.studentNumber}" readonly="true"></td>
            <td ><input type="text" id="studentName${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.studentName}" readonly="true"></td>
            <td ><input type="text" id="sex${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.sex}" readonly="true"></td>
            <td ><input type="text" id="born${student.studentNumber}" class="studentEdit${student.studentNumber}" value="<fmt:formatDate type='date' pattern='yyyy-MM-dd' value='${student.born}' />" readonly="true"></td>
            <td ><input type="text" id="home${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.home}" readonly="true"></td>
            <td ><input type="text" id="homeName${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.homeName}" readonly="true"></td>
            <td ><input type="text" id="homeContact${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.homeContact}" readonly="true"></td>
            <td ><input type="text" id="admissionDate${student.studentNumber}" class="studentEdit${student.studentNumber}" value="<fmt:formatDate type='date' pattern='yyyy-MM-dd' value='${student.admissionDate}' />" readonly="true"></td>
            <td ><input type="text" id="gradeName${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.clazz.grade.gradeName}" readonly="true"></td>
            <td ><input type="text" id="clazzName${student.studentNumber}" class="studentEdit${student.studentNumber}" value="${student.clazz.clazzName}" readonly="true"></td>
            <td ><a href="#" class="editStudent" id="editStudent${student.studentNumber}" onclick="studentEdit('${student.studentNumber}')" >编辑</a>
                <a href="#" class="saveEditStudent" id="saveEditStudent${student.studentNumber}" onclick="saveStudentEdit('${student.studentNumber}')" >保存</a> </td>
            <td ><a href="#" onclick="deleteStudent('${student.studentNumber}')" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>

</body>
</html>
