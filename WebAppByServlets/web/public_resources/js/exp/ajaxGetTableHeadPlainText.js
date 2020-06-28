// console.log('ajaxGetTableHeadPlainText.js - file loaded')
responseType = 'text'
contentType = 'application/x-www-form-url'
servletName = 'ExpAjaxServletGetTableHead'
get_what = 'row_names'

connectGETtoServerForTAbleHead(responseType, contentType, servletName, get_what)

function connectGETtoServerForTAbleHead(responseTypeIs, contentTypeIs, servletName, get_whatIs) {
    const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    request.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + '?get_what=' + get_whatIs
    request.open('GET', urlWhat, true)   // открываем соединение

    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener(events[0], () => {
        if (request.readyState === 4 && request.status === 200) {
            const responseText = request.responseText
            const tableObj = document.querySelector('#expTable')

            if (urlWhat.includes(get_whatIs)) {
                const row_Names_arrIs = responseText.split('_')
                createHeadForTable(row_Names_arrIs, tableObj)
            }
            // else if (urlPath.includes(contents)) {
            //     const contentsArr = responseText.split('_')
            //     createBodyForTable(contentsArr, tableObj)
            // }
        }
    })

    request.send()   // Выполняем запрос
}

///////////////////////
// отправка слушателя в connectGETtoServer не прокатывает как и отправка в слушатель функции
// не получается сделать универсальную функцию коннекта..(
function getListenerForGetTblHead(request, urlPath, get_What) {
    return () => {
        if (request.readyState === 4 && request.status === 200) {
            const responseText = request.responseText
            // console.log(responseText)

            const tableObj = document.querySelector('#expTable')

            if (urlPath.includes(get_What)) {
                const row_Names_arrIs = responseText.split('_')
                createHeadForTable(row_Names_arrIs, tableObj)
            }
            // else if (urlPath.includes(contents)) {
            //     const contentsArr = responseText.split('_')
            //     createBodyForTable(contentsArr, tableObj)
            // }
        }
    }
}


////////////////////////
function createHeadForTable(rows, tableObj) {
    // const parentExpTbl = document.querySelector('#parentForExpTable')

    // <table id="expTable" class="table"> _ создаем тег <table>
    // const tableObj = document.createElement('table')
    // tableObj.setAttribute('id', 'expTable')
    // tableObj.setAttribute('class', 'table')
    // parentExpTbl.appendChild(tableObj) // присоединяем его к DOM объекту страницы

    // <thead> _ создаем голову таблицы тег thead
    const theadObj = document.createElement('thead')
    tableObj.appendChild(theadObj) // присоединяем его к table объекту

    // <thead> <tr> создаем tr тег, для головы таблицы thead
    const trObj = document.createElement('tr')
    theadObj.appendChild(trObj)


    // for (let i = 0 i < row_Names_arrIs.length i++) {
    for (let i = 0; i < rows.length; i++) {
        // <th scope="col"> _ создаем th тег для tr obj _ имена колонок
        const thObj = document.createElement('th')
        // thObj.textContent = row_Names_arrIs[i]
        thObj.textContent = rows[i].toString()
        thObj.setAttribute('scope', 'col')

        // добавляем новый <th> на тот же <tr> объект
        trObj.appendChild(thObj)
    }
    // </tr> </thead>
}

function createBodyForTable(contentsArr, tableObj) {
    // <tbody>
    const tbodyObj = document.createElement('tbody')
    tableObj.appendChild(tbodyObj)

    // <tbody> <tr>
    const trObj = document.createElement('tr')
    tbodyObj.appendChild(trObj)

    for (let i = 0; i < contentsArr.length; i++) {
        // <th scope="row">1</th>
        const thObj = document.createElement('th')
        thObj.setAttribute('scope', 'row')
        thObj.textContent = i.toString()
        trObj.appendChild(thObj)

        for (let j = 0; j < contentsArr.length; j++) {
            // добавляем новый <td> на тот же <tr> объект
            const tdObj = document.createElement('td')
            tdObj.textContent = contentsArr[j]
            trObj.appendChild(tdObj)
        }
    }
    // </tr> </tbody>
}