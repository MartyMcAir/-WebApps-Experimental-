$('.openModal').click(function (e) {
    e.preventDefault();
    $('.classMainDiv').addClass('classShowHover');
});
$('.closemodal').click(function (e) {
    e.preventDefault();
    $('.classMainDiv').removeClass('classShowHover');
});

