<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xiaostudy.domain.Clazz" %>
<%@page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示班级</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/clazz.js"></script>
</head>
<body>
<div >
    <a href="#" id="addClazz" onclick="addClazz()" style="width: auto">添加班级</a>
    <a href="#" id="inAddClazz" onclick="inAddClazz()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="班级号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="年级" readonly="readonly"></td>
        <td ><input type="text" class="top" value="班级名称" readonly="readonly"></td>
        <td ><input type="text" class="top" value="班主任" readonly="readonly"></td>
    </tr>
    <c:forEach var="clazz" items="${clazzAll}" step="1">
        <tr id="deleteClazz${clazz.clazzNumber}">
            <td ><input type="text" id="clazzNumber${clazz.clazzNumber}" class="clazzEdit${clazz.clazzNumber}" value="${clazz.clazzNumber}" readonly="true"></td>
            <td ><input type="text" id="gradeName${clazz.clazzNumber}" class="clazzEdit${clazz.clazzNumber}" value="${clazz.grade.gradeName}" readonly="true"></td>
            <td ><input type="text" id="clazzName${clazz.clazzNumber}" class="clazzEdit${clazz.clazzNumber}" value="${clazz.clazzName}" readonly="true"></td>
            <td ><input type="text" id="teacherName${clazz.clazzNumber}" class="clazzEdit${clazz.clazzNumber}" value="${clazz.teacher.teacherName}" readonly="true"></td>
            <td ><a href="#" class="editClazz" id="editClazz${clazz.clazzNumber}" onclick="clazzEdit('${clazz.clazzNumber}')" >编辑</a>
            <a href="#" class="saveEditClazz" id="saveEditClazz${clazz.clazzNumber}" onclick="saveClazzEdit('${clazz.clazzNumber}')" >保存</a> </td>
            <td ><a href="#" onclick="deleteClazz('${clazz.clazzNumber}')" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<br/>
</body>
</html>
