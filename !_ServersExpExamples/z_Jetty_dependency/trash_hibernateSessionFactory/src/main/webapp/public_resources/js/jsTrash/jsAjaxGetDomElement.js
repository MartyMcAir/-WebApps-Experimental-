printLog('jsAjaxGetDomElement.js - file loaded')
/////////////////////////////////////////////////

printLog(' ___ ___ ___ Получение DOM элемента ___ ___ ___ ') // 4:38 and 4:42 and 50 in Video

printLog(document) // DOM - HTML - для изменений через JS используя API браузера
printLog(window) // API методы браузера и прочее.. для взаимодействия с DOM &etc

// устаревшие варианты получаения
const linkIdHello = document.getElementById('helloID')
const linkH2 = document.getElementsByTagName('h2')   // like array.. псевдомассив
const linkH2_2 = document.getElementsByClassName('h2-class')   // псевдомассив

// новые варианты - и он быстрее предыдуших вариантов
const linkNewH2byTag = document.querySelector('h2'); // по тэгу = arr
const linkNewH2byClass = document.querySelector('.h2-class'); // по классу
const linkNewH2byID = document.querySelector('#h2ID'); // по ID

// получение по тэгу H2 у которого нет ID или класса
const linkToNextH2Tag = linkNewH2byTag.nextElementSibling;
console.log('linkToNextH2Tag: ', linkToNextH2Tag)

const h2List = document.querySelectorAll('h2')
console.log('h2List: ', h2List)   // arr all h2 tag in DOM
console.log('h2List[1]: ', h2List[1])   // <h2>Another H2</h2>

// Output in Console
console.log('linkNewH2: ', linkNewH2byClass)   // <h2 id="h2ID" class="h2-class">Hello it's h2 tag</h2>
console.dir(linkNewH2byClass)   // h2.h2-class array
console.log('linkH2: ', linkH2)   // HTMLCollection [h2] ..
console.log('linkH2[0]: ', linkH2[0])   // <h2 id="h2ID" class="h2-class">Hello it's h2 tag</h2>
console.log('.log(linkIdHello: ', linkIdHello)   // <h1 id="hello">Hello Java Script</h1> ..
console.dir(linkIdHello)   // h1#hello

printLog(linkIdHello.id)   // ["helloID"]
printLog(linkIdHello.textContent)   // ["Hello Java Script"]


printLog(' ___ ___ ___ изменяем HTML h1 через JS: ___ ___ ___ ') // 5:00 in Video
runTimeOut(linkIdHello, 800, 'helloID', 'green', '2rem')
runTimeOut(linkNewH2byClass, 1600, '.h2-class', 'yellow')
runTimeOut(h2List[1], 2400, 'from arr h2 tag without id or class')

// изменяем через время в мс timeOut
function runTimeOut(node, timeOut, text, color, fontSize) {
    setTimeout(() => {
        addStr(node, text, color, fontSize)
    }, timeOut)
}

function addStr(node, text, color = 'pink', fontSize) {
    node.textContent = 'Changed by JS for change: ' + text  // подменит собой Hello Java Script
    node.style.color = color  // подменяем CSS для полученного элемента
    node.style.textAlign = 'center'
    node.style.backgroundColor = 'gray'
    node.style.padding = '2rem'
    // falsy: '', undefined, null, 0, false
    if (fontSize) node.style.fontSize = fontSize
}


