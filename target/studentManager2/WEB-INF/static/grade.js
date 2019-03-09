
//编辑年级
function gradeEdit(gradeNumber) {
    var cla = "gradeEdit" + gradeNumber;
    var input = document.getElementsByClassName(cla);
    for (var i = 0; i < input.length; i++) {
        input[i].removeAttribute("readonly");
        input[i].style.backgroundColor = "yellow";
    }
    var editGrade = document.getElementById("editGrade" + gradeNumber);
    editGrade.style.display = "none";
    var saveEditGrade = document.getElementById("saveEditGrade" + gradeNumber);
    saveEditGrade.style.display = "block";
}

//保存编辑年级
function saveGradeEdit(gradeNumber) {
    var cla = "gradeEdit" + gradeNumber;
    var input = document.getElementsByClassName(cla);

    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readonly", "readonly");
        input[i].style.backgroundColor = "white";
    }
    var newGradeNumber = document.getElementById("gradeNumber" + gradeNumber);
    var newGradeName = document.getElementById("gradeName" + gradeNumber);
    var json = {"newGradeNumber": newGradeNumber.value, "newGradeName": newGradeName.value, "oldGradeNumber": gradeNumber};

    var data = ajax("grade/update", "get", json, "json", false);

    if(data.status == "error") {
        newGradeNumber.value = data.oldGradeNumber;
        newGradeName.value = data.oldGradeName;
        alert(data.message);
    } else if(data.status == "ok") {
        alert(data.message);
    }

    var editGrade = document.getElementById("editGrade" + gradeNumber);
    editGrade.style.display = "block";
    var saveEditGrade = document.getElementById("saveEditGrade" + gradeNumber);
    saveEditGrade.style.display = "none";
}

//删除年级
function deleteGrade(gradeNumber) {
    var b = del();//在common.js中
    if(b) {
        var json = {"gradeNumber": gradeNumber};

        var data = ajax("grade/delete", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
        } else if(data.status == "ok") {
            $("#deleteGrade" + gradeNumber).empty();
            alert(data.message);
        }
    }
}

//添加年级时添加输入信息
function addGrade() {
    var table = document.getElementById("table");
    var tr = document.createElement("tr");
    table.appendChild(tr);

    //table.insertRow(-1);//向table最后添加tr
    //var last = table.lastChild;
    tr.setAttribute("id","add");
    var td_id = document.createElement("td");
    var td_name = document.createElement("td");
    tr.appendChild(td_id);
    tr.appendChild(td_name);
    var input_id = document.createElement("input");
    var input_name = document.createElement("input");
    input_id.setAttribute("type", "text");
    input_name.setAttribute("type", "text");
    input_id.setAttribute("id", "newGradeNumber");
    input_name.setAttribute("id", "newGradeName");
    td_id.appendChild(input_id);
    td_name.appendChild(input_name);
    input_id.style.backgroundColor = "yellow";
    input_name.style.backgroundColor = "yellow";
    document.getElementById("addGrade").style.display = "none";
    document.getElementById("inAddGrade").style.display = "block";
}

//添加年级，从后台获取数据判断
function inAddGrade() {
    var newGradeNumber = document.getElementById("newGradeNumber");
    var newGradeName = document.getElementById("newGradeName");

    var json = {"newGradeNumber": newGradeNumber.value, "newGradeName": newGradeName.value};

    var data = ajax("grade/insert", "get", json, "json", false);

    if(data.status == "error") {
        document.getElementById("table").lastChild.remove();

        alert(data.message);
    } else if(data.status == "ok") {
        var gradeNumber = document.getElementById("newGradeNumber");
        var gradeName = document.getElementById("newGradeName");

        gradeNumber.style.backgroundColor = "white";
        gradeName.style.backgroundColor = "white";
        gradeNumber.setAttribute("readonly", "true");
        gradeName.setAttribute("readonly", "true");

        var tr = document.getElementById("table").lastChild;
        tr.setAttribute("id", "deleteGrade" + gradeNumber.value);//为了方便删除
        gradeNumber.setAttribute("id", "gradeNumber" + gradeNumber.value);
        gradeName.setAttribute("id", "gradeName" + gradeNumber.value);
        gradeNumber.setAttribute("class", "gradeEdit" + gradeNumber.value);
        gradeName.setAttribute("class", "gradeEdit" + gradeNumber.value);

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
        a_edit.setAttribute("class", "editGrade");
        a_edit.setAttribute("id", "editGrade" + gradeNumber.value);
        a_edit.setAttribute("onclick", "gradeEdit('" + gradeNumber.value + "')");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("href", "#");
        a_saveEdit.setAttribute("class", "saveEditGrade");
        a_saveEdit.setAttribute("id", "saveEditGrade" + gradeNumber.value);
        a_saveEdit.setAttribute("onclick", "saveGradeEdit('" + gradeNumber.value + "')");
        a_saveEdit.setAttribute("display", "none");

        a_del.innerText = "删除";
        a_del.setAttribute("href", "#");
        a_del.setAttribute("onclick", "deleteGrade('" + gradeNumber.value + "')");
        alert(data.message);
    }
    document.getElementById("addGrade").style.display = "block";
    document.getElementById("inAddGrade").style.display = "none";
}
