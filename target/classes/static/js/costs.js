

// -------------------------Обработчики событий-----------------

function addLinksListener() {
	var linkPaginationElements = document.querySelectorAll('.page-link');
	for (var i = 0; i < linkPaginationElements.length; i++) {
		linkPaginationElements[i].onclick = function(event) {
			enableLinkPaginationElements(false);
			var currentLink = event.currentTarget;
			var currentVendorName = document.getElementById("vendorSelector").value;
			var currentFindValue = document.getElementById('modelFindInput').value;
			event.preventDefault();
			if (currentFindValue != "") {
				getPageViaAjax("/costs/find/" + currentFindValue + "/"
						+ currentLink.dataset.index);
			} else if (currentVendorName == 0) {
				getPageViaAjax("/costs/" + currentLink.dataset.index);
			} else {
				getPageViaAjax("/costs/" + currentVendorName + "/"
						+ currentLink.dataset.index);
			}
		}
	}
};
function addCardListeners() {
	var frontChooseButtons = document.querySelectorAll('.front .choose-button');
	var backChooseButtons = document.querySelectorAll('.back .choose-button');
	var backCardLink = document.querySelectorAll('.back-link');
	var quantityInputElements = document.querySelectorAll('.quantityInput');
	var plusButtonElements = document.querySelectorAll('.plusBtn');
	var minusButtonElements = document.querySelectorAll('.minusBtn');
	var checkInputElements = document.querySelectorAll('.form-check-input');

	for (var i = 0; i < backCardLink.length; i++) {
		frontChooseButtons[i].onclick = function(event) {
			var currentButton = event.currentTarget;
			currentButton.parentNode.parentNode.parentNode.classList
					.remove('rotate-back');
			currentButton.parentNode.parentNode.parentNode.classList
					.add('rotate');
		};
		backChooseButtons[i].onclick = function(event) {
			var currentButton = event.currentTarget;
			var backCardBody = currentButton.parentNode.parentNode.parentNode.parentNode.previousElementSibling;
			var currentFrontCard = backCardBody.parentNode.previousElementSibling;
			var currentPriceBlocks = backCardBody
					.querySelectorAll('.active-cost');
			var currentPrinterId = currentFrontCard.firstElementChild;
			var cartItem = {}
			cartItem["printerId"] = currentPrinterId.innerHTML;
			cartItem["itemsQuantity"] = currentButton.parentNode.previousElementSibling.children[1].value;
			cartItem["currentPrice"] = 0;
			cartItem["isChips"] = false;
			cartItem["isFlash"] = false;
			cartItem["flashQuantity"]=0;
			if (currentPriceBlocks.length > 0) {
				for (var i = 0; i < currentPriceBlocks.length; i++) {
					if (currentPriceBlocks[i].id == "flashCost") {
						cartItem["isFlash"] = true;
						cartItem["flashQuantity"]=1;
					} else if (currentPriceBlocks[i].id == "chipCost") {
						cartItem["isChips"] = true;
					} else {
						cartItem["currentPrice"] = currentPriceBlocks[i].children[1].childNodes[0].nodeValue;
					}
				}
			}
			;			
			if (cartItem["currentPrice"] == 0
					&& !cartItem["isFlash"]) {
				return false;
			} else {				
				addCartItemViaAjax(cartItem, "/cart/add");
			}

		};
		backCardLink[i].onclick = function(event) {
			var currentCardLink = event.currentTarget;
			currentCardLink.parentNode.parentNode.parentNode.classList
					.remove('rotate');
			currentCardLink.parentNode.parentNode.parentNode.classList
					.add('rotate-back');
		};
		if (quantityInputElements[i].value == 0) {
			minusButtonElements[i].setAttribute('disabled', 'disabled');
		}
		;
		plusButtonElements[i].onclick = function(event) {
			var currentPlusButton = event.currentTarget;
			var currentQuantityValue = currentPlusButton.parentNode.previousElementSibling.value;
			var currentCardBody = currentPlusButton.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling;
			var currentTotalCostElement = currentPlusButton.parentNode.parentNode.parentNode.parentNode.previousElementSibling;
			if (currentQuantityValue == 0) {
				currentPlusButton.parentNode.previousElementSibling.previousElementSibling.firstElementChild
						.removeAttribute("disabled");
			}
			currentPlusButton.parentNode.previousElementSibling.value = ++currentQuantityValue;
			currentTotalCostElement.innerHTML = calculateTotalCost(
					currentCardBody, currentQuantityValue)
					+ ' <i class="fa fa-rub"></i>';
		};
		minusButtonElements[i].onclick = function() {
			var currentMinusButton = event.currentTarget;
			var currentQuantityValue = currentMinusButton.parentNode.nextElementSibling.value;
			var currentCardBody = currentMinusButton.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling;
			var currentTotalCostElement = currentMinusButton.parentNode.parentNode.parentNode.parentNode.previousElementSibling;
			if (currentQuantityValue == 1) {
				currentMinusButton.setAttribute('disabled', 'disabled');
			}
			currentMinusButton.parentNode.nextElementSibling.value = --currentQuantityValue;
			currentTotalCostElement.innerHTML = calculateTotalCost(
					currentCardBody, currentQuantityValue)
					+ ' <i class="fa fa-rub"></i>';
		};
		
	}
	;
	if (checkInputElements) {
		for (var i = 0; i < checkInputElements.length; i++) {
			checkInputElements[i].onchange = function(event) {
				var currentCheckInputElement = event.currentTarget;
				var currentCardBody = currentCheckInputElement.parentNode.parentNode.parentNode;
				var currentTotalCostElement = currentCardBody.nextElementSibling.children[0];
				var currentQuantityValue = currentTotalCostElement.nextElementSibling.children[0].children[0].children[1].value;
				if (currentCheckInputElement.checked) {
					currentCheckInputElement.parentNode.parentNode.classList
							.add('active-cost');
				} else {
					currentCheckInputElement.parentNode.parentNode.classList
							.remove('active-cost');
				}
				currentTotalCostElement.innerHTML = calculateTotalCost(
						currentCardBody, currentQuantityValue)
						+ ' <i class="fa fa-rub"></i>';
			}
		}
	}
};
function calculateTotalCost(currentCardBody, currentQuantityValue) {
	var currentLastBlock = currentCardBody.lastElementChild;
	var currentChipPrice = 0;
	var currentFlashPrice = 0;
	if (currentLastBlock.id == "chipCost") {
		if (currentLastBlock.firstElementChild.firstElementChild.checked) {
			currentChipPrice = currentLastBlock.children[1].childNodes[0].nodeValue;
		}
		if (currentLastBlock.previousElementSibling.id == "flashCost") {
			if (currentLastBlock.previousElementSibling.firstElementChild.firstElementChild.checked) {
				currentFlashPrice = currentLastBlock.previousElementSibling.children[1].childNodes[0].nodeValue;
			}
		}
	} else if (currentLastBlock.id == "flashCost") {		
		if (currentLastBlock.firstElementChild.firstElementChild.checked) {
			currentFlashPrice = currentLastBlock.children[1].childNodes[0].nodeValue;
			
		}
	};
	if (currentQuantityValue == 0) {
		var currentPrice = 0;
		currentCardBody.children[1].classList.remove("active-cost");
	} else if (currentQuantityValue == 1) {
		var currentPrice = currentCardBody.children[1].children[1].childNodes[0].nodeValue;
		currentCardBody.children[1].classList.add("active-cost");
		currentCardBody.children[2].classList.remove("active-cost");
	} else if (currentQuantityValue < 5) {
		var currentPrice = currentCardBody.children[2].children[1].childNodes[0].nodeValue;
		currentCardBody.children[1].classList.remove("active-cost");
		currentCardBody.children[2].classList.add("active-cost");
		currentCardBody.children[3].classList.remove("active-cost");
	} else if (currentQuantityValue < 11) {
		var currentPrice = currentCardBody.children[3].children[1].childNodes[0].nodeValue;
		currentCardBody.children[2].classList.remove("active-cost");
		currentCardBody.children[3].classList.add("active-cost");
		currentCardBody.children[4].classList.remove("active-cost");
	} else {
		var currentPrice = currentCardBody.children[4].children[1].childNodes[0].nodeValue;
		currentCardBody.children[3].classList.remove("active-cost");
		currentCardBody.children[4].classList.add("active-cost");
	}
	
	return Number((currentQuantityValue * currentPrice))
			+ Number((currentQuantityValue * currentChipPrice)) + Number(currentFlashPrice);

}
function addVendorSelectorListener() {
	document.getElementById("vendorSelector").onchange = function() {
		var currentValue = document.getElementById("vendorSelector").value;
		enableLinkPaginationElements(false);
		document.getElementById('foundValue').innerHTML = "0";
		document.getElementById('modelFindInput').value = "";
		document.getElementById("showResultsButton").setAttribute("disabled",
				"disabled");
		document.getElementById("showAllButton").setAttribute("disabled",
				"disabled");
		document.getElementById('collapseCountValue').classList.remove('show');
		resetFindBlock();
		if (currentValue == 0) {
			getPageViaAjax("/costs/0");
		} else {
			getPageViaAjax("/costs/" + currentValue + "/0");
		}

	}
}
function addFindBlockListener() {
	document.getElementById("showResultsButton").setAttribute("disabled",
			"disabled");
	document.getElementById("showAllButton").setAttribute("disabled",
			"disabled");
	document.getElementById("modelFindInput").oninput = function() {
		var currentValue = modelFindInput.value;
		if (currentValue.trim() != "") {
			currentValue = currentValue.replace(/\s+/g, '');
			console.log('fire ' + currentValue);
			getPageViaAjax("/costs/find/" + currentValue, 1);
		} else {
			console.log('no data');
			document.getElementById('collapseCountValue').classList
					.remove('show');
			document.getElementById("showResultsButton").setAttribute(
					"disabled", "disabled");
		}
	}
	document.getElementById("showResultsButton").onclick = function() {
		document.getElementById("showAllButton").removeAttribute("disabled");
		document.getElementById("vendorSelector").value = 0;

		var currentValue = modelFindInput.value;
		currentValue = currentValue.replace(/\s+/g, '');
		getPageViaAjax("/costs/find/" + currentValue + "/0");
	}
	document.getElementById("showAllButton").onclick = function() {
		resetFindBlock();
		getPageViaAjax("/costs/0");
	}

}
function resetFindBlock() {
	document.getElementById('foundValue').innerHTML = "0";
	document.getElementById('modelFindInput').value = "";
	document.getElementById("showResultsButton").setAttribute("disabled",
			"disabled");
	document.getElementById("showAllButton").setAttribute("disabled",
			"disabled");
	document.getElementById('collapseCountValue').classList.remove('show');
}
$(document).ready(function() {
	addLinksListener();
	addCardListeners();
	addVendorSelectorListener();
	addFindBlockListener();
});