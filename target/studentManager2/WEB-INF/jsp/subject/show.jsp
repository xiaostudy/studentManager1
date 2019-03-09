<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.xiaostudy.domain.Subject" %>
<%@page import="com.xiaostudy.domain.Grade" %>
<%@page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>显示学科</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/subject.js"></script>
</head>
<body>
<div >
    <a href="#" id="addSubject" onclick="addSubject()" style="width: auto">添加学科</a>
    <a href="#" id="inAddSubject" onclick="inAddSubject()" style="width: auto" >确认添加</a>
</div>
<table id="table">
    <tr>
        <td ><input type="text" class="top" value="学科号" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="年级" readonly="readonly" ></td>
        <td ><input type="text" class="top" value="学科名称" readonly="readonly"></td>
    </tr>
    <c:forEach var="subject" items="${subjectAll}" step="1">
        <tr id="deleteSubject${subject.subjectNumber}">
            <td ><input type="text" id="subjectNumber${subject.subjectNumber}" class="subjectEdit${subject.subjectNumber}" value="${subject.subjectNumber}" readonly="true"></td>
            <td ><input type="text" id="gradeName${subject.subjectNumber}" class="subjectEdit${subject.subjectNumber}" value="${subject.grade.gradeName}" readonly="true"></td>
            <td ><input type="text" id="subjectName${subject.subjectNumber}" class="subjectEdit${subject.subjectNumber}" value="${subject.subjectName}" readonly="true"></td>
            <td ><a href="#" class="editSubject" id="editSubject${subject.subjectNumber}" onclick="subjectEdit('${subject.subjectNumber}')" >编辑</a>
            <a href="#" class="saveEditSubject" id="saveEditSubject${subject.subjectNumber}" onclick="saveSubjectEdit('${subject.subjectNumber}')" >保存</a> </td>
            <td ><a href="#" onclick="deleteSubject('${subject.subjectNumber}')" >删除</a> </td>
        </tr>
    </c:forEach>
</table>

<table id="page">
    <tr>
        <%--<td><a href="#" onclick="">上一页</a> </td>--%>
        <c:forEach var="page" items="${pages}" step="1">
            <td><a href="#" id="${page}" onclick="show('subject/showSubjectList?page=${page}')">${page}</a> </td>
        </c:forEach>
        <%--<td><a href="#" onclick="">下一页</a> </td>--%>
    </tr>
</table>

<br/>
</body>
</html>
