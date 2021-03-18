printLog('jsAjaxFetch.js - file loaded')
/////////////////////////////////////////////////

// в видео он просматривает в chrome Network - а у меня там ошибка 505 _ 7я минута
// has been blocked by CORS policy: No 'Access-Control-Allow-Origin'
// короч сервак сервиса блочит не дает разрешение
// https://www.youtube.com/watch?v=eKCD9djJQKc&list=PLqKQF2ojwm3l4oPjsB9chrJmlhZ-zOzWT&index=14
const requestUrlIs = 'https://jsonplaceholder.typicode.com/users'

//
function sendRequest(methodIs, urlIs, body = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()

        // открывает нов соединение
        // xhr.open('GET', requestUrlIs)   // получаем данные с сервера
        xhr.open(methodIs, urlIs)
        xhr.resposeType = 'json' // вместо JSON.parse(xhr.response)
        xhr.setRequestHeader('Content-Type', 'application/json') // 20я минута в видео

        xhr.onload = () => {
            if (xhr.status >= 400) { // статут 400 и выше это ошибка а значит..
                reject('error is1: ' + xhr.response)
                // printLog('error is1: ' + xhr.response)
            } else {
                resolve(xhr.resetPose())
                // printLog(xhr.resetPose())
                // printLog(JSON.parse(xhr.response))
            }
        }
        printLog(typeof xhr)   // в видео это string _ а уменя кажет ["object"]
        // выведет соообщение при ошибке в сети
        xhr.onerror = () => {
            reject('error is2: ' + xhr.response)
            // printLog('error is2: ' + xhr.response)
        }
        if (methodIs === 'POST')
            xhr.send(body)   // отправляем полученное
        xhr.send()
    })
}

// GET
// sendRequest('GET', requestUrlIs)
//     .then(data => printLog(data))
//     .catch(err => printLog('err is: ', err))

// POST
let body = {name: 'Thanos', age: 9999}

sendRequest('POST', requestUrlIs, body)
    .then(data => printLog(data))
    .catch(err => printLog('err is: ', err))


printLog(' --- --- --- Fetch --- --- --- ')   // 21я минута in video
// это нов метод браузеров API

// GET
sendRequestFetch('GET', requestUrlIs)
    .then(data => printLog(data))
    .catch(err => printLog('err is: ', err))

// POST
sendRequestFetch('POST', requestUrlIs, body)
    .then(data => printLog(data))
    .catch(err => printLog('err is: ', err))

function sendRequestFetch(methodIs, urlIs, body = null) {
    const headersVar = {'Content-Type': 'application/json'}

    // возвращает Promise _ и по умолчанию выполняет метод GET
    // return fetch(urlIs) // т.е. этого уже достаточно для GET запроса
    return fetch(urlIs, {
        method: methodIs,
        body: JSON.stringify(body),
        headers: headersVar
    }).then(response => {
        // if(response.status<400)
        if (response.ok) // если все норм, то даем JSON
            return response.json()  // приводит в JSON
        // return response.text()  // приводит в текст

        // иначе видаем текст ошибки
        return response.json().then(error => {
            const e = new Error('my err msg')
            e.data = error
            throw e
        })
    })
}
