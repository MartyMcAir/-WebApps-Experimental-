printLog('jsAsync.js - file loaded')
/////////////////////////////////////////////////

printLog(' ___ ___ ___ Intervals Running methods: ___ ___ ___ ') // 4:20 in Video
const myTimeOut = setTimeout(() => {
    printLog('after time out')
}, 1500)    // 1500ms = 1.5 sec
clearTimeout(myTimeOut) // if uncomment myTimeOut - не запустится

const myInterval = setInterval(() => {
    printLog('after interval')
}, 1500)    // 1500ms = 1.5 sec
clearInterval(myInterval) // if uncomment myInterval - не запустится

const delayFunction = (callback, wait = 1000) => {
    setTimeout(callback, wait)
}


// delayFunction(() => callbackFunction(), 1500)
const callbackFunction = () => printLog('my function is working after 1.5sec')


printLog(' ___ ___ ___ Promise(Промисы) _ Executors _ Catch: ___ ___ ___ ') // 4:28 in Video

const delayFunction2 = (wait = 500) => {
    const promiseVar = new Promise((resolve, reject) => {   // Executor
        setTimeout(() => {
            resolve()
            // reject('my error msg from delayFunction2')  // if uncommented: err msg: ..
        }, wait)
    })
    return promiseVar
}

// юзаем методы from Promise
delayFunction2(1500)
    .then(callbackFunction)
    .catch(err => console.error('err msg: ' + err))
    .finally(() => printLog('finally is complete'))


const getData = () => new Promise(resolve => resolve([
    1, 2, 3, 5, 8, 13, 99
]))

getData().then((data) => printLog(data))

// ассинхронная функция
async function asyncExample() {
    try {
        await delayFunction2(2000) // ждем эту строку кода пока выполнится
        const data = await getData()
        printLog('my data from async function is: ', data)
    } catch (e) {
        printLog('erro from asyncExample(): ' + e)
    } finally {
        printLog('finally from asyncExample()')
    }
}

asyncExample()




