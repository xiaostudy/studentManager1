
//编辑班级
function clazzEdit(clazzNumber) {
    var cla = "clazzEdit" + clazzNumber;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var gradeName = document.getElementById("gradeName" + clazzNumber);
    var gradeNameParent = gradeName.parentNode;
    var gradeNameId = gradeName.id;
    var gradeNameValue = gradeName.value;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("id", gradeNameId);
    select1.setAttribute("onchange", "selectTeacher(this)");

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

    var teacherName = document.getElementById("teacherName" + clazzNumber);
    var teacherNameParent = teacherName.parentNode;
    var teacherNameId = teacherName.id;
    var teacherNameValue = teacherName.value;
    teacherNameParent.innerText = "";

    var select2 = document.createElement("select");
    teacherNameParent.appendChild(select2);
    select2.setAttribute("id", teacherNameId);

    var json = {"gradeName": gradeNameValue};
    var data2 = ajax("teacher/showTeacherNameList", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var gradeName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        if(gradeName == teacherNameValue) {
            option.setAttribute("selected", "selected");
        }
        select2.appendChild(option);
    }

    var editClazz = document.getElementById("editClazz" + clazzNumber);
    editClazz.style.display = "none";
    var saveEditClazz = document.getElementById("saveEditClazz" + clazzNumber);
    saveEditClazz.style.display = "block";
}

//保存编辑班级
function saveClazzEdit(clazzNumber) {
    var cla = "clazzEdit" + clazzNumber;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newClazzNumber = document.getElementById("clazzNumber" + clazzNumber);
    var newClazzName = document.getElementById("clazzName" + clazzNumber);
    var newGradeName = document.getElementById("gradeName" + clazzNumber);
    var newGradeNameId = newGradeName.id;
    var newGradeNameValue = newGradeName.value;
    var newGradeNameParent = newGradeName.parentNode;
    var newTeacherName = document.getElementById("teacherName" + clazzNumber);
    var newTeacherNameId = newTeacherName.id;
    var newTeacherNameValue = newTeacherName.value;
    var newTeacherNameParent = newTeacherName.parentNode;
    var json = {"newClazzNumber": newClazzNumber.value, "newClazzName": newClazzName.value, "oldClazzNumber": clazzNumber, "newGradeName": newGradeNameValue, "newTeacherName": newTeacherNameValue};

    var data = ajax("clazz/update", "get", json, "json", false);

    newGradeNameParent.innerText = "";
    var input_gradeName = document.createElement("input");
    newGradeNameParent.appendChild(input_gradeName);
    input_gradeName.setAttribute("type", "text");
    input_gradeName.setAttribute("id", newGradeNameId);
    input_gradeName.setAttribute("class", "clazzEdit" + clazzNumber);
    newTeacherNameParent.innerText = "";
    var input_teacherName = document.createElement("input");
    newTeacherNameParent.appendChild(input_teacherName);
    input_teacherName.setAttribute("type", "text");
    input_teacherName.setAttribute("id", newTeacherNameId);
    input_teacherName.setAttribute("class", "clazzEdit" + clazzNumber);

    if(data.status == "error") {
        newClazzNumber.value = data.oldClazzNumber;
        newClazzName.value = data.oldClazzName;
        input_gradeName.value = data.oldGradeName;
        input_teacherName.value = data.oldTeacherName;
        alert(data.message);
    } else if(data.status == "ok") {
        input_gradeName.value = newGradeNameValue;
        input_teacherName.value = newTeacherNameValue;
        alert(data.message);
    }

    input_gradeName.setAttribute("readonly", "readonly");
    input_gradeName.style.backgroundColor = "white";
    input_teacherName.setAttribute("readonly", "readonly");
    input_teacherName.style.backgroundColor = "white";

    var editClazz = document.getElementById("editClazz" + clazzNumber);
    editClazz.style.display = "block";
    var saveEditClazz = document.getElementById("saveEditClazz" + clazzNumber);
    saveEditClazz.style.display = "none";
}

//删除班级
function deleteClazz(clazzNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"clazzNumber": clazzNumber};

        var data = ajax("clazz/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteClazz" + clazzNumber).empty();
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
    var td_clazzNumber = document.createElement("td");
    var td_clazzName = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_teacherName = document.createElement("td");
    tr.appendChild(td_clazzNumber);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_clazzName);
    tr.appendChild(td_teacherName);
    var input_id = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newClazzNumber");
    input_name.setAttribute("id", "newClazzName");
    td_clazzNumber.appendChild(input_id);
    td_clazzName.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";

    var select1 = document.createElement("select");
    td_gradeName.appendChild(select1);
    select1.setAttribute("id", "newGradeName");
    select1.setAttribute("onchange", "selectTeacher(this)");

    var data1 = ajax("grade/showGradeNameList", "get", "", "json", false);

    var length1 = getJsonLength(data1);
    var selectGradeNameValue;
    for(var i = 0; i < length1; i++) {
        var gradeName = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        select1.appendChild(option);
        if(i == 0) {
            selectGradeNameValue = gradeName;
        }
    }

    var select2 = document.createElement("select");
    td_teacherName.appendChild(select2);
    select2.setAttribute("id", "newTeacherName");

    var json = {"gradeName": selectGradeNameValue};
    var data2 = ajax("teacher/showTeacherNameList", "get", json, "json", false);

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
    var newClazzNumber = document.getElementById("newClazzNumber");
    var newClazzName = document.getElementById("newClazzName");
    var newGradeName = document.getElementById("newGradeName");
    var newTeacherName = document.getElementById("newTeacherName");

    var json = {"newClazzNumber": newClazzNumber.value, "newClazzName": newClazzName.value, "newGradeName": newGradeName.value, "newTeacherName": newTeacherName.value};

    var data = ajax("clazz/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newClazzNumber.style.backgroundColor = "white";
        newClazzName.style.backgroundColor = "white";
        newClazzNumber.setAttribute("readonly", "true");
        newClazzName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteClazz" + newClazzNumber.value);//为了方便删除
        newClazzNumber.setAttribute("id", "clazzNumber" + newClazzNumber.value);
        newClazzName.setAttribute("id", "clazzName" + newClazzNumber.value);
        newClazzNumber.setAttribute("class", "clazzEdit" + newClazzNumber.value);
        newClazzName.setAttribute("class", "clazzEdit" + newClazzNumber.value);

        var newGradeNameParent = newGradeName.parentNode;
        var newTeacherNameParent = newTeacherName.parentNode;
        var input_gradeName = document.createElement("input");
        var input_teacherName = document.createElement("input");
        newGradeNameParent.appendChild(input_gradeName);
        newTeacherNameParent.appendChild(input_teacherName);
        input_gradeName.setAttribute("type", "text");
        input_teacherName.setAttribute("type", "text");
        input_gradeName.setAttribute("id", "gradeName" + newClazzNumber.value);
        input_teacherName.setAttribute("id", "teacherName" + newClazzNumber.value);
        input_gradeName.setAttribute("class", "clazzEdit" + newClazzNumber.value);
        input_teacherName.setAttribute("class", "clazzEdit" + newClazzNumber.value);
        input_gradeName.value = newGradeName.value;
        input_teacherName.value = newTeacherName.value;
        newGradeName.remove();
        newTeacherName.remove();
        input_gradeName.setAttribute("readonly", "true");
        input_teacherName.setAttribute("readonly", "true");

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
        a_edit.setAttribute("id", "editClazz" + newClazzNumber.value);
        a_edit.setAttribute("onclick", "clazzEdit('" + newClazzNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditClazz");
        a_saveEdit.setAttribute("id", "saveEditClazz" + newClazzNumber.value);
        a_saveEdit.setAttribute("onclick", "saveClazzEdit('" + newClazzNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteClazz('" + newClazzNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addClazz").style.display = "block";
    document.getElementById("inAddClazz").style.display = "none";
}
