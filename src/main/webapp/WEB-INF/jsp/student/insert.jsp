<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.xiaostudy.domain.Student" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>添加学生</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/student/insert" method="get">
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
        <tr>
            <td ><input type="text" class="studentInsert" id="studentSid" name="sid" value="" /></td>
            <td ><input type="text" class="studentInsert" id="studentSname" name="sname" value="" /></td>
            <td class="studentInsert"><input type="radio"  name="sex" value="Y" checked="checked" />男
            <input type="radio"  name="sex" value="X" />女</td>
            <td ><input type="date" class="studentInsert" id="studentBorn" name="born" value="" /></td>
            <td ><input type="text" class="studentInsert" id="studentHome" name="home" value="" /></td>
            <td ><input type="text" class="studentInsert" id="studentHomeName" name="homeName" value="" /></td>
            <td ><input type="text" class="studentInsert" id="studentHomeContact" name="homeContact" value="" /></td>
            <td ><input type="date" class="studentInsert" id="studentAdmissionDate" name="admissionDate" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" /></td>
            <td ><input type="text" class="studentInsert" id="studentClazzId" name="clazzId" value="" /></td>
            <td ><input type="text" class="studentInsert" id="studentClazzName" name="clazzName" value="" /></td>
        </tr>
        <tr>
            <td ><input type="submit" value="确认添加" /></td>
        </tr>
    </table>
</form>
</body>
</html>
