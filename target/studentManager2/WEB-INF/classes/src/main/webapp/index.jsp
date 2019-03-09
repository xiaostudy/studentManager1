<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2019/1/18
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%--<script language=javascript>
        function out(obj){
            var i=obj;
            if(i==0)
                document.location.herf="in_login";
            document.body.innerHTML=i;
            i--;
            setTimeout("out("+i+")",1000);
        }
    </script>
</head>
<body onload="out(0)">--%>
<body >
<%
    //response.setHeader("Refresh", "0;URL=in_login");
%>

<jsp:forward page="/in_login"></jsp:forward>

</body>
</html>
