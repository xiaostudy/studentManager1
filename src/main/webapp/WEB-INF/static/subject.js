
//编辑学科
function subjectEdit(subjectNumber) {
    var cla = "subjectEdit" + subjectNumber;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var gradeName = document.getElementById("gradeName" + subjectNumber);
    var gradeNameParent = gradeName.parentNode;
    var gradeNameId = gradeName.id;
    var gradeNameValue = gradeName.value;
    gradeName.remove();

    var select = document.createElement("select");
    gradeNameParent.appendChild(select);
    select.setAttribute("id", gradeNameId);

    var data = ajax("grade/showGradeNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var gradeName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        if(gradeName == gradeNameValue) {
            option.setAttribute("selected", "selected");
        }
        select.appendChild(option);
    }

    var editSubject = document.getElementById("editSubject" + subjectNumber);
    editSubject.style.display = "none";
    var saveEditSubject = document.getElementById("saveEditSubject" + subjectNumber);
    saveEditSubject.style.display = "block";
}

//保存编辑学科
function saveSubjectEdit(subjectNumber) {
    var cla = "subjectEdit" + subjectNumber;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newSubjectNumber = document.getElementById("subjectNumber" + subjectNumber);
    var newSubjectName = document.getElementById("subjectName" + subjectNumber);
    var newGradeName = document.getElementById("gradeName" + subjectNumber);
    var newGradeNameId = newGradeName.id;
    var newGradeNameValue = newGradeName.value;
    var newGradeNameParent = newGradeName.parentNode;
    newGradeNameParent.innerText = "";
    var input_gradeName = document.createElement("input");
    newGradeNameParent.appendChild(input_gradeName);
    input_gradeName.setAttribute("type", "text");
    input_gradeName.setAttribute("id", newGradeNameId);
    input_gradeName.setAttribute("class", "clazzEdit" + subjectNumber);

    var json = {"newSubjectNumber": newSubjectNumber.value, "newSubjectName": newSubjectName.value, "newGradeName": newGradeNameValue, "oldSubjectNumber": subjectNumber};

    var data = ajax("subject/update", "get", json, "json", false);

    if(data.status == "error") {
        newSubjectNumber.value = data.oldSubjectNumber;
        input_gradeName.value = data.oldGradeName;
        newSubjectName.value = data.oldSubjectName;
        input_gradeName.setAttribute("readonly", "readonly");
        input_gradeName.style.backgroundColor = "white";
        alert(data.message);
    } else if(data.status == "ok") {
        input_gradeName.value = newGradeNameValue;
        alert(data.message);
    }

    var editSubject = document.getElementById("editSubject" + subjectNumber);
    editSubject.style.display = "block";
    var saveEditSubject = document.getElementById("saveEditSubject" + subjectNumber);
    saveEditSubject.style.display = "none";
}

//删除学科
function deleteSubject(subjectNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"subjectNumber": subjectNumber};

        var data = ajax("subject/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteSubject" + subjectNumber).empty();
            alert(data.message);
        }
    }
}

//添加学科时添加输入信息
function addSubject() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);

    tr.setAttribute("id","add");
    var td_subjectNumber = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    tr.appendChild(td_subjectNumber);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_subjectName);
    var input_id = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newSubjectNumber");
    input_name.setAttribute("id", "newSubjectName");
    td_subjectNumber.appendChild(input_id);
    td_subjectName.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";

    var select = document.createElement("select");
    td_gradeName.appendChild(select);
    select.setAttribute("id", "newGradeName");

    var data = ajax("grade/showGradeNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var gradeName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        select.appendChild(option);
    }

    document.getElementById("addSubject").style.display = "none";
    document.getElementById("inAddSubject").style.display = "block";
}

//添加学科，从后台获取数据判断
function inAddSubject() {
    var newSubjectNumber = document.getElementById("newSubjectNumber");
    var newGradeName = document.getElementById("newGradeName");
    var newSubjectName = document.getElementById("newSubjectName");

    var json = {"newSubjectNumber": newSubjectNumber.value, "newGradeName": newGradeName.value, "newSubjectName": newSubjectName.value};

    var data = ajax("subject/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();

        alert(data.message);
    } else if(data.status == "ok") {

        newSubjectNumber.style.backgroundColor = "white";
        newSubjectName.style.backgroundColor = "white";
        newSubjectNumber.setAttribute("readonly", "true");
        newSubjectName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteSubject" + newSubjectNumber.value);//为了方便删除
        newSubjectNumber.setAttribute("id", "subjectNumber" + newSubjectNumber.value);
        newSubjectName.setAttribute("id", "subjectName" + newSubjectNumber.value);
        newSubjectNumber.setAttribute("class", "subjectEdit" + newSubjectNumber.value);
        newSubjectName.setAttribute("class", "subjectEdit" + newSubjectNumber.value);

        var newGradeNameParent = newGradeName.parentNode;
        var input_gradeName = document.createElement("input");
        newGradeNameParent.appendChild(input_gradeName);
        input_gradeName.setAttribute("type", "text");
        input_gradeName.setAttribute("id", "gradeName" + newSubjectNumber.value);
        input_gradeName.setAttribute("class", "clazzEdit" + newSubjectNumber.value);
        input_gradeName.value = newGradeName.value;
        newGradeName.remove();
        input_gradeName.setAttribute("readonly", "true");

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
        a_edit.setAttribute("class", "editSubject");
        a_edit.setAttribute("id", "editSubject" + newSubjectNumber.value);
        a_edit.setAttribute("onclick", "subjectEdit('" + newSubjectNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditSubject");
        a_saveEdit.setAttribute("id", "saveEditSubject" + newSubjectNumber.value);
        a_saveEdit.setAttribute("onclick", "saveSubjectEdit('" + newSubjectNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteSubject('" + newSubjectNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addSubject").style.display = "block";
    document.getElementById("inAddSubject").style.display = "none";
}
