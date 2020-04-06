//--------------------------Валидация формы добавления комментария----------

var authorNameInput = document.getElementById('inputAuthorName');
var companyNameInput = document.getElementById('inputCompanyName');
var emailAddressInput = document.getElementById('inputEmail');
var phoneNumberInput = document.getElementById('inputPhone');
var commentInput = document.getElementById('inputComment');

createCustomValidation(authorNameInput, authorNameValidityChecks);
createCustomValidation(phoneNumberInput, phoneNumberValidityChecks);
createCustomValidation(companyNameInput, companyNameValidityChecks);
createCustomValidation(emailAddressInput, emailAddressValidityChecks);
createCustomValidation(commentInput, commentValidityChecks);

var checkNewCommentForm = function() {
	var isAuthorNameCorrect = authorNameInput.classList.contains('is-valid');
	var isCompanyNameCorrect = companyNameInput.classList.contains('is-valid')
			|| (!companyNameInput.classList.contains('is-valid') && !companyNameInput.classList
					.contains('is-invalid'));
	var isEmailAddressCorrect = emailAddressInput.classList
			.contains('is-valid')
			|| (!emailAddressInput.classList.contains('is-valid') && !emailAddressInput.classList
					.contains('is-invalid'));
	var isPhoneNumberCorrect = phoneNumberInput.classList.contains('is-valid');
	var isCommentCorrect = commentInput.classList.contains('is-valid');
	if (isAuthorNameCorrect && isCompanyNameCorrect && isEmailAddressCorrect
			&& isPhoneNumberCorrect && isCommentCorrect) {
		document.getElementById('formSubbmit').removeAttribute('disabled');
	}
}

function createCustomValidation(input, checks) {
	input.CustomValidation = new CustomValidation();
	input.CustomValidation.validityChecks = checks;
}

// -----------------------AJAX запросы блока пагинации-------------

function enableLinkPaginationElements(flag) {
	$(".page-link").prop("disabled", flag);
}

function getPageViaAjax(controller) {

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			display(data);
			addLinksListener();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			displayError(e);
		},
		done : function(e) {
			console.log("DONE");
			enableLinkPaginationElements(true);
		}
	});

}

function displayError(e) {
	document.querySelector('.listBody').innerHTML = "неудалось получить ответ сервера";
}
function toLocaleDate(date) {
	return date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear();
}
function display(data) {
	var listBodyHTML = '';
	if (data.errorMessage == '') {
		for (var i = 0; i < data.commentsPage.length; i++) {
			listBodyHTML += '<div class="row border rounded mt-2">'
					+ '<div class="listItemsDate col-12 font-italic">';
			listBodyHTML += '<p>'
					+ toLocaleDate(new Date(data.commentsPage[i].dateMessage))
					+ '</p></div>';
			listBodyHTML += '<div class="listItemsMessage col-12"><p>'
					+ data.commentsPage[i].message + '</p></div>';
			listBodyHTML += '<div class="listItemsAuthor col-12 text-right font-weight-bold"><p class="mb-0">';
			listBodyHTML += data.commentsPage[i].authorName
					+ '</p><p class="font-italic font-weight-normal mb-2">';
			listBodyHTML += data.commentsPage[i].companyName
					+ '</p></div></div>'
		}
	} else {
		listBodyHTML += data.errorMessage;
	}
	document.querySelector('.listBody').innerHTML = listBodyHTML;
	if (data.totalPages > 1) {
		var listPaginationHTML = '';
		listPaginationHTML += '<nav><ul class="pagination pagination-sm justify-content-center"><li class="page-item';
		listPaginationHTML += (data.pageNumber == 0) ? ' disabled' : '';
		listPaginationHTML += '"><a class="page-link" href="#" data-index="0">Первая</a></li><li class="page-item';
		listPaginationHTML += (data.pageNumber == 0) ? ' disabled' : '';
		listPaginationHTML += '"><a class="page-link" href="#" data-index="'
				+ (data.pageNumber - 1) + '">';
		listPaginationHTML += '<span>&#171;</span></a></li>';
		var pagesLable = (data.totalPages > 2 && data.pageNumber == (data.totalPages - 1)) ? data.pageNumber - 2
				: data.pageNumber - 1;
		for (var i = 0; (data.totalPages > 2) ? i < 3 : i < 2; i++) {
			listPaginationHTML += '<li class="page-item';
			listPaginationHTML += ((data.pageNumber == 0 && data.pageNumber == i)
					|| (data.pageNumber > 0 && data.pageNumber == pagesLable) ? ' active'
					: '');
			listPaginationHTML += '"><a class="page-link" href="#" data-index="'
					+ (data.pageNumber == 0 ? i : pagesLable) + '">';
			listPaginationHTML += data.pageNumber == 0 ? (i + 1)
					: (pagesLable + 1) + '</a></li>';
			pagesLable++;
		}
		listPaginationHTML += '<li class="page-item'
				+ (data.pageNumber == (data.totalPages - 1) ? ' disabled' : '')
				+ '">';
		listPaginationHTML += '<a class="page-link" href="#" data-index="'
				+ (data.pageNumber + 1) + '"><span>&#187;</span>';
		listPaginationHTML += '</a></li><li class="page-item'
				+ (data.pageNumber == (data.totalPages - 1) ? ' disabled' : '')
				+ '">';
		listPaginationHTML += '<a class="page-link" href="#" data-index="'
				+ (data.totalPages - 1) + '">Последняя</a></li></ul>';
		listPaginationHTML += '</nav>';
		document.querySelector('.listPagination').innerHTML = listPaginationHTML;
	}

}

// -------------------------Обработчики событий-----------------

window.onload = function() {
	$("#inputPhone").mask("+375(99) 999-99-99");
	var inputElements = document.querySelectorAll('.form-control');
	var currentSubbmitButton = document.getElementById('formSubbmit');
	for (var i = 0; i < inputElements.length; i++) {
		inputElements[i].onblur = function() {
			checkInput(this, checkNewCommentForm, currentSubbmitButton);
		};
		inputElements[i].onfocus = function() {
			currentSubbmitButton.setAttribute('disabled', 'disabled');
			this.classList.remove('is-invalid');
			this.classList.remove('is-valid');
		}
	}
	currentSubbmitButton.setAttribute('disabled', 'disabled');
	addLinksListener();
};

function addLinksListener() {
	var linkPaginationElements = document.querySelectorAll('.page-link');
	for (var i = 0; i < linkPaginationElements.length; i++) {
		linkPaginationElements[i].onclick = function(event) {
			enableLinkPaginationElements(false);
			var currentLink = event.currentTarget;
			getPageViaAjax("/comments/" + currentLink.dataset.index);
		}
	}
}
