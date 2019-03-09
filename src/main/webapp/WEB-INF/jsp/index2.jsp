<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>选项卡式导航</title>
    <style type="text/css">
        <!--
        * {
            margin: 0; padding:0
        }
        body {
            margin-top: 10px;
            margin-right: 10%;
            margin-bottom: 10px;
            margin-left: 10%;
            text-align: center;
            height: auto;
            width: auto;
            background-color: #666666;
            font-size: 12px;
            color: #000000;
        }
        #container {
            text-align: left;
            width: 760px;
            height: 400px;
            padding: 20px;
        }
        #container #title {
            height: 28px;
        }
        #container #title li {
            float: left;
            list-style-type: none;
            height: 28px;
            line-height: 28px;
            text-align: center;
            margin-right: 1px;
        }
        #container #title ul {
            height: 28px;
        }
        #container #title a {
            text-decoration: none;
            color: #000000;
            display: block;
            width: auto;
        }
        #container #title a span{
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag1 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag1 a:hover span{
            display: block;
            background-color: dodgerblue;
            padding: 0 15px 0 15px;
        }
        #container #title #tag2 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag2 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag3 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag3 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag4 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag4 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag5 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag5 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli1 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan1 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli2 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan2 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli3 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan3 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli4 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan4 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli5 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan5 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #content ul {margin: 10px;}
        #container #content li {margin: 5px; }
        #container #content li img {margin: 5px;display:block;}
        #container #content {
            height: 300px;
            padding: 10px;
        }
        .content1 {
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #3A81C8;
            border-right-color: #3A81C8;
            border-bottom-color: #3A81C8;
            border-left-color: #3A81C8;
            background-color: #DFEBF7;
        }
        .content2 {
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #ff950b;
            border-right-color: #ff950b;
            border-bottom-color: #ff950b;
            border-left-color: #ff950b;
            background-color: #FFECD2;
        }
        .content3 {
            height: 300px;
            padding: 10px;
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #FE74B8;
            border-right-color: #FE74B8;
            border-bottom-color: #FE74B8;
            border-left-color: #FE74B8;
            background-color: #FFECF5;
        }
        .content4 {
            height: 300px;
            padding: 10px;
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #00988B;
            border-right-color: #00988B;
            border-bottom-color: #00988B;
            border-left-color: #00988B;
            background-color: #E8FFFD;
        }
        .content5 {
            height: 300px;
            padding: 10px;
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #A8BC1F;
            border-right-color: #A8BC1F;
            border-bottom-color: #A8BC1F;
            border-left-color: #A8BC1F;
            background-color: #F7FAE2;
        }
        .hidecontent {display:none;}
        -->
    </style>
    <script language="javascript">
        function switchTag(tag,content)
        {
//	alert(tag);
//	alert(content);
            for(i=1; i < 6; i++)
            {
                if ("tag"+i==tag)
                {
                    document.getElementById(tag).getElementsByTagName("a")[0].className="selectli"+i;
                    document.getElementById(tag).getElementsByTagName("a")[0].getElementsByTagName("span")
                        [0].className="selectspan"+i;
                }else{
                    document.getElementById("tag"+i).getElementsByTagName("a")[0].className="";
                    document.getElementById("tag"+i).getElementsByTagName("a")[0].getElementsByTagName("span")
                        [0].className="";
                }
                if ("content"+i==content)
                {
                    document.getElementById(content).className="";
                }else{
                    document.getElementById("content"+i).className="hidecontent";
                }
                document.getElementById("content").className=content;
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="title">
        <ul>
            <li id="tag1"><a href="#" onclick="switchTag('tag1','content1');this.blur();" class="selectli1"><span
                    class="selectspan1">首页</span></a></li>
            <li id="tag2"><a href="#" onclick="switchTag('tag2','content2');this.blur();"><span>下载中心</span></a></li>
            <li id="tag3"><a href="#" onclick="switchTag('tag3','content3');this.blur();"><span>产品介绍</span></a></li>
            <li id="tag4"><a href="#" onclick="switchTag('tag4','content4');this.blur();"><span>会员注册与登录</span></a></li>
            <li id="tag5"><a href="#" onclick="switchTag('tag5','content5');this.blur();"><span>联系我们</span></a></li>
        </ul>
    </div>
    <div id="content" class="content1">
        <div id="content1" >1、根据字数自适应项目长度</div>
        <div id="content2" class="hidecontent">2、不同的项目使用不同的颜色来区分</div>
        <div id="content3" class="hidecontent">3、这回需要使用到js了，呵呵</div>
        <div id="content4" class="hidecontent">4、背景图片只需要两个图片文件就足够，减少服务器负担</div>
        <div id="content5" class="hidecontent">5、这是使用到的两个图片</div>
    </div>
</div>
</body>
</html>