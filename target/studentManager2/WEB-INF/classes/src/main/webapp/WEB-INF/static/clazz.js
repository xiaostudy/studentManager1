
//编辑班级
function clazzEdit(clazzId) {
    var cla = "clazzEdit" + clazzId;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var gradeName = document.getElementById("gradeName" + clazzId);
    var gradeNameParent = gradeName.parentNode;
    var gradeNameId = gradeName.id;
    var gradeNameValue = gradeName.value;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("id", gradeNameId);

    var data1 = ajax("grade/showGradeNameList", "get", "", "json", false);

    var length1 = getJsonLength(data1);
    for(var i = 0; i < length1; i++) {
        var gradeName = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        if(gradeName == gradeNameValue) {
            option.setAttribute("selected", "selected");
        }
        select1.appendChild(option);
    }

    var tname = document.getElementById("tname" + clazzId);
    var tnameParent = tname.parentNode;
    var tnameId = tname.id;
    var tnameValue = tname.value;
    tnameParent.innerText = "";

    var select2 = document.createElement("select");
    tnameParent.appendChild(select2);
    select2.setAttribute("id", tnameId);

    var data2 = ajax("teacher/showTnameList", "get", "", "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var gradeName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        if(gradeName == tnameValue) {
            option.setAttribute("selected", "selected");
        }
        select2.appendChild(option);
    }

    var editClazz = document.getElementById("editClazz" + clazzId);
    editClazz.style.display = "none";
    var saveEditClazz = document.getElementById("saveEditClazz" + clazzId);
    saveEditClazz.style.display = "block";
}

//保存编辑班级
function saveClazzEdit(clazzId) {
    var cla = "clazzEdit" + clazzId;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newClazzId = document.getElementById("clazzId" + clazzId);
    var newClazzName = document.getElementById("clazzName" + clazzId);
    var newGradeName = document.getElementById("gradeName" + clazzId);
    var newGradeNameId = newGradeName.id;
    var newGradeNameValue = newGradeName.value;
    var newGradeNameParent = newGradeName.parentNode;
    var newTname = document.getElementById("tname" + clazzId);
    var newTnameId = newTname.id;
    var newTnameValue = newTname.value;
    var newTnameParent = newTname.parentNode;
    var json = {"newClazzId": newClazzId.value, "newClazzName": newClazzName.value, "oldClazzId": clazzId, "newGradeName": newGradeNameValue, "newTname": newTnameValue};

    var data = ajax("clazz/update", "get", json, "json", false);

    newGradeNameParent.innerText = "";
    var input_gradeName = document.createElement("input");
    newGradeNameParent.appendChild(input_gradeName);
    input_gradeName.setAttribute("type", "text");
    input_gradeName.setAttribute("id", newGradeNameId);
    input_gradeName.setAttribute("class", "clazzEdit" + clazzId);
    newTnameParent.innerText = "";
    var input_tname = document.createElement("input");
    newTnameParent.appendChild(input_tname);
    input_tname.setAttribute("type", "text");
    input_tname.setAttribute("id", newTnameId);
    input_tname.setAttribute("class", "clazzEdit" + clazzId);

    if(data.status == "error") {
        newClazzId.value = data.oldClazzId;
        newClazzName.value = data.oldClazzName;
        input_gradeName.value = data.oldGradeName;
        input_tname.value = data.oldTname;
        alert(data.message);
    } else if(data.status == "ok") {
        input_gradeName.value = newGradeNameValue;
        input_tname.value = newTnameValue;
        alert(data.message);
    }

    var editClazz = document.getElementById("editClazz" + clazzId);
    editClazz.style.display = "block";
    var saveEditClazz = document.getElementById("saveEditClazz" + clazzId);
    saveEditClazz.style.display = "none";
}

//删除班级
function deleteClazz(clazzId) {
    var b = del();//在common.js中
    if(b) {
        var json = {"clazzId": clazzId};

        var data = ajax("clazz/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteClazz" + clazzId).empty();
            alert(data.message);
        }
    }
}

//添加班级时添加输入信息
function addClazz() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);
    tr.setAttribute("id","add");
    var td_clazzId = document.createElement("td");
    var td_clazzName = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_tname = document.createElement("td");
    tr.appendChild(td_clazzId);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_clazzName);
    tr.appendChild(td_tname);
    var input_id = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newClazzId");
    input_name.setAttribute("id", "newClazzName");
    td_clazzId.appendChild(input_id);
    td_clazzName.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";

    var select1 = document.createElement("select");
    td_gradeName.appendChild(select1);
    select1.setAttribute("id", "newGradeName");

    var data1 = ajax("grade/showGradeNameList", "get", "", "json", false);

    var length1 = getJsonLength(data1);
    for(var i = 0; i < length1; i++) {
        var gradeName = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        select1.appendChild(option);
    }

    var select2 = document.createElement("select");
    td_tname.appendChild(select2);
    select2.setAttribute("id", "newTname");

    var data2 = ajax("teacher/showTnameList", "get", "", "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var gradeName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        select2.appendChild(option);
    }

    document.getElementById("addClazz").style.display = "none";
    document.getElementById("inAddClazz").style.display = "block";
}

//添加班级，从后台获取数据判断
function inAddClazz() {
    var newClazzId = document.getElementById("newClazzId");
    var newClazzName = document.getElementById("newClazzName");
    var newGradeName = document.getElementById("newGradeName");
    var newTname = document.getElementById("newTname");

    var json = {"newClazzId": newClazzId.value, "newClazzName": newClazzName.value, "newGradeName": newGradeName.value, "newTname": newTname.value};

    var data = ajax("clazz/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newClazzId.style.backgroundColor = "white";
        newClazzName.style.backgroundColor = "white";
        newClazzId.setAttribute("readonly", "true");
        newClazzName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteClazz" + newClazzId.value);//为了方便删除
        newClazzId.setAttribute("id", "clazzId" + newClazzId.value);
        newClazzName.setAttribute("id", "clazzName" + newClazzId.value);
        newClazzId.setAttribute("class", "clazzEdit" + newClazzId.value);
        newClazzName.setAttribute("class", "clazzEdit" + newClazzId.value);

        var newGradeNameParent = newGradeName.parentNode;
        var newTnameParent = newTname.parentNode;
        var input_gradeName = document.createElement("input");
        var input_tname = document.createElement("input");
        newGradeNameParent.appendChild(input_gradeName);
        newTnameParent.appendChild(input_tname);
        input_gradeName.setAttribute("type", "text");
        input_tname.setAttribute("type", "text");
        input_gradeName.setAttribute("id", "gradeName" + newClazzId.value);
        input_tname.setAttribute("id", "tname" + newClazzId.value);
        input_gradeName.setAttribute("class", "clazzEdit" + newClazzId.value);
        input_tname.setAttribute("class", "clazzEdit" + newClazzId.value);
        input_gradeName.value = newGradeName.value;
        input_tname.value = newTname.value;
        newGradeName.remove();
        newTname.remove();
        input_gradeName.setAttribute("readonly", "true");
        input_tname.setAttribute("readonly", "true");

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
        a_edit.setAttribute("class", "editClazz");
        a_edit.setAttribute("id", "editClazz" + newClazzId.value);
        a_edit.setAttribute("onclick", "clazzEdit(" + newClazzId.value + ")");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditClazz");
        a_saveEdit.setAttribute("id", "saveEditClazz" + newClazzId.value);
        a_saveEdit.setAttribute("onclick", "saveClazzEdit(" + newClazzId.value + ")");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteClazz(" + newClazzId.value + ")");
        alert(data.message);
    }
    document.getElementById("addClazz").style.display = "block";
    document.getElementById("inAddClazz").style.display = "none";
}
