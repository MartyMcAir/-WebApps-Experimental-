// from https://www.youtube.com/watch?v=Bluxbh9CaQ0 - 6hours video about JS
// printLog('jsMain.js - file loaded')
/////////////////////////////////////////////////

const jsFiles = ['jsDataStructure.js', 'jsMathBigIntEtc.js', 'jsString.js',  // 0 1 2
    'jsFunctions.js', 'jsDataStructure2.js', 'jsObjects.js',   // 3 4 5
    'jsAsync.js', 'jsAjaxGetDomElement.js', 'jsAjax2Events.js',   // 6 7 8

    // from https://www.youtube.com/watch?v=eKCD9djJQKc&list=PLqKQF2ojwm3l4oPjsB9chrJmlhZ-zOzWT&index=14 -29m
    'jsAjaxFetch.js', 'JSON_iteration.js'   // 9 10
]

// importJsFiles(jsFiles)
importJsFile(jsFiles[10])

// Functions /////////////////////////////////////////////////
function importJsFile(jsFile) {   // load script
// $.getScript('/script.js'); // by jQuery
    let script = document.createElement('script') //.src = jsFile; - dont work
    script.src = jsFile
    document.head.append(script)
}

function printLog(...text) {   // Function Declaration
    console.log(text)
}

function importJsFiles(arrJsFiles) {
    for (let item of arrJsFiles) {
        importJsFile(item)
    }
}

function printArr(array) {
    for (let i = 0; i < array.length; i++) {
        console.log(arr[i])
    }
}

function getRandomBetween(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min)
}



















