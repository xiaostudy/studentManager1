
//编辑成绩
function resultsEdit(resultsNumber) {
    var input = document.getElementsByClassName("resultsEdit" + resultsNumber);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }

    var gradeName = document.getElementById("gradeName" + resultsNumber);
    var gradeNameParent = gradeName.parentNode;
    var gradeNameId = gradeName.id;
    var gradeNameValue = gradeName.value;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("onchange", "selectTestStudent(this)");
    select1.setAttribute("id", gradeNameId);

    var data1 = ajax("test/showGradeNameList", "get", "", "json", false);

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

    var testName = document.getElementById("testName" + resultsNumber);
    var testNameParent = testName.parentNode;
    var testNameId = testName.id;
    var testNameValue = testName.value;
    testName.remove();

    var select2 = document.createElement("select");
    testNameParent.appendChild(select2);
    select2.setAttribute("onchange", "selectSubjectByTest(this)");
    select2.setAttribute("id", testNameId);

    var json1 = {"gradeName": gradeNameValue};
    var data2 = ajax("test/showTestNameList", "get", json1, "json", false);

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

    var studentName = document.getElementById("studentName" + resultsNumber);
    var studentNameParent = studentName.parentNode;
    var studentNameId = studentName.id;
    var studentNameValue = studentName.value;
    studentName.remove();

    var select3 = document.createElement("select");
    studentNameParent.appendChild(select3);
    select3.setAttribute("id", studentNameId);

    var data3 = ajax("student/showStudentNameListAjax", "get", json1, "json", false);

    var length3 = getJsonLength(data3);
    for(var i = 0; i < length3; i++) {
        var studentName = data3[i];
        var option = document.createElement("option");
        option.setAttribute("value", studentName);
        option.innerText = studentName;
        if(studentName == studentNameValue) {
            option.setAttribute("selected", "selected");
        }
        select3.appendChild(option);
    }

    var subjectName = document.getElementById("subjectName" + resultsNumber);
    var subjectNameParent = subjectName.parentNode;
    var subjectNameId = subjectName.id;
    var subjectNameValue = subjectName.value;
    subjectName.remove();

    var select4 = document.createElement("select");
    subjectNameParent.appendChild(select4);
    select4.setAttribute("id", subjectNameId);

    var json1 = {"gradeName": gradeNameValue, "testName": testNameValue};
    var data4 = ajax("test/showSubjectNameList", "get", json1, "json", false);

    var length4 = getJsonLength(data4);
    for(var i = 0; i < length4; i++) {
        var subjectName = data4[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        if(subjectName == subjectNameValue) {
            option.setAttribute("selected", "selected");
        }
        select4.appendChild(option);
    }

    var editResults = document.getElementById("editResults" + resultsNumber);
    editResults.style.display = "none";
    var saveEditResults = document.getElementById("saveEditResults" + resultsNumber);
    saveEditResults.style.display = "block";
}

//保存编辑成绩
function saveResultsEdit(resultsNumber) {
    var input = document.getElementsByClassName("resultsEdit" + resultsNumber);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newResultsNumber = document.getElementById("resultsNumber" + resultsNumber);
    var newGradeName = document.getElementById("gradeName" + resultsNumber);
    var newGradeNameId = newGradeName.id;
    var newGradeNameValue = newGradeName.value;
    var newGradeNameParent = newGradeName.parentNode;
    var newStudentName = document.getElementById("studentName" + resultsNumber);
    var newStudentNameId = newStudentName.id;
    var newStudentNameValue = newStudentName.value;
    var newStudentNameParent = newStudentName.parentNode;
    var newTestName = document.getElementById("testName" + resultsNumber);
    var newTestNameId = newTestName.id;
    var newTestNameValue = newTestName.value;
    var newTestNameParent = newTestName.parentNode;
    var newSubjectName = document.getElementById("subjectName" + resultsNumber);
    var newSubjectNameId = newSubjectName.id;
    var newSubjectNameValue = newSubjectName.value;
    var newSubjectNameParent = newSubjectName.parentNode;
    var newScore = document.getElementById("score" + resultsNumber);

    var json = {"newResultsNumber": newResultsNumber.value, "newGradeName": newGradeNameValue, "newStudentName": newStudentNameValue, "newTestName": newTestNameValue, "oldResultsNumber": resultsNumber, "newSubjectName": newSubjectNameValue, "newScore": newScore.value};

    var data = ajax("results/update", "get", json, "json", false);

    newGradeNameParent.innerText = "";
    var input_gradeName = document.createElement("input");
    newGradeNameParent.appendChild(input_gradeName);
    input_gradeName.setAttribute("type", "text");
    input_gradeName.setAttribute("id", newGradeNameId);
    input_gradeName.setAttribute("class", "resultsEdit" + resultsNumber);

    newStudentNameParent.innerText = "";
    var input_studentName = document.createElement("input");
    newStudentNameParent.appendChild(input_studentName);
    input_studentName.setAttribute("type", "text");
    input_studentName.setAttribute("id", newStudentNameId);
    input_studentName.setAttribute("class", "resultsEdit" + resultsNumber);

    newTestNameParent.innerText = "";
    var input_testName = document.createElement("input");
    newTestNameParent.appendChild(input_testName);
    input_testName.setAttribute("type", "text");
    input_testName.setAttribute("id", newTestNameId);
    input_testName.setAttribute("class", "resultsEdit" + resultsNumber);

    newSubjectNameParent.innerText = "";
    var input_subjectName = document.createElement("input");
    newSubjectNameParent.appendChild(input_subjectName);
    input_subjectName.setAttribute("type", "text");
    input_subjectName.setAttribute("id", newSubjectNameId);
    input_subjectName.setAttribute("class", "resultsEdit" + resultsNumber);

    if(data.status == "error") {
        newResultsNumber.value = data.oldResultsNumber;
        input_gradeName.value = data.oldGradeName;
        input_studentName.value = data.oldStudentName;
        input_testName.value = data.oldTestName;
        input_subjectName.value = data.oldSubjectName;
        newScore.value = data.oldScore;
        alert(data.message);
    } else if(data.status == "ok") {
        input_gradeName.value = newGradeNameValue;
        input_studentName.value = newStudentNameValue;
        input_testName.value = newTestNameValue;
        input_subjectName.value = newSubjectNameValue;
        alert(data.message);
    }
    input_gradeName.setAttribute("readonly", "readonly");
    input_studentName.setAttribute("readonly", "readonly");
    input_testName.setAttribute("readonly", "readonly");
    input_subjectName.setAttribute("readonly", "readonly");

    var editResults = document.getElementById("editResults" + resultsNumber);
    editResults.style.display = "block";
    var saveEditResults = document.getElementById("saveEditResults" + resultsNumber);
    saveEditResults.style.display = "none";
}

//添加成绩时添加输入信息
function addResults() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);
    tr.setAttribute("id","add");
    var td_resultsNumber = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_testName = document.createElement("td");
    var td_studentName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    var td_score = document.createElement("td");
    tr.appendChild(td_resultsNumber);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_testName);
    tr.appendChild(td_studentName);
    tr.appendChild(td_subjectName);
    tr.appendChild(td_score);
    var input_resultsNumber = document.createElement("input");
    var input_score = document.createElement("input");
    input_resultsNumber.setAttribute("type", "text");
    input_score.setAttribute("type", "text");
    input_resultsNumber.setAttribute("id", "newResultsNumber");
    input_score.setAttribute("id", "newScore");
    td_resultsNumber.appendChild(input_resultsNumber);
    td_score.appendChild(input_score);
    input_resultsNumber.style.backgroundColor = "yellow";
    input_score.style.backgroundColor = "yellow";

    var select1 = document.createElement("select");
    td_gradeName.appendChild(select1);
    select1.setAttribute("onchange", "selectTestStudent(this)");
    select1.setAttribute("id", "newGradeName");

    var data1 = ajax("test/showGradeNameList", "get", "", "json", false);

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
        select1.appendChild(option);
    }

    var select2 = document.createElement("select");
    td_testName.appendChild(select2);
    select2.setAttribute("onchange", "selectSubjectByTest_add(this)");
    select2.setAttribute("id", "newTestName");

    var json1 = {"gradeName": selectdeGradeNameValue, "testName": selectdeTestNameValue};
    var data2 = ajax("test/showTestNameList", "get", json1, "json", false);

    var selectdeTestNameValue;
    var length1 = getJsonLength(data2);
    for(var i = 0; i < length1; i++) {
        var testName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", testName);
        option.innerText = testName;
        if(0 == i) {
            selectdeTestNameValue = testName;
        }
        select2.appendChild(option);
    }

    var select3 = document.createElement("select");
    td_studentName.appendChild(select3);
    select3.setAttribute("id", "newStudentName");

    var json2 = {"gradeName": selectdeGradeNameValue};
    var data3 = ajax("student/showStudentNameList", "get", json2, "json", false);

    var length3 = getJsonLength(data3);
    for(var i = 0; i < length3; i++) {
        var studentName = data3[i];
        var option = document.createElement("option");
        option.setAttribute("value", studentName);
        option.innerText = studentName;
        select3.appendChild(option);
    }

    var select4 = document.createElement("select");
    td_subjectName.appendChild(select4);
    select4.setAttribute("id", "newSubjectName");

    var json2 = {"gradeName": selectdeGradeNameValue, "testName": selectdeTestNameValue};
    var data4 = ajax("test/showSubjectNameList", "get", json2, "json", false);

    var length4 = getJsonLength(data4);
    for(var i = 0; i < length4; i++) {
        var subjectName = data4[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select4.appendChild(option);
    }

    document.getElementById("addResults").style.display = "none";
    document.getElementById("inAddResults").style.display = "block";
}

//添加成绩，从后台获取数据判断
function inAddResults() {
    var newResultsNumber = document.getElementById("newResultsNumber");
    var newGradeName = document.getElementById("newGradeName");
    var newStudentName = document.getElementById("newStudentName");
    var newTestName = document.getElementById("newTestName");
    var newSubjectName = document.getElementById("newSubjectName");
    var newScore = document.getElementById("newScore");

    var json = {"newResultsNumber": newResultsNumber.value, "newGradeName":newGradeName.value, "newStudentName": newStudentName.value, "newTestName": newTestName.value, "newSubjectName": newSubjectName.value, "newScore": newScore.value};

    var data = ajax("results/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {
        newResultsNumber.style.backgroundColor = "white";
        newScore.style.backgroundColor = "white";
        newResultsNumber.setAttribute("readonly", "true");
        newScore.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteResults" + newResultsNumber.value);//为了方便删除
        newResultsNumber.setAttribute("id", "resultsNumber" + newResultsNumber.value);
        newScore.setAttribute("id", "score" + newResultsNumber.value);
        newResultsNumber.setAttribute("class", "resultsEdit" + newResultsNumber.value);
        newScore.setAttribute("class", "resultsEdit" + newResultsNumber.value);

        var newGradeNameParent = newGradeName.parentNode;
        var input_gradeName = document.createElement("input");
        newGradeNameParent.appendChild(input_gradeName);
        input_gradeName.setAttribute("type", "text");
        input_gradeName.setAttribute("id", "gradeName" + newResultsNumber.value);
        input_gradeName.setAttribute("class", "resultsEdit" + newResultsNumber.value);
        input_gradeName.value = newGradeName.value;
        newGradeName.remove();
        input_gradeName.setAttribute("readonly", "true");

        var newStudentNameParent = newStudentName.parentNode;
        var input_studentName = document.createElement("input");
        newStudentNameParent.appendChild(input_studentName);
        input_studentName.setAttribute("type", "text");
        input_studentName.setAttribute("id", "subjectName" + newResultsNumber.value);
        input_studentName.setAttribute("class", "resultsEdit" + newResultsNumber.value);
        input_studentName.value = newStudentName.value;
        newStudentName.remove();
        input_studentName.setAttribute("readonly", "true");

        var newTestNameParent = newTestName.parentNode;
        var input_testName = document.createElement("input");
        newTestNameParent.appendChild(input_testName);
        input_testName.setAttribute("type", "text");
        input_testName.setAttribute("id", "testName" + newResultsNumber.value);
        input_testName.setAttribute("class", "resultsEdit" + newResultsNumber.value);
        input_testName.value = newTestName.value;
        newTestName.remove();
        input_testName.setAttribute("readonly", "true");

        var newSubjectNameParent = newSubjectName.parentNode;
        var input_subjectName = document.createElement("input");
        newSubjectNameParent.appendChild(input_subjectName);
        input_subjectName.setAttribute("type", "text");
        input_subjectName.setAttribute("id", "subjectName" + newResultsNumber.value);
        input_subjectName.setAttribute("class", "resultsEdit" + newResultsNumber.value);
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
        a_edit.setAttribute("id", "editResults" + newResultsNumber.value);
        a_edit.setAttribute("onclick", "resultsEdit('" + newResultsNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditResults");
        a_saveEdit.setAttribute("id", "saveEditResults" + newResultsNumber.value);
        a_saveEdit.setAttribute("onclick", "saveResultsEdit('" + newResultsNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteResults('" + newResultsNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addResults").style.display = "block";
    document.getElementById("inAddResults").style.display = "none";
}

//删除成绩
function deleteResults(resultsNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"resultsNumber": resultsNumber};

        var data = ajax("results/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteResults" + resultsNumber).empty();
            alert(data.message);
        }
    }
}