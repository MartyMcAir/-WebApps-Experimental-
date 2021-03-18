// console.log('ajaxMainGet.js - file loaded')
/////////////////////////////////////////////////
/*  Составляем строку запроса и кладем данные, строка состоит из:
пути до файла обработчика ? имя в GET запросе где будет лежать значение запроса id_product и
через & мы передаем количество qty_product. */
// const url = "http://localhost:8080/FirstWebApp_war_exploded/ExperimentalAjaxServlet?id_product=" + id_product + "&qty_product=" + qty_product

// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
// const url = "http://localhost:8080/FirstWebApp_war_exploded/ExperimentalAjaxServlet?id_product=" + id_product + "&qty_product=" + qty_product;
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

// для подключения здесь.. необходимо указывать полный путь, а не относительный.. (в обычном же HTML и так прокатывает)
// const jsFiles = ['ajaxGetFromForm.js', 'ajaxGetTableHeadPlainText.js', 'ajaxGetJsonAndShow.js',  // 0 1 2
//     'ajaxGetRequestExample.js',   // 3 4 5
// ]
// // importJsFiles(jsFiles)
// importJsFile(jsFiles[0])
//
// // Functions /////////////////////////////////////////////////
// function importJsFile(jsFile) {   // load script
// // $.getScript('/script.js'); // by jQuery
//     let script = document.createElement('script') //.src = jsFile; - dont work
//     script.src = jsFile
//     document.head.append(script)
// }

// function connectGETtoServer(servletName, get_whatIs, contentTypeIs, responseTypeIs) {
//     const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
//     request.responseType = responseTypeIs
//
//     /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
//     а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
//     const urlWhat = hostIs + servletName + '?get_what=' + get_whatIs
//     // request.open('GET', urlWhat, true)   // открываем соединение
//     request.open('GET', urlWhat)   // открываем соединение
//
//     request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
//     // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
//     request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать
//
// // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
//     request.addEventListener(events[0], () => {
//         if (request.readyState === 4 && request.status === 200) {
//             const responseText = request.responseText
//             // console.log(responseText)
//
//             const tableObj = document.querySelector('#expTable')
//
//             if (urlWhat.includes(get_whatIs)) {
//                 const row_Names_arrIs = responseText.split('_')
//                 createHeadForTable(row_Names_arrIs, tableObj)
//             }
//             // else if (urlPath.includes(contents)) {
//             //     const contentsArr = responseText.split('_')
//             //     createBodyForTable(contentsArr, tableObj)
//             // }
//         }
//     })
//
//     request.send()   // Выполняем запрос
// }