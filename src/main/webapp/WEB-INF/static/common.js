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

//年级和教师的级联
function selectTeacher(thiz) {
    var value = thiz.value;
    var selectTeacherName = thiz.parentNode.nextElementSibling.nextElementSibling.firstChild;
    var teacherNameValue = selectTeacherName.value;

    selectTeacherName.innerText = "";

    var json = {"gradeName": value};
    var data = ajax("teacher/showTeacherNameList", "get", json, "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var teacherName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", teacherName);
        option.innerText = teacherName;
        if(teacherName == teacherNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectTeacherName.appendChild(option);
    }
}

//年级和学科的级联
function selectSubject(thiz) {
    var value = thiz.value;
    var selectSubjectName = thiz.parentNode.nextElementSibling.firstChild;
    var subjectNameValue = selectSubjectName.value;

    selectSubjectName.innerText = "";

    var json = {"gradeName": value};
    var data = ajax("subject/showSubjectNameListByGradeNameAjax", "get", json, "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectSubjectName.appendChild(option);
    }
}

//年级和班级的级联
function selectClazz(thiz) {
    var value = thiz.value;
    var selectClazzName = thiz.parentNode.nextElementSibling.firstChild;
    var clazzNameValue = selectClazzName.value;

    selectClazzName.innerText = "";

    var json = {"gradeName": value};
    var data = ajax("clazz/showClazzNameList", "get", json, "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var clazzName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", clazzName);
        option.innerText = clazzName;
        if(clazzName == clazzNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectClazzName.appendChild(option);
    }
}

//年级和考试、学生的级联
function selectTestStudent(thiz) {
    var value = thiz.value;
    var selectTestName = thiz.parentNode.nextElementSibling.firstChild;
    var studentNameValue = selectTestName.value;

    selectTestName.innerText = "";

    var json = {"gradeName": value};
    var data1 = ajax("test/showTestNameList", "get", json, "json", false);

    var length1 = getJsonLength(data1);
    for(var i = 0; i < length1; i++) {
        var studentName = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", studentName);
        option.innerText = studentName;
        if(studentName == studentNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectTestName.appendChild(option);
    }

    var selectStudentName = thiz.parentNode.nextElementSibling.nextElementSibling.firstChild;
    var studentNameValue = selectStudentName.value;
    selectStudentName.innerText = "";

    var data4 = ajax("student/showStudentNameList", "get", json, "json", false);

    var length4 = getJsonLength(data4);
    for(var i = 0; i < length4; i++) {
        var studentName = data4[i];
        var option = document.createElement("option");
        option.setAttribute("value", studentName);
        option.innerText = studentName;
        if(studentName == studentNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectStudentName.appendChild(option);
    }
}

//考试和学科的级联
function selectSubjectByTest(thiz) {
    var value = thiz.value;
    var selectSubjectName = thiz.parentNode.nextElementSibling.nextElementSibling.firstChild;
    var subjectNameValue = selectSubjectName.value;
    var selectGradeName = thiz.parentNode.parentNode.firstChild.nextElementSibling.nextElementSibling.firstChild;
    var gradeNameValue = selectGradeName.value;

    selectSubjectName.innerText = "";

    var json = {"testName": value, "gradeName": gradeNameValue};
    var data = ajax("test/showSubjectNameList", "get", json, "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectSubjectName.appendChild(option);
    }
}

//考试和学科的级联
function selectSubjectByTest_add(thiz) {
    var value = thiz.value;
    var selectSubjectName = thiz.parentNode.nextElementSibling.nextElementSibling.firstChild;
    var subjectNameValue = selectSubjectName.value;
    var selectGradeName = thiz.parentNode.parentNode.firstChild.nextElementSibling.firstChild;
    var gradeNameValue = selectGradeName.value;

    selectSubjectName.innerText = "";

    var json = {"testName": value, "gradeName": gradeNameValue};
    var data = ajax("test/showSubjectNameList", "get", json, "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        selectSubjectName.appendChild(option);
    }
}

//根据单选按钮name属性获取选中的value
function getRadioValue(radioName){
    var radios = document.getElementsByName(radioName);
    var value;
    for(var i=0;i<radios.length;i++){
        if(radios[i].checked){
            value = radios[i].value;
            break;
        }
    }
    return value;
}
