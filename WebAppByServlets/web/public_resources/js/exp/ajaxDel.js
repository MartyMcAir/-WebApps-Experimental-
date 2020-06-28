responseType = 'text'
contentType = 'application/x-www-form-url'
servletNameDel = 'AjaxDeleteOneRowServlet'

// таймер и интервал т.к. этот участок кода js _ не находит то что сформировал предыдуший js код
const intervalDelForm = setTimeout(createOptionsForDelInForm, 1600);
// и прерываем интервал, чтоб не дублировался код
// setTimeout(getHandlerOffInterval, 1600)

// function getHandlerOffInterval() {
//     clearInterval(intervalDelForm)
// }

// переменная получения DOM элемента select _ для 2х функций
const selectId = document.querySelector('#selectId')

// создаем options выбор на удаление доступного ID
function createOptionsForDelInForm() {
    const tblBodyObj = tbodyObj
    const allTr = tblBodyObj.querySelectorAll('tr')

    const allTh = []
    for (let r = 0; r < allTr.length; r++) {
        const thIdIs = allTr[r].querySelector('th')
        allTh.push(thIdIs.textContent)
    }

    for (let g = 0; g < allTh.length; g++) {
        const currentId = allTh[g]
        const htmlOptionElement = document.createElement('option')
        htmlOptionElement.setAttribute('value', currentId)
        htmlOptionElement.setAttribute('id', 'optId' + currentId.toString())
        htmlOptionElement.textContent = currentId

        selectId.appendChild(htmlOptionElement)
    }
}

// создаем слушатель событий на кнопку удаления row по ID.,
const submitIdEl = document.querySelector('#delFormId')

submitIdEl.addEventListener('submit', deleteIdIs)

const resultIs = document.querySelector('#resultId2')

function deleteIdIs(event) {
    const paramsIs = '?columnID=' + selectId.value
    connectToServerForDelRow(responseType, contentType, servletNameDel, paramsIs)

    event.preventDefault()
}


// коннект с сервером для запроса на удаление поля
function connectToServerForDelRow(responseTypeIs, contentTypeIs, servletName, get_whatIs) {
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
            const responseText = request.responseText
            console.log(responseText)
            const rowIs = [selectId.value]
            deleteRowInTblDOM(rowIs)

            clearAllOptionsInDelForm()  // зачищяем Options выбор id для удаления
            createOptionsForDelInForm() // и создаем по новой без удаленных вариантов
            // setTimeout(createOptionsForDelInForm, 1500)
            resultIs.textContent = 'your fields is deleted'
            setTimeout(clearDelStatus, 2500)
        }
    })
    request.send()   // Выполняем запрос
}

function clearDelStatus() {
    resultIs.textContent = ''
}

function deleteRowInTblDOM(rows) {
    const valueForDel = rows[0]
    const htmlElementForDel = tbodyObj.querySelector('#id' + valueForDel.toString())
    htmlElementForDel.remove()
}

function clearAllOptionsInDelForm() {
    // const idIs = '#optId' + selectId.value
    // console.log(idIs)
    // const element = selectId.querySelector(idIs)  // NPE
    // element.remove()

    const nodeListOf = selectId.querySelectorAll('option');
    for (let h = 0; h < nodeListOf.length; h++) {
        nodeListOf[h].remove()
    }
}
