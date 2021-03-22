//var form = document.querySelector("form");
//console.log(form.elements[0].type);
//// → password
//console.log(form.elements.password.type);
//// → password
//console.log(form.elements.name.form == form);
//// → true
//
//var select = document.querySelector("select");
//var output = document.querySelector("#output");
//select.addEventListener("change", function () {
//    var number = 0;
//    for (var i = 0; i < select.options.length; i++) {
//        var option = select.options[i];
//        if (option.selected)
//            number += Number(option.value);
//    }
//    output.textContent = number;
//});

//{/*</script>*/
//}

//const checkID = []; // define an empty array
// class="id_checkbox"

function myPrint(){
	let arr = [1, 2, 3];
	for (let k = 0; k < arr.length; k++) {
		console.log(arr[k]);
	}

	// const arrChecks = document.querySelector('.id_checkbox')
	// let valueOfCurrentBox = item.getAttribute('value')
	// console.log(checkID);
};

//function printDetails(){
//	$("input[name='checkedid']:checked").each(function(i){//Save the values ​​of all selected checkboxes into an array
//		checkID[i] =$(this).val();
//		console.log(checkID);
//	})};



	function myFunction() {
		document.getElementById("demo").innerHTML = "Hello World";
	}