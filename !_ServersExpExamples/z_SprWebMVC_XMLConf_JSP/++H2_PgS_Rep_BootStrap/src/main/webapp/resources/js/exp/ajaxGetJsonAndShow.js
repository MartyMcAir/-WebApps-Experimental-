// console.log('ajaxGetJsonAndShow.js - file loaded')
responseType = 'json'
contentType = 'application/json'
servletName = 'ExpAjaxServletGetJson'
get_what = 'jsonObject'

const tbodyObj = document.createElement('tbody')

let row_Names_arrIs

getJsonAndShow(responseType, contentType, servletName, get_what)

function getJsonAndShow(responseTypeIs, contentTypeIs, servletName, get_whatIs) {
    const requestJson = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    requestJson.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + '?get_what=' + get_whatIs;
    requestJson.open('GET', urlWhat, true)   // открываем соединение

    requestJson.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    requestJson.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    requestJson.addEventListener(events[0], () => {
        if (requestJson.readyState === 4 && requestJson.status === 200) {
            const obj = requestJson.response
            createTbl(obj);
        }
    })
    requestJson.send()   // Выполняем запрос
}

function getCompareForTbl(a, b) {
    // if (a != null) {   //     console.log(a)
    //     if (a.includes('id')) {   //         return 0;
    //     }    // }
    if (a < b) return -1;
    if (a > b) return 1;
    return 0;
}

function createFormAddInTbl(rows) {
    // action="${pageContext.request.contextPath}/AddData"
    // <form>
    // <div id="formPlace" class="tableFormIs">
    // <form id="formPlace" action="#" name="formNameIsAdd" class="tableFormIs">
    const formPlace = document.querySelector('#formPlace')

    // <strong>ID auto</strong>
    const idEl = document.createElement('strong')
    idEl.textContent = 'ID'
    formPlace.appendChild(idEl)

    for (let i = 1; i < rows.length; i++) {
        // <input type="text" class="ol-md-3 mb-3 form-control">
        const inputEl = document.createElement('input')
        inputEl.setAttribute('type', 'text')
        inputEl.setAttribute('class', 'ol-md-3 mb-3 form-control' + ' ' + rows[i].toString())
        inputEl.setAttribute('name', rows[i].toString().toLowerCase())
        formPlace.appendChild(inputEl)
    }
    // <button class="btn btn-primary btn-sm" type="submit">
    const htmlButtonElement = document.createElement('button')
    htmlButtonElement.setAttribute('class', 'btn btn-primary btn-sm')
    htmlButtonElement.setAttribute('type', 'submit')
    htmlButtonElement.textContent = 'add in table'
    formPlace.appendChild(htmlButtonElement)
    // </div> </form>
}

function createTbl(obj) {
    const tableObj = document.querySelector('#expTable')
    row_Names_arrIs = Object.keys(obj.booksJson[0]).sort(getCompareForTbl)
    swapIdToFirst(row_Names_arrIs)   // свапаем ID чтоб был первым в таблице

    // <thead>
    createHeadForTbl(row_Names_arrIs, tableObj)

    // <tbody>
    // const tbodyObj = document.createElement('tbody')
    tbodyObj.setAttribute('id', 'tblBodyID')
    tableObj.appendChild(tbodyObj)

    // если таблица не пуста то добавляем к ней rows
    if (obj.booksJson.length > 1) {
        const contentsArrIs = obj.booksJson
        // старт с еденицы т.к. первая партия это пустышки для получения названия колонок
        for (let i = 1; i < contentsArrIs.length; i++) {
            createInnerForBodyTbl(contentsArrIs[i], tbodyObj, row_Names_arrIs)
        }
    }

    // createInputsForTbl(row_Names_arrIs, tbodyObj)
    createFormAddInTbl(row_Names_arrIs)
}

function swapIdToFirst(row_Names_arrIs) {
    const indexId = row_Names_arrIs.indexOf('ID')
    row_Names_arrIs[indexId] = row_Names_arrIs[0]
    row_Names_arrIs[0] = 'ID'
}

function createInputsForTbl(rows, tblBody) {
    // <form onsubmit="myFunction(); return false;">
    // <form onSubmit="return myFunction();">
    // <form name="myForm" id="isIdMyForm" action="#">

    // <tbody> <tr> создаем tr тег
    const trObj = document.createElement('tr')
    tblBody.appendChild(trObj)

    // <form>
    // const formTag = document.createElement('form')
    // trObj.appendChild(formTag)

    // <th>
    const thObj = document.createElement('th')
    thObj.setAttribute('scope', 'row')
    thObj.textContent = 'auto'
    trObj.appendChild(thObj)


    for (let i = 1; i < rows.length; i++) {
        // <td>
        const tdObj = document.createElement('td')
        trObj.appendChild(tdObj)

        // <input name="title" type="text" class="ol-md-3 mb-3 form-control">
        const inputTag = document.createElement('input')
        inputTag.setAttribute('type', 'text')
        inputTag.setAttribute('class', 'ol-md-3 mb-3 form-control')
        inputTag.setAttribute('name', rows[i].toString())
        tdObj.appendChild(inputTag)
        // </td>
    }
    // <button class="btn btn-primary btn-sm" type="submit" form="isIdMyForm">add row</button>
    // </tr> </tbody>
}

function createHeadForTbl(rows, tableObj) {
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

function createInnerForBodyTbl(contentsArr, tblBody, keysArr) {
    // <tr>
    const trObj = document.createElement('tr')
    tblBody.appendChild(trObj)
    trObj.setAttribute("id", ('id' + contentsArr[keysArr[0]]))

    // <th scope="col"> _ создаем th тег для ID
    const thObj = document.createElement('th')
    thObj.setAttribute('scope', 'row')
    // thObj.setAttribute('id', 'thId')
    thObj.textContent = contentsArr[keysArr[0]] + ' '
    // добавляем новый <th> на тот же <tr> объект
    trObj.appendChild(thObj)

    for (let j = 1; j < keysArr.length; j++) {
        // добавляем новый <td> на тот же <tr> объект
        const tdObj = document.createElement('td')

        const currentContent = contentsArr[keysArr[j]]
        if (currentContent != null)
            tdObj.textContent = currentContent.toString()
        trObj.appendChild(tdObj)
    }
    // </tr> </tbody>
}