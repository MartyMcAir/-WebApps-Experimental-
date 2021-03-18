// console.log('ajaxMainGet.js - file loaded')
/////////////////////////////////////////////////
/*  Составляем строку запроса и кладем данные, строка состоит из:
пути до файла обработчика ? имя в GET запросе где будет лежать значение запроса id_product и
через & мы передаем количество qty_product. */
// const url = "http://localhost:8080/FirstWebApp_war_exploded/ExperimentalAjaxServlet?id_product=" + id_product + "&qty_product=" + qty_product

// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
// const url = "http://localhost:8080/FirstWebApp_war_exploded/ExperimentalAjaxServlet?id_product=" + id_product + "&qty_product=" + qty_product;

// using in all CRUD
let get_what = '', servletName = '', contentType = '', responseType = ''
const localAddr = window.location.toString()
const index = localAddr.indexOf('d/')
const hostIs = localAddr.substring(0, (index + 2)) // http://localhost:8080/FirstWebApp_war_exploded/
const events = ['readystatechange']

// функция отправки контента на сервер и в консоль
function sendContentToServerAndConsole(responseTypeIs, contentTypeIs, servletName, get_whatIs) {
    const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    request.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + '?get_what=' + get_whatIs;
    request.open('GET', urlWhat, true)   // открываем соединение

    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener(events[0], () => {
        if (request.readyState === 4 && request.status === 200) {
            const responseText = request.responseText
            // console.log(contentTypeIs)
            // console.log('www?: ', contentTypeIs.includes('www'))
            // console.log('json?: ', contentTypeIs.includes('json'))

            // такое почемуто не прокатывает..
            // if (contentTypeIs.includes('www')) {
            //     responseText = request.responseText
            // } else if (contentTypeIs.includes('json')) {
            //     responseText = request.response
            // }
            console.log(responseText)
        }
    })
    request.send()   // Выполняем запрос
}

//using in all CRUD
const tbodyHibernateObj = document.querySelector('#expTable')

const resultIs = document.querySelector('#resultId')

function clearDelStatus() {
    resultId.textContent = ''
}

// Using When Add or Update data from form in inputs
function fillArray(elements, i, namesArr, valuesArr, params) {
    const fieldName = elements[i].getAttribute('name')
    const fieldValue = elements[i].value // value with out submit
    // const fieldValue = formPlace.elements[i].textContent
    // const fieldValue = formPlace.elements[i].content

    // добавляем названия полей и их значения в массив
    namesArr.push(fieldName)
    valuesArr.push(fieldValue)

    params += fieldName + '=' + fieldValue + '&'

    // console.log(fieldName + '=' + fieldValue)
    // делаем проверку, чтоб скрытые значения input  полей не чистил
    if (fieldName !== '_method' && fieldName !== 'id')
        elements[i].value = ''
    return params;
}

function getParamsFromForm(idFormObj) {
    let params = '?'
    const elements = idFormObj.querySelectorAll('input')
    // const elements = formPlace.elements
    const idIs = idFormObj.querySelector('input[name="id"]')


    // создаем массив ключей и значений отправляемых на сервер
    let namesArr = [], valuesArr = []
    for (let i = 0; i < elements.length; i++) { // 0 это _method и его value обнуляется что не есть гуд
        // console.dir(elements[i])
        params = fillArray(elements, i, namesArr, valuesArr, params)
    }

    // return array
    // return [params, namesArr, valuesArr]
    // using
    // const paramsFromForm = getParamsFromForm(formObj)
    // const params = paramsFromForm[0]
    // const namesArr = paramsFromForm[1]
    // const valuesArr = paramsFromForm[2]

    // or return object _ удобнее
    return {params: params, namesArr: namesArr, valuesArr: valuesArr, idIs: idIs}
    // using
    // const params = paramsFromForm.params
    // const namesArr = paramsFromForm.namesArr
    // const valuesArr = paramsFromForm.valuesArr
}