printLog('jsString.js - file loaded')
/////////////////////////////////////////////////

const strToo = String('that is too String')
const name = 'Thanos', age = 9999

// String methods
printLog(' ___ ___ ___ String methods: ___ ___ ___ ')
printLog(name.length)   // 6
printLog(name.toUpperCase())  // THANOS
printLog(name.charAt(2))   // a
printLog(name.indexOf('o'))   //
printLog(name.startsWith('T'))   // true
printLog(name.repeat(3))   // ThanosThanosThanos
printLog('   password   '.trim())   // 'password'
printLog('   password   '.trimLeft())   // 'password   '


// String concatenates
printLog(' ___ ___ ___ String concatenates: ___ ___ ___ ')
const outStr = 'Hello my name is ' + name + ' and I am is ' + age + ' of old'
const outStr2 = `Hello my name is ${name} and I am is ${age > 999 ? 'thousand <' : '> thousand year'} of old`

printLog(outStr2)
printLog('String typeof: ' + (typeof strToo))

const htmlOut = `<div>  Hello World  </div>
    <p>  Hello DiV  </p>`

printLog(htmlOut)


// Functions /////////////////////////////////////////////////
printLog(' ___ ___ ___ Used Functions: ___ ___ ___ ')

function logPerson(s, name, age) {   // 2:39 in video
    console.log(s, name, age)
    // return `${s[0]}` // Name is:
    return `${s[0]}${name}${s[1]}${age}${s[2]}` // Name is: Thanos, and age is: 9999
    // return 'Info about person'
}

// logPerson - не вызывается и JS делит на подмассивы
const outExp = logPerson`Name is: ${name}, and age is: ${age}`
printLog(outExp)

printLog(' --- --- --- ')

function logPersonForValidate(s, name, age) {
    if (age > 1000) {
        age = 'age is > thousand years!'
    }
    return `${s[0]}${name}${s[1]}${age}${s[2]}`
}

const outExp2 = logPersonForValidate`Name is: ${name}, and age is: ${age}`
printLog(outExp2)