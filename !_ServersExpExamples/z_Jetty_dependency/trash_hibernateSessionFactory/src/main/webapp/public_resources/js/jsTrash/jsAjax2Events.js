printLog('jsAjax2Events.js - file loaded')
/////////////////////////////////////////////////

printLog(' ___ ___ ___ Реагируем на event события: ___ ___ ___ ') // 5:22 in Video
// MDN mouse event
// https://developer.mozilla.org/ru/docs/Web/API/MouseEvent
// https://developer.mozilla.org/ru/docs/Web/Events

// Ссылки на DOM объекты
const linkFirstH2byClass = document.querySelector('.helloClass') // по классу _ первый h2
const linkSecondH2byID = document.querySelector('#h2ID') // по ID _ второй h2
const linkH2byTag = document.querySelector('h2') // без id и class _ по тэгу = arr
const linkToNextH2Tag = linkH2byTag.nextElementSibling
const linkToThirdH2 = linkToNextH2Tag[1]   //  _ третий h2

// for 5:32 in video
const linkFourthH2byID = document.querySelector('#MDN') // по ID _ четвертый h2
// получить вгутри h2 ссылку на внутренний тэг т.е. ссылку <a>
const htmlAnchorElement = linkFourthH2byID.querySelector('a')
const childFromFourTag = linkFourthH2byID.children[0]  // тож самое
printLog('childFromFourTag: ', childFromFourTag)

// переххватываем событие клика по ссылке
childFromFourTag.addEventListener('click', clickCounter)

//
linkFirstH2byClass.style.color = 'black'
linkSecondH2byID.style.color = 'black'

// toggle меняет цвет тескта и бэкграунд его при каждом клике
linkFirstH2byClass.onclick = toggleChangeTextInFirst

// изменяем по двойному клику  (изменяем DOM в ответ на событие двойного клика)
// linkSecondH2byID.ondblclick = toggleChangeTextInSecond // ниже тож самое _ реагирует на двойно клик
linkSecondH2byID.addEventListener('dblclick', toggleChangeTextInSecond)


////////////////
let clickCounterVar = 0

function clickCounter() {
    // event.preventDefault() // если раскоментить, то при нажатии по ссылке переход на др сайт не будет
    // работает хоть и IDE ругается
    // let urlFromTag = event.target.getAttribute('href');
    // printLog(urlFromTag)  // ["https://developer.mozilla.org/ru/docs/Web/Events"]
    printLog('click on tag a link is: ' + (++clickCounterVar))  // при каждом клике по элементу выводит в консоль

    // программно, переход по ссылке прямо в этом же окне _ а не в новом
    // window.location = urlFromTag  // target = _blank - не сработает
}

function toggleChangeTextInFirst() {
    printLog('click on first tag')  // при каждом клике по элементу выводит в консоль
    if (linkFirstH2byClass.style.color === 'black') {
        linkFirstH2byClass.style.color = 'pink'
        linkFirstH2byClass.style.backgroundColor = 'gray'
    } else {
        linkFirstH2byClass.style.color = 'black'
        linkFirstH2byClass.style.backgroundColor = 'white'
    }
}

function toggleChangeTextInSecond() {
    printLog('dblclick on second tag')  // при каждом клике по элементу выводит в консоль
    if (linkSecondH2byID.style.color === 'black') {
        linkSecondH2byID.style.color = 'green'
        linkSecondH2byID.style.backgroundColor = 'gray'
    } else {
        linkSecondH2byID.style.color = 'black'
        linkSecondH2byID.style.backgroundColor = 'white'
    }
}


