responseType = 'text'
contentType = 'application/x-www-form-url'
servletName = 'HibernateAjaxCrudUser'

// ..............................................................................................
function listenerForCloseWindow(idFormObj, classMainDivObj) {
    // находим внутри id объекта, все объекты с классом .closeModal
    const closeModalObjects = idFormObj.querySelectorAll('.closeModal')
    for (let j = 0; j < closeModalObjects.length; j++) {
        closeModalObjects[j].addEventListener('click', function (event) {
            event.preventDefault()
            classMainDivObj.className = 'classMainDiv'
            console.log('closed')
        }, false)
    }
}

// OPEN LISTENER _ добавляем слушатель событий ко всем, формам с классом .showUpdForm
// и + код добавления слушателей для открытия окон
const updFormsArray = Array.from(document.querySelectorAll('.showUpdForm'))

function clickAndShowFormListener() {
    for (let i = 0; i < updFormsArray.length; i++) {
        updFormsArray[i].addEventListener('click', function (event) {
            event.preventDefault()
            // находим форму что надо открыть по указанному id, в инпуте
            const idIs = updFormsArray[i].querySelector('input[name="id"]')
                // находим по value, объект формы с определенным id
                const idFormObj = document.querySelector('#idUpdForm' + idIs.value)

                // находим у данного id класс .classMainDiv и меняем его _ для открытия окна
                const classMainDivObj = idFormObj.querySelector('.classMainDiv')
                classMainDivObj.className = 'classMainDiv' + ' classShowHover '

                // to OK button
                listenerForSubmitDataToServer(idFormObj, idIs, classMainDivObj);
                listenerForCloseWindow(idFormObj, classMainDivObj);
        })
    }
}

clickAndShowFormListener()

function listenerForSubmitDataToServer(idFormObj, idIs, classMainDivObj) {
    const currentFormObj = idFormObj.querySelector('#idSubmitUpdForm' + idIs.value)
    currentFormObj.addEventListener('submit', function (event) {
        event.preventDefault()
        const pObj = getParamsFromForm(idFormObj)

        updateContentInServerDataAndChangeRow(responseType, contentType, servletName, pObj, idFormObj)
        classMainDivObj.className = 'classMainDiv' // close window
        resultIs.textContent = 'your form submitted, for id is: ' + idIs.value   // show result
    })
}

// ..............................................................................................
function updateContentInServerDataAndChangeRow(responseTypeIs, contentTypeIs, servletName, pObj, idFormObj) {
    const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    request.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + pObj.params + '_method=update';
    request.open('GET', urlWhat, true)   // открываем соединение

    console.log(urlWhat)

    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener(events[0], () => {
        // if (request.status === 200) { // если юзать эту строку тьо добавляет по 2 юзера
        if (request.readyState === 4 && request.status === 200) {
            const responseText = request.responseText
            console.log(responseText)

            updateRowInTblDOM(pObj.valuesArr, tbodyHibernateObj, idFormObj)

            // обновляем надпись оповещающую пользователя
            resultId.textContent = 'your fields is update'
            setTimeout(clearDelStatus, 2500)
        }
    })
    request.send()   // Выполняем запрос
}

function updateRowInTblDOM(valuesArr, tblBody, idFormObj) {
    // console.log('#id 1' + valuesArr[1]) // id
    // console.log('#id 2' + valuesArr[2]) // name ..

    // obj row таблицы, что необходимо изменить
    const trIdObjForUpd = tblBody.querySelector('#id' + valuesArr[1])
    // массив td тегов нашего row
    const tdElementsObj = trIdObjForUpd.querySelectorAll('td')
    // console.dir(tdElementsObj)
    // объект полей формы отправки
    const inputsObj = idFormObj.querySelectorAll('input')

    // (length - 1) последний td не меняем, т.к. там формы..
    for (let i = 0, j = 2; i < tdElementsObj.length - 1; i++, j++) {
        // меняем значения в полях таблицы
        const currentTd = tdElementsObj[i]
        currentTd.textContent = valuesArr[j]

        // меняем значения в полях формы
        inputsObj[j].setAttribute('value', valuesArr[j])
        inputsObj[j].setAttribute('placeholder', valuesArr[j])
        inputsObj[j].value = valuesArr[j]
    }
}