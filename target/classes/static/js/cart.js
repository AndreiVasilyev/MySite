//-------ВАЛИДАЦИЯ ФОРМЫ ЗАКАЗА--------------------------


var authorNameInputOrder = document.getElementById('customerNameInput');
var phoneNumberInputOrder = document.getElementById('phoneNumberInput');
var customerAddressInputOrder = document.getElementById('customerAddressInput');
var addInfoInputOrder = document.getElementById('addInfoInput');

createCustomValidation(authorNameInputOrder, authorNameValidityChecks);
createCustomValidation(phoneNumberInputOrder, phoneNumberValidityChecks);
createCustomValidation(customerAddressInputOrder, customerAddressValidityChecks);
createCustomValidation(addInfoInputOrder, addInfoValidityChecks);

var checkOrderForm = function() {
	var isAuthorNameCorrect = authorNameInputOrder.classList
			.contains('is-valid');
	var isPhoneNumberCorrect = phoneNumberInputOrder.classList
			.contains('is-valid');
	var isCustomerAddressCorrect = customerAddressInputOrder.classList
			.contains('is-valid');	
	var isAddInfoCorrect = addInfoInputOrder.classList
			.contains('is-valid')
			|| (!addInfoInputOrder.classList.contains('is-valid') && !addInfoInputOrder.classList
					.contains('is-invalid'));

	if (isAuthorNameCorrect && isPhoneNumberCorrect && isCustomerAddressCorrect && isAddInfoCorrect) {
		document.getElementById('orderSubbmit')
				.removeAttribute('disabled');
	}
};




// ------- Добавление слушателей -------

function addCartListeners() {
	var plusButtonElements = document.querySelectorAll('.plusValueBtn');
	var minusButtonElements = document.querySelectorAll('.minusValueBtn');
	var removeButtonElements = document.querySelectorAll('.removeItemButton');
	var quantityInputElements = document
			.querySelectorAll('.quantityValueInput');
	var chipCheckBoxes = document.querySelectorAll('.chip-checkboxes');
	for (var i = 0; i < quantityInputElements.length; i++) {
		if (quantityInputElements[i].value == 0) {
			minusButtonElements[i].setAttribute('disabled', 'disabled');
		} else {
			minusButtonElements[i].removeAttribute('disabled');
		};
		plusButtonElements[i].onclick = function(event) {
			var currentPlusButton = event.currentTarget;
			var serviceNameElement = currentPlusButton.parentNode.parentNode.parentNode.children[3].firstElementChild;
			var currentQuantityInput = currentPlusButton.parentNode.previousElementSibling;
			var currentQuantityValue = currentQuantityInput.value;
			var currentCostValue = currentQuantityInput.parentNode.nextElementSibling.firstElementChild.childNodes[0].nodeValue;
			var currentPriceElement=currentQuantityInput.parentNode.previousElementSibling.previousElementSibling.firstElementChild;
			var currentPriceValue = currentPriceElement.childNodes[0].nodeValue;
			var currentTotalCostElement = currentQuantityInput.parentNode.parentNode.parentNode.lastElementChild.firstElementChild.firstElementChild;
			var allCostElements=document.querySelectorAll('.cost-selector');
			var currentChipElement=currentPlusButton.parentNode.parentNode.previousElementSibling.lastElementChild;
			var currentChipPrice = currentChipElement.innerHTML;
			
			if (currentQuantityValue == 0) {
				currentPlusButton.parentNode.previousElementSibling.previousElementSibling.firstElementChild
						.removeAttribute("disabled");
			};
			if (serviceNameElement.innerHTML == "Заправка") {				
				var basePrice = Number(currentPriceValue);
				var chipPrice = 0;				
				if (currentQuantityValue > 1 && currentQuantityValue < 5) {
					basePrice += 0.5;
				} else if (currentQuantityValue > 4
						&& currentQuantityValue < 11) {
					basePrice += 1.5;
				} else if (currentQuantityValue > 10) {
					basePrice += 2.5;
				}
				;
				if (currentChipPrice!=0 && currentChipElement.previousElementSibling.previousElementSibling.checked) {
					chipPrice = currentChipPrice;
				}
				;
				currentPlusButton.parentNode.previousElementSibling.value = ++currentQuantityValue;
				inputChangeHeandler(currentQuantityInput, basePrice, chipPrice);
			} else {
				currentPlusButton.parentNode.previousElementSibling.value = ++currentQuantityValue;
				currentQuantityInput.parentNode.nextElementSibling.firstElementChild.childNodes[0].nodeValue=currentPriceValue*currentQuantityValue;
			};
			var currentTotalCostValue=Number(0);
			for(var i=0;i<allCostElements.length;i++){
				currentTotalCostValue+=Number(allCostElements[i].childNodes[0].nodeValue);
			};
			currentTotalCostElement.childNodes[1].nodeValue=currentTotalCostValue;			
			var cartItem={};
			var printerIdElement=currentPlusButton.parentNode.parentNode.parentNode.firstElementChild;
			var isChippedElement=currentPlusButton.parentNode.parentNode.previousElementSibling.lastElementChild;
			cartItem["printerId"]=printerIdElement.innerHTML;
			if(serviceNameElement.innerHTML == "Заправка"){
				cartItem["itemsQuantity"]=currentQuantityValue;
				cartItem["currentPrice"]= currentPriceElement.childNodes[0].nodeValue;				
				if(isChippedElement.innerHTML!=0 && isChippedElement.previousElementSibling.previousElementSibling.checked){
					cartItem["isChips"]=true;
				}else{
					cartItem["isChips"]=false;
				}
				cartItem["isFlash"]=false;
				cartItem["flashQuantity"]=0;
			} else{
				cartItem["itemsQuantity"]=0;
				cartItem["currentPrice"]=currentPriceElement.childNodes[0].nodeValue;
				cartItem["isChips"]=false;						
				cartItem["isFlash"]=true;
				cartItem["flashQuantity"]=currentQuantityValue;
			}			
			updateCartItemViaAjax(cartItem, "/cart/update");
		};
		minusButtonElements[i].onclick = function(event) {
			var currentMinusButton = event.currentTarget;
			var serviceNameElement = currentMinusButton.parentNode.parentNode.parentNode.children[3].firstElementChild;
			var currentQuantityInput = currentMinusButton.parentNode.nextElementSibling;
			var currentQuantityValue = currentQuantityInput.value;
			var currentCostValue = currentQuantityInput.parentNode.nextElementSibling.firstElementChild.childNodes[0].nodeValue;
			var currentPriceElement=currentQuantityInput.parentNode.previousElementSibling.previousElementSibling.firstElementChild;
			var currentPriceValue = currentPriceElement.childNodes[0].nodeValue;
			var currentTotalCostElement = currentQuantityInput.parentNode.parentNode.parentNode.lastElementChild.firstElementChild.firstElementChild;
			var allCostElements=document.querySelectorAll('.cost-selector');
			var currentChipPrice = currentMinusButton.parentNode.parentNode.previousElementSibling.lastElementChild.innerHTML;
			if (currentQuantityValue == 1) {
				currentMinusButton.setAttribute('disabled', 'disabled');
			};
			if (serviceNameElement.innerHTML == "Заправка") {				
				var basePrice = Number(currentPriceValue);
				var chipPrice = 0;				
				if (currentQuantityValue > 1 && currentQuantityValue < 5) {
					basePrice += 0.5;
				} else if (currentQuantityValue > 4
						&& currentQuantityValue < 11) {
					basePrice += 1.5;
				} else if (currentQuantityValue > 10) {
					basePrice += 2.5;
				}
				;
				if (currentPriceValue * currentQuantityValue != currentCostValue) {
					chipPrice = (currentCostValue - currentPriceValue
							* currentQuantityValue)
							/ currentQuantityValue;
				}
				;
				currentMinusButton.parentNode.nextElementSibling.value = --currentQuantityValue;
				inputChangeHeandler(currentQuantityInput, basePrice, chipPrice);
			} else {
				currentMinusButton.parentNode.nextElementSibling.value = --currentQuantityValue;
				currentQuantityInput.parentNode.nextElementSibling.firstElementChild.childNodes[0].nodeValue=currentPriceValue*currentQuantityValue;
			};
			var currentTotalCostValue=Number(0);
			for(var i=0;i<allCostElements.length;i++){
				currentTotalCostValue+=Number(allCostElements[i].childNodes[0].nodeValue);
			};
			currentTotalCostElement.childNodes[1].nodeValue=currentTotalCostValue;					
			var cartItem={};
			var printerIdElement=currentMinusButton.parentNode.parentNode.parentNode.firstElementChild;
			var isChippedElement=currentMinusButton.parentNode.parentNode.previousElementSibling.lastElementChild;
			cartItem["printerId"]=printerIdElement.innerHTML;
			if(serviceNameElement.innerHTML == "Заправка"){
				cartItem["itemsQuantity"]=currentQuantityValue;
				cartItem["currentPrice"]= currentPriceElement.childNodes[0].nodeValue;				
				if(isChippedElement.innerHTML!=0 && isChippedElement.previousElementSibling.previousElementSibling.checked){
					cartItem["isChips"]=true;
				}else{
					cartItem["isChips"]=false;
				}
				cartItem["isFlash"]=false;
				cartItem["flashQuantity"]=0;
			} else{
				cartItem["itemsQuantity"]=0;
				cartItem["currentPrice"]=currentPriceElement.childNodes[0].nodeValue;
				cartItem["isChips"]=false;						
				cartItem["isFlash"]=true;
				cartItem["flashQuantity"]=currentQuantityValue;
			}			
			updateCartItemViaAjax(cartItem, "/cart/update");
			
		};
		removeButtonElements[i].onclick=function(event){
			var currentRemoveButton=event.currentTarget;
			var currentServiceNameElement=currentRemoveButton.parentNode.parentNode.children[3].firstElementChild;
			var currentPrinterIdElement=currentRemoveButton.parentNode.parentNode.firstElementChild;
			if(currentServiceNameElement.innerHTML == "Заправка"){
				removeCartItemViaAjax("/cart/remove/"+currentPrinterIdElement.innerHTML+"/true");
			} else{
				removeCartItemViaAjax("/cart/remove/"+currentPrinterIdElement.innerHTML+"/false");
			}
		};
	};
	for (var i = 0; i < chipCheckBoxes.length; i++) {
		chipCheckBoxes[i].onchange = function(event) {
			var currentCheckBox = event.currentTarget;
			var currentQuantityValue = currentCheckBox.parentNode.nextElementSibling.children[1].value;
			var currentCostElement = currentCheckBox.parentNode.nextElementSibling.nextElementSibling.firstElementChild;
			var currentCostValue = currentCostElement.childNodes[0].nodeValue;
			var currentPriceValue = currentCheckBox.parentNode.previousElementSibling.firstElementChild.childNodes[0].nodeValue;
			var currentChipPrice = currentCheckBox.nextElementSibling.nextElementSibling.childNodes[0].nodeValue;
			var currentTotalCostElement = currentCheckBox.parentNode.parentNode.parentNode.lastElementChild.firstElementChild.firstElementChild;
			var allCostElements=document.querySelectorAll('.cost-selector');			
			var cartItem={};
			var printerIdElement=currentCheckBox.parentNode.parentNode.firstElementChild;
			cartItem["printerId"]=printerIdElement.innerHTML;
			cartItem["itemsQuantity"]=currentQuantityValue;
			cartItem["currentPrice"]= currentPriceValue;
			if (currentCheckBox.checked) {
				currentCostElement.childNodes[0].nodeValue = currentPriceValue
						* currentQuantityValue + currentChipPrice
						* currentQuantityValue;
				cartItem["isChips"]=true;
			} else {
				currentCostElement.childNodes[0].nodeValue = currentPriceValue
						* currentQuantityValue;
				cartItem["isChips"]=false;
			};
			var currentTotalCostValue=Number(0);
			for(var i=0;i<allCostElements.length;i++){
				currentTotalCostValue+=Number(allCostElements[i].childNodes[0].nodeValue);
			};
			currentTotalCostElement.childNodes[1].nodeValue=currentTotalCostValue;	
			cartItem["isFlash"]=false;
			cartItem["flashQuantity"]=0;			
			updateCartItemViaAjax(cartItem, "/cart/update");
		};
	};
	document.querySelector('.clear-cart-btn').onclick=function(){		
		removeCartItemViaAjax("/cart/remove");
	};
};

function inputChangeHeandler(currentQuantityInput, basePrice, chipPrice) {
	var currentPriceElement = currentQuantityInput.parentNode.previousElementSibling.previousElementSibling.firstElementChild;
	var currentCostElement = currentQuantityInput.parentNode.nextElementSibling.firstElementChild;	
	if (currentQuantityInput.value < 2) {
		currentPriceElement.childNodes[0].nodeValue = basePrice;
	} else if (currentQuantityInput.value < 5) {
		currentPriceElement.childNodes[0].nodeValue = basePrice - 0.5;
	} else if (currentQuantityInput.value < 11) {
		currentPriceElement.childNodes[0].nodeValue = basePrice - 1.5;
	} else {
		currentPriceElement.childNodes[0].nodeValue = basePrice - 2.5;
	}
	;
	currentCostElement.childNodes[0].nodeValue = currentPriceElement.childNodes[0].nodeValue
			* currentQuantityInput.value
			+ chipPrice
			* currentQuantityInput.value;
	
}

$(document).ready(function() {
	addCartListeners();
	resetOrderForm();	
});

document.getElementById('orderSubbmit').setAttribute('disabled','disabled');
document.getElementById('orderSubbmit').onclick = function() {	
	enableButton(this, false);
	var order = {}
	order['customerName'] = $('#customerNameInput').val();
	order['phoneNumber'] = $('#phoneNumberInput').val();
	order['customerAddress'] = $('#customerAddressInput').val();		
	order['additionalInfo'] = $('#addInfoInput').val();
	order['orderSource'] = 'FULL_ORDER';
	order['dateOrder'] = new Date();
	sendViaAjax(order, this);
	removeCartItemViaAjax("/cart/remove");
};
 function resetOrderForm(){
	 initForm(".order-form","orderSubbmit","phoneNumberInput",checkOrderForm);
 };

