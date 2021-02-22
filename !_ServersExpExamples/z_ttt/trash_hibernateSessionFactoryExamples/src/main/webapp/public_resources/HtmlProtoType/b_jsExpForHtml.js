// добавляем слушателя событий к каждому элементу
Array.from(document.querySelectorAll('.delFormClass'))
    .forEach(function (formCl) {
        // formCl.addEventListener('click', function (event) {
        formCl.addEventListener('click', function (event) {
            const idIs = formCl.querySelector('input[name="id"]');
            // alert('Вы хотите удалить ' + idIs.value + '?')

            const answer = window.confirm('Вы хотите удалить пользователя с id: ' + idIs.value + '?')
            if (answer) {
                console.log('Thing was saved to the database.');
            } else {
                console.log('Thing was not saved to the database.');
            }

            const paramsIs = '?columnID=' + idIs.value
            console.log('deleteIdIs' + paramsIs)
            // connectToServerForDelRow(responseType, contentType, servletNameDel, paramsIs)
            event.preventDefault()
        })
    });