// имена полей запрашиваемые с базы
// Так же как и в GET составляем строку с данными, но уже без пути к файлу 
const id_product = 321, qty_product = 2, get_what = 'cat'
const params2 = 'get_what=' + get_what + '&id_product=' + id_product + '&qty_product=' + qty_product


// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
const localAddr = window.location.toString()
const index2 = localAddr.indexOf('d/')
const hostIs2 = localAddr.substring(0, (index2 + 2)) // http://localhost:8080/FirstWebApp_war_exploded/
const urlGerContent2 = hostIs2 + 'ExperimentalAjaxServletPost'   // .java


const events2 = ['readystatechange']

console.log('ajaxPostJsonRequest.js')

connectPostToServer(urlGerContent2, events2, params2)


function connectPostToServer(urlPath, eventName = 'readystatechange', params) {
    console.log('ajaxPostJsonRequest.js connectPostToServer(..')

    const request = new XMLHttpRequest() // Создаем экземпляр класса XMLHttpRequest

    //	Здесь нужно указать в каком формате мы будем принимать данные вот и все	отличие
    request.responseType = 'json';
    // request.responseType = 'text';

    /* Указываем что соединение	у нас будет POST, говорим что путь к файлу в переменной url, и что запрос у нас
    асинхронный, по умолчанию так и есть не стоит его указывать, еще есть 4-й параметр пароль авторизации, но этот
        параметр тоже необязателен.*/
    request.open('POST', urlPath, true)

    //В заголовке говорим что тип передаваемых данных закодирован.
    // request.setRequestHeader('Content-Type', 'text/plain');   // желательно указывать
    request.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр
    request.setRequestHeader('Content-type', 'application/json') // application/javascript application/json

    request.addEventListener('readystatechange', () => {
        if (request.readyState === 4 && request.status === 200) {
            console.log('result is:..');
            const obj = request.response;

            console.log(obj);   //

            // Здесь мы можем обращаться к свойству объекта и получать	его значение
            // console.log(obj.id_product);
            // console.log(obj.qty_product);
        }
        console.log('ajaxPostJsonRequest.js request.addEventListener(..')
    })

    //	Вот здесь мы и передаем строку с данными, которую формировали выше. И собственно выполняем запрос.
    request.send(params)
    console.log('ajaxPostJsonRequest.js request.send(params)')
    console.log('ajaxPostJsonRequest.js params is: ', params)
}
