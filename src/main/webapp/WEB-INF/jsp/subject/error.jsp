<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/19
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出错</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
</head>
<body>
    ${error}
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/subject/showSubjectList" >返回学科列表</a>
    <a href="${pageContext.request.contextPath}/index" >返回首页</a>
</body>
</html>
