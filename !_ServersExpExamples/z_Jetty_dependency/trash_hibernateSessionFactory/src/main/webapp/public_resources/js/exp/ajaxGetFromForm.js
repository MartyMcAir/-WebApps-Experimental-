// console.log('ajaxGetFromForm.js - file loaded')
responseType = 'text'
contentType = 'application/x-www-form-url'
servletName = 'ExpAjaxServletGetFromForm'
// get_what = 'GetForm'

const form = document.querySelector('#form')   // получаем obj формы по id
const log = document.querySelector('#log')   // получаем obj в который выводим сообщение по id

// получаем из obj формы obj инпута
const input1ClassObj = form.querySelector('.input1Class')
// console.dir(input1ClassObj)   // получение подробной инфы об obj

//     <%--    ошибка сама исчезла не понял как --%>
// <%--    <p>Ошибка синтаксического анализа XML: ошибка синтаксиса Адрес:.. </p>--%>

function logSubmit(event) {
    const message = input1ClassObj.value   // получаем значение поля
    // делаем вычисления без сервака)) и сразу выводим результ юзеру
    // const numSum = Number.parseInt(message) + 2
    input1ClassObj.value = ''   // очищаем
    // выводим в необходимый obj в HTML в качестве контента для данного obj
    get_what = `Form Submitted! Time stamp: ${event.timeStamp} and ${message}`

    sendContentToServerAndConsole(responseType, contentType, servletName, get_what)

    log.textContent = get_what

    // если закоментить, то форма будет работать как обычная форма с (по умолчанию) GET запросом
    // т.е. будет отправлять текущий запрос, текущему адресу (если не указан в action) и тем самым грузить сервак
    event.preventDefault()
}


// добавляем слушатель событий на полученный DOM элемент из объекта form
// а слушать будет событие type="submit" - с формы т.е. нажатие кнопки
// вторым аргументом идёт logSubmit - функция, которая должна сработать при данном событии
form.addEventListener('submit', logSubmit)