//-------ВАЛИДАЦИЯ ФОРМ--------------------------

//------------заказа звонка-----------------------

var authorNameInputCallOrder = document.getElementById('customerNameCall');
var phoneNumberInputCallOrder = document.getElementById('phoneNumberCall');

createCustomValidation(authorNameInputCallOrder, authorNameValidityChecks);
createCustomValidation(phoneNumberInputCallOrder, phoneNumberValidityChecks);

var checkCallOrderForm = function() {
	var isAuthorNameCorrect = authorNameInputCallOrder.classList
			.contains('is-valid');
	var isPhoneNumberCorrect = phoneNumberInputCallOrder.classList
			.contains('is-valid');
	if (isAuthorNameCorrect && isPhoneNumberCorrect) {
		document.getElementById('callOrderSubbmit').removeAttribute('disabled');
	}
}

function createCustomValidation(input, checks) {
	input.CustomValidation = new CustomValidation();
	input.CustomValidation.validityChecks = checks;
}

// ------------быстрого заказа-----------------------

var authorNameInputQuickOrder = document.getElementById('customerName');
var phoneNumberInputQuickOrder = document.getElementById('phoneNumber');
var customerAddressInputQuickOrder = document.getElementById('customerAddress');
var deviceModelInputQuickOrder = document.getElementById('deviceModel');
var addInfoInputQuickOrder = document.getElementById('addInfo');

createCustomValidation(authorNameInputQuickOrder, authorNameValidityChecks);
createCustomValidation(phoneNumberInputQuickOrder, phoneNumberValidityChecks);
createCustomValidation(customerAddressInputQuickOrder,
		customerAddressValidityChecks);
createCustomValidation(deviceModelInputQuickOrder, deviceModelValidityChecks);
createCustomValidation(addInfoInputQuickOrder, addInfoValidityChecks);

var checkQuickOrderForm = function() {
	var isAuthorNameCorrect = authorNameInputQuickOrder.classList
			.contains('is-valid');
	var isPhoneNumberCorrect = phoneNumberInputQuickOrder.classList
			.contains('is-valid');
	var isCustomerAddressCorrect = customerAddressInputQuickOrder.classList
			.contains('is-valid');
	var isDeviceModelCorrect = deviceModelInputQuickOrder.classList
			.contains('is-valid')
			|| (!deviceModelInputQuickOrder.classList.contains('is-valid') && !deviceModelInputQuickOrder.classList
					.contains('is-invalid'));
	var isAddInfoCorrect = addInfoInputQuickOrder.classList
			.contains('is-valid')
			|| (!addInfoInputQuickOrder.classList.contains('is-valid') && !addInfoInputQuickOrder.classList
					.contains('is-invalid'));

	if (isAuthorNameCorrect && isPhoneNumberCorrect && isCustomerAddressCorrect
			&& isDeviceModelCorrect && isAddInfoCorrect) {
		document.getElementById('quickOrderSubbmit')
				.removeAttribute('disabled');
	}
}

//---------Вызов модального окна с формой заказа звонка----------

$("#callOrderModal").on(
		'show.bs.modal',
		function(event) {
			initForm(".callback-form", "callOrderSubbmit", "phoneNumberCall",
					checkCallOrderForm);
		});

// --------Вызов модального окна с формой быстрого заказа----------
$("#quickOrderModal").on(
		'show.bs.modal',
		function(event) {
			initForm(".quick-order-form", "quickOrderSubbmit", "phoneNumber",
					checkQuickOrderForm);
		});

function initForm(form, subbmitButtonId, maskElementId, checker) {
	var inputElements = document.querySelectorAll(form + ' .form-control');
	var currentSubbmitButton = document.getElementById(subbmitButtonId);
	$('#' + maskElementId).mask("+375(99) 999-99-99");
	for (var i = 0; i < inputElements.length; i++) {
		inputElements[i].value = '';
		inputElements[i].classList.remove('is-invalid');
		inputElements[i].classList.remove('is-valid');
		inputElements[i].onblur = function() {
			checkInput(this, checker, currentSubbmitButton);
		};
		inputElements[i].onfocus = function() {
			currentSubbmitButton.setAttribute('disabled', 'disabled');
			this.classList.remove('is-invalid');
			this.classList.remove('is-valid');
		}
	}
	currentSubbmitButton.setAttribute('disabled', 'disabled');
}

//------Обработка нажатия кнопок отправки модальных окон---------

document.getElementById('callOrderSubbmit').onclick = function() {
	enableButton(this, false);
	var order = {}
	order['customerName'] = $('#customerNameCall').val();
	order['phoneNumber'] = $('#phoneNumberCall').val();
	order['orderSource'] = 'CALL_ORDER';
	order['dateOrder'] = new Date();
	sendViaAjax(order, this);
}

document.getElementById('quickOrderSubbmit').onclick = function() {
	enableButton(this, false);
	var order = {}
	order['customerName'] = $('#customerName').val();
	order['phoneNumber'] = $('#phoneNumber').val();
	order['customerAddress'] = $('#customerAddress').val();
	order['deviceModel'] = $('#deviceModel').val();
	order['additionalInfo'] = $('#addInfo').val();
	order['orderSource'] = 'QUICK_ORDER';	
	order['dateOrder'] = new Date();
	sendViaAjax(order, this);
	
}
// -----------------------AJAX запросы модальных окон-------------
function sendViaAjax(order, sourceButton) {

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/orders",
		data : JSON.stringify(order),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			$('#responseText').html(
					'Спасибо за заявку!<br>Ваш заказ сохранен в базе под номером №'
							+ data.orderId);
			console.log($('#responseText').html());
			$('#confirmOrderModal').modal('show');
			if(sourceButton.id=='orderSubbmit'){
				resetOrderForm();
			};
			
		},
		error : function(errors) {
			console.log("ERROR: ", errors);
			$('#responseText').html('Ошибка!<br>' + errors);
			$('#confirmOrderModal').modal('show');
		},
		done : function(e) {
			console.log("DONE");
			enableButton(sourceButton, true);
		}
	});

};

function enableButton(button, flag) {
	if (flag == true) {
		button.setAttribute("disabled", flag);
	} else {
		button.removeAttribute("disabled");
	};
};

/*$('#quickOrderModal').on('hidden.bs.modal', function(e) {
	console.log("begin hide qom");
	console.log(('#responseText').text());
	if ($('#responseText').text() != '') {
		$('#confirmOrderModal').modal('show');
	};
});
$('#callOrderModal').on('hidden.bs.modal', function(e) {
	if ($('#responseText').html() != '') {
		$('#confirmOrderModal').modal('show');
	};
});*/
$('#confirmOrderModal').on('hidden.bs.modal', function(e) {
	$('#responseText').text('');
});

// --- прокрутка Наверх------

$(document).ready(function() {	
	$('#back-top').hide;
	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});
		$('#back-top a').click(function() {
			$('body,html').animate({
				scrollTop : 0
			}, 800);
			return false;
		});
	});
});

// ------активация подсказок --------


$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	});
