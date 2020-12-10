// for dataTables.. from FrontBackEnd site
$('#example').DataTable({
  "processing": true,
  "serverSide": true,
  "ajax": {
    "url": "/employees",
    "type": "POST",
    "dataType": "json",
    "contentType": "application/json",
    "data": function (d) {
      return JSON.stringify(d);
    }
  },
  "columns": [
    {"data": "name", "width": "20%"},
    {"data": "position","width": "20%"},
    {"data": "office", "width": "20%"},
    {"data": "start_date", "width": "20%"},
    {"data": "salary", "width": "20%"}
  ]
});

//  ...
// https://ru.stackoverflow.com/questions/972943/%D0%9A%D0%B0%D0%BA-%D0%BE%D0%B1%D0%BE%D0%B7%D0%BD%D0%B0%D1%87%D0%B8%D1%82%D1%8C-%D0%B0%D0%BA%D1%82%D0%B8%D0%B2%D0%BD%D1%8B%D0%B9-%D1%8D%D0%BB%D0%B5%D0%BC%D0%B5%D0%BD%D1%82-%D0%B2-%D0%BC%D0%B5%D0%BD%D1%8E-%D1%81-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%D0%BC-bootstrap-u-thymeleaf
var currentPage = window.location.pathname;
//Получаем список элементов
var items = document.getElementById("navbarResponsive").getElementsByClassName("nav-link");
var activeClass = "active";
//записываем паттерн, в котором убираем "/"
var patt = new RegExp(currentPage.split("/")[1],"i");

for(var i = 0; i < items.length; i++){
  var navItem = items[i];
  //в дочернем элементе получаем ссылку на последний элемент в иерархии адреса
  var linkArray = navItem.children[0].href.split("/");
  var linkHTML = linkArray[linkArray.length - 1];
  if(patt.test(linkHTML)){     //добавляем класс к элементу
    navItem.classList.add("active");
  }
}

// ..
$(".alert").alert('close')

// for customer dataTables
var url = '/customer/data_for_datatable';

  $(document).ready(function () {

    $('#customerTable').DataTable({
      "ajax": url,
      "processing": true,
      "serverSide": true,
      "columns": [
        {
          "data": "id",
          "render": function (data, type, row, meta) {
            return '<a href="/customer/edit/' + row.id + '">' + data + '</a>';
          }
        },
        {
          "data": "firstName",
          "render": function (data, type, row, meta) {
            return '<a href="/customer/edit/' + row.id + '">' + data + '</a>';
          }
        },
        {
          "data": "lastName",
          "render": function (data, type, row, meta) {
            return '<a href="/customer/edit/' + row.id + '">' + data + '</a>';
          }
        },
        {"data": "emailAddress"},
        {"data": "city"},
        {"data": "country"},
        {"data": "phoneNumber"}
      ]
    });
  });
