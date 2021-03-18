responseType = 'text'
contentType = 'application/x-www-form-url'
servletName = 'HibernateAjaxCrudUser'

// ..............................................................................................
// таймер и интервал т.к. этот участок кода js _ не находит то что сформировал предыдущий js код
// const intervalDelForm = setTimeout(createOptionsForDelInForm, 1600);
// и прерываем интервал, чтоб не дублировался код
// setTimeout(getHandlerOffInterval, 1600)

// function getHandlerOffInterval() {
//     clearInterval(intervalDelForm)
// }

// добавляем слушателя событий к каждому элементу
Array.from(document.querySelectorAll('.delFormClass'))
    .forEach(function (formCl) {
        // formCl.addEventListener('click', function (event) {
        formCl.addEventListener('click', function (event) {
            let idIs = formCl.querySelector('input[name="id"]')
            // alert('Вы хотите удалить ' + idIs.value + '?')

            // выводим сообщение с вопросом..
            let answerConfirm = window.confirm('Вы хотите удалить пользователя с id: ' + idIs.value + '?')
            if (answerConfirm) {
                let rowsIs = [idIs.value]
                // deleteRowInTblDOM(rowsIs)

                let paramsIs = '?_method=delete'+'&id=' + idIs.value
                console.log('deleteIdIs' + paramsIs)
                connectToServerForDelRow(responseType, contentType, servletName, paramsIs, rowsIs)
            } else {
                console.log('Thing was not saved to the database.')
            }
            event.preventDefault()
        })
    });

function deleteRowInTblDOM(rows) {
    for (let i = 0; i < rows.length; i++) {
        let valueForDel = rows[i]
        // if tr id="${user.getId()}" ___ but Element.querySelector: '#996' is not a valid selector
        // let htmlElementForDel = tbodyHibernateObj.querySelector(`#${valueForDel}`)

        // `#${CSS.escape(theId)}` or "[id='1']" _ Element.querySelector: '[id=996]' is not a valid selector
        // let htmlElementForDel = tbodyHibernateObj.querySelector("[id=" + valueForDel + "]")

        // if tr id="id${user.getId()}" using:
        // let htmlElementForDel = tbodyHibernateObj.querySelector(`#id${valueForDel}`)
        let htmlElementForDel = tbodyHibernateObj.querySelector('#id' + valueForDel)
        htmlElementForDel.remove()
    }
}

// ..............................................................................................
// коннект с сервером для запроса на удаление поля
function connectToServerForDelRow(responseTypeIs, contentTypeIs, servletName, get_whatIs, rowForDel) {
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
            const rowsIs = [rowForDel]
            deleteRowInTblDOM(rowsIs)

            resultIs.textContent = 'your fields is deleted'
            setTimeout(clearDelStatus, 2500)
        }
    })
    request.send()   // Выполняем запрос
}