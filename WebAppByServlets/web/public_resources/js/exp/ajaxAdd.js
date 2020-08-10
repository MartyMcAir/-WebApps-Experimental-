// <form id="formPlace" action="${pageContext.request.contextPath}/AddData">

responseType = 'text'
contentType = 'application/x-www-form-url'
const servletNameAdd = 'AjaxAddDataServlet'

const formPlace = document.querySelector('#formPlace')

formPlace.addEventListener('submit', addInTbl)

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
    elements[i].value = ''
    return params;
}

function addInTbl(event) {
    // console.log('hello from addInTbl function')

    let params = '?'
    const elements = formPlace.querySelectorAll('input')
    // const elements = formPlace.elements

    // создаем массив ключей и значений отправляемых на сервер
    let namesArr = [], valuesArr = []
    for (let i = 0; i < elements.length; i++) {
        // console.dir(elements[i])
        params = fillArray(elements, i, namesArr, valuesArr, params)
    }

    const indexIs = params.lastIndexOf('&')
    // const paramNew = params.substring(0, indexIs)
    const paramsNew = params + 'ajax=true'
    // console.log(hostIs + servletName + paramsNew)
    event.preventDefault()

    sendContentToServerAndAddRow(responseType, contentType, servletNameAdd, paramsNew, valuesArr, namesArr)
}

// this with out: '+' _ and dont work in upper case!?
// http://localhost:8080/FirstWebApp_war_exploded/AddData?FAQA=test+faqa&AUTHOR=test+author&QUANTITY=999&TESTCOLUMN=test+column&TITLE=test+title

// WIth lowerCase WORK!
// http://localhost:8080/FirstWebApp_war_exploded/AddData?faqa=bbb&author=autho TO&quantity=888888&testcolumn=hhh&title=Title 24 06 2020

// Right
// http://localhost:8080/FirstWebApp_war_exploded/AddData?title=Title+24+06+2020&author=autho+TO&quantity=333&testcolumn=hhh&faqa=bbb

const resultId = document.querySelector('#resultId')

function sendContentToServerAndAddRow(responseTypeIs, contentTypeIs, servletName, get_whatIs, valuesArr, namesArr) {
    const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    request.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + get_whatIs;
    request.open('GET', urlWhat, true)   // открываем соединение

    console.log(urlWhat)

    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener(events[0], () => {
        if (request.readyState === 4 && request.status === 200) {
            const newIDis = request.responseText
            // const tbodyEl = document.querySelector('#tblBodyID')
            const tbodyEl = tbodyObj
            // при успешном добавлении в БД сервера создаем соответствие поля с нов ID
            addTooBodyTbl(valuesArr, tbodyEl, namesArr, newIDis)

            // обновляем надпись оповещающую пользователя
            resultId.textContent = 'your fields added'
            setTimeout(deleteResultNotification, 2500)

            // обновляем форму удаления Id'шек
            clearAllOptionsInDelForm()
            createOptionsForDelInForm()
        }
    })
    request.send()   // Выполняем запрос
}

function deleteResultNotification() {
    resultId.textContent = ''
}


function addTooBodyTbl(contentsArr, tblBody, keysArr, newIdIs) {
    // <tr>
    const trObj = document.createElement('tr')
    tblBody.appendChild(trObj)
    trObj.setAttribute("id", ('id' + newIdIs))

    // <th scope="col"> _ создаем th тег для ID
    const thObj = document.createElement('th')
    thObj.setAttribute('scope', 'row')
    thObj.textContent = newIdIs.toString()
    // добавляем новый <th> на тот же <tr> объект
    trObj.appendChild(thObj)

    for (let j = 0; j < keysArr.length; j++) {
        // добавляем новый <td> на тот же <tr> объект
        const tdObj = document.createElement('td')

        const currentContent = contentsArr[j]
        if (currentContent != null)
            tdObj.textContent = currentContent.toString()
        trObj.appendChild(tdObj)
    }
    // </tr> </tbody>
}