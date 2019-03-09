//退出页面时删除session
window.onbeforeunload = function ()
{
    var json = {"username": "delete"};
    ajax("in_login", "post", json, "json", true);
}

//主页点击导航栏显示不同模块
function show(str) {
    $.ajax({
        url: str,
        type: "get",
        data: "",
        dataType: "text",
        async: false,
        success: function (data) {
            document.getElementById("show").innerHTML = body(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
            console.log(XMLHttpRequest.responseText);
        }
    })
}

//主页点击导航栏显示不同模块【弃用】
function show2(str) {
    var xmlHTTP;
    if (window.XMLHttpRequest) {
        xmlHTTP = new XMLHttpRequest();
    } else {
        xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    }
    var url = "${pageContext.request.contextPath}/" + str;
    xmlHTTP.open("get", url, false);
    xmlHTTP.onreadystatechange = function () {
        if (xmlHTTP.readyState == 4 && xmlHTTP.status == 200) {
            document.getElementById("show").innerHTML = body(xmlHTTP.responseText);//在common.js里面
        }
    }
    xmlHTTP.send();
}

//自定义删除确认框
function del() {
    if (confirm("是否确认删除?")) {
        return true;
    } else {
        return false;
    }
}

//获取html中的body内的文本，不包括body标签
function body(html) {
    var reg = /<body>([^\x00]+)$/i;
    //对整段HTML片段按<\/script>拆分
    var htmlBlock = html.split("<\/body>");
    var code = "";

    for (var i in htmlBlock) {
        var body;//匹配正则表达式的内容数组，blocks[1]就是真正的一段脚本内容，因为前面reg定义我们用了括号进行了捕获分组
        if (body = htmlBlock[i].match(reg)) {
            code = code+  body[1].replace(/<!--/, '').replace(/-->/, '');
        }
    }
    return code;
}

//字符串日期处理，格式2019-01-22
function strToDate(strDate) {
    var date = new Date(strDate);
    var day = ("0" + date.getDate()).slice(-2);
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var time = date.getFullYear() + "-" + (month) + "-" + (day);
    return time;
}

//获取json数据长度
function getJsonLength(jsonData) {
    var length = 0;
    for (var ever in jsonData) {
        length++;
    }
    return length;
}

//通过ajax获取数据
function ajax(url, type, data, dataType, async) {
    var json;
    $.ajax({
        url: url,
        type: type,
        data: data,
        dataType: dataType,
        async: async,
        success: function (data) {
            json = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
            console.log(XMLHttpRequest.responseText);
            json = XMLHttpRequest.responseText;
        }
    })
    return json;
}