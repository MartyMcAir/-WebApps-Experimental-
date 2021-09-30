


function btnUseCheckedBoxesAndDelAndRedirect() {
    // get all .cl_checkbox objects
	const classObj3 = Array.from(document.querySelectorAll('.cl_checkbox'))
    const array = []
    // fill array (index = value_id ) - only checked field
    for (let j = 0; j < classObj3.length; j++) {
		if(classObj3[j].checked){ array.push(classObj3[j].value) }	}

    	let request = new Request('http://localhost:8080/simple_mvc/books/delArray', {
        method: 'POST',
        body: JSON.stringify(array),
        headers: { 'Content-Type': 'application/json' }, });

      fetch(request).then(
          function(response) {   // if ok
            console.log(response);
            window.location.href = 'http://localhost:8080/simple_mvc/books';
          },
          function(error) {   // else _ if error
            console.error(error); } );
      console.log('Request is pushed');
}

//...............................................................................
        // MULTI SELECT column checkbox select all or cancel
            $(function(){
                $("input.select-all").click(function () {
                    var checked = this.checked;
                    $("input.select-item").each(function (index,item) {
                        item.checked = checked;
                    });
                });
            });