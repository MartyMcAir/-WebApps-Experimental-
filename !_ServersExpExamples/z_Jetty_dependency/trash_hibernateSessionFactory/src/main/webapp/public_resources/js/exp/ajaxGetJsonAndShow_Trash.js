// console.log('ajaxGetJsonAndShow.js - file loaded')
responseType = 'json'
contentType = 'application/json'
servletName = 'ExpAjaxServletGetJson'
get_what = 'jsonObject'

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

            const tableObj = document.querySelector('#expTable')
            const row_Names_arrIs = Object.keys(obj.booksJson[0])
            createHeadForTbl(row_Names_arrIs, tableObj)

            // Object { TESTCOLUMN: null, QUANTITY: "10", TITLE: "testTItle", ID: "1", AUTHOR: "authorTor", FAQA: null }
            // console.log(contentsArrIs[i])
            // createInnerFoBodyTblOrig(Object.values(contentsArrIs[i]).sort(getCompareForTbl), tbodyObj)
            // const number = row_Names_arrIs.findIndex(isPredicate)

            console.log(obj)
            console.log(Object.values(obj))

            console.log('Experimental with JSON obj..: ')
            // console.log(obj[0]) // undefined
            // console.log(obj.booksJson[0][0]) // undefined
            // Object { TESTCOLUMN: null, QUANTITY: "10", TITLE: "testTItle", ID: "1", AUTHOR: "authorTor", FAQA: null }
            // console.log(obj.booksJson[0])

            // console.log(Object.keys(obj.booksJson[0])) // Array(6) [ "TESTCOLUMN", "QUANTITY", "TITLE", "ID", "AUTHOR", "FAQA" ]
            // console.log(Object.keys(obj.booksJson[0])[0]) // TESTCOLUMN


            // SyntaxError: JSON.parse: unexpected character at line 1 column 2 of the JSON data
            // const newObj = JSON.parse(obj)   // мы уже получаем JS объект и его парсить нет необходимости
            // console.log(obj.name) // Amazon
            // 0: Object { firstName: "John", lastName: "Doe" }
            // 1: Object { firstName: "Anna", lastName: "Smith" }
            // 2: Object { firstName: "Peter", lastName: "Jones" }
            // console.log(obj.employees)

            // console.log(Object.values(obj[0])) // Array(3) [ "Amazon", "Jeff Bezos", (3) […] ]
        }
    })
    requestJson.send()   // Выполняем запрос
}

function createHeadForTbl(rows, tableObj) {
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

function isPredicate(element, index, array) {
    if (element === 'id') {
        console.log(element)
        return index
    } else return -1;
}

function createInnerFoBodyTblOrig(contentsArr, tblBody) {
    // <tr>
    const trObj = document.createElement('tr')
    tblBody.appendChild(trObj)

    for (let j = 0; j < contentsArr.length; j++) {
        // добавляем новый <td> на тот же <tr> объект
        const tdObj = document.createElement('td')
        if (contentsArr[j] != null)
            tdObj.textContent = contentsArr[j].toString()
        trObj.appendChild(tdObj)
    }
    // </tr> </tbody>
}