
//编辑学生
function studentEdit(_sid) {
    var input = document.getElementsByClassName("studentEdit" + _sid);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }
    var sid = document.getElementById("sid" + _sid);
    var sname = document.getElementById("sname" + _sid);
    var studentSex = document.getElementById("studentSex" + _sid);
    var studentBorn = document.getElementById("studentBorn" + _sid);
    var studentHome = document.getElementById("studentHome" + _sid);
    var studentHomeName = document.getElementById("studentHomeName" + _sid);
    var studentHomeContact = document.getElementById("studentHomeContact" + _sid);
    var studentAdmissionDate = document.getElementById("studentAdmissionDate" + _sid);
    var clazzName = document.getElementById("clazzName" + _sid);

    sid.removeAttribute("readonly");
    sid.style.backgroundColor = "yellow";
    sname.removeAttribute("readonly");
    sname.style.backgroundColor = "yellow";
    studentHome.removeAttribute("readonly");
    studentHome.style.backgroundColor = "yellow";
    studentHomeName.removeAttribute("readonly");
    studentHomeName.style.backgroundColor = "yellow";
    studentHomeContact.removeAttribute("readonly");
    studentHomeContact.style.backgroundColor = "yellow";

    var studentSexValue = studentSex.value;
    var sexParent = studentSex.parentNode;
    studentSex.remove();
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

    if(studentSexValue == "Y" || studentSexValue == "y" || studentSexValue == "男") {
        sexY.setAttribute("checked", "checked");
    } else {
        sexX.setAttribute("checked", "checked");
    }
    sexY.setAttribute("type", "radio");
    sexX.setAttribute("type", "radio");
    sexY.setAttribute("value", "Y");
    sexY.setAttribute("id", "sex");
    sexX.setAttribute("value", "X");
    sexY.setAttribute("name", "studentSex" + _sid);
    sexX.setAttribute("name", "studentSex" + _sid);
    sexY.style.width = "15px";
    sexX.style.width = "15px";

    var strBorn = studentBorn.value;
    studentBorn.setAttribute("type", "date");
    studentBorn.setAttribute("value", strToDate(strBorn));

    var strAdmissionDate = studentAdmissionDate.value;
    studentAdmissionDate.setAttribute("type", "date");
    studentAdmissionDate.setAttribute("value", strToDate(strAdmissionDate));

    var clazzNameValue = clazzName.value;
    var clazzNameId = clazzName.id;
    var clazzNameParent = clazzName.parentNode;
    clazzName.remove();
    var select = document.createElement("select");
    clazzNameParent.appendChild(select);
    select.setAttribute("id", clazzNameId);

    var data = ajax("clazz/showClazzNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var clazzName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", clazzName);
        option.innerText = clazzName;
        if(clazzName == clazzNameValue) {
            option.setAttribute("selected", "selected");
        }
        select.appendChild(option);
    }

    var editStudent = document.getElementById("editStudent" + _sid);
    editStudent.style.display = "none";
    editStudent.setAttribute("style", "display: none");
    var saveEditStudent = document.getElementById("saveEditStudent" + _sid);
    saveEditStudent.style.display = "block";
}

//保存编辑学生
function saveStudentEdit(_sid) {
    var input = document.getElementsByClassName("studentEdit" + _sid);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }

    var sid = document.getElementById("sid" + _sid);
    var sname = document.getElementById("sname" + _sid);
    var studentSexParent = document.getElementById("sex").parentNode;
    var studentSexValue = getRadioValue("studentSex" + _sid);
    var studentBorn = document.getElementById("studentBorn" + _sid);
    var studentHome = document.getElementById("studentHome" + _sid);
    var studentHomeName = document.getElementById("studentHomeName" + _sid);
    var studentHomeContact = document.getElementById("studentHomeContact" + _sid);
    var studentAdmissionDate = document.getElementById("studentAdmissionDate" + _sid);
    var clazzNameParent = document.getElementById("clazzName" + _sid).parentNode;
    var clazzNameValue = document.getElementById("clazzName" + _sid).value;

    if(studentSexValue == "Y" || studentSexValue == "y") {
        studentSexValue = "男";
    } else {
        studentSexValue = "女";
    }

    var json = {"newSid": sid.value, "newSname": sname.value, "oldSid": _sid, "newStudentSex": studentSexValue,
        "newStudentBorn": studentBorn.value, "newStudentHome": studentHome.value,"newStudentHomeName": studentHomeName.value,
        "newStudentHomeContact": studentHomeContact.value, "newStudentAdmissionDate": studentAdmissionDate.value, "newClazzName": clazzNameValue
    };

    var data = ajax("student/update", "get", json, "json", false);

    studentSexParent.innerText = "";
    var inputSex = document.createElement("input");
    inputSex.setAttribute("type", "text");
    studentSexParent.appendChild(inputSex);

    studentBorn.setAttribute("type", "text");
    studentAdmissionDate.setAttribute("type", "text");

    clazzNameParent.innerText = "";
    var inputClazzName = document.createElement("input");
    inputClazzName.setAttribute("type", "text");
    clazzNameParent.appendChild(inputClazzName);
    if(data.status == "error") {
        sid.value = data.sid;
        sname.value = data.sname;
        inputSex.setAttribute("id", "studentSex" + data.sid);
        inputSex.value = data.studentSex;
        inputSex.setAttribute("class", "studentEdit" + data.sid);
        studentBorn.value = data.studentStrBorn;
        studentHome.value = data.studentHome;
        studentHomeName.value = data.studentHomeName;
        studentHomeContact.value = data.studentHomeContact;
        studentAdmissionDate.value = data.studentStrAdmissionDate;
        inputClazzName.value = data.clazzName;
        inputClazzName.setAttribute("id", "clazzName" + data.sid);
        inputClazzName.setAttribute("class", "studentEdit" + data.sid);
        alert(data.message);
    } else if(data.status == "ok") {
        inputSex.setAttribute("id", "studentSex" + sid.value);
        inputSex.setAttribute("value", studentSexValue);
        inputSex.setAttribute("class", "studentEdit" + sid.value);

        inputClazzName.setAttribute("id", "clazzName" + sid.value);
        inputClazzName.setAttribute("value", clazzNameValue);
        inputClazzName.setAttribute("class", "studentEdit" + sid.value);

        alert(data.message);
    }

    sid.setAttribute("readonly", true);
    sname.setAttribute("readonly", true);
    inputSex.setAttribute("readonly", true);
    studentBorn.setAttribute("readonly", true);
    studentHome.setAttribute("readonly", true);
    studentHomeName.setAttribute("readonly", true);
    studentHomeContact.setAttribute("readonly", true);
    studentAdmissionDate.setAttribute("readonly", true);
    inputClazzName.setAttribute("readonly", true);

    var editStudent = document.getElementById("editStudent" + _sid);
    editStudent.style.display = "block";
    var saveEditStudent = document.getElementById("saveEditStudent" + _sid);
    saveEditStudent.style.display = "none";
}


//点击添加学生时添加输入信息
function addStudent() {
    var table = document.getElementById("table");
    $('table tr:last');
    var tr = document.createElement("tr");
    table.appendChild(tr);

    //table.insertRow(-1);//向table最后添加tr
    //var last = table.lastChild;
    tr.setAttribute("id","add");
    var td_sid = document.createElement("td");
    var td_sname = document.createElement("td");
    var td_studentSex = document.createElement("td");
    var td_studentBorn = document.createElement("td");
    var td_studentHome = document.createElement("td");
    var td_studentHomeName = document.createElement("td");
    var td_studentContact = document.createElement("td");
    var td_studentAdmissionDate = document.createElement("td");
    var td_clazzName = document.createElement("td");
    tr.appendChild(td_sid);
    tr.appendChild(td_sname);
    tr.appendChild(td_studentSex);
    tr.appendChild(td_studentBorn);
    tr.appendChild(td_studentHome);
    tr.appendChild(td_studentHomeName);
    tr.appendChild(td_studentContact);
    tr.appendChild(td_studentAdmissionDate);
    tr.appendChild(td_clazzName);
    var input_sid = document.createElement("input");
    var input_sname = document.createElement("input");
    var input_studentSexY = document.createElement("input");
    var input_studentSexX = document.createElement("input");
    var input_studentBorn = document.createElement("input");
    var input_studentHome = document.createElement("input");
    var input_studentHomeName = document.createElement("input");
    var input_studentContact = document.createElement("input");
    var input_studentAdmissionDate = document.createElement("input");
    var select_clazzName = document.createElement("select");
    var y = document.createElement("span");
    var x = document.createElement("span");
    td_sid.appendChild(input_sid);
    td_sname.appendChild(input_sname);
    td_studentSex.appendChild(input_studentSexY);
    td_studentSex.appendChild(y);
    td_studentSex.appendChild(input_studentSexX);
    td_studentSex.appendChild(x);
    td_studentBorn.appendChild(input_studentBorn);
    td_studentHome.appendChild(input_studentHome);
    td_studentHomeName.appendChild(input_studentHomeName);
    td_studentContact.appendChild(input_studentContact);
    td_studentAdmissionDate.appendChild(input_studentAdmissionDate);
    td_clazzName.appendChild(select_clazzName);

    y.innerText = "男";
    x.innerText = "女";
    input_studentSexY.setAttribute("checked", "checked");
    input_studentSexY.setAttribute("type", "radio");
    input_studentSexX.setAttribute("type", "radio");
    input_studentSexY.setAttribute("value", "男");
    input_studentSexY.setAttribute("id", "studentSex");
    input_studentSexX.setAttribute("value", "女");
    input_studentSexY.setAttribute("name", "studentSex");
    input_studentSexX.setAttribute("name", "studentSex");
    input_studentSexY.style.width = "15px";
    input_studentSexX.style.width = "15px";

    input_sid.setAttribute("type", "text");
    input_sname.setAttribute("type", "text");
    input_studentSexY.setAttribute("type", "radio");
    input_studentSexX.setAttribute("type", "radio");
    input_studentBorn.setAttribute("type", "date");
    input_studentHome.setAttribute("type", "text");
    input_studentContact.setAttribute("type", "text");
    input_studentAdmissionDate.setAttribute("type", "date");

    input_sid.setAttribute("id", "sid");
    input_sname.setAttribute("id", "sname");
    input_studentBorn.setAttribute("id", "studentBorn");
    input_studentHome.setAttribute("id", "studentHome");
    input_studentHomeName.setAttribute("id", "studentHomeName");
    input_studentContact.setAttribute("id", "studentContact");
    input_studentAdmissionDate.setAttribute("id", "studentAdmissionDate");

    select_clazzName.setAttribute("id", "clazzName");

    var data = ajax("clazz/showClazzNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var clazzName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", clazzName);
        option.innerText = clazzName;
        select_clazzName.appendChild(option);
    }

    input_sid.style.backgroundColor = "yellow";
    input_sname.style.backgroundColor = "yellow";
    input_studentBorn.style.backgroundColor = "yellow";
    input_studentHome.style.backgroundColor = "yellow";
    input_studentHomeName.style.backgroundColor = "yellow";
    input_studentContact.style.backgroundColor = "yellow";
    input_studentAdmissionDate.style.backgroundColor = "yellow";
    document.getElementById("addStudent").style.display = "none";
    document.getElementById("inAddStudent").style.display = "block";
}

//添加学科，从后台获取数据判断
function inAddStudent() {

    var newSid = document.getElementById("sid");
    var newSname = document.getElementById("sname");
    var newStudentSexParent = document.getElementById("studentSex").parentNode;
    var newStudentSexValue = document.getElementById("studentSex").value;
    var newStudentBorn = document.getElementById("studentBorn");
    var newStudentHome = document.getElementById("studentHome");
    var newStudentHomeName = document.getElementById("studentHomeName");
    var newStudentHomeContact = document.getElementById("studentContact");
    var newStudentAdmissionDate = document.getElementById("studentAdmissionDate");
    var newClazzNameParent = document.getElementById("clazzName").parentNode;
    var newClazzNameValue = document.getElementById("clazzName").value;

    var json = {"newSid": newSid.value, "newSname": newSname.value, "newStudentSex": newStudentSexValue,
        "newStudentBorn": newStudentBorn.value, "newStudentHome": newStudentHome.value, "newStudentHomeName": newStudentHomeName.value,
        "newStudentHomeContact": newStudentHomeContact.value, "newStudentAdmissionDate": newStudentAdmissionDate.value, "newClazzName": newClazzNameValue
    };

    var data = ajax("student/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {

        newStudentSexParent.innerText = "";
        var inputSex = document.createElement("input");
        inputSex.setAttribute("type", "text");
        inputSex.setAttribute("value", newStudentSexValue);
        newStudentSexParent.appendChild(inputSex);

        newStudentBorn.setAttribute("type", "text");
        newStudentAdmissionDate.setAttribute("type", "text");

        newClazzNameParent.innerText = "";
        var inputSubjectName = document.createElement("input");
        inputSubjectName.setAttribute("type", "text");
        inputSubjectName.setAttribute("value", newClazzNameValue);
        newClazzNameParent.appendChild(inputSubjectName);

        newSid.style.backgroundColor = "white";
        newSname.style.backgroundColor = "white";
        newStudentBorn.style.backgroundColor = "white";
        newStudentHome.style.backgroundColor = "white";
        newStudentHomeName.style.backgroundColor = "white";
        newStudentHomeContact.style.backgroundColor = "white";
        newStudentAdmissionDate.style.backgroundColor = "white";
        newSid.setAttribute("readonly", "true");
        newSname.setAttribute("readonly", "true");
        inputSex.setAttribute("readonly", "true");
        newStudentBorn.setAttribute("readonly", "true");
        newStudentHome.setAttribute("readonly", "true");
        newStudentHomeName.setAttribute("readonly", "true");
        newStudentHomeContact.setAttribute("readonly", "true");
        newStudentAdmissionDate.setAttribute("readonly", "true");
        inputSubjectName.setAttribute("readonly", "true");

        newSid.setAttribute("id", "sid" + newSid.value);
        newSname.setAttribute("id", "sname" + newSid.value);
        inputSex.setAttribute("id", "studentSex" + newSid.value);
        newStudentBorn.setAttribute("id", "studentBorn" + newSid.value);
        newStudentHome.setAttribute("id", "studentHome" + newSid.value);
        newStudentHomeName.setAttribute("id", "studentHomeName" + newSid.value);
        newStudentHomeContact.setAttribute("id", "studentHomeContact" + newSid.value);
        newStudentAdmissionDate.setAttribute("id", "studentAdmissionDate" + newSid.value);
        inputSubjectName.setAttribute("id", "clazzName" + newSid.value);

        newSid.setAttribute("class", "studentEdit" + newSid.value);
        newSname.setAttribute("class", "studentEdit" + newSid.value);
        inputSex.setAttribute("class", "studentEdit" + newSid.value);
        newStudentBorn.setAttribute("class", "studentEdit" + newSid.value);
        newStudentHome.setAttribute("class", "studentEdit" + newSid.value);
        newStudentHomeName.setAttribute("class", "studentEdit" + newSid.value);
        newStudentHomeContact.setAttribute("class", "studentEdit" + newSid.value);
        newStudentAdmissionDate.setAttribute("class", "studentEdit" + newSid.value);
        inputSubjectName.setAttribute("class", "studentEdit" + newSid.value);

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteStudent" + newSid.value);//为了方便删除

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
        a_edit.setAttribute("class", "editStudent");
        a_edit.setAttribute("id", "editStudent" + newSid.value);
        a_edit.setAttribute("onclick", "studentEdit(" + newSid.value + ")");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditStudent");
        a_saveEdit.setAttribute("id", "saveEditStudent" + newSid.value);
        a_saveEdit.setAttribute("onclick", "saveStudentEdit(" + newSid.value + ")");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteStudent(" + newSid.value + ")");
        alert(data.message);
    }
    document.getElementById("addStudent").style.display = "block";
    document.getElementById("inAddStudent").style.display = "none";
}

//删除学生
function deleteStudent(sid) {
    var b = del();//在common.js中
    if(b) {
        var json = {"sid": sid};

        var data = ajax("student/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteStudent" + sid).empty();
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