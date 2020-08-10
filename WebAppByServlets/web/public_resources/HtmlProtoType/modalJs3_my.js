// For Open Window, when click on button in Form..
const objFormForShowWindow = document.querySelector('#idShowUpdForm')
const mainDivObj = document.querySelector('.classMainDiv')

objFormForShowWindow.addEventListener('submit', function (event) {
    event.preventDefault()
    const className = mainDivObj.className
    mainDivObj.className = 'classMainDiv' + ' classShowHover '
})

// For Close Window, when click on classElement
let elements = document.getElementsByClassName('closeModal');

let closeWindow = function (event) {
    event.preventDefault()
    mainDivObj.className = 'classMainDiv'
}

for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', closeWindow, false)
}

// For submit data on the Server
const objResult = document.querySelector('#resultId');
const objFormForSubmit = document.querySelector('#idSubmitUpdForm')

objFormForSubmit.addEventListener('submit', function (event) {
    event.preventDefault()
    mainDivObj.className = 'classMainDiv'   // close
    objResult.textContent = 'your form submitted'   // show result
})