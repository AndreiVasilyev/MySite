// ---Обрезание длинного текста с добавлением ... + прокрутка Наверх------
$(document).ready(function() {
	$(".commentText").dotdotdot({
		ellipsis : '...',
		wrap : 'letter',
		fallbackToLetter : true,
		after : ".commentLink",
		watch : true,
		height : null,
		tolerance : 31,
		callback : function(isTruncated, orgContent) {
			if (!isTruncated) {
				$(".commentLink", this).remove();
			}
		},
		lastCharacter : {
			remove : [ ' ', ',', ';', '.', '!', '?' ],
			noEllipsis : []
		}
	});
	
});
// ---------Вызов модального окна с полным отзывом----------
$(".commentLink").click(function(event) {
	var link = $(event.target.parentElement);
	var message = link.data('message');
	var date = link.data('date');
	var author = link.data('author');
	var company = "\u00AB" + link.data('company') + "\u00BB";
	$("#commentModal").find('.modal-body').text('');
	$("#commentModal").find('.modal-body').text(message);
	$("#commentModal").find('.modal-title').text('');
	$("#commentModal").find('.modal-title').text(date);
	$("#commentModal").find('.modal-footer #author').text('');
	$("#commentModal").find('.modal-footer #author').text(author);
	$("#commentModal").find('.modal-footer #company').text('');
	$("#commentModal").find('.modal-footer #company').text(company);
	$("#commentModal").modal('show');
});


