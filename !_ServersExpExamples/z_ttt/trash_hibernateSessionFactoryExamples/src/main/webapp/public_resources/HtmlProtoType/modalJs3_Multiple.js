let responseType = 'text'
let contentType = 'application/x-www-form-url'
let servletName = 'HibernateAjaxCrudUser'
const resultIs = document.querySelector('#resultId')

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

function listenerForSubmitDataToServer(idFormObj, idIs, classMainDivObj) {
    const currentFormObj = idFormObj.querySelector('#idSubmitUpdForm' + idIs.value)
    currentFormObj.addEventListener('submit', function (event) {
        event.preventDefault()
        classMainDivObj.className = 'classMainDiv' // close window
        resultIs.textContent = 'your form submitted, for id is: ' + idIs.value   // show result
    })
}

// добавляем слушатель событий ко всем, формам с классом .showUpdForm
// и + код добавления слушателей для открытия окон
const updFormsArray = Array.from(document.querySelectorAll('.showUpdForm'))

for (let i = 0; i < updFormsArray.length; i++) {
    updFormsArray[i].addEventListener('click', function (event) {
        event.preventDefault()
        // находим форму что надо открыть по указанному id, в инпуте
        const idIs = updFormsArray[i].querySelector('input[name="id"]')

        // выводим сообщение с вопросом
        const answerConfirm = window.confirm('Вы хотите изменить данные пользователя с id: ' + idIs.value + '?')
        if (answerConfirm) {
            // находим по value, объект формы с определенным id
            const idFormObj = document.querySelector('#idUpdForm' + idIs.value)

            // находим у данного id класс .classMainDiv и меняем его _ для открытия окна
            const classMainDivObj = idFormObj.querySelector('.classMainDiv')
            classMainDivObj.className = 'classMainDiv' + ' classShowHover '

            listenerForCloseWindow(idFormObj, classMainDivObj);
            listenerForSubmitDataToServer(idFormObj, idIs, classMainDivObj);
        } else {
            console.log('Thing was not saved to the database.');
        }
    })
}

// ..............................................................................................