printLog('jsObjects.js - file loaded')
/////////////////////////////////////////////////

// 3:45 in Video
const personObj = {
    name: 'Thanos',
    lastName: undefined,
    age: 9999,
    doters: ['Nebula', 'Gomora'],
    'key': 'value',
    ['key_' + (1 + 3)]: 'value is',   // key_4
    // называют методом т.к. это внутри объекта
    greet: function () {
    },
    greetNewSyntax() {   // нов упрощенный вариант объявл метода внутри объекта
        // printLog('Hello ' + personObj.name)
        // printLog('Hello ' + this.name)
        printLog('Hello ' + name)
    }
}
printLog(personObj.doters)   // ["Nebula", "Gomora"]
printLog(personObj['age'])   // 9999
printLog(personObj['key'])   // value
printLog(personObj['key_4'])   // value is

// change in Object
personObj.age++
printLog(personObj['age'])   // 10000
personObj.doters.push('superWoman')
printLog(personObj.doters)   // ["Nebula", "Gomora", "superWoman"]

personObj['key_4'] = 'new value for key_4'
printLog(personObj['key_4'])   // new value for key_4

delete personObj['key_4']   // delete key in Object
printLog(personObj['key_4'])   // undefined


printLog(' --- --- --- Destructors Деструктуризация  --- --- --- ')   // 3:54 in video
const name1 = personObj.name, age1 = personObj.age, doters1 = personObj.doters
printLog(doters1)   // ["Nebula", "Gomora", "superWoman"]

// in new Way is:..
const {name, age: personAgeVar, doters, lastName: personLastN = 'default value if undefined'} = personObj
printLog(doters)   // ["Nebula", "Gomora", "superWoman"]
printLog(personAgeVar)   // 10000
printLog(personLastN)   // default value if undefined


printLog(' --- --- --- iteration in Objects  --- --- --- ')   // 3:57 in video
for (let key in personObj) {
    printLog('key is: ' + key + ' and value is: ' + personObj[key])
}

printLog(' --- --- --- iteration in Objects 2  --- --- --- ')
for (let key in personObj) {
    if (personObj.hasOwnProperty(key))
        printLog('key is: ' + key + ' and value is: ' + personObj[key])
}

printLog(' --- --- --- iteration in Objects 3  --- --- --- ')
let keys = Object.keys(personObj);
keys.forEach((key) => {
    printLog('key is: ' + key + ' and value is: ' + personObj[key])
})


printLog(' --- --- --- context (this.name) функц внутри функц.. внутри..  --- --- --- ')   // 4:03 in Video
personObj.greetNewSyntax()   // Hello Thanos

const contextFunction = {
    keys() {
        printLog('Object keys is: ', Object.keys(this))
    },
    keysAndValues() {
        // printLog('key is: ', Object.keys(this), ' and value is: '+) // wrong

        // Object.keys(this).forEach(key=>{   // variant 1
        //     printLog(`"${key}": ${this[key]}`)
        // })

        // variant 2
        const self = this   // or use }.bind(this) or use =>
        Object.keys(this).forEach(function (key) {
                // printLog(`"${key}": ${this[key]}`)
                printLog(`"${key}": ${self[key]}`)
            }
            // }.bind(this)
        )
    },
    withParams(top = false, between = false, bottom = false) {
        if (top) printLog('----Start----')

        const self = this
        Object.keys(this).forEach((key, index, arr) => {   // variant 1
            printLog(`"${key}": ${this[key]}`)
            // if (between && index === (arr.length / 2)) // don't work
            if (between && index === 3)
                printLog(' ___ every poin ___ ')
        })

        // Object.keys(this).forEach(function (key) { printLog(`"${key}": ${self[key]}`) })
        if (bottom) printLog('----End----')
    }
}

const bound = contextFunction.keys.bind(contextFunction)
bound()

const bound2 = contextFunction.keys.bind(personObj)
bound2()  // выводит массив ключей любого объекта

printLog(' --- --- --- keys and values printLog: --- --- --- ')
// logger.keys.call(personObj)   // Don't work
// logger.keys.apply(personObj)   // Don't work
const bound3 = contextFunction.keysAndValues.bind(personObj)
bound3() // выводит массив ключей и значений

printLog(' --- --- --- keys and values withParams: --- --- --- ')
const top1 = true, meddle1 = true, bottom1 = true
const bound4 = contextFunction.withParams.bind(personObj, top1, meddle1, bottom1)
bound4() // выводит массив ключей и значений


