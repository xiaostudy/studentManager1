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
    <title>显示学生</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript">
        function insert() {
            alert("insert");
        }
    </script>

</head>
<body>
<%--<input type="button" value="添加学生" onclick="insertStudent()" />--%>
<a href="${pageContext.request.contextPath}/student/in_insert" >添加学生</a>
<table >
    <tr>
        <td ><input type="text" class="studentTop" value="学号" readonly="readonly" ></td>
        <td ><input type="text" class="studentTop" value="姓名" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="性别" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="出生日期" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="家庭住址" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="家长姓名" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="家长联系方式" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="入学日期" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="班号" readonly="readonly"></td>
        <td ><input type="text" class="studentTop" value="班级名称" readonly="readonly"></td>
    </tr>
    <c:forEach var="student" items="${studentAll}" step="1">
        <tr>
            <td ><input type="text" class="studentEdit" value="${student.sid}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.sname}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.sex}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.born}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.home}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.homeName}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.homeContact}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.admissionDate}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.clazzId}" readonly="readonly"></td>
            <td ><input type="text" class="studentEdit" value="${student.clazzName}" readonly="readonly"></td>
            <td ><a href="#" class="" >编辑</a> </td>
            <td ><a href="#" class="" >删除</a> </td>
        </tr>
    </c:forEach>
    <div id="student-tbody-result">
        <form action="${pageContext.request.contextPath}/student/insert">
            <tr>
                <td ><input type="text" class="studentInsert" id="studentSid" name="studentSid" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentSname" name="studentSname" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentSex" name="studentSex" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentBorn" name="studentBorn" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentHome" name="studentHome" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentHomeName" name="studentHomeName" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentHomeContact" name="studentHomeContact" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentAdmissionDate" name="studentAdmissionDate" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentClazzId" name="studentClazzId" value="" /></td>
                <td ><input type="text" class="studentInsert" id="studentClazzName" name="studentClazzName" value="" /></td>
            </tr>
            <tr>
                <td ><input type="submit" value="确认添加" /></td>
            </tr>
        </form>
    </div>
</table>

<br/>

</body>
</html>
