
//编辑成绩
function resultsEdit(resultsId) {
    var input = document.getElementsByClassName("resultsEdit" + resultsId);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var sname = document.getElementById("sname" + resultsId);
    var snameParent = sname.parentNode;
    var snameId = sname.id;
    var snameValue = sname.value;
    sname.remove();

    var select1 = document.createElement("select");
    snameParent.appendChild(select1);
    select1.setAttribute("id", snameId);

    var data1 = ajax("student/showSnameListAjax", "get", "", "json", false);

    var length1 = getJsonLength(data1);
    for(var i = 0; i < length1; i++) {
        var sname = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", sname);
        option.innerText = sname;
        if(sname == snameValue) {
            option.setAttribute("selected", "selected");
        }
        select1.appendChild(option);
    }

    var testName = document.getElementById("testName" + resultsId);
    var testNameParent = testName.parentNode;
    var testNameId = testName.id;
    var testNameValue = testName.value;
    testName.remove();

    var select2 = document.createElement("select");
    testNameParent.appendChild(select2);
    select2.setAttribute("id", testNameId);

    var data2 = ajax("test/showTestNameList", "get", "", "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var testName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", testName);
        option.innerText = testName;
        if(testName == testNameValue) {
            option.setAttribute("selected", "selected");
        }
        select2.appendChild(option);
    }

    var subjectName = document.getElementById("subjectName" + resultsId);
    var subjectNameParent = subjectName.parentNode;
    var subjectNameId = subjectName.id;
    var subjectNameValue = subjectName.value;
    subjectName.remove();

    var select3 = document.createElement("select");
    subjectNameParent.appendChild(select3);
    select3.setAttribute("id", subjectNameId);

    var data3 = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length3 = getJsonLength(data3);
    for(var i = 0; i < length3; i++) {
        var subjectName = data3[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        select3.appendChild(option);
    }

    var editResults = document.getElementById("editResults" + resultsId);
    editResults.style.display = "none";
    var saveEditResults = document.getElementById("saveEditResults" + resultsId);
    saveEditResults.style.display = "block";
}

//保存编辑成绩
function saveResultsEdit(resultsId) {
    var input = document.getElementsByClassName("resultsEdit" + resultsId);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newResultsId = document.getElementById("resultsId" + resultsId);
    var newSname = document.getElementById("sname" + resultsId);
    var newSnameId = newSname.id;
    var newSnameValue = newSname.value;
    var newSnameParent = newSname.parentNode;
    var newTestName = document.getElementById("testName" + resultsId);
    var newTestNameId = newTestName.id;
    var newTestNameValue = newTestName.value;
    var newTestNameParent = newTestName.parentNode;
    var newSubjectName = document.getElementById("subjectName" + resultsId);
    var newSubjectNameId = newSubjectName.id;
    var newSubjectNameValue = newSubjectName.value;
    var newSubjectNameParent = newSubjectName.parentNode;
    var newScore = document.getElementById("score" + resultsId);

    var json = {"newResultsId": newResultsId.value, "newSname": newSnameValue, "newTestName": newTestNameValue, "oldResultsId": resultsId, "newSubjectName": newSubjectNameValue, "newScore": newScore.value};

    var data = ajax("results/update", "get", json, "json", false);

    newSnameParent.innerText = "";
    var input_sname = document.createElement("input");
    newSnameParent.appendChild(input_sname);
    input_sname.setAttribute("type", "text");
    input_sname.setAttribute("id", newSnameId);
    input_sname.setAttribute("class", "resultsEdit" + resultsId);

    newTestNameParent.innerText = "";
    var input_testName = document.createElement("input");
    newTestNameParent.appendChild(input_testName);
    input_testName.setAttribute("type", "text");
    input_testName.setAttribute("id", newTestNameId);
    input_testName.setAttribute("class", "resultsEdit" + resultsId);

    newSubjectNameParent.innerText = "";
    var input_subjectName = document.createElement("input");
    newSubjectNameParent.appendChild(input_subjectName);
    input_subjectName.setAttribute("type", "text");
    input_subjectName.setAttribute("id", newSubjectNameId);
    input_subjectName.setAttribute("class", "resultsEdit" + resultsId);

    if(data.status == "error") {
        newResultsId.value = data.oldResultsId;
        input_sname.value = data.oldSname;
        input_testName.value = data.oldTestName;
        input_subjectName.value = data.oldSubjectName;
        newScore.value = data.oldScore;
        alert(data.message);
    } else if(data.status == "ok") {
        input_sname.value = newSnameValue;
        input_testName.value = newTestNameValue;
        input_subjectName.value = newSubjectNameValue;
        alert(data.message);
    }
    input_sname.setAttribute("readonly", "readonly");
    input_testName.setAttribute("readonly", "readonly");
    input_subjectName.setAttribute("readonly", "readonly");

    var editResults = document.getElementById("editResults" + resultsId);
    editResults.style.display = "block";
    var saveEditResults = document.getElementById("saveEditResults" + resultsId);
    saveEditResults.style.display = "none";
}

//添加成绩时添加输入信息
function addResults() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);
    tr.setAttribute("id","add");
    var td_resultsId = document.createElement("td");
    var td_sname = document.createElement("td");
    var td_testName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    var td_score = document.createElement("td");
    tr.appendChild(td_resultsId);
    tr.appendChild(td_sname);
    tr.appendChild(td_testName);
    tr.appendChild(td_subjectName);
    tr.appendChild(td_score);
    var input_resultsId = document.createElement("input");
    var input_score = document.createElement("input");
    input_resultsId.setAttribute("type", "text");
    input_score.setAttribute("type", "text");
    input_resultsId.setAttribute("id", "newResultsId");
    input_score.setAttribute("id", "newScore");
    td_resultsId.appendChild(input_resultsId);
    td_score.appendChild(input_score);
    input_resultsId.style.backgroundColor = "yellow";
    input_score.style.backgroundColor = "yellow";

    var select1 = document.createElement("select");
    td_sname.appendChild(select1);
    select1.setAttribute("id", "newSname");

    var data1 = ajax("student/showSnameList", "get", "", "json", false);

    var length = getJsonLength(data1);
    for(var i = 0; i < length; i++) {
        var sname = data1[i];
        var option = document.createElement("option");
        option.setAttribute("value", sname);
        option.innerText = sname;
        select1.appendChild(option);
    }

    var select2 = document.createElement("select");
    td_testName.appendChild(select2);
    select2.setAttribute("id", "newTestName");

    var data2 = ajax("test/showTestNameList", "get", "", "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var testName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", testName);
        option.innerText = testName;
        select2.appendChild(option);
    }

    var select3 = document.createElement("select");
    td_subjectName.appendChild(select3);
    select3.setAttribute("id", "newSubjectName");

    var data3 = ajax("subject/showSubjectNameList", "get", "", "json", false);

    var length3 = getJsonLength(data3);
    for(var i = 0; i < length3; i++) {
        var subjectName = data3[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select3.appendChild(option);
    }

    document.getElementById("addResults").style.display = "none";
    document.getElementById("inAddResults").style.display = "block";
}

//添加成绩，从后台获取数据判断
function inAddResults() {
    var newResultsId = document.getElementById("newResultsId");
    var newSname = document.getElementById("newSname");
    var newTestName = document.getElementById("newTestName");
    var newSubjectName = document.getElementById("newSubjectName");
    var newScore = document.getElementById("newScore");

    var json = {"newResultsId": newResultsId.value, "newSname": newSname.value, "newTestName": newTestName.value, "newSubjectName": newSubjectName.value, "newScore": newScore.value};

    var data = ajax("results/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newResultsId.style.backgroundColor = "white";
        newScore.style.backgroundColor = "white";
        newResultsId.setAttribute("readonly", "true");
        newScore.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteResults" + newResultsId.value);//为了方便删除
        newResultsId.setAttribute("id", "resultsId" + newResultsId.value);
        newScore.setAttribute("id", "score" + newResultsId.value);
        newResultsId.setAttribute("class", "resultsEdit" + newResultsId.value);
        newScore.setAttribute("class", "resultsEdit" + newResultsId.value);

        var newSnameParent = newSname.parentNode;
        var input_sname = document.createElement("input");
        newSnameParent.appendChild(input_sname);
        input_sname.setAttribute("type", "text");
        input_sname.setAttribute("id", "subjectName" + newResultsId.value);
        input_sname.setAttribute("class", "resultsEdit" + newResultsId.value);
        input_sname.value = newSname.value;
        newSname.remove();
        input_sname.setAttribute("readonly", "true");

        var newTestNameParent = newTestName.parentNode;
        var input_testName = document.createElement("input");
        newTestNameParent.appendChild(input_testName);
        input_testName.setAttribute("type", "text");
        input_testName.setAttribute("id", "testName" + newResultsId.value);
        input_testName.setAttribute("class", "resultsEdit" + newResultsId.value);
        input_testName.value = newTestName.value;
        newTestName.remove();
        input_testName.setAttribute("readonly", "true");

        var newSubjectNameParent = newSubjectName.parentNode;
        var input_subjectName = document.createElement("input");
        newSubjectNameParent.appendChild(input_subjectName);
        input_subjectName.setAttribute("type", "text");
        input_subjectName.setAttribute("id", "subjectName" + newResultsId.value);
        input_subjectName.setAttribute("class", "resultsEdit" + newResultsId.value);
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
        a_edit.setAttribute("class", "editResults");
        a_edit.setAttribute("id", "editResults" + newResultsId.value);
        a_edit.setAttribute("onclick", "resultsEdit(" + newResultsId.value + ")");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditResults");
        a_saveEdit.setAttribute("id", "saveEditResults" + newResultsId.value);
        a_saveEdit.setAttribute("onclick", "saveResultsEdit(" + newResultsId.value + ")");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteResults(" + newResultsId.value + ")");
        alert(data.message);
    }
    document.getElementById("addResults").style.display = "block";
    document.getElementById("inAddResults").style.display = "none";
}

//删除成绩
function deleteResults(resultsId) {
    var b = del();//在common.js中
    if(b) {
        var json = {"resultsId": resultsId};

        var data = ajax("results/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteResults" + resultsId).empty();
            alert(data.message);
        }
    }
}