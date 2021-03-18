// https://o7planning.org/ru/12287/javascript-json-tutorial
// JSON строка например полученная с сервера
const jsonStr = '{ '
    + ' "name": "Amazon", '   // поле
    + ' "ceo" : "Jeff Bezos", '
    + ' "employees" : ['   // массив
    + '    { "firstName":"John" , "lastName":"Doe" },'
    + '    { "firstName":"Anna" , "lastName":"Smith" },'
    + '    { "firstName":"Peter" , "lastName":"Jones" } '
    + '  ] '
    + '}'

const jsonObj = JSON.parse(jsonStr)   // преобразуем JSON строку в JS объект

// printJsonObjAmazon()
function printJsonObjAmazon() {
    console.log(jsonObj.employees)
// 0: jsonObject { firstName: "John", lastName: "Doe" }
// 1: jsonObject { firstName: "Anna", lastName: "Smith" }
// 2: jsonObject { firstName: "Peter", lastName: "Jones" }
// length: 3
// <prototype>: [..

    console.log(jsonObj.name)   // Amazon
    console.log(jsonObj.ceo) // Jeff Bezos
    console.log(jsonObj.employees[0].firstName) // John
    console.log(jsonObj.employees[1].firstName) // Anna
    console.log(jsonObj.employees[2].firstName) // Peter
    console.log(jsonObj.employees[2].lastName) // Jones
    console.log(jsonObject.keys(jsonObj.employees[2])) // Array [ "firstName", "lastName" ]
    console.log(jsonObject.values(jsonObj.employees[2])) // Array [ "Peter", "Jones" ]
}

// ..........................................................................................
// https://itchief.ru/javascript/json
//JSON String
const personData = '{"name":"Иван","age":37,' +
    '"mother":{"name":"Ольга","age":58},' +
    '"children":["Маша","Игорь","Таня"],' +
    '"married": true,' +
    '"dog": null}'

const person = JSON.parse(personData)   //Объект JavaScript person

// printPersonObj();
function printPersonObj() {
//получить значения ключа (свойства) name
    console.log(person.name) // Иван
    console.log(person["name"]) // Иван
//получить значения ключа (свойства) name, находящегося в объекте mother
    console.log(person.mother.name) // Ольга

    console.log(person.age) // 37
//удалить элемент age
    delete (person.age)
    console.log(person.age) // undefined

    console.log(person.eye) // undefined
//добавить (или обновить) ключ (свойство)
    person.eye = "карие"
    console.log(person.eye) // карие

    console.log(person.children) // Array(3) [ "Маша", "Игорь", "Таня" ]
//при работе с массивами необходимо использовать методы, предназначенные для работы именно с массивами
//удалить 1 элементиз массива (метод splice)
    person.children.splice(1, 1)
    console.log(person.children) // Array [ "Маша", "Таня" ]

//добавить элемент в массив (метод push)
    person.children.push("Катя")
    console.log(person.children) // [ "Маша", "Таня", "Катя" ]
}

// перерабрать все ключи (свойства) в объекте
// iterateInPersonObj();
function iterateInPersonObj() {
    for (key in person) {
        if (person.hasOwnProperty(key)) {
            console.log("Ключ = " + key) //ключ = key
            console.log("Значение = " + person[key]) //значение = person[key]
            console.log('ключ=' + key)
        } // если объект person имеет key (если у person есть свойство key)
    }
// Ключ = age
// Значение = 37
// Ключ = mother
// Значение = [object Object]
// Ключ = children
// Значение = Маша, Игорь, Таня
// Ключ = married
// Значение = true
// Ключ = dog
// Значение = null
}


// ..........................................................................................
// https://overcoder.net/q/2833/%D0%BA%D0%B0%D0%BA-%D0%BC%D0%BD%D0%B5-%D0%BF%D0%B5%D1%80%D0%B5%D0%B1%D1%80%D0%B0%D1%82%D1%8C-%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D1%83-json
const arr = [{"id": "10", "class": "child-of-9"}, {"id": "11", "class": "child-of-10"}];

// iterateIn2Arr()
// iterateInArr()

// Output from iterateInArr() or iterateInArr2()
// key: id value: 10
// key: class value: child-of-9
// key: id value: 11
// key: class value: child-of-10

function iterateInArr() {
    // console.log('count is: ', arr.count) // undefined
    for (let i = 0; i < arr.length; i++) {
        let obj = arr[i]
        for (let key in obj) {
            let value = obj[key]
            console.log('key: ' + key + ' value: ' + value)
        }
    }
}

function iterateIn2Arr() {
    for (let i = 0; i < arr.length; i++) {
        let obj = arr[i]
        for (let key in obj) {
            let attrName = key
            let attrValue = obj[key]
            console.log('key: ' + attrName + ' value: ' + attrValue)
        }
    }
}


const myCars = [{name: 'Susita'}, {name: 'BMW'}]

// printCars()
function printCars() {
    for (car in myCars) {
        // document.write(myCars[car].name + "<br />")
        console.log(myCars[car].name) // Susita \n BMW
    }
}

// inside(person) // рекурсивно выводит в консоль всё содержимое
function inside(events) {
    console.log(events) // сам текузший массив
    for (i in events) {
        if (typeof events[i] === 'object') {
            inside(events[i])
        } else {
            console.log(events[i]) // каждое значение массива
        }
    }
}

// Don't Work
// person.forEach(function (item) {
//     console.log(item)
// })

const arr2 = [{"id": "10", "class": "child-of-9"}, {"id": "11", "class": "child-of-10"}]
// var data = JSON.parse(hr.responseText)
// const data = JSON.parse(arr2)
// const results = document.getElementById("someId");//myDiv is the div id
// for (let obj in arr2) {
//     results.innerHTML += data[obj].id + "is" + data[obj].class + "<br/>"
// }

// ..........................................................................................
// https://stackoverflow.com/questions/34913675/how-to-iterate-keys-values-in-javascript
// const dictionary = ['{"one":"1",' + '"two":"3",' + '"three": "3"}']
const dictionary = [{"one": "1"}, {"two": "2"}, {"three": "3"}]
// const dictionary = [{"id": "10", "class": "child-of-9"}, {"id": "11", "class": "child-of-10"}]

forEachDictionary();

function forEachDictionary() {
    for (let key in dictionary) {
        // check if the property/key is defined in the object itself, not in parent
        if (dictionary.hasOwnProperty(key)) {
            console.log(key, dictionary[key]);
        }
    }
    // Object { one: "1" }
    // Object { two: "2" }
    // Object { three: "3" }
}