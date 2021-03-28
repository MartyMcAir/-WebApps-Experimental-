printLog('jsMathBigIntEtc.js - file loaded')
/////////////////////////////////////////////////

printLog('Number.MAX_SAFE_INTEGER', Number.MAX_SAFE_INTEGER) // 9_007_199_254_740_991
printLog(Math.pow(2, 53) - 1) // 9007199254740991
printLog(Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY)  // Infinity -Infinity
printLog(NaN) // NaN _ Not a Number
printLog(typeof NaN)  // number
printLog(2 / undefined)  // NaN
printLog(isNaN(NaN)) // true
printLog(Number.isFinite(Number.POSITIVE_INFINITY))  // false _ конечна ли бессконечность

const strInt = '13'
const strFloat = '3.14'
printLog(Number.parseInt(strInt) + 3)
printLog(Number.parseFloat(strInt) + 3)

// decimal..
printLog(0.4 + 0.2) // 0.6000000000000001
printLog((2 / 5) + (1 / 5)) // 0.6000000000000001
printLog((0.4 + 0.2).toFixed(3)) // 0.600 - string
printLog(+(0.4 + 0.2).toFixed(3)) // 0.6 - number
printLog(Number.parseFloat((0.4 + 0.2).toFixed(3))) // 0.6 - number


// BigInt
printLog(typeof 90071992547409919007199254740991) // number
printLog(typeof 90071992547409919007199254740991n) // BigInt with n
printLog(BigInt(6))  // BigInt
printLog(5n / 2n) // 2n - BigInt

// Math
printLog(Math.E)  // экспонента
printLog(Math.PI)  // число PI окружность 3.14..
printLog(Math.sqrt(25))
printLog(Math.max(3, 6, 8, 9, 13))  // 13
printLog(Math.floor(4.9))  // 4
printLog(Math.ceil(4.9))  // 5
// in Google: mdn math round
// https://developer.mozilla.org/ru/docs/Web/JavaScript/Reference/Global_Objects/Math/round
printLog(Math.round(4.9))  // 5 - округление к ближайшему целому
printLog(Math.random())   // random number..
printLog(getRandomBetween(3, 13))   // random number.. diapason