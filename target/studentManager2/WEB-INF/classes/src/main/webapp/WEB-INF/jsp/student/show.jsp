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
        <td ><input type="text" class="top" value="班级" readonly="readonly"></td>
    </tr>
    <c:forEach var="student" items="${studentAll}" step="1">
        <tr id="deleteStudent${student.sid}">
            <td ><input type="text" id="sid${student.sid}" class="studentEdit${student.sid}" value="${student.sid}" readonly="true"></td>
            <td ><input type="text" id="sname${student.sid}" class="studentEdit${student.sid}" value="${student.sname}" readonly="true"></td>
            <td ><input type="text" id="studentSex${student.sid}" class="studentEdit${student.sid}" value="${student.sex}" readonly="true"></td>
            <td ><input type="text" id="studentBorn${student.sid}" class="studentEdit${student.sid}" value="${student.strBorn}" readonly="true"></td>
            <td ><input type="text" id="studentHome${student.sid}" class="studentEdit${student.sid}" value="${student.home}" readonly="true"></td>
            <td ><input type="text" id="studentHomeName${student.sid}" class="studentEdit${student.sid}" value="${student.homeName}" readonly="true"></td>
            <td ><input type="text" id="studentHomeContact${student.sid}" class="studentEdit${student.sid}" value="${student.homeContact}" readonly="true"></td>
            <td ><input type="text" id="studentAdmissionDate${student.sid}" class="studentEdit${student.sid}" value="${student.strAdmissionDate}" readonly="true"></td>
            <td ><input type="text" id="clazzName${student.sid}" class="studentEdit${student.sid}" value="${student.clazzName}" readonly="true"></td>
            <td ><a href="#" class="editStudent" id="editStudent${student.sid}" onclick="studentEdit(${student.sid})" >编辑</a>
                <a href="#" class="saveEditStudent" id="saveEditStudent${student.sid}" onclick="saveStudentEdit(${student.sid})" >保存</a> </td>
            <td ><a href="#" onclick="deleteStudent(${student.sid})" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>

</body>
</html>
