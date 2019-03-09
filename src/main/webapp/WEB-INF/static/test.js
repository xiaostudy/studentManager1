
//编辑考试
function testEdit(testNumber) {
    var cla = "testEdit" + testNumber;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var gradeName = document.getElementById("gradeName" + testNumber);
    var gradeNameParent = gradeName.parentNode;
    var gradeNameId = gradeName.id;
    var gradeNameValue = gradeName.value;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("id", gradeNameId);
    select1.setAttribute("onchange", "selectSubject(this)");

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

    var subjectName = document.getElementById("subjectName" + testNumber);
    var subjectNameParent = subjectName.parentNode;
    var subjectNameId = subjectName.id;
    var subjectNameValue = subjectName.value;
    subjectName.remove();

    var select2 = document.createElement("select");
    subjectNameParent.appendChild(select2);
    select2.setAttribute("id", subjectNameId);

    var json = {"gradeName": gradeNameValue};
    var data2 = ajax("subject/showSubjectNameListByGradeNameAjax", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var subjectName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        select2.appendChild(option);
    }

    var editTest = document.getElementById("editTest" + testNumber);
    editTest.style.display = "none";
    var saveEditTest = document.getElementById("saveEditTest" + testNumber);
    saveEditTest.style.display = "block";
}

//保存编辑考试
function saveTestEdit(testNumber) {
    var cla = "testEdit" + testNumber;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newTestNumber = document.getElementById("testNumber" + testNumber);
    var newTestName = document.getElementById("testName" + testNumber);
    var newGradeName = document.getElementById("gradeName" + testNumber);
    var newGradeNameId = newGradeName.id;
    var newGradeNameValue = newGradeName.value;
    var newGradeNameParent = newGradeName.parentNode;
    var newSubjectName = document.getElementById("subjectName" + testNumber);
    var newSubjectNameId = newSubjectName.id;
    var newSubjectNameValue = newSubjectName.value;
    var newSubjectNameParent = newSubjectName.parentNode;
    var json = {"newTestNumber": newTestNumber.value, "newGradeName": newGradeNameValue, "newTestName": newTestName.value,
        "oldTestNumber": testNumber, "newSubjectName": newSubjectNameValue};

    var data = ajax("test/update", "get", json, "json", false);

    newGradeNameParent.innerText = "";
    var input_gradeName = document.createElement("input");
    newGradeNameParent.appendChild(input_gradeName);
    input_gradeName.setAttribute("type", "text");
    input_gradeName.setAttribute("id", newGradeNameId);
    input_gradeName.setAttribute("class", "testEdit" + testNumber);

    newSubjectNameParent.innerText = "";
    var input_subjectName = document.createElement("input");
    newSubjectNameParent.appendChild(input_subjectName);
    input_subjectName.setAttribute("type", "text");
    input_subjectName.setAttribute("id", newSubjectNameId);
    input_subjectName.setAttribute("class", "testEdit" + testNumber);

    if(data.status == "error") {
        newTestNumber.value = data.oldTestNumber;
        newTestName.value = data.oldTestName;
        input_gradeName.value = data.oldGradeName;
        input_subjectName.value = data.oldSubjectName;
        alert(data.message);
    } else if(data.status == "ok") {
        input_gradeName.value = newGradeNameValue;
        input_subjectName.value = newSubjectNameValue;
        alert(data.message);
    }
    input_gradeName.setAttribute("readonly", "readonly");
    input_subjectName.setAttribute("readonly", "readonly");

    var editTest = document.getElementById("editTest" + testNumber);
    editTest.style.display = "block";
    var saveEditTest = document.getElementById("saveEditTest" + testNumber);
    saveEditTest.style.display = "none";
}

//删除考试
function deleteTest(testNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"testNumber": testNumber};

        var data = ajax("test/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteTest" + testNumber).empty();
            alert(data.message);
        }
    }
}

//添加考试时添加输入信息
function addTest() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);
    tr.setAttribute("id","add");
    var td_testNumber = document.createElement("td");
    var td_testName = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    tr.appendChild(td_testNumber);
    tr.appendChild(td_testName);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_subjectName);
    var input_id = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newTestNumber");
    input_name.setAttribute("id", "newTestName");
    td_testNumber.appendChild(input_id);
    td_testName.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";

    var select_gradeName = document.createElement("select");
    td_gradeName.appendChild(select_gradeName);
    select_gradeName.setAttribute("id", "newGradeName");
    select_gradeName.setAttribute("onchange", "selectSubject(this)");
    var data1 = ajax("grade/showGradeNameListAjax", "get", "", "json", false);

    var selectdeGradeNameValue;
    var length1 = getJsonLength(data1);
    for(var i = 0; i < length1; i++) {
        var gradeName = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", gradeName);
        option.innerText = gradeName;
        if(0 == i) {
            option.setAttribute("selected", "selected");
            selectdeGradeNameValue = gradeName;
        }
        select_gradeName.appendChild(option);
    }

    var select_subjectName = document.createElement("select");
    td_subjectName.appendChild(select_subjectName);
    select_subjectName.setAttribute("id", "newSubjectName");

    var json = {"gradeName": selectdeGradeNameValue};
    var data2 = ajax("subject/showSubjectNameListByGradeNameAjax", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var subjectName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select_subjectName.appendChild(option);
    }

    document.getElementById("addTest").style.display = "none";
    document.getElementById("inAddTest").style.display = "block";
}

//添加考试，从后台获取数据判断
function inAddTest() {
    var newTestNumber = document.getElementById("newTestNumber");
    var newTestName = document.getElementById("newTestName");
    var newGradeName = document.getElementById("newGradeName");
    var newSubjectName = document.getElementById("newSubjectName");

    var json = {"newTestNumber": newTestNumber.value, "newGradeName": newGradeName.value, "newTestName": newTestName.value, "newSubjectName": newSubjectName.value};

    var data = ajax("test/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newTestNumber.style.backgroundColor = "white";
        newTestName.style.backgroundColor = "white";
        newTestNumber.setAttribute("readonly", "true");
        newTestName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteTest" + newTestNumber.value);//为了方便删除
        newTestNumber.setAttribute("id", "testNumber" + newTestNumber.value);
        newTestName.setAttribute("id", "testName" + newTestNumber.value);
        newTestNumber.setAttribute("class", "testEdit" + newTestNumber.value);
        newTestName.setAttribute("class", "testEdit" + newTestNumber.value);

        var newGradeNameParent = newGradeName.parentNode;
        var input_gradeName = document.createElement("input");
        newGradeNameParent.appendChild(input_gradeName);
        input_gradeName.setAttribute("type", "text");
        input_gradeName.setAttribute("id", "gradeName" + newTestNumber.value);
        input_gradeName.setAttribute("class", "testEdit" + newTestNumber.value);
        input_gradeName.value = newGradeName.value;
        newGradeName.remove();
        input_gradeName.setAttribute("readonly", "true");

        var newSubjectNameParent = newSubjectName.parentNode;
        var input_subjectName = document.createElement("input");
        newSubjectNameParent.appendChild(input_subjectName);
        input_subjectName.setAttribute("type", "text");
        input_subjectName.setAttribute("id", "subjectName" + newTestNumber.value);
        input_subjectName.setAttribute("class", "testEdit" + newTestNumber.value);
        input_subjectName.value = newSubjectName.value;
        newSubjectName.remove();
        input_subjectName.setAttribute("readonly", "true");

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
        a_edit.setAttribute("class", "editTest");
        a_edit.setAttribute("id", "editTest" + newTestNumber.value);
        a_edit.setAttribute("onclick", "testEdit('" + newTestNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditTest");
        a_saveEdit.setAttribute("id", "saveEditTest" + newTestNumber.value);
        a_saveEdit.setAttribute("onclick", "saveTestEdit('" + newTestNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteTest('" + newTestNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addTest").style.display = "block";
    document.getElementById("inAddTest").style.display = "none";
}
