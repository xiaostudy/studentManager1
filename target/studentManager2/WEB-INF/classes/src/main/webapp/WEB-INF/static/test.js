
//编辑考试
function testEdit(testId) {
    var cla = "testEdit" + testId;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var subjectName = document.getElementById("subjectName" + testId);
    var subjectNameParent = subjectName.parentNode;
    var subjectNameId = subjectName.id;
    var subjectNameValue = subjectName.value;
    subjectName.remove();

    var select = document.createElement("select");
    subjectNameParent.appendChild(select);
    select.setAttribute("id", subjectNameId);

    var data = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        select.appendChild(option);
    }

    var editTest = document.getElementById("editTest" + testId);
    editTest.style.display = "none";
    var saveEditTest = document.getElementById("saveEditTest" + testId);
    saveEditTest.style.display = "block";
}

//保存编辑考试
function saveTestEdit(testId) {
    var cla = "testEdit" + testId;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newTestId = document.getElementById("testId" + testId);
    var newYear = document.getElementById("year" + testId);
    var newTestName = document.getElementById("testName" + testId);
    var newSubjectName = document.getElementById("subjectName" + testId);
    var newSubjectNameId = newSubjectName.id;
    var newSubjectNameValue = newSubjectName.value;
    var newSubjectNameParent = newSubjectName.parentNode;
    var json = {"newTestId": newTestId.value, "newYear": newYear.value, "newTestName": newTestName.value, "oldTestId": testId, "newSubjectName": newSubjectNameValue};

    var data = ajax("test/update", "get", json, "json", false);

    newSubjectNameParent.innerText = "";
    var input_subjectName = document.createElement("input");
    newSubjectNameParent.appendChild(input_subjectName);
    input_subjectName.setAttribute("type", "text");
    input_subjectName.setAttribute("id", newSubjectNameId);
    input_subjectName.setAttribute("class", "testEdit" + testId);

    if(data.status == "error") {
        newTestId.value = data.oldTestId;
        newYear.value = data.oldYear;
        newTestName.value = data.oldTestName;
        input_subjectName.value = data.oldSubjectName;
        alert(data.message);
    } else if(data.status == "ok") {
        input_subjectName.value = newSubjectNameValue;
        alert(data.message);
    }
    input_subjectName.setAttribute("readonly", "readonly");

    var editTest = document.getElementById("editTest" + testId);
    editTest.style.display = "block";
    var saveEditTest = document.getElementById("saveEditTest" + testId);
    saveEditTest.style.display = "none";
}

//删除考试
function deleteTest(testId) {
    var b = del();//在common.js中
    if(b) {
        var json = {"testId": testId};

        var data = ajax("test/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteTest" + testId).empty();
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
    var td_testId = document.createElement("td");
    var td_year = document.createElement("td");
    var td_testName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    tr.appendChild(td_testId);
    tr.appendChild(td_year);
    tr.appendChild(td_testName);
    tr.appendChild(td_subjectName);
    var input_id = document.createElement("input");
    var input_year = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_year.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newTestId");
    input_year.setAttribute("id", "newYear");
    input_name.setAttribute("id", "newTestName");
    td_testId.appendChild(input_id);
    td_year.appendChild(input_year);
    td_testName.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_year.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";

    var select = document.createElement("select");
    td_subjectName.appendChild(select);
    select.setAttribute("id", "newSubjectName");

    var data = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length = getJsonLength(data);
    for(var i = 0; i < length; i++) {
        var subjectName = data[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select.appendChild(option);
    }

    document.getElementById("addTest").style.display = "none";
    document.getElementById("inAddTest").style.display = "block";
}

//添加考试，从后台获取数据判断
function inAddTest() {
    var newTestId = document.getElementById("newTestId");
    var newYear = document.getElementById("newYear");
    var newTestName = document.getElementById("newTestName");
    var newSubjectName = document.getElementById("newSubjectName");

    var json = {"newTestId": newTestId.value, "newYear": newYear.value, "newTestName": newTestName.value, "newSubjectName": newSubjectName.value};

    var data = ajax("test/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newTestId.style.backgroundColor = "white";
        newYear.style.backgroundColor = "white";
        newTestName.style.backgroundColor = "white";
        newTestId.setAttribute("readonly", "true");
        newYear.setAttribute("readonly", "true");
        newTestName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteTest" + newTestId.value);//为了方便删除
        newTestId.setAttribute("id", "testId" + newTestId.value);
        newYear.setAttribute("id", "year" + newTestId.value);
        newTestName.setAttribute("id", "testName" + newTestId.value);
        newTestId.setAttribute("class", "testEdit" + newTestId.value);
        newYear.setAttribute("class", "testEdit" + newTestId.value);
        newTestName.setAttribute("class", "testEdit" + newTestId.value);

        var newSubjectNameParent = newSubjectName.parentNode;
        var input_subjectName = document.createElement("input");
        newSubjectNameParent.appendChild(input_subjectName);
        input_subjectName.setAttribute("type", "text");
        input_subjectName.setAttribute("id", "subjectName" + newTestId.value);
        input_subjectName.setAttribute("class", "testEdit" + newTestId.value);
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
        a_edit.setAttribute("id", "editTest" + newTestId.value);
        a_edit.setAttribute("onclick", "testEdit(" + newTestId.value + ")");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditTest");
        a_saveEdit.setAttribute("id", "saveEditTest" + newTestId.value);
        a_saveEdit.setAttribute("onclick", "saveTestEdit(" + newTestId.value + ")");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteTest(" + newTestId.value + ")");
        alert(data.message);
    }
    document.getElementById("addTest").style.display = "block";
    document.getElementById("inAddTest").style.display = "none";
}
