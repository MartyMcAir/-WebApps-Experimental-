var form = document.querySelector("form");
console.log(form.elements[0].type);
// → password
console.log(form.elements.password.type);
// → password
console.log(form.elements.name.form == form);
// → true

var select = document.querySelector("select");
var output = document.querySelector("#output");
select.addEventListener("change", function () {
    var number = 0;
    for (var i = 0; i < select.options.length; i++) {
        var option = select.options[i];
        if (option.selected)
            number += Number(option.value);
    }
    output.textContent = number;
});
{/*</script>*/
}