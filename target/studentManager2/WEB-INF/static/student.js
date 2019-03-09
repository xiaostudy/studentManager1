
//编辑学生
function studentEdit(_studentNumber) {
    var input = document.getElementsByClassName("studentEdit" + _studentNumber);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }
    var studentNumber = document.getElementById("studentNumber" + _studentNumber);
    var studentName = document.getElementById("studentName" + _studentNumber);
    var sex = document.getElementById("sex" + _studentNumber);
    var born = document.getElementById("born" + _studentNumber);
    var home = document.getElementById("home" + _studentNumber);
    var homeName = document.getElementById("homeName" + _studentNumber);
    var homeContact = document.getElementById("homeContact" + _studentNumber);
    var admissionDate = document.getElementById("admissionDate" + _studentNumber);
    var gradeName = document.getElementById("gradeName" + _studentNumber);
    var clazzName = document.getElementById("clazzName" + _studentNumber);

    studentNumber.removeAttribute("readonly");
    studentNumber.style.backgroundColor = "yellow";
    studentName.removeAttribute("readonly");
    studentName.style.backgroundColor = "yellow";
    home.removeAttribute("readonly");
    home.style.backgroundColor = "yellow";
    homeName.removeAttribute("readonly");
    homeName.style.backgroundColor = "yellow";
    homeContact.removeAttribute("readonly");
    homeContact.style.backgroundColor = "yellow";

    var sexValue = sex.value;
    var sexParent = sex.parentNode;
    sex.remove();
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

    if(sexValue == "Y" || sexValue == "y" || sexValue == "男") {
        sexY.setAttribute("checked", "checked");
    } else {
        sexX.setAttribute("checked", "checked");
    }
    sexY.setAttribute("type", "radio");
    sexX.setAttribute("type", "radio");
    sexY.setAttribute("value", "Y");
    sexY.setAttribute("id", "sex");
    sexX.setAttribute("value", "X");
    sexY.setAttribute("name", "sex" + _studentNumber);
    sexX.setAttribute("name", "sex" + _studentNumber);
    sexY.style.width = "15px";
    sexX.style.width = "15px";

    var strBorn = born.value;
    born.setAttribute("type", "date");
    born.setAttribute("value", strToDate(strBorn));

    var strAdmissionDate = admissionDate.value;
    admissionDate.setAttribute("type", "date");
    admissionDate.setAttribute("value", strToDate(strAdmissionDate));

    var gradeNameValue = gradeName.value;
    var gradeNameId = gradeName.id;
    var gradeNameParent = gradeName.parentNode;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("id", gradeNameId);
    select1.setAttribute("onchange", "selectClazz(this)");

    var data1 = ajax("grade/showGradeNameListAjax", "get", "", "json", false);

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

    var clazzNameValue = clazzName.value;
    var clazzNameId = clazzName.id;
    var clazzNameParent = clazzName.parentNode;
    clazzName.remove();
    var select2 = document.createElement("select");
    clazzNameParent.appendChild(select2);
    select2.setAttribute("id", clazzNameId);

    var json = {"gradeName": gradeNameValue};
    var data2 = ajax("clazz/showClazzNameList", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var clazzName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", clazzName);
        option.innerText = clazzName;
        if(clazzName == clazzNameValue) {
            option.setAttribute("selected", "selected");
        }
        select2.appendChild(option);
    }

    var editStudent = document.getElementById("editStudent" + _studentNumber);
    editStudent.style.display = "none";
    editStudent.setAttribute("style", "display: none");
    var saveEditStudent = document.getElementById("saveEditStudent" + _studentNumber);
    saveEditStudent.style.display = "block";
}

//保存编辑学生
function saveStudentEdit(_studentNumber) {
    var input = document.getElementsByClassName("studentEdit" + _studentNumber);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }

    var studentNumber = document.getElementById("studentNumber" + _studentNumber);
    var studentName = document.getElementById("studentName" + _studentNumber);
    var sexParent = document.getElementById("sex").parentNode;
    var sexValue = getRadioValue("sex" + _studentNumber);
    var born = document.getElementById("born" + _studentNumber);
    var home = document.getElementById("home" + _studentNumber);
    var homeName = document.getElementById("homeName" + _studentNumber);
    var homeContact = document.getElementById("homeContact" + _studentNumber);
    var admissionDate = document.getElementById("admissionDate" + _studentNumber);
    var gradeName = document.getElementById("gradeName" + _studentNumber).parentNode;
    var gradeNameValue = document.getElementById("gradeName" + _studentNumber).value;
    var clazzNameParent = document.getElementById("clazzName" + _studentNumber).parentNode;
    var clazzNameValue = document.getElementById("clazzName" + _studentNumber).value;

    if(sexValue == "Y" || sexValue == "y") {
        sexValue = "男";
    } else {
        sexValue = "女";
    }

    var json = {"newStudentNumber": studentNumber.value, "newStudentName": studentName.value, "oldStudentNumber": _studentNumber, "newSex": sexValue,
        "newBorn": born.value, "newHome": home.value,"newHomeName": homeName.value, "newHomeContact": homeContact.value,
        "newAdmissionDate": admissionDate.value, "newGradeName": gradeNameValue, "newClazzName": clazzNameValue
    };

    var data = ajax("student/update", "get", json, "json", false);

    sexParent.innerText = "";
    var inputSex = document.createElement("input");
    inputSex.setAttribute("type", "text");
    sexParent.appendChild(inputSex);

    born.setAttribute("type", "text");
    admissionDate.setAttribute("type", "text");

    gradeName.innerText = "";
    var inputGradeName = document.createElement("input");
    inputGradeName.setAttribute("type", "text");
    gradeName.appendChild(inputGradeName);

    clazzNameParent.innerText = "";
    var inputClazzName = document.createElement("input");
    inputClazzName.setAttribute("type", "text");
    clazzNameParent.appendChild(inputClazzName);
    if(data.status == "error") {
        studentNumber.value = data.studentNumber;
        studentName.value = data.studentName;
        inputSex.setAttribute("id", "sex" + data.studentNumber);
        inputSex.value = data.sex;
        inputSex.setAttribute("class", "studentEdit" + data.studentNumber);
        born.value = strToDate(data.born);
        home.value = data.home;
        homeName.value = data.homeName;
        homeContact.value = data.homeContact;
        admissionDate.value = strToDate(data.admissionDate);
        inputGradeName.value = data.gradeName;
        inputGradeName.setAttribute("id", "gradeName" + data.studentNumber);
        inputGradeName.setAttribute("class", "studentEdit" + data.studentNumber);
        inputClazzName.value = data.clazzName;
        inputClazzName.setAttribute("id", "clazzName" + data.studentNumber);
        inputClazzName.setAttribute("class", "studentEdit" + data.studentNumber);
        alert(data.message);
    } else if(data.status == "ok") {
        inputSex.setAttribute("id", "sex" + studentNumber.value);
        inputSex.setAttribute("value", sexValue);
        inputSex.setAttribute("class", "studentEdit" + studentNumber.value);

        inputGradeName.setAttribute("id", "gradeName" + studentNumber.value);
        inputGradeName.setAttribute("value", gradeNameValue);
        inputGradeName.setAttribute("class", "studentEdit" + studentNumber.value);
        inputClazzName.setAttribute("id", "clazzName" + studentNumber.value);
        inputClazzName.setAttribute("value", clazzNameValue);
        inputClazzName.setAttribute("class", "studentEdit" + studentNumber.value);

        alert(data.message);
    }

    studentNumber.setAttribute("readonly", true);
    studentName.setAttribute("readonly", true);
    inputSex.setAttribute("readonly", true);
    born.setAttribute("readonly", true);
    home.setAttribute("readonly", true);
    homeName.setAttribute("readonly", true);
    homeContact.setAttribute("readonly", true);
    admissionDate.setAttribute("readonly", true);
    inputGradeName.setAttribute("readonly", true);
    inputClazzName.setAttribute("readonly", true);

    var editStudent = document.getElementById("editStudent" + _studentNumber);
    editStudent.style.display = "block";
    var saveEditStudent = document.getElementById("saveEditStudent" + _studentNumber);
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
    var td_studentNumber = document.createElement("td");
    var td_studentName = document.createElement("td");
    var td_sex = document.createElement("td");
    var td_born = document.createElement("td");
    var td_home = document.createElement("td");
    var td_homeName = document.createElement("td");
    var td_studentContact = document.createElement("td");
    var td_admissionDate = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_clazzName = document.createElement("td");
    tr.appendChild(td_studentNumber);
    tr.appendChild(td_studentName);
    tr.appendChild(td_sex);
    tr.appendChild(td_born);
    tr.appendChild(td_home);
    tr.appendChild(td_homeName);
    tr.appendChild(td_studentContact);
    tr.appendChild(td_admissionDate);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_clazzName);
    var input_studentNumber = document.createElement("input");
    var input_studentName = document.createElement("input");
    var input_sexY = document.createElement("input");
    var input_sexX = document.createElement("input");
    var input_born = document.createElement("input");
    var input_home = document.createElement("input");
    var input_homeName = document.createElement("input");
    var input_studentContact = document.createElement("input");
    var input_admissionDate = document.createElement("input");
    var select_gradeName = document.createElement("select");
    var select_clazzName = document.createElement("select");
    var y = document.createElement("span");
    var x = document.createElement("span");
    td_studentNumber.appendChild(input_studentNumber);
    td_studentName.appendChild(input_studentName);
    td_sex.appendChild(input_sexY);
    td_sex.appendChild(y);
    td_sex.appendChild(input_sexX);
    td_sex.appendChild(x);
    td_born.appendChild(input_born);
    td_home.appendChild(input_home);
    td_homeName.appendChild(input_homeName);
    td_studentContact.appendChild(input_studentContact);
    td_admissionDate.appendChild(input_admissionDate);
    td_gradeName.appendChild(select_gradeName);
    td_clazzName.appendChild(select_clazzName);

    y.innerText = "男";
    x.innerText = "女";
    input_sexY.setAttribute("checked", "checked");
    input_sexY.setAttribute("type", "radio");
    input_sexX.setAttribute("type", "radio");
    input_sexY.setAttribute("value", "男");
    input_sexY.setAttribute("id", "sex");
    input_sexX.setAttribute("value", "女");
    input_sexY.setAttribute("name", "sex");
    input_sexX.setAttribute("name", "sex");
    input_sexY.style.width = "15px";
    input_sexX.style.width = "15px";

    input_studentNumber.setAttribute("type", "text");
    input_studentName.setAttribute("type", "text");
    input_sexY.setAttribute("type", "radio");
    input_sexX.setAttribute("type", "radio");
    input_born.setAttribute("type", "date");
    input_home.setAttribute("type", "text");
    input_studentContact.setAttribute("type", "text");
    input_admissionDate.setAttribute("type", "date");

    input_studentNumber.setAttribute("id", "studentNumber");
    input_studentName.setAttribute("id", "studentName");
    input_born.setAttribute("id", "born");
    input_home.setAttribute("id", "home");
    input_homeName.setAttribute("id", "homeName");
    input_studentContact.setAttribute("id", "studentContact");
    input_admissionDate.setAttribute("id", "admissionDate");
    input_born.valueAsDate = new Date(new Date().getTime() - 1000*60*60*24*365*8 - 1000*60*60*24*2);
    input_admissionDate.valueAsDate = new Date();

    select_gradeName.setAttribute("id", "gradeName");
    select_gradeName.setAttribute("onchange", "selectClazz(this)");
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

    select_clazzName.setAttribute("id", "clazzName");

    var json = {"gradeName": selectdeGradeNameValue};
    var data2 = ajax("clazz/showClazzNameList", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var clazzName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", clazzName);
        option.innerText = clazzName;
        select_clazzName.appendChild(option);
    }

    input_studentNumber.style.backgroundColor = "yellow";
    input_studentName.style.backgroundColor = "yellow";
    input_born.style.backgroundColor = "yellow";
    input_home.style.backgroundColor = "yellow";
    input_homeName.style.backgroundColor = "yellow";
    input_studentContact.style.backgroundColor = "yellow";
    input_admissionDate.style.backgroundColor = "yellow";
    document.getElementById("addStudent").style.display = "none";
    document.getElementById("inAddStudent").style.display = "block";
}

//添加学科，从后台获取数据判断
function inAddStudent() {

    var newStudentNumber = document.getElementById("studentNumber");
    var newStudentName = document.getElementById("studentName");
    var newSexParent = document.getElementById("sex").parentNode;
    var newSexValue = document.getElementById("sex").value;
    var newBorn = document.getElementById("born");
    var newHome = document.getElementById("home");
    var newHomeName = document.getElementById("homeName");
    var newHomeContact = document.getElementById("studentContact");
    var newAdmissionDate = document.getElementById("admissionDate");
    var newGradeNameParent = document.getElementById("gradeName").parentNode;
    var newGradeNameValue = document.getElementById("gradeName").value;
    var newClazzNameParent = document.getElementById("clazzName").parentNode;
    var newClazzNameValue = document.getElementById("clazzName").value;

    var json = {"newStudentNumber": newStudentNumber.value, "newStudentName": newStudentName.value, "newSex": newSexValue,
        "newBorn": newBorn.value, "newHome": newHome.value, "newHomeName": newHomeName.value, "newHomeContact": newHomeContact.value,
        "newAdmissionDate": newAdmissionDate.value, "newGradeName": newGradeNameValue, "newClazzName": newClazzNameValue
    };

    var data = ajax("student/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();
        alert(data.message);
    } else if(data.status == "ok") {

        newSexParent.innerText = "";
        var inputSex = document.createElement("input");
        inputSex.setAttribute("type", "text");
        inputSex.setAttribute("value", newSexValue);
        newSexParent.appendChild(inputSex);

        newBorn.setAttribute("type", "text");
        newAdmissionDate.setAttribute("type", "text");

        newGradeNameParent.innerText = "";
        var inputGradeName = document.createElement("input");
        inputGradeName.setAttribute("type", "text");
        inputGradeName.setAttribute("value", newGradeNameValue);
        newGradeNameParent.appendChild(inputGradeName);

        newClazzNameParent.innerText = "";
        var inputSubjectName = document.createElement("input");
        inputSubjectName.setAttribute("type", "text");
        inputSubjectName.setAttribute("value", newClazzNameValue);
        newClazzNameParent.appendChild(inputSubjectName);

        newStudentNumber.style.backgroundColor = "white";
        newStudentName.style.backgroundColor = "white";
        newBorn.style.backgroundColor = "white";
        newHome.style.backgroundColor = "white";
        newHomeName.style.backgroundColor = "white";
        newHomeContact.style.backgroundColor = "white";
        newAdmissionDate.style.backgroundColor = "white";
        newStudentNumber.setAttribute("readonly", "true");
        newStudentName.setAttribute("readonly", "true");
        inputSex.setAttribute("readonly", "true");
        newBorn.setAttribute("readonly", "true");
        newHome.setAttribute("readonly", "true");
        newHomeName.setAttribute("readonly", "true");
        newHomeContact.setAttribute("readonly", "true");
        newAdmissionDate.setAttribute("readonly", "true");
        inputGradeName.setAttribute("readonly", "true");
        inputSubjectName.setAttribute("readonly", "true");

        newStudentNumber.setAttribute("id", "studentNumber" + newStudentNumber.value);
        newStudentName.setAttribute("id", "studentName" + newStudentNumber.value);
        inputSex.setAttribute("id", "sex" + newStudentNumber.value);
        newBorn.setAttribute("id", "born" + newStudentNumber.value);
        newHome.setAttribute("id", "home" + newStudentNumber.value);
        newHomeName.setAttribute("id", "homeName" + newStudentNumber.value);
        newHomeContact.setAttribute("id", "homeContact" + newStudentNumber.value);
        newAdmissionDate.setAttribute("id", "admissionDate" + newStudentNumber.value);
        inputGradeName.setAttribute("id", "gradeName" + newStudentNumber.value);
        inputSubjectName.setAttribute("id", "clazzName" + newStudentNumber.value);

        newStudentNumber.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newStudentName.setAttribute("class", "studentEdit" + newStudentNumber.value);
        inputSex.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newBorn.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newHome.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newHomeName.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newHomeContact.setAttribute("class", "studentEdit" + newStudentNumber.value);
        newAdmissionDate.setAttribute("class", "studentEdit" + newStudentNumber.value);
        inputGradeName.setAttribute("class", "studentEdit" + newStudentNumber.value);
        inputSubjectName.setAttribute("class", "studentEdit" + newStudentNumber.value);

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteStudent" + newStudentNumber.value);//为了方便删除

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
        a_edit.setAttribute("id", "editStudent" + newStudentNumber.value);
        a_edit.setAttribute("onclick", "studentEdit('" + newStudentNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditStudent");
        a_saveEdit.setAttribute("id", "saveEditStudent" + newStudentNumber.value);
        a_saveEdit.setAttribute("onclick", "saveStudentEdit('" + newStudentNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteStudent('" + newStudentNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addStudent").style.display = "block";
    document.getElementById("inAddStudent").style.display = "none";
}

//删除学生
function deleteStudent(studentNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"studentNumber": studentNumber};

        var data = ajax("student/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteStudent" + studentNumber).empty();
            alert(data.message);
        }
    }
}

