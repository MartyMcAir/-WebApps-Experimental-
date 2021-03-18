printLog('jsDataStructure2.js - file loaded')
/////////////////////////////////////////////////

printLog(' --- --- --- Array2 --- --- --- ')   // 3:03 in video
const strCarsArr = ['Mazda', 'Ford', 'BMV']  // cars
const numArr = [1, 2, 3, 5, 8, 13]  // fibonacci
const mixArr = [1, 2, 3, 5, true, 'string']
printLog(strCarsArr)  // __proto__: - там методы которые можно юзать для конкретного элемента данного объекта

strCarsArr.push('new Car', 'Tesla')
strCarsArr.unshift('put Car in 0 index')   // in beginning array
printLog(strCarsArr)

// delete 0 index in array
printLog(strCarsArr.shift())   // put Car in 0 index

// delete last element in Array
printLog(strCarsArr.pop())   // Tesla

printLog(strCarsArr.reverse())   // ["new Car", "BMV", "Ford", "Mazda"]

printLog(' --- --- --- string to Array --- --- --- ')   // 3:03 in video
const strSimple = 'my string is, not array, but can..'
printLog(strSimple.split(','))   // array by ,
// array to String
printLog(strSimple.split(',').join(''))   // my string is not array but can..
printLog(strSimple.split(''))   // arrays every char

// if not found that return -1
printLog('Ford is element: ' + strCarsArr.indexOf('Ford') + ' in array')   // Ford is element: 2 in array


printLog(' --- --- --- Objects Array --- --- --- ')   // 3:23 in video
const superHeroesArr = [
    {name: 'Tony Stark', in_bucket: 999},
    {name: 'Captain America', in_bucket: 130},
    {name: 'Hulk', in_bucket: 777}
]

// findIndex - это цикл, перебирающие элементы массива на соответствие предикату
//  let findIndexIs = superHeroesArr.findIndex(criteriaForHero(superHeroesArr));   // ERROR

function criteriaForHero(superHeroesArr) {
    return superHeroesArr.in_bucket === 999
}

const findIndexIs2 = superHeroesArr.findIndex(function (superHeroesArr) {
    return superHeroesArr.in_bucket === 999
})
// если не найдет вернет undefined
const findIs = superHeroesArr.find(function (superHeroesArr) {
    return superHeroesArr.in_bucket === 999
})

printLog(superHeroesArr[findIndexIs2])   // {name: "Tony Stark", in_bucket: 999}
printLog(findIs)   // {name: "Tony Stark", in_bucket: 999}

// Using lambda
const findIs2 = superHeroesArr.find((superHeroesArr) => {
    return superHeroesArr.in_bucket === 999
})

const findIs3 = superHeroesArr.find(superHeroesArr => superHeroesArr.in_bucket === 999)


printLog(' --- --- --- Contains in Array and oth methods --- --- --- ')   // 3:23 in video
printLog('contains BMV in strCarsArr?: ' + strCarsArr.includes('BMV'))   // true

const lowerCaseCars = strCarsArr.map(car => {
    return car.toLowerCase()
})
printLog(lowerCaseCars)   // ["new car", "bmv", "ford", "mazda"]
// оригинал массив без имзенений
printLog(strCarsArr)   // ["new Car", "BMV", "Ford", "Mazda"]

printLog('numArr is: ' + numArr)   // [1, 2, 3, 5, 8, 13]

const myLambdaPow2 = num => num ** 2    // возводит каждый элемент в квадрат
const numPow2 = numArr.map(myLambdaPow2)
printLog(numPow2)   // [1, 4, 9, 25, 64, 169]

const sqrtLambda = num => Math.sqrt(num)
const numPow3 = numArr.map(myLambdaPow2).map(sqrtLambda)   // [1, 2, 3, 5, 8, 13]
// Reference Link _ ссылочная функция
const numPow4 = numArr.map(myLambdaPow2).map(Math.sqrt)   // [1, 2, 3, 5, 8, 13]
printLog(numPow3)   // [1, 2, 3, 5, 8, 13]

printLog(' --- --- --- Filters --- --- --- ')   // 3:36 in video
const pow2Fib = numArr.map(myLambdaPow2)
const filteredNums = pow2Fib.filter(num => num > 20)

printLog(filteredNums)   // [25, 64, 169]


printLog(' --- --- --- Reduce объединить all elements in array --- --- --- ')   // 3:38 in video

const sumAllBuckets = superHeroesArr.reduce(function (accumulator, person) {
    // вместо использования if(..) конструкции внутри reduce(..) лучше юзать
    // сначала filter и на filter().reduce(..)
    accumulator += person.in_bucket
    return accumulator
}, 0)
printLog(sumAllBuckets)   // 1906

const sumAllBucketsWithFilter = superHeroesArr
    .filter(person => person.in_bucket < 999)
    .reduce((acc, person) => {
        return acc += person.in_bucket
    }, 0)
printLog(sumAllBucketsWithFilter)   // 907