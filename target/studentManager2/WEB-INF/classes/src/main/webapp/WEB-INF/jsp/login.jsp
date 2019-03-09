<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/26
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js"></script>
    <title>登录学生管理系统</title>
    
    <script type="text/javascript">
        function submit() {
            var username = document.getElementById("username");
            var password = document.getElementById("password");
            var json = {"username": username.value, "password": password.value};
            ajax("login", "post", json, "json", true);
        }

    </script>

    <script type="text/javascript">
        /*window.onbeforeunload = function ()
        {
            var json = {"sessionDel": "delete"};
            ajax("in_login", "post", json, "json", true);
        }*/

    </script>

</head>
<body>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>
    <form id="from" action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username" /><br/>
        密码：<input type="password" name="password" /><br/>
        <input type="submit" value="确认登录">
    </form>
    <%--用户名：<input type="text" id="username" /><br/>
    密码：<input type="password" id="password" /><br/>
    <input type="button" value="确认登录" onclick="submit()">--%>
</body>
</html>
