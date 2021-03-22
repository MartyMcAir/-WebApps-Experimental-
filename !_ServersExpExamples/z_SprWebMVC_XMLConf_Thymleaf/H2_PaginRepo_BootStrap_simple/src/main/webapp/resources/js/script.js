function myPrint(){
	let arr = [1, 2, 3]
	for (let k = 0; k < arr.length; k++) {
		console.log(arr[k])
	}

	// const arrChecks = document.querySelector('.id_checkbox')
	// let valueOfCurrentBox = item.getAttribute('value')
	// console.log(checkID);
};

function myFunction() {

	const classObj = Array.from(document.querySelectorAll('.id_checkbox'))
	// const classObj = Array.from(document.querySelectorAll('.id_checkbox'))
	for (let j = 0; j < classObj.length; j++) {
		if(classObj[j].checked){
			console.log(classObj[j].value)
			document.getElementById("demo").innerHTML = "its working for id: "+classObj[j].value
		}
	}
}

// https://www.geeksforgeeks.org/how-to-send-a-json-object-to-a-server-using-javascript/
function sendJSON(){
	let result = document.querySelector('.result');
	const name = document.querySelector('#name');
	const email = document.querySelector('#email');

	// Creating a XHR object
	const xhr = new XMLHttpRequest();
	const url = "submit.php";

	// open a connection
	xhr.open("POST", url, true);

	// Set the request header i.e. which type of content you are sending
	xhr.setRequestHeader("Content-Type", "application/json");

	// Create a state change callback
	xhr.onreadystatechange = function () {
		if (xhr.readyState === 4 && xhr.status === 200) {

			// Print received data from server
			result.innerHTML = this.responseText;

		}
	};

	// Converting JSON data to string
	const data = JSON.stringify({ "name": name.value, "email": email.value });

	// Sending data with the request
	xhr.send(data);
}