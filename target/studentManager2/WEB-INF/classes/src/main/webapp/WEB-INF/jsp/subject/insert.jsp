<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.xiaostudy.domain.Subject" %>
<html>
<head>
    <title>添加学科</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/subject/insert" method="get">
    <table >
        <tr>
            <td ><input type="text" class="subjectTop" value="学科号" readonly="readonly" ></td>
            <td ><input type="text" class="subjectTop" value="学科名称" readonly="readonly"></td>
        </tr>
        <tr>
            <td ><input type="text" class="subjectInsert" id="subjectId" name="subjectId" value="" /></td>
            <td ><input type="text" class="subjectInsert" id="subjectName" name="subjectName" value="" /></td>
        </tr>
        <tr>
            <td ><input type="submit" value="确认添加" /></td>
        </tr>
    </table>
</form>
</body>
</html>
