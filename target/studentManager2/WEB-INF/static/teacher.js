
//编辑教师
function teacherEdit(_teacherNumber) {
    var cla = "teacherEdit" + _teacherNumber;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }
    var teacherNumber = document.getElementById("teacherNumber" + _teacherNumber);
    var teacherName = document.getElementById("teacherName" + _teacherNumber);
    var sex = document.getElementById("sex" + _teacherNumber);
    var born = document.getElementById("born" + _teacherNumber);
    var home = document.getElementById("home" + _teacherNumber);
    var contact = document.getElementById("contact" + _teacherNumber);
    var entryDate = document.getElementById("entryDate" + _teacherNumber);
    var gradeName = document.getElementById("gradeName" + _teacherNumber);
    var subjectName = document.getElementById("subjectName" + _teacherNumber);

    teacherNumber.removeAttribute("readonly");
    teacherNumber.style.backgroundColor = "yellow";

    teacherName.removeAttribute("readonly");
    teacherName.style.backgroundColor = "yellow";

    home.removeAttribute("readonly");
    home.style.backgroundColor = "yellow";

    contact.removeAttribute("readonly");
    contact.style.backgroundColor = "yellow";

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
    sexY.setAttribute("name", "sex" + _teacherNumber);
    sexX.setAttribute("name", "sex" + _teacherNumber);
    sexY.style.width = "15px";
    sexX.style.width = "15px";

    var strBorn = born.value;
    born.setAttribute("type", "date");
    born.setAttribute("value", strToDate(strBorn));

    var strEntryDate = entryDate.value;
    entryDate.setAttribute("type", "date");
    entryDate.setAttribute("value", strToDate(strEntryDate));

    var gradeNameValue = gradeName.value;
    var gradeNameId = gradeName.id;
    var gradeNameParent = gradeName.parentNode;
    gradeName.remove();

    var select1 = document.createElement("select");
    gradeNameParent.appendChild(select1);
    select1.setAttribute("id", gradeNameId);
    select1.setAttribute("onchange", "selectSubject(this)");

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

    var subjectNameValue = subjectName.value;
    var subjectNameId = subjectName.id;
    var subjectNameParent = subjectName.parentNode;
    subjectName.remove();
    var select2 = document.createElement("select");
    subjectNameParent.appendChild(select2);
    select2.setAttribute("id", subjectNameId);

    var json = {"gradeName": gradeNameValue};
    var data2 = ajax("subject/showSubjectNameListByGradeName", "get", json, "json", false);

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

    var editTeacher = document.getElementById("editTeacher" + _teacherNumber);
    editTeacher.style.display = "none";
    editTeacher.setAttribute("style", "display: none");
    var saveEditTeacher = document.getElementById("saveEditTeacher" + _teacherNumber);
    saveEditTeacher.style.display = "block";
}

//保存编辑教师
function saveTeacherEdit(_teacherNumber) {
    var cla = "teacherEdit" + _teacherNumber;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }

    var teacherNumber = document.getElementById("teacherNumber" + _teacherNumber);
    var teacherName = document.getElementById("teacherName" + _teacherNumber);
    var sex = document.getElementById("sex").parentNode;
    var sexValue = getRadioValue("sex" + _teacherNumber);
    var born = document.getElementById("born" + _teacherNumber);
    var home = document.getElementById("home" + _teacherNumber);
    var contact = document.getElementById("contact" + _teacherNumber);
    var entryDate = document.getElementById("entryDate" + _teacherNumber);
    var gradeName = document.getElementById("gradeName" + _teacherNumber).parentNode;
    var gradeNameValue = document.getElementById("gradeName" + _teacherNumber).value;
    var subjectName = document.getElementById("subjectName" + _teacherNumber).parentNode;
    var subjectNameValue = document.getElementById("subjectName" + _teacherNumber).value;

    if(sexValue == "Y" || sexValue == "y") {
        sexValue = "男";
    } else {
        sexValue = "女";
    }

    var json = {"newTeacherNumber": teacherNumber.value, "newTeacherName": teacherName.value, "oldTeacherNumber": _teacherNumber,
        "newSex": sexValue, "newBorn": born.value, "newHome": home.value, "newContact": contact.value, "newEntryDate": entryDate.value,
        "newGradeName": gradeNameValue, "newSubjectName": subjectNameValue
    };

    var data = ajax("teacher/update", "get", json, "json", false);

    sex.innerText = "";
    var inputSex = document.createElement("input");
    inputSex.setAttribute("type", "text");
    sex.appendChild(inputSex);

    born.setAttribute("type", "text");
    entryDate.setAttribute("type", "text");

    gradeName.innerText = "";
    var inputGradeName = document.createElement("input");
    inputGradeName.setAttribute("type", "text");
    gradeName.appendChild(inputGradeName);
    subjectName.innerText = "";
    var inputSubjectName = document.createElement("input");
    inputSubjectName.setAttribute("type", "text");
    subjectName.appendChild(inputSubjectName);
    if(data.status == "error") {
        teacherNumber.value = data.teacherNumber;
        teacherName.value = data.teacherName;
        inputSex.setAttribute("id", "sex" + data.teacherNumber);
        inputSex.value = data.sex;
        inputSex.setAttribute("class", "teacherEdit" + data.teacherNumber);
        born.value = strToDate(data.born);
        home.value = data.home;
        contact.value = data.contact;
        entryDate.value = strToDate(data.entryDate);
        inputGradeName.value = data.gradeName;
        inputGradeName.setAttribute("id", "gradeName" + data.teacherNumber);
        inputGradeName.setAttribute("class", "teacherEdit" + data.teacherNumber);
        inputSubjectName.value = data.subjectName;
        inputSubjectName.setAttribute("id", "subjectName" + data.teacherNumber);
        inputSubjectName.setAttribute("class", "teacherEdit" + data.teacherNumber);
        alert(data.message);
    } else if(data.status == "ok") {
        inputSex.setAttribute("id", "sex" + teacherNumber.value);
        inputSex.setAttribute("value", sexValue);
        inputSex.setAttribute("class", "teacherEdit" + teacherNumber.value);

        inputGradeName.setAttribute("id", "gradeName" + teacherNumber.value);
        inputGradeName.setAttribute("value", gradeNameValue);
        inputGradeName.setAttribute("class", "teacherEdit" + teacherNumber.value);
        inputSubjectName.setAttribute("id", "subjectName" + teacherNumber.value);
        inputSubjectName.setAttribute("value", subjectNameValue);
        inputSubjectName.setAttribute("class", "teacherEdit" + teacherNumber.value);

        alert(data.message);
    }

    teacherNumber.setAttribute("readonly", true);
    teacherName.setAttribute("readonly", true);
    inputSex.setAttribute("readonly", true);
    born.setAttribute("readonly", true);
    home.setAttribute("readonly", true);
    contact.setAttribute("readonly", true);
    entryDate.setAttribute("readonly", true);
    inputGradeName.setAttribute("readonly", true);
    inputSubjectName.setAttribute("readonly", true);

    var editTeacher = document.getElementById("editTeacher" + _teacherNumber);
    editTeacher.style.display = "block";
    var saveEditTeacher = document.getElementById("saveEditTeacher" + _teacherNumber);
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
    var td_teacherNumber = document.createElement("td");
    var td_teacherName = document.createElement("td");
    var td_sex = document.createElement("td");
    var td_born = document.createElement("td");
    var td_home = document.createElement("td");
    var td_contact = document.createElement("td");
    var td_entryDate = document.createElement("td");
    var td_gradeName = document.createElement("td");
    var td_subjectName = document.createElement("td");
    tr.appendChild(td_teacherNumber);
    tr.appendChild(td_teacherName);
    tr.appendChild(td_sex);
    tr.appendChild(td_born);
    tr.appendChild(td_home);
    tr.appendChild(td_contact);
    tr.appendChild(td_entryDate);
    tr.appendChild(td_gradeName);
    tr.appendChild(td_subjectName);
    var input_teacherNumber = document.createElement("input");
    var input_teacherName = document.createElement("input");
    var input_sexY = document.createElement("input");
    var input_sexX = document.createElement("input");
    var input_born = document.createElement("input");
    var input_home = document.createElement("input");
    var input_contact = document.createElement("input");
    var input_entryDate = document.createElement("input");
    var select_gradeName = document.createElement("select");
    var select_subjectName = document.createElement("select");
    var y = document.createElement("span");
    var x = document.createElement("span");
    td_teacherNumber.appendChild(input_teacherNumber);
    td_teacherName.appendChild(input_teacherName);
    td_sex.appendChild(input_sexY);
    td_sex.appendChild(y);
    td_sex.appendChild(input_sexX);
    td_sex.appendChild(x);
    td_born.appendChild(input_born);
    td_home.appendChild(input_home);
    td_contact.appendChild(input_contact);
    td_entryDate.appendChild(input_entryDate);
    td_gradeName.appendChild(select_gradeName);
    td_subjectName.appendChild(select_subjectName);

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

    input_teacherNumber.setAttribute("type", "text");
    input_teacherName.setAttribute("type", "text");
    input_sexY.setAttribute("type", "radio");
    input_sexX.setAttribute("type", "radio");
    input_born.setAttribute("type", "date");
    input_home.setAttribute("type", "text");
    input_contact.setAttribute("type", "text");
    input_entryDate.setAttribute("type", "date");

    input_teacherNumber.setAttribute("id", "teacherNumber");
    input_teacherName.setAttribute("id", "teacherName");
    input_born.setAttribute("id", "born");
    input_home.setAttribute("id", "home");
    input_contact.setAttribute("id", "contact");
    input_entryDate.setAttribute("id", "entryDate");
    input_born.valueAsDate = new Date(new Date().getTime() - 1000*60*60*24*365*18 - 1000*60*60*24*4);
    input_entryDate.valueAsDate = new Date();

    select_gradeName.setAttribute("id", "gradeName");
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

    select_subjectName.setAttribute("id", "subjectName");

    var json = {"gradeName": selectdeGradeNameValue};
    var data2 = ajax("subject/showSubjectNameListByGradeName", "get", json, "json", false);

    var length2 = getJsonLength(data2);
    for(var i = 0; i < length2; i++) {
        var subjectName = data2[i];
        var option = document.createElement("option");
        option.setAttribute("value", subjectName);
        option.innerText = subjectName;
        select_subjectName.appendChild(option);
    }

    input_teacherNumber.style.backgroundColor = "yellow";
    input_teacherName.style.backgroundColor = "yellow";
    input_born.style.backgroundColor = "yellow";
    input_home.style.backgroundColor = "yellow";
    input_contact.style.backgroundColor = "yellow";
    input_entryDate.style.backgroundColor = "yellow";
    document.getElementById("addTeacher").style.display = "none";
    document.getElementById("inAddTeacher").style.display = "block";
}

//添加学科，从后台获取数据判断
function inAddTeacher() {

    var newTeacherNumber = document.getElementById("teacherNumber");
    var newTeacherName = document.getElementById("teacherName");
    var newSexParent = document.getElementById("sex").parentNode;
    var newSexValue = document.getElementById("sex").value;
    var newBorn = document.getElementById("born");
    var newHome = document.getElementById("home");
    var newContact = document.getElementById("contact");
    var newEntryDate = document.getElementById("entryDate");
    var newGradeNameParent = document.getElementById("gradeName").parentNode;
    var newGradeNameValue = document.getElementById("gradeName").value;
    var newSubjectNameParent = document.getElementById("subjectName").parentNode;
    var newSubjectNameValue = document.getElementById("subjectName").value;

    var json = {"newTeacherNumber": newTeacherNumber.value, "newTeacherName": newTeacherName.value, "newSex": newSexValue,
        "newBorn": newBorn.value, "newHome": newHome.value, "newContact": newContact.value,
        "newEntryDate": newEntryDate.value, "newGradeName": newGradeNameValue, "newSubjectName": newSubjectNameValue
    };

    var data = ajax("teacher/insert", "get", json, "json", false);

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
        newEntryDate.setAttribute("type", "text");

        newGradeNameParent.innerText = "";
        var inputGradeName = document.createElement("input");
        inputGradeName.setAttribute("type", "text");
        inputGradeName.setAttribute("value", newGradeNameValue);
        newGradeNameParent.appendChild(inputGradeName);
        newSubjectNameParent.innerText = "";
        var inputSubjectName = document.createElement("input");
        inputSubjectName.setAttribute("type", "text");
        inputSubjectName.setAttribute("value", newSubjectNameValue);
        newSubjectNameParent.appendChild(inputSubjectName);

        newTeacherNumber.style.backgroundColor = "white";
        newTeacherName.style.backgroundColor = "white";
        newBorn.style.backgroundColor = "white";
        newHome.style.backgroundColor = "white";
        newContact.style.backgroundColor = "white";
        newEntryDate.style.backgroundColor = "white";
        newTeacherNumber.setAttribute("readonly", "true");
        newTeacherName.setAttribute("readonly", "true");
        inputSex.setAttribute("readonly", "true");
        newBorn.setAttribute("readonly", "true");
        newHome.setAttribute("readonly", "true");
        newContact.setAttribute("readonly", "true");
        newEntryDate.setAttribute("readonly", "true");
        inputGradeName.setAttribute("readonly", "true");
        inputSubjectName.setAttribute("readonly", "true");

        newTeacherNumber.setAttribute("id", "teacherNumber" + newTeacherNumber.value);
        newTeacherName.setAttribute("id", "teacherName" + newTeacherNumber.value);
        inputSex.setAttribute("id", "sex" + newTeacherNumber.value);
        newBorn.setAttribute("id", "born" + newTeacherNumber.value);
        newHome.setAttribute("id", "home" + newTeacherNumber.value);
        newContact.setAttribute("id", "contact" + newTeacherNumber.value);
        newEntryDate.setAttribute("id", "entryDate" + newTeacherNumber.value);
        inputGradeName.setAttribute("id", "gradeName" + newTeacherNumber.value);
        inputSubjectName.setAttribute("id", "subjectName" + newTeacherNumber.value);

        newTeacherNumber.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        newTeacherName.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        inputSex.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        newBorn.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        newHome.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        newContact.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        newEntryDate.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        inputGradeName.setAttribute("class", "teacherEdit" + newTeacherNumber.value);
        inputSubjectName.setAttribute("class", "teacherEdit" + newTeacherNumber.value);

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteTeacher" + newTeacherNumber.value);//为了方便删除

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
        a_edit.setAttribute("id", "editTeacher" + newTeacherNumber.value);
        a_edit.setAttribute("onclick", "teacherEdit('" + newTeacherNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditTeacher");
        a_saveEdit.setAttribute("id", "saveEditTeacher" + newTeacherNumber.value);
        a_saveEdit.setAttribute("onclick", "saveTeacherEdit('" + newTeacherNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteTeacher('" + newTeacherNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addTeacher").style.display = "block";
    document.getElementById("inAddTeacher").style.display = "none";
}

//删除教师
function deleteTeacher(teacherNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"teacherNumber": teacherNumber};

        var data = ajax("teacher/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteTeacher" + teacherNumber).empty();
            alert(data.message);
        }
    }
}

