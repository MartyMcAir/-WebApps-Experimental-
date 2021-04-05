
                function sendJsonUseFetch_is(){
                  let obj_is = [13, 14, 15]
                  sendJsonUseFetch('http://localhost:8080/simple_mvc/product/experimentalGetJsonList', obj_is)
                }

                function sendIntDelAndRedirect(){
                  sendJsonUseFetch('http://localhost:8080/simple_mvc/listExp/deleteById', 4)
                  // Redirect in JavaScript
                  // Simulate an HTTP redirect:
                  // window.location.replace('http://localhost:8080/simple_mvc/listExp/listVersion4_exp');
                  // Simulate a mouse click:
                  console.log('deleted?')
                  window.location.href = 'http://localhost:8080/simple_mvc/listExp/listVersion4_exp_0';
                }

function myCheckBoxIs() {
	document.getElementById("demo").innerHTML = "Hello World"

	const classObj = Array.from(document.querySelectorAll('.id_checkbox'))
	// const classObj = Array.from(document.querySelectorAll('.id_checkbox'))
	for (let j = 0; j < classObj.length; j++) {
		if(classObj[j].checked){ console.log(classObj[j].value) }	}
}

function useCheckBoxAndDel() {
	const classObj3 = Array.from(document.querySelectorAll('.id_checkbox_s'))
//    const array = new Array()
    const array = []

    for (let j = 0; j < classObj3.length; j++) {
		if(classObj3[j].checked){
			array.push(classObj3[j].value)
		}
	}

    console.log(array)
//    console.log(JSON.stringify(classObj3, null, 4));
//    console.log(JSON.stringify(classObj3));
//    console.dir(classObj3)
//    console.table(classObj3)
//    console.entries(classObj3)

//        for(a in classObj3) console.log(classObj3[a])
//        for(a in classObj3) console.log(classObj3[j].value)

//    sendJsonUseFetch('http://localhost:8080/simple_mvc/listExp/experimentalGetJsonList', array)
//    sendJsonUseFetch('http://localhost:8080/simple_mvc/listExp/deleteArrayById', array)

    	let request = new Request('http://localhost:8080/simple_mvc/listExp/deleteArrayById', {
        method: 'POST',
        body: JSON.stringify(array),
        headers: {      			'Content-Type': 'application/json'	    },      });

      fetch(request).then(
          function(response) {   // if ok
            console.log(response);
                        // Redirect in JavaScript
                        // Simulate an HTTP redirect:
                        // window.location.replace('http://localhost:8080/simple_mvc/listExp/listVersion4_exp');
                        // Simulate a mouse click:
                        window.location.href = 'http://localhost:8080/simple_mvc/listExp/listVersion4_exp_0';
          },
          function(error) {   // else _ if error
            console.error(error);
          }      );
      console.log('Request is pushed');
}