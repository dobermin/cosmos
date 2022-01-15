let url = "https://app-cosmos.herokuapp.com/";
let urlLord = url + "lord";
let urlPlanet = url + "planet";

function addLord() {
    let $name = $("#name");
    let name = $name.val();
    let $age = $("#age");
    let age = $age.val();
    let data = {
        name: name,
        age: age
    }
    $.ajax({
        url: urlLord + "/add",
        contentType: "application/json; charset=UTF-8",
        datatype: "json",
        method: "POST",
        data: JSON.stringify(data),
        success: function () {
            alert("Успешно")
            $name.val("")
            $age.val("")
        },
        error: function () {
            alert("Ошибка")
        }
    });
}

function addPlanet() {
    let $name = $("#name");
    let name = $name.val();
    let data = {
        name: name
    }
    $.ajax({
        url: urlPlanet + "/add",
        contentType: "application/json; charset=UTF-8",
        datatype: "json",
        method: "POST",
        data: JSON.stringify(data),
        success: function () {
            alert("Успешно")
            $name.val("");
        },
        error: function () {
            alert("Ошибка")
        }
    });
}

function setLords(a) {
    let tr = $(a).parents("tr");
    let id = $('th[scope="row"]', tr).first().text();
    let lord = $('select', tr).first().val();
    $.ajax({
        url: urlPlanet + "/" + id + "/lord/" + lord,
        contentType: "application/json; charset=UTF-8",
        datatype: "json",
        method: "PUT",
        success: function () {
            alert("Успешно")
        },
        error: function () {
            alert("Ошибка")
        }
    });
}

function allLords() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlLord,
        cache: false,
        success: function (data) {
            $.each(data, function (i) {
                let items = [];
                let obj = data[i];
                items.push("<th scope='row'>" + (i + 1) + "</th>")
                items.push("<td>" + obj.name + "</td>>")
                items.push("<td>" + obj.age + "</td>>")
                $("<tr/>", {
                    "class": "",
                    html: items.join("")
                }).appendTo("#allLords");
            })
        }
    });
}
function slackers() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlLord + "/slackers",
        cache: false,
        success: function (data) {
            $.each(data, function (i) {
                let items = [];
                let obj = data[i];
                items.push("<th scope='row'>" + (i + 1) + "</th>")
                items.push("<td>" + obj.name + "</td>>")
                items.push("<td>" + obj.age + "</td>>")
                $("<tr/>", {
                    "class": "",
                    html: items.join("")
                }).appendTo("#slackers");
            })
        }
    });
}
function top10() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlLord + "/younger/10",
        cache: false,
        success: function (data) {
            $.each(data, function (i) {
                let items = [];
                let obj = data[i];
                items.push("<th scope='row'>" + (i + 1) + "</th>")
                items.push("<td>" + obj.name + "</td>>")
                items.push("<td>" + obj.age + "</td>>")
                $("<tr/>", {
                    "class": "",
                    html: items.join("")
                }).appendTo("#top");
            })
        }
    });
}

function allPlanets() {
    let lords = [];
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlLord,
        cache: false,
        success: function (data) {
            lords = $.merge(lords, data)
        }
    });
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlPlanet,
        cache: false,
        success: function (data) {
            $.each(data, function (i) {
                let items = [];
                let obj = data[i];
                items.push("<th scope='row'>" + obj.id + "</th>")
                items.push("<td class='name'>" + obj.name + "</td>>")
                let options = [];
                let sel = (obj.lord === null) ? " selected" : "";
                options.push("<option" + sel + "></option>")
                $.each(lords, function (i) {
                    let sel = (obj.lord != null && obj.lord.name === lords[i].name) ? " selected" : "";
                    options.push("<option" + sel + ">" + lords[i].name + "</option>")
                })
                items.push("<td><select>" + options.join("") + "</select></td>")
                let save = '<button class="btn btn-primary d-flex m-auto" onclick="setLords(this)" type="button">Сохранить</button>'
                items.push("<td>" + save + "</td>")
                let remove = '<button class="btn btn-danger d-flex m-auto" onclick="deletePlanet(this)" type="button">Удалить</button>'
                items.push("<td>" + remove + "</td>")
                $("<tr/>", {
                    "class": "",
                    html: items.join("")
                }).appendTo("#allPlanets");
            })
        }
    });
}

function deletePlanet(a) {
    let tr = $(a).parents("tr");
    let id = $('th[scope="row"]', tr).first().text();
    $.ajax({
        url: urlPlanet + "/" + id,
        contentType: "application/json; charset=UTF-8",
        datatype: "json",
        method: "DELETE",
        success: function () {
            $(tr).remove();
            alert("Успешно")
        },
        error: function () {
            alert("Ошибка")
        }
    });
}
$(document).ready(function(){
    let url = $(location).attr('href');
    let index = url.indexOf(".html");
    if (index === -1) url = url.substr(0, url.length)
    else url = url.substr(0, index)
    url = url.substr(0, url.lastIndexOf("/"))
    $("a").each(function () {
        let href = $(this).attr("href")
        $(this).attr("href", url + href)
    })
})