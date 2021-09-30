function myLoopArrayInConsole(){
	let arr = [1, 2, 3]
	for (let k = 0; k < arr.length; k++) {
		console.log(arr[k])
	}

	// const arrChecks = document.querySelector('.id_checkbox')
	// let valueOfCurrentBox = item.getAttribute('value')
	// console.log(checkID);
};

// https://stackoverflow.com/questions/1295584/most-efficient-way-to-create-a-zero-filled-javascript-array
function newFilledArray(len, val) {
    var a = [];
        while(len--) a.push(val)
    return a;
}



// JSON POST -----------------------------------------------------------------------------------------------------------

// from webAppByServlet
// using in all CRUD
const localAddr = window.location.toString()
const index = localAddr.indexOf('d/')
const hostIs = localAddr.substring(0, (index + 2)) // http://localhost:8080/FirstWebApp_war_exploded/

/* Здесь мы указываем параметры соединения с сервером, т.е. мы указываем метод соединения GET,
а после запятой мы указываем путь к файлу на сервере который будет обрабатывать наш запрос. */
const urlWhat = hostIs + 'servletName' + '?get_what=' + 'get_whatIs';

function sendJsonUseFetch(url, obj){
	let request = new Request(url, {
    method: 'POST',
    body: JSON.stringify(obj),
    headers: {      			'Content-Type': 'application/json'	    },      });

  fetch(request).then(
      function(response) {   // if ok
        console.log(response);
      },
      function(error) {   // else _ if error
        console.error(error);
      }      );
  console.log('Request is pushed');
}

function sendJsonUseXMLHttpRequest(url, obj){
  const xhr = new XMLHttpRequest();  // Creating a XHR object
  // open a connection
  xhr.open("POST", url, true);
  // Set the request header i.e. which type of content you are sending
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.setRequestHeader('charset', 'UTF-8') // при JSON обязательный параметр

  // Create a state change callback
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) { // if ok
      // Print received data from server
        // console.log(xhr.responseText);
        // console.log(xhr.status);
        console.log(xhr.statusText);
      }  };

  // Converting JSON data to string
  const data = JSON.stringify(obj);
  xhr.send(data);  // Sending data with the request
}

// .....................................................................................................................