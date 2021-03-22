printLog('jsDataStructure.js - file loaded')
/////////////////////////////////////////////////

// Variables ....................................
const floatNum = 3.14 // constant
const powNum = 10e3 // 10 в 3ей степени
let str
let num1 = 13

printLog(powNum)

// Array ....................................
printLog(' --- --- --- Array --- --- --- ')   //
const arr = ['zero', 'one']
const arr2 = new Array(['zer', 'on', 'tw'])
printLog(arr2)
printLog(arr.length)  // 2

arr[0] = 'three'
console.log(arr)
arr[2] = 'two' // add in array third element _ dynamically
arr[arr.length] = 'four'

printArr(arr);


// Object  ....................................
const person = {
    firstName: 'Thanos',
    year: 9999,
    doter: 'Nebula',
    alive: true,
    languages: ['ru', 'en'],
    greetFunction: function () {
        console.log('greet function from Object person')
    }
}

printLog(person)
printLog(person.firstName)
person.greetFunction()

person.isProgrammer = true  // add field to Object
printLog(person)