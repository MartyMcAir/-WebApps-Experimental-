// <form id="formPlace" action="${pageContext.request.contextPath}/AddData">
responseType = 'text'
contentType = 'application/x-www-form-url'
servletName = 'HibernateAjaxCrudUser'

// ..............................................................................................
const formPlace = document.querySelector('#addForm')

formPlace.addEventListener('submit', addInTbl)

function addInTbl(event) {
    event.preventDefault()

    // console.log('hello from addInTbl function')

    const pObj = getParamsFromForm(formPlace)

    // const indexIs = params.lastIndexOf('&')
    // _ не требует т.к. внизу к нему в sendContentToServerAndAddRow(..) к & добавляют..
    // const paramsNew = params.substring(0, indexIs) // обрезаем последний амперсанд &
    // const paramsNew = params + '_method=add'
    // console.log(hostIs + servletName + paramsNew)

    sendContentToServerAndAddRow(responseType, contentType, servletName, pObj.params, pObj.valuesArr, pObj.namesArr)
}

// ..............................................................................................
function sendContentToServerAndAddRow(responseTypeIs, contentTypeIs, servletName, get_whatIs, valuesArr, namesArr) {
    const request = new XMLHttpRequest()   // Создаём объект класса XMLHttpRequest
    request.responseType = responseTypeIs

    /* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
    а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
    const urlWhat = hostIs + servletName + get_whatIs + '_method=add';
    request.open('GET', urlWhat, true)   // открываем соединение

    console.log(urlWhat)

    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', contentTypeIs)   // желательно указывать

    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener(events[0], () => {
        // if (request.status === 200) { // если юзать эту строку тьо добавляет по 2 юзера
        if (request.readyState === 4 && request.status === 200) {
            const newIDis = request.responseText
            // при успешном добавлении в БД сервера создаем соответствующие поля с нов ID
            addTooBodyTbl(valuesArr, tbodyHibernateObj, namesArr, newIDis)

            // вызываем функцию добавления слушателей на все формы (включая на только что созданные)
            // clickAndShowFormListener() // не прокатило
            // setTimeout(clickAndShowFormListener, 2500) // тож самое

            // обновляем надпись оповещающую пользователя
            resultId.textContent = 'your fields added'
            setTimeout(clearDelStatus, 2500)
        }
    })
    request.send()   // Выполняем запрос
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

    for (let j = 1; j < keysArr.length; j++) { // 0 element is _method
        // добавляем новый <td> на тот же <tr> объект
        const tdObj = document.createElement('td')

        const currentContent = contentsArr[j]
        if (currentContent != null)
            tdObj.textContent = currentContent.toString()
        trObj.appendChild(tdObj)
    }

    // добавляем формы удаления и обновления
    const tdObj = document.createElement('td')
    const divEl = document.createElement('div')
    divEl.setAttribute('class', 'inlineDivs')
    tdObj.appendChild(divEl)

    // UPD Form
    addedUpdForm(divEl, newIdIs, keysArr, contentsArr);
    // DEL Form
    addedDelForm(divEl, newIdIs);

    // добавляем последний TD object с формами удаления и обновления
    trObj.appendChild(tdObj)

    // </tr> </tbody>
}

function addedUpdForm(divEl, newIdIs, keysArr, contentsArr) {
    // form for show window _ <form id="idShowUpdForm${user.getId()}" class="showUpdForm formClass">
    formForShowWindow(newIdIs, divEl);

    // form Window _ <div id="idUpdForm${user.getId()}">
    // DIVS _ OUTER
    const divModalBody = divsInShowForm(newIdIs, divEl);

    // сама форма
    const updForm = document.createElement('form')
    // updForm.setAttribute('action', '/HibernateAjaxCrudUser')
    updForm.setAttribute('id', 'idSubmitUpdForm' + newIdIs)
    // updForm.setAttribute('class', 'formClass')
    updForm.setAttribute('method', 'get')
    divModalBody.appendChild(updForm)

    const inputMethod = document.createElement('input')
    inputMethod.setAttribute('type', 'hidden')
    inputMethod.setAttribute('name', '_method')
    inputMethod.setAttribute('value', 'update')
    updForm.appendChild(inputMethod)

    const inputUpdId = document.createElement('input')
    inputUpdId.setAttribute('type', 'hidden')
    inputUpdId.setAttribute('name', 'id')
    inputUpdId.setAttribute('value', newIdIs)
    updForm.appendChild(inputUpdId)

    for (let j = 1; j < keysArr.length; j++) { // first 0 - it's _method.. value.. add
        const divFormGroup = document.createElement('div')
        divFormGroup.setAttribute('class', 'form-group')
        updForm.appendChild(divFormGroup)

        const htmlLabelElement = document.createElement('label')
        htmlLabelElement.setAttribute('for', 'nameLabel' + newIdIs)
        htmlLabelElement.textContent = keysArr[j]
        divFormGroup.appendChild(htmlLabelElement)

        const newInput = document.createElement('input')
        newInput.setAttribute('name', keysArr[j])
        newInput.setAttribute('value', contentsArr[j])
        newInput.setAttribute('placeholder', contentsArr[j])
        newInput.setAttribute('class', 'form-control')
        newInput.value = contentsArr[j]
        newInput.textContent = contentsArr[j]
        divFormGroup.appendChild(newInput)
    }
    const btnOk = document.createElement('button')
    btnOk.setAttribute('type', 'submit')
    btnOk.setAttribute('class', 'btn btn-primary marginRight')
    btnOk.textContent = 'ok'
    updForm.appendChild(btnOk)

    const btnClose = document.createElement('button')
    btnClose.setAttribute('type', 'button')
    btnClose.setAttribute('class', 'btn btn-secondary closeModal')
    btnClose.setAttribute('data-dismiss', 'modal')
    btnClose.textContent = 'Close'
    updForm.appendChild(btnClose)
}

function addedDelForm(divEl, newIdIs) {
    const delForm = document.createElement('form')
    delForm.setAttribute('class', 'formClass delFormClass')
    delForm.setAttribute('method', 'get')
    divEl.appendChild(delForm)
    const inputDelEl = document.createElement('input')
    inputDelEl.setAttribute('name', '_method')
    inputDelEl.setAttribute('value', 'delete')
    inputDelEl.setAttribute('type', 'hidden')
    delForm.appendChild(inputDelEl)
    const inputDelId = document.createElement('input')
    inputDelId.setAttribute('name', 'id')
    inputDelId.setAttribute('value', newIdIs)
    inputDelId.setAttribute('type', 'hidden')
    delForm.appendChild(inputDelId)

    const delBtn = document.createElement('button')
    delBtn.setAttribute('type', 'submit')
    delBtn.setAttribute('class', 'btn btn-primary btn-sm')
    delBtn.textContent = 'Удалить'
    delForm.appendChild(delBtn)

    // добавляем слушатель событий, что бы удаление было успешно
    delForm.addEventListener('click', function (event) {
        let idIs = delForm.querySelector('input[name="id"]');
        // alert('Вы хотите удалить ' + idIs.value + '?')
        let answerConfirm = window.confirm('Вы хотите удалить пользователя с id: ' + idIs.value + '?')
        if (answerConfirm) {
            let rowsIs = [idIs.value]
            // deleteRowInTblDOM(rowsIs)

            let paramsIs = '?_method=delete' + '&id=' + idIs.value
            console.log('deleteIdIs' + paramsIs)
            // from ajaxHibernateDelete.js
            connectToServerForDelRow(responseType, contentType, servletName, paramsIs, rowsIs)
        } else {
            console.log('Thing was not saved to the database.');
        }
        event.preventDefault()
    })
}


// ............................................................

function formForShowWindow(newIdIs, divEl) {
    const showForm = document.createElement('form')
    showForm.setAttribute('id', 'idShowUpdForm' + newIdIs)
    showForm.setAttribute('class', 'showUpdForm formClass')
    divEl.appendChild(showForm)

    const showFormIdInput = document.createElement('input')
    showFormIdInput.setAttribute('name', 'id')
    showFormIdInput.setAttribute('value', newIdIs)
    showFormIdInput.setAttribute('type', 'hidden')
    showForm.appendChild(showFormIdInput)

    const showFormBtn = document.createElement('button');
    showFormBtn.setAttribute('type', 'submit')
    showFormBtn.setAttribute('class', 'btn btn-primary btn-sm openModal')
    showFormBtn.textContent = 'Изменить'
    showForm.appendChild(showFormBtn)

    showForm.addEventListener('click', function (event) {
        event.preventDefault()
        // находим форму что надо открыть по указанному id, в инпуте
        const idIs = showForm.querySelector('input[name="id"]')
            // находим по value, объект формы с определенным id
            const idFormObj = document.querySelector('#idUpdForm' + idIs.value)

            // находим у данного id класс .classMainDiv и меняем его _ для открытия окна
            const classMainDivObj = idFormObj.querySelector('.classMainDiv')
            classMainDivObj.className = 'classMainDiv' + ' classShowHover '

            // to OK button _ from ajaxHibernateUpdate.js
            listenerForSubmitDataToServer(idFormObj, idIs, classMainDivObj);
            listenerForCloseWindow(idFormObj, classMainDivObj);
    })
}

function divsInShowForm(newIdIs, divEl) {
    const divUpdForm = document.createElement('div')
    divUpdForm.setAttribute('id', 'idUpdForm' + newIdIs)
    divEl.appendChild(divUpdForm)

    const divMain = document.createElement('div')
    divMain.setAttribute('class', 'classMainDiv')
    divMain.setAttribute('aria-hidden', 'true')
    divUpdForm.appendChild(divMain)

    const divInner = document.createElement('div')
    divInner.setAttribute('class', 'classInnerInMain')
    divMain.appendChild(divInner)

    const divModalDialog = document.createElement('div')
    divModalDialog.setAttribute('class', 'modal-dialog')
    divModalDialog.setAttribute('role', 'document')
    divInner.appendChild(divModalDialog)

    const divModalContent = document.createElement('div')
    divModalContent.setAttribute('class', 'modal-content')
    divModalDialog.appendChild(divModalContent)

    // divModalHeader >>>
    const divModalHeader = document.createElement('div')
    divModalHeader.setAttribute('class', 'modal-header')
    divModalContent.appendChild(divModalHeader)

    const htmlHeadingElement = document.createElement('h5')
    htmlHeadingElement.setAttribute('class', 'modal-title')
    htmlHeadingElement.textContent = 'Edit User ' + newIdIs
    divModalHeader.appendChild(htmlHeadingElement)

    const htmlButtonElement = document.createElement('button')
    htmlButtonElement.setAttribute('type', 'button')
    htmlButtonElement.setAttribute('class', 'close closeModal')
    htmlButtonElement.setAttribute('data-dismiss', 'modal')
    htmlButtonElement.setAttribute('aria-label', 'Close')
    const htmlSpanElement = document.createElement('span')
    htmlSpanElement.setAttribute('aria-hidden', 'true')
    htmlSpanElement.textContent = 'x'
    htmlButtonElement.appendChild(htmlSpanElement)

    divModalHeader.appendChild(htmlButtonElement)
    divModalContent.appendChild(divModalHeader)
    // <<< divModalHeader

    const divModalBody = document.createElement('div')
    divModalBody.setAttribute('class', 'modal-body')
    divModalContent.appendChild(divModalBody)
    return divModalBody;
}