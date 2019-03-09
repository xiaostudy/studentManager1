
//自定义删除确认框
function del() {
    if (confirm("是否确认删除?")) {
        return true;
    } else {
        return false;
    }
}

function subjectEdit(subjectId) {
    var cla = "subjectEdit" + subjectId;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        //删除节点上指定的属性
        input[i].removeAttribute("readonly");
        //设置背景颜色
        input[i].style.backgroundColor = "yellow";
    }
}


//原生ajax
function update_ajax(type, url2, text, async) {//使用ajax，参数1：请求方式【如："get"】，参数2：相当路径【如："subject/update?"】，参数3：请求参数【如："name=lisi&password=123"】，参数4：是否为异步【如：true】
    var xmlHTTP;
    //var em;
    if (window.XMLHttpRequest) {//构造方法，不同浏览器，甚至相同浏览器的不同版本，获取该对象的方式是不同的。
        xmlHTTP = new XMLHttpRequest();
    } else {
        xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    }
    var url = url2 + text + "&X-Requested-With=XMLHttpRequest";//自己加参数，让后台判断是否为异步请求
    xmlHTTP.open(type, url, async);//请求方式、路径、是否为异步
    xmlHTTP.onreadystatechange = function () {//处理
        if (xmlHTTP.readyState == 4 && xmlHTTP.status == 200) {//成功访问且一切正常
            //em = "修改成功！";
            var json = xmlHTTP.responseText;
            alert(json);
            var jsonObj = JSON.parse(json);//后台返回json数据，处理json数据
            alert(jsonObj);
            alert(jsonObj.message);
            alert(jsonObj.status);
        } else {
            //em = "服务器出错！";
            console.log(xmlHTTP.status);
            console.log(xmlHTTP.readyState);
            console.log(xmlHTTP.responseText);
        }
    }
    xmlHTTP.send();//发送
    //alert(em);
}

//jQuery的Ajax
function update(url, type, data, dataType, async) {//使用ajax，参数1：访问路径【"subject/update"】，参数2：请求方式【如："get"】，参数3：访问数据【如json数据：{"name":"lisi", "password":"123"}】，参数4：数据类型【如："json"】，参数5：是否为异步【如：true】
    $.ajax({
        url: url,//访问路径，相对路径
        type: type,//请求方式
        //注意序列化的值一定要放在最前面,并且不需要头部变量,不然获取的值得格式会有问题
        data: data,//数据
        dataType: dataType,//数据类型，有json、text、String、xml、html、script、
        async: async,//异步
        success: function (data) {//成功访问且一切正常
            alert(data);
            //后台返回json数据，解析json
            var jsonObj = JSON.parse(data);
            alert(jsonObj);
            alert(jsonObj.message);
            alert(jsonObj.status);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
        }
    })
}

//删除当前节点及所有子节点，参数为id
function del(del) {
    document.getElementById(del).remove();
}

//删除当前节点及所有子节点，参数为id
function del2(del) {
    $("#" + del).empty();
}

//删除当前节点及所有子节点，参数为id
function del3(del) {
    var de = document.getElementById(del);
    de.parentNode.removeChild(de);
}
