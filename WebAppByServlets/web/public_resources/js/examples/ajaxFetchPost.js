// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
const localAddr3 = window.location.toString()
const index3 = localAddr.indexOf('d/')
const hostIs3 = localAddr.substring(0, (index3 + 2)) // http://localhost:8080/FirstWebApp_war_exploded/
const urlGetContent3 = hostIs3 + 'ExperimentalAjaxServletPost'   // .java

// Так же как и в GET составляем строку с данными, но уже без пути к файлу 
let id_product3 = 321, qty_product3 = 2, get_what3 = 'testFetch'
const params3 = 'get_what=' + get_what3 + '&id_product=' + id_product3 + '&qty_product=' + qty_product3

// Первым аргументом кладем путь, + строку как и в любом другом запросе, ключ=значение&ключ=значение 
fetch(urlGetContent3 + "?" + params3,

// Второй аргумент это объект с указаниями, методаи заголовка 
    {
        method: "GET",
        headers: {"content-type": "application/x-www-form-urlencoded"}
    })
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject()
        }
        return response.text()
    })
    .then(i => console.log(i))
    .catch((e) => console.log('ошибка', e))