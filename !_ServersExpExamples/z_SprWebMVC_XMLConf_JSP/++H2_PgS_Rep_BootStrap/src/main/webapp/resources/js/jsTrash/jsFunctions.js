printLog('jsFunctions.js - file loaded')
/////////////////////////////////////////////////

const name = 'Thanos', age = 9999, lastName = 'LastNameOfThanos'

function greetZero0(text) {   // Function Declaration
    console.log(text)
}

function greetZero(text) {   // Function Declaration
    return 'my functions'
}

// Antonyms анонимные Functions // 2:45 in Video
const greetOne = function () {
    // my functions
}

// Functions Expression
const greetTwo = function functionNameIs() {
    // my functions
}

// не работают примеры _ говрит функция это объект
printLog(typeof greetZero())   // string
printLog(typeof greetZero0('greetZero0 running'))   // greetZero0 running

console.dir(greetZero0())   // undefined

printLog(' --- --- --- Anonymous анонимные Functions --- --- --- ')   // 2:49 in Video
let counter = 0
// const myInterval = setInterval(function () {
//     if (counter < 3) {
//         // if(counter ==3) clearInterval(myInterval)
//         printLog(++counter)
//     }
// }, 300)

// задает задержку
// const myTimeOut = setTimeout(function () {
//     if (counter < 6)
//         printLog(++counter)
// }, 300)


printLog(' --- --- --- Стрелочаня функция // Using lambda --- --- --- ')   // 2:50 in video
const arrowFunction = (name) => {
    printLog('hello: ' + name)
}
arrowFunction('Thanos')   // hello: Thanos

const arrowFunction2 = (name) => printLog('hello: ' + name)
const arrowFunction3 = (name, age) => printLog('hello: ' + name + ' and your age is: ' + age)
const arrowFunction4 = () => printLog('anyway hello ')

arrowFunction3(name, age)   // hello: Thanos and your age is: 9999

const arrowPowFunc = num => {
    return num ** 2
}

const arrowPowFunc2 = num => num ** 2

printLog(arrowPowFunc2(2))   // 4


printLog(' --- --- --- Параметры по умолчанию --- --- --- ')   // 2:55 in video
const sumFunc = (a, b) => a + b
printLog(sumFunc(3, 3))   // 6
printLog(sumFunc(3))   // NaN = a + undefined

const sumFunc2 = (a, b = 6) => a + b
printLog(sumFunc2(3))   // 9


printLog(' --- --- --- ...all _ rest _ like in Java Varargs.. --- --- --- ')   // 2:55 in video
function sumAllArgs(...all) {
    let tmp = 0
    for (let i = 0; i < all.length; i++)
        tmp += all[i]
    return tmp
}

printLog(sumAllArgs(3, 6, 9))   // 18

printLog(' --- --- --- Замыкание --- --- --- ')   // 2:59 in video
function createMember(name) {
    return function (lastName) {
        printLog(name + lastName)
    }
}

const logWithLastName = createMember(name)
printLog(logWithLastName)   // ƒ (lastName) { printLog(name + lastName) }
printLog(logWithLastName('Nebula')) // ThanosNebula
printLog(logWithLastName('Gomora')) // ThanosGomora