<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/18
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.xiaostudy.domain.Teacher" %>
<%@page import="com.xiaostudy.domain.Subject" %>
<%@ page import="java.util.Date" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示教师</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/teacher.js"></script>
</head>
<body>
<div >
    <a href="#" id="addTeacher" onclick="addTeacher()" style="width: auto">添加教师</a>
    <a href="#" id="inAddTeacher" onclick="inAddTeacher()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="职工号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="姓名" readonly="readonly"></td>
        <td ><input type="text" class="top" value="性别" readonly="readonly"></td>
        <td ><input type="text" class="top" value="出生日期" readonly="readonly"></td>
        <td ><input type="text" class="top" value="住址" readonly="readonly"></td>
        <td ><input type="text" class="top" value="联系方式" readonly="readonly"></td>
        <td ><input type="text" class="top" value="入职日期" readonly="readonly"></td>
        <td ><input type="text" class="top" value="年级" readonly="readonly"></td>
        <td ><input type="text" class="top" value="学科" readonly="readonly"></td>
    </tr>
    <c:forEach var="teacher" items="${teacherAll}" step="1">
        <tr>
            <td ><input type="text" id="teacherNumber${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.teacherNumber}" readonly="true"></td>
            <td ><input type="text" id="teacherName${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.teacherName}" readonly="true"></td>
            <td ><input type="text" id="sex${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.sex}" readonly="true"></td>
            <td ><input type="text" id="born${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="<fmt:formatDate type='date' pattern='yyyy-MM-dd' value='${teacher.born}' />" readonly="true"></td>
            <td ><input type="text" id="home${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.home}" readonly="true"></td>
            <td ><input type="text" id="contact${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.contact}" readonly="true"></td>
            <td ><input type="text" id="entryDate${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="<fmt:formatDate type='date' pattern='yyyy-MM-dd' value='${teacher.entryDate}' />" readonly="true"></td>
            <td ><input type="text" id="gradeName${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.subject.grade.gradeName}" readonly="true"></td>
            <td ><input type="text" id="subjectName${teacher.teacherNumber}" class="teacherEdit${teacher.teacherNumber}" value="${teacher.subject.subjectName}" readonly="true"></td>
            <td ><a href="#" class="editTeacher" id="editTeacher${teacher.teacherNumber}" onclick="teacherEdit('${teacher.teacherNumber}')" >编辑</a>
                <a href="#" class="saveEditTeacher" id="saveEditTeacher${teacher.teacherNumber}" onclick="saveTeacherEdit('${teacher.teacherNumber}')" >保存</a> </td>
            <td ><a href="#" onclick="deleteTeacher('${teacher.teacherNumber}')" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>

</body>
</html>
