
//编辑教师
function teacherEdit(_tid) {
    var cla = "teacherEdit" + _tid;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }
    var tid = document.getElementById("tid" + _tid);
    var tname = document.getElementById("tname" + _tid);
    var teacherSex = document.getElementById("teacherSex" + _tid);
    var teacherBorn = document.getElementById("teacherBorn" + _tid);
    var teacherHome = document.getElementById("teacherHome" + _tid);
    var teacherContact = document.getElementById("teacherContact" + _tid);
    var teacherEntryDate = document.getElementById("teacherEntryDate" + _tid);
    var teacherSubjectName = document.getElementById("teacherSubjectName" + _tid);

    tid.removeAttribute("readonly");
    tid.style.backgroundColor = "yellow";

    tname.removeAttribute("readonly");
    tname.style.backgroundColor = "yellow";

    teacherHome.removeAttribute("readonly");
    teacherHome.style.backgroundColor = "yellow";

    teacherContact.removeAttribute("readonly");
    teacherContact.style.backgroundColor = "yellow";

    var teacherSexValue = teacherSex.value;
    var sexParent = teacherSex.parentNode;
    teacherSex.remove();
    var sexY = document.createElement("input");
    var sexX = document.createElement("input");
    sexParent.appendChild(sexY);
    var y = document.createElement("span");
    y.innerText = "男";
    sexParent.appendChild(y);

    sexParent.appendChild(sexX);
    var x = document.createElement("span");
    x.innerText = "女";
    sexParent.appendChild(x);

    if(teacherSexValue == "Y" || teacherSexValue == "y" || teacherSexValue == "男") {
        sexY.setAttribute("checked", "checked");
    } else {
        sexX.setAttribute("checked", "checked");
    }
    sexY.setAttribute("type", "radio");
    sexX.setAttribute("type", "radio");
    sexY.setAttribute("value", "Y");
    sexY.setAttribute("id", "sex");
    sexX.setAttribute("value", "X");
    sexY.setAttribute("name", "teacherSex" + _tid);
    sexX.setAttribute("name", "teacherSex" + _tid);
    sexY.style.width = "15px";
    sexX.style.width = "15px";

    var strBorn = teacherBorn.value;
    teacherBorn.setAttribute("type", "date");
    teacherBorn.setAttribute("value", strToDate(strBorn));

    var strEntryDate = teacherEntryDate.value;
    teacherEntryDate.setAttribute("type", "date");
    teacherEntryDate.setAttribute("value", strToDate(strEntryDate));

    var teacherSubjectNameValue = teacherSubjectName.value;
    var teacherSubjectNameId = teacherSubjectName.id;
    var teacherSubjectNameParent = teacherSubjectName.parentNode;
    teacherSubjectName.remove();
    var select = document.createElement("select");
    teacherSubjectNameParent.appendChild(select);
    select.setAttribute("id", teacherSubjectNameId);

    var data = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == teacherSubjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        select.appendChild(option);
    }

    var editTeacher = document.getElementById("editTeacher" + _tid);
    editTeacher.style.display = "none";
    editTeacher.setAttribute("style", "display: none");
    var saveEditTeacher = document.getElementById("saveEditTeacher" + _tid);
    saveEditTeacher.style.display = "block";
}

//保存编辑教师
function saveTeacherEdit(_tid) {
    var cla = "teacherEdit" + _tid;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }

    var tid = document.getElementById("tid" + _tid);
    var tname = document.getElementById("tname" + _tid);
    var teacherSex = document.getElementById("sex").parentNode;
    var teacherSexValue = getRadioValue("teacherSex" + _tid);
    var teacherBorn = document.getElementById("teacherBorn" + _tid);
    var teacherHome = document.getElementById("teacherHome" + _tid);
    var teacherContact = document.getElementById("teacherContact" + _tid);
    var teacherEntryDate = document.getElementById("teacherEntryDate" + _tid);
    var teacherSubjectName = document.getElementById("teacherSubjectName" + _tid).parentNode;
    var teacherSubjectNameValue = document.getElementById("teacherSubjectName" + _tid).value;

    if(teacherSexValue == "Y" || teacherSexValue == "y") {
        teacherSexValue = "男";
    } else {
        teacherSexValue = "女";
    }

    var json = {"newTid": tid.value, "newTname": tname.value, "oldTid": _tid, "newTeacherSex": teacherSexValue,
        "newTeacherBorn": teacherBorn.value, "newTeacherHome": teacherHome.value, "newTeacherContact": teacherContact.value,
        "newTeacherEntryDate": teacherEntryDate.value, "newTeacherSubjectName": teacherSubjectNameValue
    };

    var data = ajax("teacher/update", "get", json, "json", false);

    teacherSex.innerText = "";
    var inputSex = document.createElement("input");
    inputSex.setAttribute("type", "text");
    teacherSex.appendChild(inputSex);

    teacherBorn.setAttribute("type", "text");
    teacherEntryDate.setAttribute("type", "text");

    teacherSubjectName.innerText = "";
    var inputSubjectName = document.createElement("input");
    inputSubjectName.setAttribute("type", "text");
    teacherSubjectName.appendChild(inputSubjectName);
    if(data.status == "error") {
        tid.value = data.tid;
        tname.value = data.tname;
        inputSex.setAttribute("id", "teacherSex" + data.tid);
        inputSex.value = data.teacherSex;
        inputSex.setAttribute("class", "teacherEdit" + data.tid);
        teacherBorn.value = data.teacherStrBorn;
        teacherHome.value = data.teacherHome;
        teacherContact.value = data.teacherContact;
        teacherEntryDate.value = data.teacherStrEntryDate;
        inputSubjectName.value = data.teacherSubjectName;
        inputSubjectName.setAttribute("id", "teacherSubjectName" + data.tid);
        inputSubjectName.setAttribute("class", "teacherEdit" + data.tid);
        alert(data.message);
    } else if(data.status == "ok") {
        inputSex.setAttribute("id", "teacherSex" + tid.value);
        inputSex.setAttribute("value", teacherSexValue);
        inputSex.setAttribute("class", "teacherEdit" + tid.value);

        inputSubjectName.setAttribute("id", "teacherSubjectName" + tid.value);
        inputSubjectName.setAttribute("value", teacherSubjectNameValue);
        inputSubjectName.setAttribute("class", "teacherEdit" + tid.value);

        alert(data.message);
    }

    tid.setAttribute("readonly", true);
    tname.setAttribute("readonly", true);
    inputSex.setAttribute("readonly", true);
    teacherBorn.setAttribute("readonly", true);
    teacherHome.setAttribute("readonly", true);
    teacherContact.setAttribute("readonly", true);
    teacherEntryDate.setAttribute("readonly", true);
    inputSubjectName.setAttribute("readonly", true);

    var editTeacher = document.getElementById("editTeacher" + _tid);
    editTeacher.style.display = "block";
    var saveEditTeacher = document.getElementById("saveEditTeacher" + _tid);
    saveEditTeacher.style.display = "none";
}


//点击添加教师时添加输入信息
function addTeacher() {
    var table = document.getElementById("table");
    $('table tr:last');
    var tr = document.createElement("tr");
    table.appendChild(tr);

    //table.insertRow(-1);//向table最后添加tr
    //var last = table.lastChild;
    tr.setAttribute("id","add");
    var td_tid = document.createElement("td");
    var td_tname = document.createElement("td");
    var td_teacherSex = document.createElement("td");
    var td_teacherBorn = document.createElement("td");
    var td_teacherHome = document.createElement("td");
    var td_teacherContact = document.createElement("td");
    var td_teacherEntryDate = document.createElement("td");
    var td_teacherSubjectName = document.createElement("td");
    tr.appendChild(td_tid);
    tr.appendChild(td_tname);
    tr.appendChild(td_teacherSex);
    tr.appendChild(td_teacherBorn);
    tr.appendChild(td_teacherHome);
    tr.appendChild(td_teacherContact);
    tr.appendChild(td_teacherEntryDate);
    tr.appendChild(td_teacherSubjectName);
    var input_tid = document.createElement("input");
    var input_tname = document.createElement("input");
    var input_teacherSexY = document.createElement("input");
    var input_teacherSexX = document.createElement("input");
    var input_teacherBorn = document.createElement("input");
    var input_teacherHome = document.createElement("input");
    var input_teacherContact = document.createElement("input");
    var input_teacherEntryDate = document.createElement("input");
    var select_teacherSubjectName = document.createElement("select");
    var y = document.createElement("span");
    var x = document.createElement("span");
    td_tid.appendChild(input_tid);
    td_tname.appendChild(input_tname);
    td_teacherSex.appendChild(input_teacherSexY);
    td_teacherSex.appendChild(y);
    td_teacherSex.appendChild(input_teacherSexX);
    td_teacherSex.appendChild(x);
    td_teacherBorn.appendChild(input_teacherBorn);
    td_teacherHome.appendChild(input_teacherHome);
    td_teacherContact.appendChild(input_teacherContact);
    td_teacherEntryDate.appendChild(input_teacherEntryDate);
    td_teacherSubjectName.appendChild(select_teacherSubjectName);

    y.innerText = "男";
    x.innerText = "女";
    input_teacherSexY.setAttribute("checked", "checked");
    input_teacherSexY.setAttribute("type", "radio");
    input_teacherSexX.setAttribute("type", "radio");
    input_teacherSexY.setAttribute("value", "男");
    input_teacherSexY.setAttribute("id", "teacherSex");
    input_teacherSexX.setAttribute("value", "女");
    input_teacherSexY.setAttribute("name", "teacherSex");
    input_teacherSexX.setAttribute("name", "teacherSex");
    input_teacherSexY.style.width = "15px";
    input_teacherSexX.style.width = "15px";

    input_tid.setAttribute("type", "text");
    input_tname.setAttribute("type", "text");
    input_teacherSexY.setAttribute("type", "radio");
    input_teacherSexX.setAttribute("type", "radio");
    input_teacherBorn.setAttribute("type", "date");
    input_teacherHome.setAttribute("type", "text");
    input_teacherContact.setAttribute("type", "text");
    input_teacherEntryDate.setAttribute("type", "date");

    input_tid.setAttribute("id", "tid");
    input_tname.setAttribute("id", "tname");
    input_teacherBorn.setAttribute("id", "teacherBorn");
    input_teacherHome.setAttribute("id", "teacherHome");
    input_teacherContact.setAttribute("id", "teacherContact");
    input_teacherEntryDate.setAttribute("id", "teacherEntryDate");

    select_teacherSubjectName.setAttribute("id", "teacherSubjectName");

    var data = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select_teacherSubjectName.appendChild(option);
    }

    input_tid.style.backgroundColor = "yellow";
    input_tname.style.backgroundColor = "yellow";
    input_teacherBorn.style.backgroundColor = "yellow";
    input_teacherHome.style.backgroundColor = "yellow";
    input_teacherContact.style.backgroundColor = "yellow";
    input_teacherEntryDate.style.backgroundColor = "yellow";
    document.getElementById("addTeacher").style.display = "none";
    document.getElementById("inAddTeacher").style.display = "block";
}

//添加学科，从后台获取数据判断
function inAddTeacher() {

    var newTid = document.getElementById("tid");
    var newTname = document.getElementById("tname");
    var newTeacherSexParent = document.getElementById("teacherSex").parentNode;
    var newTeacherSexValue = document.getElementById("teacherSex").value;
    var newTeacherBorn = document.getElementById("teacherBorn");
    var newTeacherHome = document.getElementById("teacherHome");
    var newTeacherContact = document.getElementById("teacherContact");
    var newTeacherEntryDate = document.getElementById("teacherEntryDate");
    var newTeacherSubjectNameParent = document.getElementById("teacherSubjectName").parentNode;
    var newTeacherSubjectNameValue = document.getElementById("teacherSubjectName").value;

    var json = {"newTid": newTid.value, "newTname": newTname.value, "newTeacherSex": newTeacherSexValue,
        "newTeacherBorn": newTeacherBorn.value, "newTeacherHome": newTeacherHome.value, "newTeacherContact": newTeacherContact.value,
        "newTeacherEntryDate": newTeacherEntryDate.value, "newTeacherSubjectName": newTeacherSubjectNameValue
    };

    var data = ajax("teacher/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {

        newTeacherSexParent.innerText = "";
        var inputSex = document.createElement("input");
        inputSex.setAttribute("type", "text");
        inputSex.setAttribute("value", newTeacherSexValue);
        newTeacherSexParent.appendChild(inputSex);

        newTeacherBorn.setAttribute("type", "text");
        newTeacherEntryDate.setAttribute("type", "text");

        newTeacherSubjectNameParent.innerText = "";
        var inputSubjectName = document.createElement("input");
        inputSubjectName.setAttribute("type", "text");
        inputSubjectName.setAttribute("value", newTeacherSubjectNameValue);
        newTeacherSubjectNameParent.appendChild(inputSubjectName);

        newTid.style.backgroundColor = "white";
        newTname.style.backgroundColor = "white";
        newTeacherBorn.style.backgroundColor = "white";
        newTeacherHome.style.backgroundColor = "white";
        newTeacherContact.style.backgroundColor = "white";
        newTeacherEntryDate.style.backgroundColor = "white";
        newTid.setAttribute("readonly", "true");
        newTname.setAttribute("readonly", "true");
        inputSex.setAttribute("readonly", "true");
        newTeacherBorn.setAttribute("readonly", "true");
        newTeacherHome.setAttribute("readonly", "true");
        newTeacherContact.setAttribute("readonly", "true");
        newTeacherEntryDate.setAttribute("readonly", "true");
        inputSubjectName.setAttribute("readonly", "true");

        newTid.setAttribute("id", "tid" + newTid.value);
        newTname.setAttribute("id", "tname" + newTid.value);
        inputSex.setAttribute("id", "teacherSex" + newTid.value);
        newTeacherBorn.setAttribute("id", "teacherBorn" + newTid.value);
        newTeacherHome.setAttribute("id", "teacherHome" + newTid.value);
        newTeacherContact.setAttribute("id", "teacherContact" + newTid.value);
        newTeacherEntryDate.setAttribute("id", "teacherEntryDate" + newTid.value);
        inputSubjectName.setAttribute("id", "teacherSubjectName" + newTid.value);

        newTid.setAttribute("class", "teacherEdit" + newTid.value);
        newTname.setAttribute("class", "teacherEdit" + newTid.value);
        inputSex.setAttribute("class", "teacherEdit" + newTid.value);
        newTeacherBorn.setAttribute("class", "teacherEdit" + newTid.value);
        newTeacherHome.setAttribute("class", "teacherEdit" + newTid.value);
        newTeacherContact.setAttribute("class", "teacherEdit" + newTid.value);
        newTeacherEntryDate.setAttribute("class", "teacherEdit" + newTid.value);
        inputSubjectName.setAttribute("class", "teacherEdit" + newTid.value);

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteTeacher" + newTid.value);//为了方便删除

        var edit = document.createElement("td");
        var del = document.createElement("td");
        tr.appendChild(edit);
        tr.appendChild(del);

        var a_edit = document.createElement("a");
        var a_saveEdit = document.createElement("a");
        var a_del = document.createElement("a");
        edit.appendChild(a_edit);
        edit.appendChild(a_saveEdit);
        del.appendChild(a_del);

        a_edit.innerText = "编辑";
        a_edit.setAttribute("href", "#");
        a_edit.setAttribute("class", "editTeacher");
        a_edit.setAttribute("id", "editTeacher" + newTid.value);
        a_edit.setAttribute("onclick", "teacherEdit(" + newTid.value + ")");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditTeacher");
        a_saveEdit.setAttribute("id", "saveEditTeacher" + newTid.value);
        a_saveEdit.setAttribute("onclick", "saveTeacherEdit(" + newTid.value + ")");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteTeacher(" + newTid.value + ")");
        alert(data.message);
    }
    document.getElementById("addTeacher").style.display = "block";
    document.getElementById("inAddTeacher").style.display = "none";
}

//删除教师
function deleteTeacher(tid) {
    var b = del();//在common.js中
    if(b) {
        var json = {"tid": tid};

        var data = ajax("teacher/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteTeacher" + tid).empty();
            alert(data.message);
        }
    }
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